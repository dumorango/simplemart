import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.mockito.*;
import simplemart.entity.FacetProdutoList;
import simplemart.entity.Produto;
import simplemart.exception.ResponseException;
import simplemart.providers.ResponseExceptionMapper;
import simplemart.resources.ProdutoResource;
import simplemart.service.ProdutoService;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dumorango on 28/10/14.
 */

public class ProdutoResourceTest {


    @InjectMocks
    private ProdutoResource produtoResource;

    @Spy
    private ProdutoService produtoService;



    public ProdutoResourceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testeQueryProduto() throws ResponseException {
        FacetProdutoList expectedFacetProdutoList = new FacetProdutoList();
        Mockito.doReturn(expectedFacetProdutoList).when(produtoService).queryProduto("teste");
        FacetProdutoList facetProdutoListFull = (FacetProdutoList) produtoResource.searchProdutos("teste").getEntity();
        assertEquals(facetProdutoListFull,expectedFacetProdutoList);
    }

    @Test
    public void testeQueryProdutoThrowsException() {
        FacetProdutoList expectedFacetProdutoList = new FacetProdutoList();
        Exception expectedException = new ResponseException("");
        Exception exception = null;
        try {
            Mockito.doThrow(expectedException).when(produtoService).queryProduto("teste");
            produtoResource.searchProdutos("teste");
        } catch (ResponseException e) {
            exception = e;
        }
        assertEquals(expectedException,exception);

    }

    @Test
    public void testAddProduto() throws ResponseException{
        String uuid = UUID.randomUUID().toString();
        Produto produto = new Produto("produtoTeste - "+uuid,"produtoTeste - descricao -"+uuid,uuid);
        Mockito.doNothing().when(produtoService).addProduto(produto);
        Response response = produtoResource.upsertProduto(produto);
        assertEquals(response.getStatus(),200);
    }

    @Test(expected=ResponseException.class)
    public void testAddProdutoThrowsException() throws ResponseException{
        String uuid = UUID.randomUUID().toString();
        Produto produto = new Produto();
        Mockito.doThrow(IOException.class).when(produtoService).addProduto(produto);
        Response response = produtoResource.upsertProduto(produto);
        assertEquals(response.getStatus(),200);
    }


}
