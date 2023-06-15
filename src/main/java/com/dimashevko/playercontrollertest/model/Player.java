package com.dimashevko.playercontrollertest.model;

import com.dimashevko.playercontrollertest.validators.AgeConstraint;
import com.dimashevko.playercontrollertest.validators.UniqueLogin;
import com.dimashevko.playercontrollertest.validators.UniqueScreenName;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;


public class Player {
    private  Integer id;
    @NotNull(message = "Age is required")
    @Positive
    @AgeConstraint(message = "User must be older than 16 and younger than 60 years old")
    private  Integer age;
    @Pattern(regexp = "^(male|female)$", message = "Role must be either 'male' or 'female'")
    private  String gender;
    @UniqueLogin(message = "Login must be unique")
    private  String login;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]{7,15}$", message = "Password must contain Latin letters and numbers (min 7 max 15 characters)")
    private  String password;
    @Pattern(regexp = "^(admin|user)$", message = "Role must be either 'admin' or 'user'")
    private  String role;
    @UniqueScreenName(message = "Screen name must be unique")
    private  String screenName;


    public Player(Integer id, Integer age, String gender, String login, String password, String role, String screenName) {
        this.id = id;
        this.age = age;
        this.gender = gender;
        this.login = login;
        this.password = password;
        this.role = role;
        this.screenName = screenName;
    }
    public Integer getId() {
        return id;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
}
