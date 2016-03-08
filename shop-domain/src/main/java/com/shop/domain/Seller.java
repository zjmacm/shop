package com.shop.domain;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
/**
 * 卖家表
 * Created by qf on 2014/12/3
 * @author yqf.
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_seller")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class Seller extends BaseDomain implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator="systemUUID")//使用32位uuid生成策略
    private String id;

    @Size(min=2,max=14,message = "用户名长度只能在2-14之间")
    @Pattern(regexp = "[A-Z a-z][A-Z a-z 0-9 _]*")
    @Column(name = "seller_name")
    private String sellerName;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name="card_number")
    private String cardNumber;

    @Column(name="front_pic")
    private String frontPic;

    @Column(name="back_pic")
    private String backPic;

    @Column(name="QQ")
    private String qq;

    @Column(name="owner_photos")
    private String ownerPhotos;

    @Column(name= "phone")
    private String phone;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "gender")
    private String gender;

    @Pattern(regexp = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",message = "邮箱格式错误")
    @Column(name = "email")
    private String email;

    @Column(name = "seller_status")
    private String sellerStatus;

    @Size(min=6,max=20,message="密码长度只能在6-20之间")
    @Column(name = "password")
    private String password;

    @Column(name = "icon")
    private String icon;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    //出生地，格式合并“省+市+县（区）”
    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "register_date")
    private Date registerDate;

    //现在居住地，格式合并“省+市+县（区）”
    @Column(name = "living")
    private String living;

    private String school;

    private String department;

    private String grade;

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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getFrontPic() {
        return frontPic;
    }

    public void setFrontPic(String frontPic) {
        this.frontPic = frontPic;
    }

    public String getBackPic() {
        return backPic;
    }

    public void setBackPic(String backPic) {
        this.backPic = backPic;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getOwnerPhotos() {
        return ownerPhotos;
    }

    public void setOwnerPhotos(String ownerPhotos) {
        this.ownerPhotos = ownerPhotos;
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

    public String getSellerStatus() {
        return sellerStatus;
    }

    public void setSellerStatus(String sellerStatus) {
        this.sellerStatus = sellerStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getLiving() {
        return living;
    }

    public void setLiving(String living) {
        this.living = living;
    }

    //to show the seller's info

    @Override
    public String toString() {
        return "Seller{" +
                "id='" + id + '\'' +
                ", SellerName='" + sellerName + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", registerDate=" + registerDate +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", sellerStatus='" + sellerStatus + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", icon='" + icon + '\'' +
                ", living='" + living + '\'' +
                ", birthPlace='" + birthPlace + '\'' +
                '}';
    }
}
