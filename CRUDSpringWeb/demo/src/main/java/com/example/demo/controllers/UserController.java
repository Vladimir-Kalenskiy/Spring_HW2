package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Класс контроллер для работы с сервисом пользователей.
 */
@Controller
public class UserController {
    /**
     * Объект сервиса для работы с пользвателями
     */
    private final UserService userService;

    /**
     * Конструктор класса
     * @param userService сервис пользователей
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Получения всех пользователей
     * @param model модель для передачи данных в предстовление
     * @return предстовление со списком пользователей
     */
    @GetMapping("/users")
    public String findAll(Model model){
        List<User> users = userService.findAll();


        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    /**
     * Создание нового пользователя
     * @param user объект пользователя
     * @return представление для создания пользователя
     */
    @GetMapping("/user-create")
    public String createUserForm(User user){
        return "user-create";
    }

    /**
     * Получение данных нового пользователя с формы представления
     * @param user объект пользователя
     * @return перенаправление на страницу со списком всех пользователей
     */
    @PostMapping("/user-create")
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users";
    }

    /**
     * Удаление пользователя по id
     * @param id уникальный идентификатор
     * @return перенаправление на страницу со списком всех пользователей
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        userService.deleteById(id);
        return "redirect:/users";
    }

    /**
     * Изменение данных пользователя
     * @param id уникальный идентификатор
     * @param model модель для передачи данных в представление
     * @return представление для изменения данных пользователя
     */
    @GetMapping("/user-update/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    /**
     * Получение данных об измененном пользователе с формы представления
     * @param user объект пользователя
     * @return перенаправление на страницу со списком всех пользователей
     */
    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/users";
    }

}
