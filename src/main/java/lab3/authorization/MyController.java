package lab3.authorization;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestController
public class MyController {
    private static HashMap<String, Person> persons = new HashMap<>();
    private static HashMap<String, Person> rememberedPerson = new HashMap<>();
    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @PostMapping("/LogIn")
    public Response LogIn(@RequestBody Person person, HttpServletRequest httpServletRequest) {
        if (!persons.keySet().contains(person.getPersonId())) {
            Response response = new Response("Такого пользователя нет!","Вы можете зарегестрироваться нажав кнопку \"Создать аккаунт\"", "warning");
            return response;
        }

        String password = persons.get(person.getPersonId()).getPersonPass();
        if (!passwordEncoder.matches(person.getPersonPass(), password)) {
            Response response = new Response("Ошибка!","Неверный пароль!", "error");
            return response;
        }

        Response response = new Response("Приветствуем, " + person.getPersonId(),"Вы заходили в прошлый раз: " + persons.get(person.getPersonId()).getDate(), "success");
        createAndUpdatePerson(person, httpServletRequest);
        return response;
    }

    @PostMapping("/createAccount")
    public Response createAccount(@RequestBody Person person, HttpServletRequest httpServletRequest) {
        if (persons.keySet().contains(person.getPersonId())) {
            Response response = new Response("Такой пользователь уже существует!","Вы должны выбрать другое имя и попробовать снова.", "warning");
            return response;
        }

        createAndUpdatePerson(person, httpServletRequest);
        Response response = new Response("Поздравляем!","Вы успешно зарегестрированы!", "success");
        return response;
    }

    @GetMapping("/rememberMe")
    public Response rememberMeChecker(HttpServletRequest httpServletRequest) {
        if (rememberedPerson.keySet().contains(httpServletRequest.getRemoteAddr())) {
            Person person = rememberedPerson.get(httpServletRequest.getRemoteAddr());
            Response response = new Response(person.getPersonId(), person.getPersonPass());
            System.out.println(person);
            return response;
        }
        Response response = new Response("", "");
        return response;
    }

    private void createAndUpdatePerson(Person person, HttpServletRequest httpServletRequest) {
        Person personForDataBase = new Person();
        personForDataBase.copyPerson(person);
        String password = passwordEncoder.encode(person.getPersonPass());
        personForDataBase.setPersonPass(password);
        persons.put(personForDataBase.getPersonId(), personForDataBase);

        if (person.getRememberMe()) {
            rememberedPerson.put(httpServletRequest.getRemoteAddr(), person);
        }
    }
}