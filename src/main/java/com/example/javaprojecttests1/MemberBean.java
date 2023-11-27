package com.example.javaprojecttests1;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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

    private Member memberToEdit;

    public String editMember(int memberID) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT * FROM Member WHERE MemberID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, memberID);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        Member member = new Member();
                        member.setMemberID(resultSet.getInt("MemberID"));
                        member.setFirstName(resultSet.getString("FirstName"));
                        member.setLastName(resultSet.getString("LastName"));
                        member.setEmail(resultSet.getString("Email"));
                        member.setPhone(resultSet.getString("Phone"));
                        member.setAddress(resultSet.getString("Address"));
                        memberToEdit = member;
                    }
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(MemberBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        return "editMember"; // Return the name of the page where the user can edit the member data
    }

    public Member getMemberToEdit() {
        return memberToEdit;
    }

    public String saveOrUpdateMember() {
        try (Connection connection = dataSource.getConnection()) {
            String sql;
            if (memberToEdit.getMemberID() == -1) {
                sql = "INSERT INTO Member (FirstName, LastName, Email, Phone, Address) VALUES (?, ?, ?, ?, ?)";
            } else {
                sql = "UPDATE Member SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ? WHERE MemberID = ?";
            }
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, memberToEdit.getFirstName());
                statement.setString(2, memberToEdit.getLastName());
                statement.setString(3, memberToEdit.getEmail());
                statement.setString(4, memberToEdit.getPhone());
                statement.setString(5, memberToEdit.getAddress());
                if (memberToEdit.getMemberID() != -1) {
                    statement.setInt(6, memberToEdit.getMemberID());
                }
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            Logger.getLogger(MemberBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        loadMembers();
        return "members"; // Return the name of the page where the user can see the member list
    }

    public String addMember() {
        memberToEdit = new Member(); // Create a new Member object
        memberToEdit.setMemberID(-1); // Set memberID to -1 to represent a new member
        return "editMember"; // Return the name of the page where the user can input the data for the new member
    }


    public String deleteMember(int memberID) {
        try (Connection connection = dataSource.getConnection()) {
            String sql = "DELETE FROM Member WHERE MemberID = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, memberID);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("An error occurred while deleting the member."));
            Logger.getLogger(MemberBean.class.getName()).log(Level.SEVERE, "SQL Exception", e);
        }
        loadMembers();
        return "members"; // Return the name of the page where the user can see the member list
    }

    // Getter method for the members list
    public List<Member> getMembers() {
        return members;
    }
}

