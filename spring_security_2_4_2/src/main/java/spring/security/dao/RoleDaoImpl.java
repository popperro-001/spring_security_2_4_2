package spring.security.dao;

import org.springframework.stereotype.Repository;
import spring.security.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> role = entityManager.createQuery("from Role R where R.name = :name", Role.class);
        role.setParameter("name", name);

        return role.getSingleResult();
    }
}
