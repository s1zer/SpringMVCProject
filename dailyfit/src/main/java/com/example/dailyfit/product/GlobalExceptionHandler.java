package com.example.dailyfit.product;

import com.example.dailyfit.message.Message;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handleError1(MultipartException e, Model model) {
        model.addAttribute("message", new Message("Multipart exception", e.getCause().getMessage()));
        return "error";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleError2(MaxUploadSizeExceededException e, Model model) {
        model.addAttribute("message", new Message("File's size exception", e.getCause().getMessage()));
        return "error";
    }
}