package Lab3.Controllers.Advices;

import Lab3.Controllers.Exceptions.CatNotFoundException;
import Lab3.Dto.CatDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(CatNotFoundException.class)
    public ResponseEntity<Response> handeException(CatNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
