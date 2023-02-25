package my.course.spring.dao;

import my.course.spring.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    private List<Person> people;
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Tom", 26, "tom@mail.ru"));
        people.add(new Person(++PEOPLE_COUNT, "Bob", 38, "bob@gmail.com"));
        people.add(new Person(++PEOPLE_COUNT, "Mike", 14, "mike@microsoft.com"));
        people.add(new Person(++PEOPLE_COUNT, "Katy", 21, "katy@yande.ru"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(x->x.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person person) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(person.getName());
        personToBeUpdated.setAge(person.getAge());
        personToBeUpdated.setEmail(person.getEmail());
    }

    public void delete(int id) {
        people.removeIf(x->x.getId()==id);
    }
}
