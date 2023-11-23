package com.example.javaprojecttests1;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class HelloBean {

    String username;
    String imageLink = "/images/img1.png";
    public String getMessage() {
        return "Hello from the CDI Bean!";
    }
    public String getUsername() {
        return username;
    }
    public String getImageLink() {
        return imageLink;
    }
}


