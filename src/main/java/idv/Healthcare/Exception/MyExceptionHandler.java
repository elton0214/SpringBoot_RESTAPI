package idv.Healthcare.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
        HttpStatus badrequest = HttpStatus.BAD_REQUEST;

        ErrorReponse errorReponse = new ErrorReponse("Username not founddd");
        return new ResponseEntity<>(errorReponse, badrequest);
    }

}
