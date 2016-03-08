package com.shop.service.impl;

import com.shop.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

//import javax.persistence.Column;
import java.util.Collection;
import java.util.Date;

/**
 * Created by ldz on 29/11/14.
 */
@Component ("shopUserDetails")
public final class ShopUserDetails extends User implements UserDetails {
    @Autowired
    private  UserAuthorityUtils userAuthorityUtils;

    private String id;

    private String userName;

    private String realName;

    private Date registerDate;

    private String phone;

    private String mobile;

    private String gender;

    private String email;

    private String userStatus;

    private String password;

    private Date birthday;

    private String icon;


    //学校+院系+年级
    private String school;
    private String department;
    private String grade;


    //----第三方登陆----//
    private String thirdAccount;
    private String thirdToken;
    private String thirdParty;
    public ShopUserDetails init(User user) {
        setId(user.getId());
        System.err.println("construct"+user.getId());
        setUserName(user.getUserName());
        setRealName(user.getRealName());
        setRegisterDate(user.getRegisterDate());
        setPhone(user.getPhone());
        setMobile(user.getMobile());
        setGender(user.getGender());
        setEmail(user.getEmail());
        setUserStatus(user.getUserStatus());
        setPassword(user.getPassword());
        setIcon(user.getIcon());
        setBirthday(user.getBirthday());
        setSchool(user.getSchool());
        setDepartment(user.getDepartment());
        setGrade(user.getGrade());
        setThirdAccount(user.getThirdAccount());
        setThirdToken(user.getThirdToken());
        setThirdParty(user.getThirdParty());

        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        System.err.println("this"+this);
        System.err.println("this"+userAuthorityUtils);
        System.err.println("thisssssss"+userAuthorityUtils.createAuthorities(this));
        return  userAuthorityUtils.createAuthorities(this);
    }

    /**
     * 先认证用email呗
     * @return
     */
    @Override
    public String getUsername() {
        return getEmail();
    }
    //账户是否过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //指定用户是否被锁定或者解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //是否被禁用,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }


    public UserAuthorityUtils getUserAuthorityUtils() {
        return userAuthorityUtils;
    }

    public void setUserAuthorityUtils(UserAuthorityUtils userAuthorityUtils) {
        this.userAuthorityUtils = userAuthorityUtils;
    }

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

    private ShopUserDetails(){

    }
}