package project.com.Recruitment.error;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.ui.Model;

import project.com.Recruitment.dto.RegisterDTO;
import project.com.Recruitment.exceptions.IllegalRegistrationException;

@ControllerAdvice
public class ErrorHandling{
    public static final String ERROR_TYPE = "errorType";
    public static final String GENERIC_ERROR = "generic";
    public static final String ERROR_MSG = "errorMsg";
    public static final String USERNAME_ERROR = "usernameError";
    public static final String EMAIL_ERROR = "emailError";
    public static final String PSN_ERROR = "psnError";

    public static final String ERROR_URL = "error";
    public static final String REGISTER_URL = "register";


    @ExceptionHandler(IllegalRegistrationException.class)
    public String exceptionHandler(IllegalRegistrationException e, Model model) {
        if (e.getMessage().toLowerCase().contains("username")) {
            model.addAttribute(ERROR_TYPE, USERNAME_ERROR);
            model.addAttribute("registerDTO", new RegisterDTO());
        } else if (e.getMessage().toLowerCase().contains("email")) {
            model.addAttribute(ERROR_TYPE, EMAIL_ERROR);
            model.addAttribute("registerDTO", new RegisterDTO());
        } else if (e.getMessage().toLowerCase().contains("personal")) {
            model.addAttribute(ERROR_TYPE, PSN_ERROR);
            model.addAttribute("registerDTO", new RegisterDTO());
        } else {
            model.addAttribute(ERROR_TYPE, GENERIC_ERROR);
        }
        model.addAttribute(ERROR_MSG, e.getMessage());
        return REGISTER_URL;
    }

    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model){
        model.addAttribute(ERROR_TYPE, GENERIC_ERROR);
        model.addAttribute(ERROR_MSG, e.getMessage());
        return ERROR_URL;
    }
}
