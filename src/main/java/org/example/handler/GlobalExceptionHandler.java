package org.example.handler;

import org.example.exceptions.MethodNotAllowedException;
import org.example.exceptions.NotFoundException;
import org.example.exceptions.UnAuthorizedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author "Tojaliyev Asliddin"
 * @since 01/08/22 19:50 (Monday)
 * SpringMvcJpa/IntelliJ IDEA
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NoHandlerFoundException.class})
    public String handler_404(NoHandlerFoundException e){
        return "error/404";
    }

    @ExceptionHandler({NotFoundException.class})
    public String NotFoundHandler(NotFoundException e){
        return "error/404";
    }

    @ExceptionHandler({MethodNotAllowedException.class})
    public String NotAllowedHandler(MethodNotAllowedException e) {
        return "error/400";
    }

    @ExceptionHandler({UnAuthorizedException.class})
    public String handle401(UnAuthorizedException e,
                            Model model,
                            WebRequest request) {
        String path = ((ServletWebRequest) request).getRequest().getRequestURI().toString();
        model.addAttribute("message", e.getMessage());
        model.addAttribute("path", path);
        return "errors/401";
    }
}
