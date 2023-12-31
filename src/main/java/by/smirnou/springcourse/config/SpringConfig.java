package by.smirnou.springcourse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan("by.smirnou.springcourse")
@EnableWebMvc
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
@EnableJpaRepositories("by.smirnou.springcourse.repositories")
public class SpringConfig implements WebMvcConfigurer {

//    private final Environment environment;
    private final ApplicationContext applicationContext;
    private final Environment env; //Получение доступа к конфиг файлу

    @Autowired
    public SpringConfig(ApplicationContext applicationContext, Environment env) {
        this.applicationContext = applicationContext;
        this.env =env;
    }


    //Thymeleaf
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/view/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }


    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty("hibernate.driver_class"));
        dataSource.setUrl(env.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(env.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(env.getRequiredProperty("hibernate.connection.password"));


        return dataSource;
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate(){
//        return new JdbcTemplate(dataSource());
//    }


    private Properties hibernateProperties(){
        Properties properties = new Properties();
        properties.put("hibernate.dialect" , env.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql" , env.getRequiredProperty("hibernate.show_sql"));

        return properties;
    }


//    @Bean
//    public LocalSessionFactoryBean sessionFactory(){
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource()); //Указываем все сущности из dataSource
//        sessionFactory.setPackagesToScan("by.snirnou.springcource.models");
//        sessionFactory.setHibernateProperties(hibernateProperties());
//
//        return sessionFactory;
//    }

    // Нужно заменить на

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource((dataSource()));
        em.setPackagesToScan("by.smirnou.springcourse.models");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());

        return em;
    }


    @Bean
    public PlatformTransactionManager transactionManager(){
        //HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        // transactionManager.setSessionFactory(sessionFactory().getObject());
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return transactionManager;
    }



//    @Bean       //Метод для Контроля транзакциями
//    public PlatformTransactionManager hibernateTransactionManager(){
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//        transactionManager.setSessionFactory(sessionFactory().getObject());
//
//        return transactionManager;
//    }
}





