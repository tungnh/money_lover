package d2.money.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "password", columnDefinition = "TEXT")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "status")
    private boolean status;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "provinder_type")
    private int provinderType;
    @Column(name = "provinder_id")
    private long provinderId;
    @Column(name = "accsess_token", columnDefinition = "TEXT")
    private String accsessToken;
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Wallet> walletList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Category> categoryList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Config> configList = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Notification> notificationList = new ArrayList<>();
    public User() {
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

    public List<Wallet> getWalletList() {
        return walletList;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Config> getConfigList() {
        return configList;
    }

    public void setConfigList(List<Config> configList) {
        this.configList = configList;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }
}