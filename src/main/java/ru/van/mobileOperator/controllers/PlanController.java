package ru.van.mobileOperator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.van.mobileOperator.models.Plan;
import ru.van.mobileOperator.services.PlanService;
import ru.van.mobileOperator.services.UserService;

import java.security.Principal;

@Controller
public class PlanController {
    private final PlanService planService;
    private final UserService userService;

    @Autowired
    public PlanController(PlanService planService, UserService userService) {
        this.planService = planService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String plans(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("plans", planService.listPlans(title));
        model.addAttribute("user", planService.getUserByPrincipal(principal));
        return "plans";
    }

    @GetMapping("/plan/{id}")
    public String planInfo(@PathVariable Long id, Principal principal, Model model) {
        model.addAttribute("plan", planService.getPlanById(id));
        model.addAttribute("user", planService.getUserByPrincipal(principal));
        return "plan-info";
    }

    @PostMapping("/plan/create")
    public String createPlan(Plan plan) {
        planService.savePlan(plan);
        return "redirect:/admin";
    }

    @PostMapping("/plan/delete/{id}")
    public String deletePlan(@PathVariable Long id) {
        planService.deletePlan(id);
        return "redirect:/admin";
    }

    @PostMapping("/plan/connect/{id}")
    public String connectPlan(@PathVariable Long id, Principal principal) {
        userService.changeUserPlan(userService.getUserByPrincipal(principal), planService.getPlanById(id));
        return "redirect:/";
    }
}
