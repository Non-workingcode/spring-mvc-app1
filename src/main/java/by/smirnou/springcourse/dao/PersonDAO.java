package by.smirnou.springcourse.dao;

import by.smirnou.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private  static final String URL = "jdbc:postgresql://localhost:5432/first_db";
    private static final String USERNAME ="postgres";
    private static final String PASSWORD = "Postgres";

    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME ,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setEmail(resultSet.getString("email"));
                person.setAge(resultSet.getInt("age"));

                people.add(person);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return people;
    }

    public Person show(int id){
//        return  people.stream()
//                .filter(people -> people.getId() == id)
//                .findAny().orElse(null);
        return null;
    }
    public void save(Person person) throws SQLException {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);

        Statement statement = connection.createStatement();
        String SQL = "INSER INTO Person VALUES(" + 1 + ",'" + person.getEmail() +
                "'," + person.getAge() + ",'" + person.getEmail() + "')";
        statement.executeUpdate(SQL);
    }

    public void update(int id, Person updatePerson){
//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(updatePerson.getName());
//        personToBeUpdated.setAge(updatePerson.getAge());
//        personToBeUpdated.setEmail(updatePerson.getEmail());
    }

    public void delete(int id){
//        people.removeIf(p -> p.getId() == id);
    }
}
