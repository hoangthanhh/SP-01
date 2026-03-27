package SP1.payload.Response;

import lombok.*;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Getter
@Setter
public class ResponseObject<T> {
    private Integer status;
    private String message;
    private T data;
    public  ResponseObject<T> responseSuccess(String message, T data){
        return new ResponseObject<>(HttpURLConnection.HTTP_OK, message, data);
    }
    public  ResponseObject<T> responseError(int status, String message, T data){
        return new ResponseObject<>(status, message, data);
    }
}
