package com.gbastos.Filter.Model;

/**
 * User Model Object.
 * 
 * Visit the Github page for more details:
 * https://github.com/guilhermeborgesbastos/Spring-Boot-Studies/tree/Filter
 * 
 * @author Guilherme Borges Bastos <guilhermeborgesbastos@gmail.com>
 */
public class User {

    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email) {
        super();
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
