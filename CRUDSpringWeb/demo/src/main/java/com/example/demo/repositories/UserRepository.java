package com.example.demo.repositories;

import com.example.demo.model.User;
import com.example.demo.utils.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для запросов к БД пользователей
 */
@Repository
public class UserRepository {

    /**
     * Объект подключения к БД
     */
    private final JdbcTemplate jdbc;

    /**
     * Конструктор класса
     * @param jdbc объект подключения к БД
     */
    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * Получение всех пользователей из БД
     * @return список всех пользователей
     */
    public List<User> findAll() {
        String sql = "SELECT * FROM userTable";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    /**
     * Сохранение пользователя в БД
     * @param user объект пользователя
     * @return объект пользователя с id
     */
    public User save(User user) {
        String sql = "INSERT INTO userTable VALUES (NULL, ?, ?)";
        jdbc.update(sql, user.getFirstName(), user.getLastName());
        return  user;
    }

    /**
     * Удаление пользователя по id
     * @param id уникальный идентификатор пользователя
     */
    public void deleteById(int id){
        String sql = "DELETE FROM userTable WHERE id=?";
        jdbc.update(sql, id);
    }

    /**
     * Обновление данных о пользователе
     * @param user объект пользователя
     */
    public void update(User user){
        String sql = "UPDATE userTable SET firstName = ?, lastName = ? WHERE id = ?";
        jdbc.update(sql, user.getFirstName(), user.getLastName(), user.getId());
    }

    /**
     * Получить пользователя по id
     * @param id уникальный идентификатор пользователя
     * @return пользователь
     */
    public User findUserById(Integer id) {
        String sql = "SELECT * FROM userTable WHERE id = ?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            return rowObject;
        };
        return jdbc.query(sql, new Object[]{id}, new UserMapper())
                .stream().findFirst().orElse(null);
    }
}
