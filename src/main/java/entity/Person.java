package entity;

import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

/**
 * Created by Evgeniy Golubtsov on 09.02.2018.
 */

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "LOGIN", unique = true, updatable = false)
    private String login;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private Role role;

    public Person(){

    }

    public Person(String login, String password, Role role) {
        this(null, login, password, role);
    }

    public Person(Long id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
