package org.ejb;

import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import org.common.UserDto;
import org.example.parkinglot.entities.User;
import org.example.parkinglot.entities.UserGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PasswordBean passwordBean;

    public List<UserDto> findAllUsers() {
        try {
            LOG.info("findAllUsers");

            TypedQuery<User> query =
                    entityManager.createQuery("SELECT u FROM User u", User.class);

            List<User> users = query.getResultList();
            return copyUsersToDto(users);

        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    private List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();

        for (User u : users) {
            userDtos.add(new UserDto(
                    u.getId(),
                    u.getUsername(),
                    u.getEmail()
            ));
        }

        return userDtos;
    }

    public void createUser(String username, String email, String password, List<String> groups) {

        LOG.info("createUser");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);

        String hashedPassword = passwordBean.convertToSha256(password);
        user.setPassword(hashedPassword);

        entityManager.persist(user);
        entityManager.flush();

        assignGroupsToUser(username, groups);
    }

    public void assignGroupsToUser(String username, List<String> groups) {

        LOG.info("assignGroupsToUser");

        for (String groupName : groups) {
            UserGroup userGroup = new UserGroup();
            userGroup.setUsername(username);
            userGroup.setUsergroup(groupName);

            entityManager.persist(userGroup);
        }
    }

    public List<String> findUsernamesByUserIds(Collection<Long> userIds) {
        LOG.info("findUsernamesByUserIds");

        if (userIds == null || userIds.isEmpty()) {
            return new ArrayList<>();
        }

        return entityManager.createQuery(
                        "SELECT u.username FROM User u WHERE u.id IN :ids",
                        String.class
                )
                .setParameter("ids", userIds)
                .getResultList();
    }
}
