package ru.van.mobileOperator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.van.mobileOperator.models.Plan;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findByTitle(String title);
}
