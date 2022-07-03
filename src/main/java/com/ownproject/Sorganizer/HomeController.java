package com.ownproject.Sorganizer;


import com.ownproject.Sorganizer.RepRequestForm;
import com.ownproject.Sorganizer.ScheduleForm;
import com.ownproject.Sorganizer.User;
import com.ownproject.Sorganizer.RepReqService;
import com.ownproject.Sorganizer.ScheduleService;
import com.ownproject.Sorganizer.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;

@Controller
@RequestMapping("/home")
public class HomeController {


    private final RepReqService repReqService;
    private final UserService userService;
    private final ScheduleService scheduleService;

    public HomeController(RepReqService repReqService, UserService userService, ScheduleService scheduleService) {

        this.repReqService = repReqService;
        this.userService = userService;
        this.scheduleService = scheduleService;

    }


    @ModelAttribute
    public RepRequestForm repRequestForm() {
        return new RepRequestForm();
    }

    @ModelAttribute
    public ScheduleForm scheduleForm() {
        return new ScheduleForm();
    }

    @GetMapping()
    public String getHomePage(Authentication auth, Model model) {
        User user = userService.getUser(auth.getName());

        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))||auth.getAuthorities().contains(new SimpleGrantedAuthority("EDITOR"))){
            model.addAttribute("SavedRepairRequests", repReqService.getAllRequestsByUserId(user.getUserId()));
            model.addAttribute("UnscheduledRequests", repReqService.getUnscheduledRepReqList());
            model.addAttribute("localDate", Instant.now());
            model.addAttribute("ScheduledRepairs", scheduleService.getAllSchedules());
            model.addAttribute("AvailableMechanics", scheduleService.allMechanics());
            model.addAttribute("ScheduledHours", scheduleService.allSchedules());
        } else {
            model.addAttribute("SavedRepairRequests", repReqService.getAllRequestsByUserId(user.getUserId()));
            model.addAttribute("ScheduledRepairs", scheduleService.getAllSchedules());
            model.addAttribute("AvailableMechanics", scheduleService.allMechanics());
            model.addAttribute("localDate", Instant.now());
            model.addAttribute("ScheduledHours", scheduleService.allSchedules());

        }
        return "home";
    }

        @PostMapping
        public String postHomePage (Authentication auth, Model model){
            User user = userService.getUser(auth.getName());
            model.addAttribute("SavedRepairRequests", repReqService.getAllRequestsByUserId(user.getUserId()));
            model.addAttribute("UnscheduledRequests", repReqService.getUnscheduledRepReqList());
            model.addAttribute("ScheduledRepairs", scheduleService.getAllSchedules());
            model.addAttribute("AvailableMechanics", scheduleService.allMechanics());
            model.addAttribute("localDate", Instant.now());
            model.addAttribute("ScheduledHours", scheduleService.allSchedules());
            return "home";
        }

        @PostMapping("/logout")
        public String logoutView (HttpServletRequest request, HttpServletResponse response){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return "redirect:/login?logout";
        }
    
    
 


}