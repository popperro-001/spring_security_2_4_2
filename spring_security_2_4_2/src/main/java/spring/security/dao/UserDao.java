package spring.security.dao;

import spring.security.entity.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> userList();

    User findUserByUsername(String username);
}
