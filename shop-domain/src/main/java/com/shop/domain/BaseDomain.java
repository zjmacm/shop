package com.shop.domain;

import java.io.Serializable;



import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 所有Domain的基类，实现序列化，和toString
 * @author ldz
 */
public class BaseDomain implements Serializable
{
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}