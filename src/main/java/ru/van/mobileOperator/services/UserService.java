package ru.van.mobileOperator.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.van.mobileOperator.models.Plan;
import ru.van.mobileOperator.models.User;
import ru.van.mobileOperator.models.enums.Role;
import ru.van.mobileOperator.repositories.UserRepository;

import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class UserService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        Random rand = new Random();
        user.setPhoneNumber("+" + (Math.abs(rand.nextInt() * rand.nextInt()) % 1000000000 + 79000000000L));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (Objects.equals(email, "van.stepa@mail.ru")) {
            user.setRole(Role.ROLE_ADMIN);
        } else {
            user.setRole(Role.ROLE_USER);
        }
        log.info("Saving new user with email: {}", email);
        userRepository.save(user);
        return true;
    }

    public List<User> list() {
        return userRepository.findAll();
    }

    public void banUser(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id: {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id: {}; email: {}", user.getId(), user.getEmail());
            }
            userRepository.save(user);
        }
    }

    public void changeUserRole(Long user_id) {
        User user = userRepository.findById(user_id).orElse(null);
        if (user != null) {
            user.setRole(Role.values()[user.getRole().ordinal() ^ 1]);
            log.info("Changing user role with id: {}; email: {} To role: {}", user.getId(), user.getEmail(), user.getRole());
            userRepository.save(user);
        }
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void changeUserPlan(User user, Plan plan) {
        user.setPlan(plan);
        userRepository.save(user);
    }
}
