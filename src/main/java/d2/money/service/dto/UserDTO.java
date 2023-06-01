package d2.money.service.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private boolean status;
    private String image;
    private String role;
    private int provinderType;
    private long provinderId;
    private String accsessToken;

    public UserDTO() {
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getProvinderType() {
        return provinderType;
    }

    public void setProvinderType(int provinderType) {
        this.provinderType = provinderType;
    }

    public long getProvinderId() {
        return provinderId;
    }

    public void setProvinderId(long provinderId) {
        this.provinderId = provinderId;
    }

    public String getAccsessToken() {
        return accsessToken;
    }

    public void setAccsessToken(String accsessToken) {
        this.accsessToken = accsessToken;
    }
}