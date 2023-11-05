package by.smirnou.springcourse.controlles;


import by.smirnou.springcourse.models.Person;

import by.smirnou.springcourse.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


//@PathVariable сопряжение из маппинга и объекта в параметрах
//@ModelAttribute внедреяет из формы в объект

@Controller
@RequestMapping("/people")
public class PeopleController {

//    private final PersonDAO personDAO;
//    private final PersonValidator personValidator;
    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService){
        this.peopleService = peopleService;
    }


    // Получим всех людей из DAO и передадим на отображение всех людей на представление
    @GetMapping
    public String index(Model model){
       model.addAttribute("people" , peopleService.findAll());
        return "/people/index";
    }


    //Получим одного человека по его id из  DAO и передадим на отображение в представление
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person" , peopleService.findOne(id));
        return "/people/show";
    }


    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person")  @Valid Person person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/people/new";
        }
        return "/people/new";
    }


    @PostMapping()
    public String create(@ModelAttribute("person")
                             @Valid Person person , BindingResult bindingResult)  {
        if(bindingResult.hasErrors()){
            return "/people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model ,@PathVariable("id") int id){
        model.addAttribute("person" , peopleService.findOne(id));
        return "/people/edit";
    }

    //@PathVariable сопряжение из маппинга и объекта в параметрах
    //@@ModelAttribute внедреяет из формы в объект


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id){
        if(bindingResult.hasErrors()){
            return "/people/edit";
        }
        peopleService.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }



}
