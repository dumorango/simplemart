package simplemart.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import simplemart.entity.FacetProdutoList;
import simplemart.entity.Produto;
import simplemart.exception.ResponseException;

import javax.annotation.ManagedBean;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by dumorango on 28/10/14.
 */

@ManagedBean
public class ProdutoService {

    private SolrServer solrServer;

    public ProdutoService() {
        InputStream inputStream  = getClass().getClassLoader().getResourceAsStream("solr.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            //throw new ResponseException("Erro no servidor");
        }
        String solrUrl = properties.getProperty("solrUrl");
        solrServer = new HttpSolrServer(solrUrl);
    }

    private QueryResponse query(String query) throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(query);
        return solrServer.query(solrQuery);
    }

    public FacetProdutoList queryProduto(String query) throws ResponseException {
        QueryResponse response = null;
        try {
            response = query(query);
        } catch (SolrServerException e) {
            throw new ResponseException("Erro ao buscar produtos");
        }
        return new FacetProdutoList(response.getResults(),response.getFacetField("categoria"));
    }

    public void addProduto(Produto produto) throws ResponseException {
        try {
            solrServer.add(produto.toSolrInputDocument());
            solrServer.commit();
        } catch (Exception e) {
            throw new ResponseException("Erro ao adicionar produtos");
        }
    }

    public SolrServer getSolrServer() {
        return solrServer;
    }

    public void setSolrServer(SolrServer solrServer) {
        this.solrServer = solrServer;
    }
}
