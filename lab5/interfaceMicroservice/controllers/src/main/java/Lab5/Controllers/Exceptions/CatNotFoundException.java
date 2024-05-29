package Lab5.Controllers.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CatNotFoundException extends Exception {
    public CatNotFoundException(){
        super();
    }
    public CatNotFoundException(String message){
        super(message);
    }

}
