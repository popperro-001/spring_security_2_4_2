package spring.security.service;

import spring.security.entity.Role;
import spring.security.entity.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> userList();

    Role getRoleByName(String role);
}
