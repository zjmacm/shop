package com.shop.service;


import com.shop.domain.User;

/**
 * Created by ldz on 24/11/14.
 */
public interface UserContext {

    /**
     * Gets the currently logged in {@link User} or null if there is no authenticated user.
     *
     * @return
     */
    User getCurrentUser();

    /**
     * Sets the currently logged in {@link User}.
     * @param user the logged in {@link User}. Cannot be null.
     * @throws IllegalArgumentException if the {@link User} is null.
     */
    void setCurrentUser(User user);
}
