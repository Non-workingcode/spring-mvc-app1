package by.smirnou.springcourse.util;

import by.smirnou.springcourse.dao.PersonDAO;
import by.smirnou.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//@Component
//public class PersonValidator implements Validator {
//    private final PersonDAO personDAO;
//
//    @Autowired
//    PersonValidator (PersonDAO personDAO){
//        this.personDAO = personDAO;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return Person.class.equals(clazz);
//    }
//
////    @Override
////    public void validate(Object target, Errors errors) {
////        Person person = (Person) target;
////        if(personDAO.show(person.getEmail()).isPresent()){
////            errors.rejectValue("email","", "This email is already taken");
////        }
//
//        //посмотреть, есть ли человек с таким же email'ом в БД
//    }
//}
