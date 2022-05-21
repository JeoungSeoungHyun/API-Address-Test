package site.metacoding.login_system.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import site.metacoding.login_system.handler.ex.CustomApiException;
import site.metacoding.login_system.handler.ex.CustomException;

public class UtilValid {

    public static void 페이지요청에러처리(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomException(errors.toString());
        }
    }

    public static void 데이터요청에러처리(BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomApiException(errors.toString());
        }
    }
}
