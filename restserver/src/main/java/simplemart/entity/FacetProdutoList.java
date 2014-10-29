package simplemart.entity;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dumorango on 27/10/14.
 */
public class FacetProdutoList {

    private List<Produto> produtos = new ArrayList();
    private List<CategoryFacet> facet = new ArrayList();

    public FacetProdutoList(){}

    public FacetProdutoList(SolrDocumentList documentList, FacetField facetField) {
        for(SolrDocument solrDocument:documentList)
            produtos.add(new Produto(solrDocument));
        for(FacetField.Count facetFieldCount:facetField.getValues())
            facet.add(new CategoryFacet(facetFieldCount.getName(),facetFieldCount.getCount()));
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<CategoryFacet> getFacet() {
        return facet;
    }

    public void setFacet(List<CategoryFacet> facet) {
        this.facet = facet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FacetProdutoList)) return false;

        FacetProdutoList that = (FacetProdutoList) o;

        for(CategoryFacet facetField:that.getFacet())
            if(!facet.contains(facetField)) return false;
        for(CategoryFacet facetField:facet)
            if(!that.getFacet().contains(facetField)) return false;
        if (!produtos.equals(that.produtos)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = produtos.hashCode();
        result = 31 * result + facet.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FacetProdutoList{" +
                "produtos=" + produtos +
                ", facet=" + facet +
                '}';
    }
}
