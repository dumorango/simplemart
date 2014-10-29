package simplemart.providers;

/**
 * Created by dumorango on 28/10/14.
 */
import simplemart.exception.ErrorMessage;
import simplemart.exception.ResponseException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResponseExceptionMapper implements ExceptionMapper<ResponseException> {

    @Override
    public Response toResponse(ResponseException ex) {
        return Response.status(ex.getStatus())
                .entity(new ErrorMessage(ex.getMessage()))
                .type(MediaType.APPLICATION_JSON).
                        build();
    }

}
