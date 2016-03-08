package com.shop.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
public class Store extends BaseDomain {
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator="systemUUID")//使用32位uuid生成策略
    private String id;

    @Column(name="seller_id",unique = true,nullable = false,length=32)
    private String sellerId;

    @Column(name="store_name",unique = true,nullable = false,length = 120)
    private String storeName;

    @Column(name = "longitude")
    private double longitude;

    @Column(name="latitude")
    private double latitude;

    @Column(name = "address")
    private String address;

    @Column(name="zip")
    private String zip;

    @Column(name="audit_pass")
    private String auditPass;

    @Column(name="audit_time")
    private Date auditTime;

    @Column(name="level")
    private int level;

    @Column(name="sort_order")
    private int sortOrder;

    @Column(name="store_status")
    private String storeStatus;

    @Column(name="start_time")
    private Date startTime;

    @Column(name="end_time")
    private Date endTime;

    @Column(name="transfer_fee")
    private int transferFee;

    @Column(name="tranfer_limit")
    private int transferLimit;

    @Column(name="invoice")
    private String invoice;

    @Column(name="pay_way")
    private int payWay;

    @Column(name="province")
    private String province;

    @Column(name="city")
    private String city;

    @Column(name="country")
    private String country;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "register_date")
    private Date registerDate;

    //dispay the info of the store
    @Override
    public String toString(){
        return new StringBuffer().append("store_name:")
                .append(this.storeName)
                .append(",store_id:")
                .append(this.id).toString();
    }
}