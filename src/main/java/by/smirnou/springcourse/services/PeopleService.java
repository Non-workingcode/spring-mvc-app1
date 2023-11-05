package by.smirnou.springcourse.services;


import by.smirnou.springcourse.models.Person;
import by.smirnou.springcourse.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;


    @Autowired
    public PeopleService (PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll(){
        return peopleRepository.findAll(); // findAll() возвращает все сущности из таблицы
    }

    public Person findOne(int id){
        Optional<Person> foundPerson = peopleRepository.findById(id); // findById() возвращает Optional
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person){
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id , Person updatedPerson){
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id){
        peopleRepository.deleteById(id);
    }

}
