package by.smirnou.springcourse.util;

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
