package ru.asd.CRUDSpringB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.asd.CRUDSpringB.entity.Person;
import ru.asd.CRUDSpringB.entity.User;
import ru.asd.CRUDSpringB.service.PersonService;
import ru.asd.CRUDSpringB.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller

public class PersonController {
    @Autowired
    PersonService personService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String list(@RequestParam Map<String, String> allRequestParams, ModelMap model) {
        System.out.println("dsfsdfsdf");
        for (Map.Entry entry : allRequestParams.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        List<Person> persons = personService.getAll();
        for (Person p : persons) {
            System.out.println(p);
        }
        model.addAttribute("persons", persons);
        //  personService.deletePersonById(6);

        return "index1";
    }

    @RequestMapping("/")
    public String listAll(ModelMap model) {
        System.out.println("mainpage");
        List<Person> persons = personService.getAll();

        model.addAttribute("persons", persons);

        return "index1";
    }

    @RequestMapping(value = "/new")
    public String newPerson(@RequestParam Map<String, String> allRequestParams) {
        System.out.println("New Person");
        if (allRequestParams.get("surname") != null) {
            System.out.println("Создаём");
            Person p = new Person(allRequestParams.get("surname"), allRequestParams.get("name"), allRequestParams.get("address"));
            personService.addPerson(p);
        }
        return "new";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editPerson(@RequestParam Map<String, String> allRequestParams, ModelMap model) {
        System.out.println("EditPage");
        Person person = personService.getPersonById(Long.parseLong(allRequestParams.get("id")));
        model.addAttribute("person", person);
        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String esPerson(@RequestParam Map<String, String> allRequestParams, ModelMap model) {
        Person p = new Person(allRequestParams.get("surname"), allRequestParams.get("name"), allRequestParams.get("address"));
        p.setId(Long.parseLong(allRequestParams.get("id")));
        Person retPers = personService.updatePerson(p);
        return "redirect:./";
    }

    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String dPerson(@RequestParam Map<String, String> allRequestParams, ModelMap model) {
        personService.deletePersonById(Long.parseLong(allRequestParams.get("id")));
        return "redirect:./";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(@RequestParam Map<String, String> allRequestParams, ModelMap model) {
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String checkUser(@RequestParam Map<String, String> allRequestParams, ModelMap model) {

        return "/login";
    }

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = userService.getAll();

        model.addAttribute("users", users);


        return "/userlist";
    }
  /*  @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }*/

    @RequestMapping(value = "/newuser",method = RequestMethod.POST)
    public String newUserSave(@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, @RequestParam(name = "role[]") List<String> role) {
        System.out.println("New User");
        if (login != null) {
            System.out.println("Создаём");

            Set<String> roles = new HashSet<>();
            for (String r : role
            ) {
                roles.add(r);
            }
            User newUser = new User(login, password);
            userService.addUser(newUser, roles);
        }
        return "redirect:userlist";
    }

    @RequestMapping(value = "/newuser",method = RequestMethod.GET)
    public String newUserForm(){
        return "newUser";
    }
    @RequestMapping(value = "/deluser", method = RequestMethod.GET)
    public String dUser(@RequestParam Map<String, String> allRequestParams, ModelMap model) {
        userService.deleteUserById(Long.parseLong(allRequestParams.get("id")));
        return "redirect:/userlist";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.GET)
    public String editUser(@RequestParam Map<String, String> allRequestParams, ModelMap model) {
        System.out.println("EditUserPage");
        User user = userService.getUserById(Long.parseLong(allRequestParams.get("id")));
        model.addAttribute("user", user);
        return "editUser";
    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String esUser(@RequestParam(name = "id") Long id,@RequestParam(name = "login") String login, @RequestParam(name = "password") String password, @RequestParam(name = "role[]") List<String> role, ModelMap model) {

        User updateUser = new User(login, password);
        Set<String> roles = new HashSet<>();
        for (String r : role
        ) {
            roles.add(r);
        }
        updateUser.setId(id);
        userService.updateUser(updateUser,roles);
        return "redirect:/userlist";
    }

}
