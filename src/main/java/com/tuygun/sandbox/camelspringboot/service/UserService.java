package com.tuygun.sandbox.camelspringboot.service;

import com.tuygun.sandbox.camelspringboot.exception.UserAlreadyExistException;
import com.tuygun.sandbox.camelspringboot.model.User;

import java.util.Collection;

public interface UserService {
    User createUser(User user) throws UserAlreadyExistException;

    Collection<User> findUsers();
}