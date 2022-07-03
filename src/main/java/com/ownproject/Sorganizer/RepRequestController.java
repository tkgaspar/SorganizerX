package com.ownproject.Sorganizer;

import com.ownproject.Sorganizer.RepRequest;
import com.ownproject.Sorganizer.RepRequestForm;
import com.ownproject.Sorganizer.User;
import com.ownproject.Sorganizer.RepReqService;
import com.ownproject.Sorganizer.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RepRequestController {

    private RepReqService repReqService;
    private UserService userService;

    public RepRequestController(RepReqService repReqService, UserService userService) {
        this.repReqService = repReqService;
        this.userService = userService;
    }


    @GetMapping("/reprequest")
    public String getRepReqList(Authentication auth, RepRequestForm repRequestForm, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        if (user.getRoles().contains("ADMIN") || user.getRoles().contains("EDITOR")) {
            attributes.addAttribute("SavedRepairRequests", repReqService.getUnscheduledRepReqList());

        } else {
            attributes.addAttribute("SavedRepairRequests", repReqService.getAllRequestsByUserId(repRequestForm.getUserId()));
        }
        return "/fragments/reprequest";
    }


    @PostMapping("/reprequest")
    public ModelAndView addRepRequest(RepRequestForm repRequestForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        if (repRequestForm.getRepReqId() == null) {
            if (!this.repReqService.addRepReq(repRequestForm, user).equals(null)) {
                attributes.addAttribute("repReqSuccessBool", true);
                attributes.addAttribute("repReqSuccess", "Your repair request has been saved successfully ! ");
                attributes.addAttribute("SavedRepairRequests", repReqService.getUnscheduledRepReqList());
            } else {
                attributes.addAttribute("repReqErrorBool", true);
                attributes.addAttribute("repReqError", "Something went wrong, please try again!");
            }
        } else {
            this.repReqService.save(repRequestForm);
            attributes.addAttribute("repReqSuccessBool", true);
            attributes.addAttribute("repReqSuccess", "Your repair request has been saved successfully ! ");
            attributes.addAttribute("SavedRepairRequests", repReqService.getUnscheduledRepReqList());

        }
        return new ModelAndView("forward:/result", attributes);
    }

    @GetMapping("/reprequest-delete")
    public ModelAndView deleteNote(@ModelAttribute("repRequestForm") RepRequestForm repRequestForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        for (RepRequest repRequest : this.repReqService.getAllRequestsByUserId(user.getUserId())) {
            if (repRequest.getRepReqId().equals(repRequestForm.getRepReqId())) {
                this.repReqService.delete(repRequest);
                attributes.addAttribute("repReqSuccessBool", true);
                attributes.addAttribute("repReqSuccess", "Your repair request has been deleted! ");
                attributes.addAttribute("repReqErrorBool",false);
            }else{
            	attributes.addAttribute("repReqErrorBool",true);
            	attributes.addAttribute("repReqError","Something went wrong, request not deleted, pls. try again!");            }
            
        }
        attributes.addAttribute("SavedRepairRequests", repReqService.getAllRequestsByUserId(user.getUserId()));
        return new ModelAndView("forward:/result", attributes);
    }

   

}
