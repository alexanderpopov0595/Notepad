package com.epam.javacore.jotter.controller;

import com.epam.javacore.jotter.exceptions.AccessDeniedException;
import com.epam.javacore.jotter.exceptions.ImageUploadException;
import com.epam.javacore.jotter.exceptions.LoginIsCurrentlyExisting;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class CustomControllerAdvice  {

    private static final Logger log=Logger.getLogger(CustomControllerAdvice.class);

    /**
     * Method handles exception when trying to add/update user with already existing login in database
     * @param ex
     * @param model
     * @return signup/update page
     */
    @ExceptionHandler(LoginIsCurrentlyExisting.class)
    public String handleLoginIsCurrentlyExistingException(LoginIsCurrentlyExisting ex, Model model) {
        log.debug("Trying to add/update user with already existing login");
        model.addAttribute("error", ex.getMessage());
        model.addAttribute("user", ex.getUser());
        if(ex.getUser().getId()==0){
            return "users/signup";
        }
        else{
            return "users/update";
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(){
        return "errors/403";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleEmptyResultException(EmptyResultDataAccessException ex, Model model){
        return "errors/exception";
    }
    @ExceptionHandler(NullPointerException.class)
    public String handleEmptyResultException(NullPointerException ex, Model model){
        log.error("Nullpointer exception was thrown: "+ ex.getMessage());
        return "errors/exception";
    }

    @ExceptionHandler(ImageUploadException.class)
    public String handleImageUploadException(ImageUploadException ex, Model model){
        log.error(ex.getMessage());
        model.addAttribute("error", ex.getMessage());
        return "redirect:/users/account/update?error="+ex.getMessage();
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String error404(Exception ex) {
        return "errors/exception";
    }
}