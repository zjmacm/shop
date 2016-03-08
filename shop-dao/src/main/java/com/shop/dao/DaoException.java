package com.shop.dao;

/**
 * Created by ldz on 31/10/14.
 * @author ldz
 */
public class DaoException extends RuntimeException{

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String msg,Throwable cause) {
        super(msg,cause);
    }

}
