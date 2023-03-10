package my.course.spring.dao;

import my.course.spring.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PersonDAO {
    JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person;", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id=?;", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).
                stream().findAny().orElse(null);
    }
    public void save(Person person) {

        jdbcTemplate.update("INSERT INTO people.person (name, age, email) VALUES (?, ?, ?);",
                person.getName(), person.getAge(), person.getEmail());
    }
    public void update(int id, Person person) {
        jdbcTemplate.update("UPDATE people.person SET name = ?, age = ?, email = ? WHERE id = ?;",
                person.getName(), person.getAge(), person.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM people.person WHERE id = ?;", id);
    }
}
