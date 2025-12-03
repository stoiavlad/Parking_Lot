package org.example.parkinglot.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "usergroups")
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String usergroup;

    public UserGroup() {}

    public UserGroup(String username, String usergroup) {
        this.username = username;
        this.usergroup = usergroup;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUsergroup() {
        return usergroup;
    }

    // ⭐ NECESARE pentru UserBean.assignGroupsToUser()
    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsergroup(String usergroup) {
        this.usergroup = usergroup;
    }
}
