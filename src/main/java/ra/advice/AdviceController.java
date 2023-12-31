package ra.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ra.exception.LoginException;
import ra.exception.RegisterException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> loginFail(LoginException loginException){
        return new ResponseEntity<>(loginException.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RegisterException.class)
    public ResponseEntity<String> registerFail(RegisterException registerException){
        return new ResponseEntity<>(registerException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> invalidRegister(MethodArgumentNotValidException ex){
        Map<String, String> error = new HashMap();
        ex.getBindingResult().getFieldErrors().forEach(c->{
            error.put(c.getField(),c.getDefaultMessage());
        });
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
