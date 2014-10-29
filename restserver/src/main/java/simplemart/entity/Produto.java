package simplemart.entity;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrInputDocument;

import java.util.UUID;

/**
 * Created by dumorango on 27/10/14.
 */
public class Produto {

    private String id;
    private String titulo;
    private String categoria;
    private String descricao;

    public Produto(){
        this.id = UUID.randomUUID().toString();
    }

    public Produto(String id, String titulo, String descricao, String categoria) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto(String titulo, String descricao, String categoria) {
        this();
        this.titulo = titulo;
        this.descricao = descricao;
        this.categoria = categoria;
    }

    public Produto(SolrDocument solrDocument){
        this.titulo = (String)solrDocument.getFieldValue("titulo");
        this.descricao = (String)solrDocument.getFieldValue("descricao");
        this.categoria = (String)solrDocument.getFieldValue("categoria");
    }

    public SolrInputDocument toSolrInputDocument(){
        SolrInputDocument solrDocument = new SolrInputDocument();
        solrDocument.addField("id",id);
        solrDocument.addField("titulo",titulo);
        solrDocument.addField("descricao",descricao);
        solrDocument.addField("categoria",categoria);
        return solrDocument;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;

        Produto produto = (Produto) o;

        if (!categoria.equals(produto.categoria)) return false;
        if (!descricao.equals(produto.descricao)) return false;
        if (!titulo.equals(produto.titulo)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = titulo.hashCode();
        result = 31 * result + categoria.hashCode();
        result = 31 * result + descricao.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
