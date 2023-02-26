package my.course.spring.dao;

import my.course.spring.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private static final String URL = "jdbc:mysql://localhost:3306/people?serverTimezone=Asia/Yekaterinburg";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "12345678";

    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connection to database successfully established!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection to database wasn't established!");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private List<Person> people = new ArrayList<>();


    public List<Person> index() {
        Statement statement = null;
        List<Person> peopleFromBD = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String SQL = "SELECT * FROM Person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("id"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("Email"));
                peopleFromBD.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return peopleFromBD;
    }

    public Person show(int id) {
        //return people.stream().filter(x->x.getId() == id).findAny().orElse(null);
        Person person = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM people.person WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));
            return person;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO people.person (name, age, email) VALUES(?, ?, ?);");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(int id, Person person) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE people.person SET name=?, age=?, email=? WHERE id=?;");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        Person personToBeUpdated = show(id);
//        personToBeUpdated.setName(person.getName());
//        personToBeUpdated.setAge(person.getAge());
//        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id) {
        //people.removeIf(x->x.getId()==id);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM people.person WHERE id = ?;");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
