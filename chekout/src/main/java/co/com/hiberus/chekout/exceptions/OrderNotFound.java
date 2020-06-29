package co.com.hiberus.chekout.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderNotFound extends RuntimeException {

    private int code;
    public OrderNotFound(String mensaje, int codigo) {
        super(mensaje);
        code = codigo;
    }
}
