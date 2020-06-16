package com.epam.javacore.jotter.controller;

import com.epam.javacore.jotter.domain.user.User;
import com.epam.javacore.jotter.service.image.ImageService;
import com.epam.javacore.jotter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

/**
 * Controller is responsible for all user's requests
 */
@Controller
@RequestMapping("/users")
public class UserController {

    /**
     * Injected user service
     */
    private UserService userService;

    /**
     * Injected image service
     */
    private ImageService imageService;


    @Autowired
    public UserController(UserService userService, ImageService imageService){
        this.userService=userService;
        this.imageService=imageService;
    }

    /**
     * Method shows sign in form
     * @return sign in page
     */
    @GetMapping("/signin")
    public String showSignInForm(@RequestParam(required = false) String message)
    {
        System.out.println("Mes:"+message);
        return "users/signin";
    }

    /**
     * Method adds user in model and shows sign up form
     * @param model
     * @return sign up page
     */
    @GetMapping("/signup")
    public String showUserForm(Model model) {
        model.addAttribute(new User());
        return "users/signup";
    }

    /**
     * Method gets user from form
     * If form has some errors - method redirects back to sign up page
     * Then method adds user to database
     * If form also contains image - method saves image on server
     * Then method logs user in and redirects to user's notes page
     * @param user
     * @param result
     * @param model
     * @param request
     * @param image
     * @return account page
     * @throws ServletException
     */
    @PostMapping("/signup")
    public String addUserFromForm(@Valid User user, BindingResult result, Model model, HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image) throws ServletException {
        if (result.hasErrors()) {
            return "users/signup";
        }
        userService.addUser(user);
        if(!image.isEmpty()){
            imageService.uploadImage(image, user.getLogin());
        }
        request.login(user.getLogin(), user.getPassword());
        return "redirect:/notes/";
    }

    /**
     * Method gets user login from principle object and loads user from database
     * Then method puts user in model
     * If request contains error parameter - method also puts error message in model
     * @param principal
     * @param model
     * @param error
     * @return user update page
     */
    @GetMapping("/account/update")
    public String showUserUpdateForm(Principal principal, Model model,  @RequestParam(value="error", required = false) String error) {
        model.addAttribute(userService.selectUser(principal.getName()));
        model.addAttribute("error", error);
        return "users/update";
    }

    /**
     * Method gets user from form
     * If form has some errors - method redirects back to update form
     * Then method updates user in database
     * If user has changed his login - method logs user out
     * If there is some image - method saves/ overrides image on server
     * @param user
     * @param model
     * @param principal
     * @param request
     * @return user account page
     */
    @PostMapping("/account/update")
    public String updateUserFromForm(@Valid User user, BindingResult result, Model model, Principal principal, HttpServletRequest request, @RequestParam(value = "image", required = false) MultipartFile image)  {
        if(result.hasErrors()){
            return "users/update";
        }
        try {
            userService.updateUser(user);
        }
        catch (Exception ex){
            model.addAttribute("error", "Login is existing");
            return "users/update";
        }
        if (!principal.getName().equals(user.getLogin())) {
            new SecurityContextLogoutHandler().logout(request, null, null);
        }
        imageService.uploadImage(image, user.getLogin());
        return "redirect:/users/account/update";
    }
    /**
     * Method deletes user from user update form
     * @param user
     * @return redirect to signout
     */
    @PostMapping("/account/delete")
    public String deleteAccount(@ModelAttribute("user") User user) {
        userService.deleteUser(user.getId());
        return "redirect:/users/signout";
    }

    @PostMapping("/searchUsers")
    public String showUserList(Model model, Principal principal, @RequestParam String login){
        model.addAttribute("userList", userService.selectUserList(principal.getName(), login));
        return "users/list";
    }
}
