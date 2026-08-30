package com.solar.services;

import com.solar.dto.UserUpdateDTO;
import com.solar.entity.SolarPanel;
import com.solar.entity.User;
import com.solar.exception.ResourceNotFoundException;
import com.solar.repository.SensorDataRepository;
import com.solar.repository.SolarPanelRepository;
import com.solar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SolarPanelRepository solarPanelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SensorDataRepository sensorDataRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public User updateUser(Long id, UserUpdateDTO user){
        User extingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        extingUser.setName(user.getName());

        if(user.getPassword() != null && !user.getPassword().isBlank()){
            extingUser.setPassword(
                    passwordEncoder.encode(user.getPassword())
            );
        }

        return userRepository.save(extingUser);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with id: " + id
                        ));

        List<SolarPanel> solarPanels = solarPanelRepository.findByUserId(user.getId());

        for(SolarPanel solarPanel : solarPanels){
            sensorDataRepository.deleteByPanelId(solarPanel.getId());
            solarPanelRepository.delete(solarPanel);
        }

        userRepository.delete(user);
    }
}
