import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;
import org.mockito.*;
import simplemart.entity.FacetProdutoList;
import simplemart.entity.Produto;
import simplemart.exception.ResponseException;
import simplemart.service.ProdutoService;

import java.io.IOException;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;

    private SolrServer solrServer;

    public ProdutoServiceTest() {
        produtoService = new ProdutoService();
        MockitoAnnotations.initMocks(this);
        solrServer = Mockito.mock(SolrServer.class);
        produtoService.setSolrServer(solrServer);
    }

    @Test
    public void testeQueryProduto() throws SolrServerException, ResponseException {
        QueryResponse queryResponse = Mockito.mock(QueryResponse.class);
        SolrDocumentList solrDocumentList = new SolrDocumentList();
        when(queryResponse.getResults()).thenReturn(solrDocumentList);
        FacetField facetField = new FacetField("");
        when(queryResponse.getFacetField("categoria")).thenReturn(facetField);
        SolrQuery solrQuery = new SolrQuery("query");
        when(solrServer.query(any(SolrParams.class))).thenReturn(queryResponse);
        FacetProdutoList facetProdutoList = new FacetProdutoList(queryResponse.getResults(),queryResponse.getFacetField("categoria"));
        assertEquals(produtoService.queryProduto("query"), facetProdutoList);
    }

    @Test(expected=ResponseException.class)
    public void testeQueryProdutoThrowsException() throws SolrServerException, ResponseException {
        when(solrServer.query(any(SolrParams.class))).thenThrow(new SolrServerException(""));
        produtoService.queryProduto("");
    }

    @Test
    public void testeAddProduto() throws ResponseException, IOException, SolrServerException {
        String uuid = UUID.randomUUID().toString();
        Produto produto = new Produto("produtoTeste - "+uuid,"produtoTeste - descricao -"+uuid,uuid);
        produtoService.addProduto(produto);
        verify(solrServer).add(any(SolrInputDocument.class));
    }

    @Test(expected=ResponseException.class)
    public void testeAddProdutoThrowException() throws ResponseException, IOException, SolrServerException {
        String uuid = UUID.randomUUID().toString();
        Produto produto = new Produto("produtoTeste - "+uuid,"produtoTeste - descricao -"+uuid,uuid);
        when(solrServer.commit()).thenThrow(new SolrServerException(""));
        produtoService.addProduto(produto);
    }


}
