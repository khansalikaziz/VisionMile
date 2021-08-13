package com.example.splashtrial1;

public class model {
    String name,about,email,image;
    model()
            {

            }

public model(String name, String about, String email, String image) {
        this.name = name;
        this.about = about;
        this.email = email;
        this.image = image;
        }

public String getName() {
        return name;
        }

public void setName(String name) {
        this.name = name;
        }

public String getAbout() {
        return about;
        }

public void setAbout(String about) {
        this.about = about;
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
