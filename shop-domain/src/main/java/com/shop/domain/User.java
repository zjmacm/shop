package com.shop.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * 买家表
 * Created by ldz on 21/11/14.
 * @author ldz
 * added by smy on 26/11/14: validated annotation
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_user")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class User extends BaseDomain implements Serializable{

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator="systemUUID")//使用32位uuid生成策略
    private String id;

    @Size(min=2,max=14,message = "用户名长度只能在2-14之间")
    @Pattern(regexp = "[A-Z a-z][A-Z a-z 0-9 _]*")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "register_date")
    private Date registerDate;

    @Column(name= "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    private String gender;

    @Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",message = "邮箱格式错误")
    @Column(name = "email")
    private String email;

    @Column(name = "user_status")
    private String userStatus;

    @Size(min=6,max=20,message="密码长度只能在6-20之间")
    @Column(name = "password")
    private String password;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "icon")
    private String icon;


    //学校+院系+年级
    @Column(name = "school")
    private String school;
    @Column(name = "department")
    private String department;
    @Column(name ="grade")
    private String grade;



    //----第三方登陆----//
    @Column(name = "third_account")
    private String thirdAccount;
    @Column(name = "third_token")
    private String thirdToken;
    @Column(name = "third_party")
    private String thirdParty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getThirdAccount() {
        return thirdAccount;
    }

    public void setThirdAccount(String thirdAccount) {
        this.thirdAccount = thirdAccount;
    }

    public String getThirdToken() {
        return thirdToken;
    }

    public void setThirdToken(String thirdToken) {
        this.thirdToken = thirdToken;
    }

    public String getThirdParty() {
        return thirdParty;
    }

    public void setThirdParty(String thirdParty) {
        this.thirdParty = thirdParty;
    }

    //to show the user's info


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", registerDate=" + registerDate +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", icon='" + icon + '\'' +
                ", school='" + school + '\'' +
                ", department='" + department + '\'' +
                ", grade='" + grade + '\'' +
                ", thirdAccount='" + thirdAccount + '\'' +
                ", thirdToken='" + thirdToken + '\'' +
                ", thirdParty='" + thirdParty + '\'' +
                '}';
    }
}