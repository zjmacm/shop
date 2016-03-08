package com.shop.captcha;

import javax.security.sasl.AuthenticationException;

/**
 * Created by ldz on 29/11/14.
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String detail) {
        super(detail);
    }
}
