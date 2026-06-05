package org.qa.models;

public class User {
    private String id;
    private String email;
    private String passwordHash;
    private String role;
    private boolean active;

    public User(String id, String email, String passwordHash,String role, boolean active){
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.active = active;
    }

    public String getId(){return id;}
    public String getEmail(){return email;}
    public String getPasswordHash(){return passwordHash;}
    public String getRole(){return role;}
    public boolean isActive(){return active;}

    void setPasswordHash(String passwordHash){this.passwordHash = passwordHash;}
    void setActive(boolean active){this.active = active;}

}
