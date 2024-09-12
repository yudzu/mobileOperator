package ru.van.mobileOperator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.van.mobileOperator.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
