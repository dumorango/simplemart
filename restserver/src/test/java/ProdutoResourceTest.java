

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sun.jersey.api.core.ClassNamesResourceConfig;
import com.sun.jersey.spi.container.servlet.WebComponent;
import com.sun.jersey.spi.inject.SingletonTypeInjectableProvider;
import com.sun.jersey.test.framework.WebAppDescriptor;
import org.glassfish.jersey.client.ClientResponse;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.inmemory.InMemoryTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.mockito.Mockito;
import simplemart.SimpleMartApp;
import simplemart.entity.CategoryFacet;
import simplemart.entity.FacetProdutoList;
import simplemart.entity.Produto;
import simplemart.exception.ResponseException;
import simplemart.providers.ResponseExceptionMapper;
import simplemart.resources.ProdutoResource;
import simplemart.service.ProdutoService;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * Created by dumorango on 28/10/14.
 */
public class ProdutoResourceTest extends JerseyTest {

    public static ProdutoService produtoServiceMock = Mockito.mock(ProdutoService.class);


    @Override
    protected Application configure() {
        return new SimpleMartApp();
    }

    @Override
    public TestContainerFactory getTestContainerFactory() {
        return new InMemoryTestContainerFactory();
    }

    @Test
    public void testAddProduto() throws ResponseException {
        String uuid = UUID.randomUUID().toString();
        Produto produto = new Produto("produtoTeste - "+uuid,"produtoTeste - descricao -"+uuid,uuid);
        Response response = target("produtos/")
                .request(MediaType.APPLICATION_JSON).put(Entity.json(produto));
        assertEquals(200,response.getStatus());
        FacetProdutoList foundFacetProdutoList = target("produtos/search").queryParam("q",produto.getTitulo())
                .request(MediaType.APPLICATION_JSON).get(FacetProdutoList.class);
        Boolean contains = false;
        for(Produto produtoInList:foundFacetProdutoList.getProdutos()){
            if(produtoInList.equals(produto)) contains = true;
        }
        assertTrue(contains);
        Boolean hasCategory = false;
        for(CategoryFacet categoryFacet:foundFacetProdutoList.getFacet())
            if(uuid.startsWith(categoryFacet.getName())) hasCategory = true;
        assertTrue(hasCategory);
    }

    @Test
    public void testearchProdutos() throws ResponseException {
        FacetProdutoList facetProdutoListFull = target("produtos/search").queryParam("q","*")
                .request(MediaType.APPLICATION_JSON).get(FacetProdutoList.class);
        Produto produto = facetProdutoListFull.getProdutos().get(0);
        FacetProdutoList facetProdutoList = target("produtos/search").queryParam("q",produto.getTitulo())
                .request(MediaType.APPLICATION_JSON).get(FacetProdutoList.class);
        Boolean contains = false;
        for(Produto produtoInList:facetProdutoList.getProdutos()){
            if(produtoInList.equals(produto)) contains = true;
        }
        assertTrue(contains);
    }

    @Test
    public void testAddNullAtributeProduct() throws ResponseException {
        String uuid = UUID.randomUUID().toString();
        Produto produto = new Produto();
        Response response = target("produtos/")
                .request(MediaType.APPLICATION_JSON).put(Entity.json(produto));
        assertEquals(500,response.getStatus());
    }

    @Provider
    public static class MockProdutoServiceProvider extends
            SingletonTypeInjectableProvider {
        public MockProdutoServiceProvider() {
            super(ProdutoService.class, produtoServiceMock);
        }
    }
}
