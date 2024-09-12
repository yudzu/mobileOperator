package ru.van.mobileOperator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.van.mobileOperator.models.Plan;
import ru.van.mobileOperator.services.PlanService;
import ru.van.mobileOperator.services.UserService;


@Controller
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;
    private final PlanService planService;

    @Autowired
    public AdminController(UserService userService, PlanService planService) {
        this.userService = userService;
        this.planService = planService;
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("users", userService.list());
        model.addAttribute("plans", planService.listPlans(title));
        return "admin";
    }

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/user/change-role/{id}")
    public String userEdit(@PathVariable("id") Long id) {
        userService.changeUserRole(id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/plan/edit/{plan}")
    public String planEdit(@PathVariable("plan") Plan plan, Model model) {
        return "plan-edit";
    }
}
