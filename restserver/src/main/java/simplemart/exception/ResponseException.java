package simplemart.exception;

/**
 * Created by dumorango on 28/10/14.
 */
public class ResponseException extends Exception{
    private Integer status = 500;
    private String message;

    public ResponseException(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
