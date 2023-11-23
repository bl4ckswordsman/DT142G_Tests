package com.example.javaprojecttests1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/guitarshop1";
        String username = "root_test";
        String password = "1234";

        List<Member> members = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Member";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Member member = new Member();
                    member.setMemberID(resultSet.getInt("MemberID"));
                    member.setFirstName(resultSet.getString("FirstName"));
                    member.setLastName(resultSet.getString("LastName"));
                    member.setEmail(resultSet.getString("Email"));
                    member.setPhone(resultSet.getString("Phone"));
                    member.setAddress(resultSet.getString("Address"));
                    members.add(member);

                    System.out.println(member.getFirstName());
                    System.out.println(member.getLastName());
                    System.out.println(member.getEmail());
                }
            }
        } catch (SQLException e) {
            System.out.println("Error loading members from the database: " + e.getMessage());
        }
    }
}