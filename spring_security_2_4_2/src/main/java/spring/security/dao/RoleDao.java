package spring.security.dao;


import spring.security.entity.Role;

public interface RoleDao {
    Role getRoleByName(String name);
}
