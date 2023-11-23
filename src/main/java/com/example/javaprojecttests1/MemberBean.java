package com.example.javaprojecttests1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import javax.sql.DataSource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@SessionScoped
public class MemberBean implements Serializable {
    @Resource(name = "mysql_guitarshop1")
    private DataSource dataSource;

    private static final Logger LOGGER = Logger.getLogger(MemberBean.class.getName());
    private List<Member> members;

    @PostConstruct
    public void init() {
        // Load member data from the database during bean initialization
        loadMembers();
    }

    private void loadMembers() {
        members = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM Member";
            LOGGER.log(Level.INFO, "Executing SQL query: {0}", sql);
            //FIXME: System.out.println("Executing SQL query: " + sql);
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
                }
            }
        } catch (SQLException e) {
            // Log the exception
            Logger.getLogger(MemberBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
    }

    // Getter method for the members list
    public List<Member> getMembers() {
        return members;
    }
}

