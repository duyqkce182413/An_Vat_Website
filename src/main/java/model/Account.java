/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
import java.util.Date;
import java.util.List;

public class Account {
    private int id;
    private String username;
    private String password;
    private String email;
    private Date createdAt;
    private boolean isAdmin;
    private String phone_number;
    private List<Address> addresses;
    private List<Cart> carts;

    public Account() {
    }
    // Constructor
    public Account(String username, String password, String email, String phone_number) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
    }
    
    public Account(int id, String username, String password, String email, Date createdAt, boolean isAdmin, String phone_number) {
        this.id = id;
        this.username = username;
        this.password = password;   
        this.email = email;
        this.createdAt = createdAt;
        this.isAdmin = isAdmin;
        this.phone_number = phone_number;
    }
    
    public Account(int id, String username, String password, String email, String phone_number, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone_number = phone_number;
        this.isAdmin = isAdmin;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

  
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    @Override
    public String toString() {
        return "Account{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", createdAt=" + createdAt + ", isAdmin=" + isAdmin + ", phone_number=" + phone_number + ", addresses=" + addresses + ", carts=" + carts + '}';
    }
    
    
}

