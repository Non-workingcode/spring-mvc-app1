package by.smirnou.springcourse.dao;

import by.smirnou.springcourse.models.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public PersonDAO(SessionFactory sessionFactory){
        this.sessionFactory =sessionFactory;
    }

    @Transactional(readOnly = true)
    public List<Person> index() {
        Session session = sessionFactory.getCurrentSession();

        List<Person> people = session.createQuery("select p from Person p" , Person.class)
                .getResultList();

        return people;
    }



    public Person show(int id) {
        return  null;
    }

    public void save(Person person) {
    }

    public void update(int id, Person updatedPerson) {
    }

    public void delete(int id) {

    }







    //////////////////////
    ////Тестируем производительность пакетной вставки
    /////////////////////




//    public void testMultipleUpdate(){
//        List<Person> people = create1000People();
//
//        long before = System.currentTimeMillis();
//
//        for (Person person : people){
//            jdbcTemplate.update("INSERT INTO Person VALUES  (?,?, ?, ?)", person.getId(),person.getName(),person.getAge(),person.getEmail());
//        }
//        long after = System.currentTimeMillis();
//
//        System.out.println("Time: " + (after-before) );
//    }
//
//    public void testBatchUpdate(){
//        List<Person> people = create1000People();
//
//        long before = System.currentTimeMillis();
//
//        jdbcTemplate.batchUpdate("INSERT INTO Person values (?,?,?,?)",
//                new BatchPreparedStatementSetter() {
//                    @Override
//                    public void setValues(PreparedStatement ps, int i) throws SQLException {
//                        ps.setInt(1,people.get(i).getId());
//                        ps.setString(2, people.get(i).getName());
//                        ps.setInt(3,people.get(i).getAge());
//                        ps.setString(4,people.get(i).getEmail());
//                    }
//
//                    @Override
//                    public int getBatchSize() {
//                        return people.size();
//                    }
//                });
//
//        long after  = System.currentTimeMillis();
//
//        System.out.println("Time: " + (after - before));
//    }
//
//    private List<Person> create1000People(){
//        List<Person> people = new ArrayList<>();
//
//        for(int i = 0; i<1000;i++){
//            people.add(new Person(i, "Name" + i,30,"test"+1 +"@mail.ru" , "some address"));
//        }
//        return people;
//    }
}
