package simplemart;



import org.glassfish.jersey.server.ResourceConfig;
import simplemart.providers.ResponseExceptionMapper;
import simplemart.resources.ProdutoResource;

import javax.ws.rs.ApplicationPath;

/**
 * Created by dumorango on 28/10/14.

/*public class SimpleMartApp extends ResourceConfig {

    public SimpleMartApp() {
        register(ProdutoServiceProvider.class);
        register(ResponseExceptionMapper.class);
    }
}*/


@ApplicationPath("/")
public class SimpleMartApp extends ResourceConfig {
    public SimpleMartApp() {
        //packages("simplemart.resources;simplemart.providers");
        register(ProdutoResource.class);
        register(ResponseExceptionMapper.class);

    }
}



/*public class SimpleMartApp extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        // register root resource
        classes.add(ProdutoResource.class);
        classes.add(ProdutoServiceProvider.class);
        return classes;
    }
}*/