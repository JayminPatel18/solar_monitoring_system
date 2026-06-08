package com.solar.services;

import com.solar.entity.User;
import com.solar.dto.UserUpdateDTO;

import java.util.List;

public interface UserService {

    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(Long id, UserUpdateDTO user);

    void deleteUser(Long id);
}
