package simplemart.resources;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import simplemart.service.ProdutoService;

/**
 * Created by dumorango on 30/10/14.
 */
public class MyApplicationBinder extends AbstractBinder {
    @Override
    protected void configure() {

        bind(ProdutoService.class).to(ProdutoService.class);

    }
}
