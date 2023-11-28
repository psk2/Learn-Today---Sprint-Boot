package com.learntoday.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.learntoday.configs.TrainerInfoDetails;
import com.learntoday.entity.Trainer;
import com.learntoday.exceptions.TrainerNotFoundException;
import com.learntoday.repository.TrainerRepository;

@Component
public class TrainerInfoDetailsService implements UserDetailsService {

    @Autowired
    private TrainerRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws TrainerNotFoundException {
        Optional<Trainer> userInfo = repository.findByName(username);
        return userInfo.map(TrainerInfoDetails::new)
                .orElseThrow(() -> new TrainerNotFoundException("user not found " + username));

    }
}