package com.tuygun.sandbox.camelspringboot.service;

import com.tuygun.sandbox.camelspringboot.exception.UserAlreadyExistException;
import com.tuygun.sandbox.camelspringboot.model.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

import static java.util.Comparator.comparing;

@Service("userService")
public class UserServiceImpl implements UserService {

    private final Map<Integer, User> users = new TreeMap<>();

    public UserServiceImpl() {
        users.put(1, new User(1, "Linus Torvalds"));
        users.put(2, new User(2, "Ada Lovelace"));
        users.put(3, new User(3, "Alan Turing"));
    }

    @Override
    public Collection<User> findUsers() {
        return users.values();
    }

    @Override
    public User createUser(User user) throws UserAlreadyExistException {
        Optional<User> matchedUser = users.values().stream().filter(u -> u.getName().equals(user.getName())).findFirst();
        if (matchedUser.isPresent()) {
            throw new UserAlreadyExistException(user.getName() + " is already exist!");
        }

        int nextId = users.values().stream().max(comparing(User::getId)).get().getId() + 1;
        User newUser = new User();
        newUser.setId(nextId);
        newUser.setName(user.getName());
        users.put(nextId, newUser);
        return newUser;
    }
}