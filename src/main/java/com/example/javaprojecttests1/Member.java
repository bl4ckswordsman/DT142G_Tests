package com.example.javaprojecttests1;

import java.io.Serializable;

public class Member implements Serializable {

    // The serialVersionUID is a universal version identifier for a Serializable class.
    //private static final long serialVersionUID = 1L;


    private int memberID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    // Constructors, getters, and setters

    public Member() {
        // Default constructor
    }

    public Member(int memberID, String firstName, String lastName, String email, String phone, String address) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

