package simplemart;



import org.glassfish.jersey.server.ResourceConfig;
import simplemart.providers.ResponseExceptionMapper;
import simplemart.resources.MyApplicationBinder;
import simplemart.resources.ProdutoResource;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/")
public class SimpleMartApp extends ResourceConfig {
    public SimpleMartApp() {
        //packages("simplemart.resources;simplemart.providers");
        register(new MyApplicationBinder());
        register(ProdutoResource.class);
        register(ResponseExceptionMapper.class);

    }
}