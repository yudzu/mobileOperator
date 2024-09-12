package ru.van.mobileOperator.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.van.mobileOperator.models.Plan;
import ru.van.mobileOperator.models.User;
import ru.van.mobileOperator.repositories.PlanRepository;
import ru.van.mobileOperator.repositories.UserRepository;

import java.security.Principal;
import java.util.List;

@Service
public class PlanService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(PlanService.class);
    private final PlanRepository planRepository;
    private final UserRepository userRepository;

    @Autowired
    public PlanService(PlanRepository planRepository, UserRepository userRepository) {
        this.planRepository = planRepository;
        this.userRepository = userRepository;
    }

    public List<Plan> listPlans(String title) {
        if (title != null) return planRepository.findByTitle(title);
        return planRepository.findAll();
    }

    public void savePlan(Plan plan) {
        log.info("Saving new {}", plan);
        planRepository.save(plan);
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    public void deletePlan(Long id) {
        planRepository.deleteById(id);
    }

    public Plan getPlanById(Long id) {
        return planRepository.findById(id).orElse(null);
    }
}
