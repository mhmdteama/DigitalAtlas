package com.compubase.mhmd.digitalatlas;
public class Patient {
    private String name;
    private String userId;
    private String email;
    private String image;


    public Patient(String name, String userId, String email, String image) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
