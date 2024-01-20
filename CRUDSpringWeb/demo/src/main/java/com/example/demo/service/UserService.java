package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    /**
     * Объект репозитория для работы БД
     */
    private final UserRepository userRepository;


    /**
     * Конструктор класса
     * @param userRepository репозиторий пользователей.
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получить пользователя по id
     * @param id уникальный индетификатор пользователя
     * @return пользователя по id
     */
    public User getUserById(Integer id){
        return userRepository.findUserById(id);
    }

    /**
     * Получение списка всех пользователей
     * @return список пользователей
     */
    public List<User> findAll(){
        return userRepository.findAll();
    }

    /**
     * @param user объект пользователя.
     * @return объект пользователя с присвоенным id
     */
    public User saveUser(User user){
        return userRepository.save(user);
    }

    /**
     * Удаление пользователя.
     * @param id уникальны индетификатор пользователя.
     */
    public void deleteById(int id){userRepository.deleteById(id);}

    /**
     * Изменение данных о пользователею
     * @param user объект пользователя с новыми данными
     */
    public void updateUser(User user){userRepository.update(user);}

}
