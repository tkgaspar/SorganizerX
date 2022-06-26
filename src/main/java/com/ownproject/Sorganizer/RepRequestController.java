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
@RequestMapping("/repRequest")
public class RepRequestController {

    private RepReqService repReqService;
    private UserService userService;

    public RepRequestController(RepReqService repReqService, UserService userService) {
        this.repReqService = repReqService;
        this.userService = userService;
    }


    @GetMapping
    public ModelAndView getNoteList(Authentication auth, RepRequestForm repRequestForm, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        if (user.getRoles().contains("ADMIN") || user.getRoles().contains("EDITOR")) {
            attributes.addAttribute("SavedRepairRequests", repReqService.getUnscheduledRepReqList());

        } else {
            attributes.addAttribute("SavedRepairRequests", repReqService.getAllRequestsByUserId(repRequestForm.getUserId()));
        }
        return new ModelAndView("home.html", attributes);
    }


    @PostMapping
    public ModelAndView addRepRequest(RepRequestForm repRequestForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        if (repRequestForm.getRepReqId() == null) {
            if (!this.repReqService.addRepReq(repRequestForm, user).equals(null)) {
                attributes.addAttribute("noteUploadSuccessBool", true);
                attributes.addAttribute("noteUploadSuccess", "Your note has been saved successfully ! ");
                attributes.addAttribute("SavedRepairRequests", repReqService.getUnscheduledRepReqList());
            } else {
                attributes.addAttribute("noteUploadErrorBool", true);
                attributes.addAttribute("noteUploadError", "Something went wrong, please try again!");
            }
        } else {
            this.repReqService.save(repRequestForm);
            attributes.addAttribute("noteUploadSuccessBool", true);
            attributes.addAttribute("noteUploadSuccess", "Your note has been saved successfully ! ");
            attributes.addAttribute("SavedRepairRequests", repReqService.getUnscheduledRepReqList());

        }
        return new ModelAndView("forward:/result.html", attributes);
    }

    @GetMapping("/repRequest-delete")
    public ModelAndView deleteNote(@ModelAttribute("repRequestForm") RepRequestForm repRequestForm, Authentication auth, ModelMap attributes) {
        User user = this.userService.getUser(auth.getName());
        for (RepRequest repRequest : this.repReqService.getAllRequestsByUserId(user.getUserId())) {
            if (repRequest.getRepReqId().equals(repRequestForm.getRepReqId())) {
                this.repReqService.delete(repRequest);
                attributes.addAttribute("noteUploadSuccessBool", true);
                attributes.addAttribute("noteUploadSuccess", "Your note has been deleted! ");
            }
        }
        attributes.addAttribute("SavedRepairRequests", repReqService.getAllRequestsByUserId(user.getUserId()));
        return new ModelAndView("forward:/result.html", attributes);
    }

    @GetMapping("/result")
    public String getResultPage() {
        return "result.html";
    }

    @PostMapping("/result")
    public String postResultPage() {
        return "result.html";
    }

}
