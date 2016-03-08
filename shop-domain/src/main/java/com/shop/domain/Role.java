package com.shop.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * 角色表
 * Created by ldz on 26/11/14.
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "shop_role")
@GenericGenerator(name="systemUUID",strategy="uuid")
public class Role extends BaseDomain{
    @Id
    @Column(name = "id", unique = true, nullable = false, length = 32)
    @GeneratedValue(generator="systemUUID")//使用32位uuid生成策略
    private String id;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "default_level")
    private Integer defaultLevel;
    @Column(name = "create_time")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getDefaultLevel() {
        return defaultLevel;
    }

    public void setDefaultLevel(Integer defaultLevel) {
        this.defaultLevel = defaultLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
