package simplemart.resources;

import simplemart.entity.FacetProdutoList;
import simplemart.entity.Produto;
import simplemart.exception.ResponseException;
import simplemart.service.ProdutoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.enterprise.context.RequestScoped;
/**
 * Created by dumorango on 27/10/14.
 */
@RequestScoped
@Path("produtos")
public class ProdutoResource {

    @Inject
    ProdutoService produtoService;

    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchProdutos(@QueryParam("q") String query) throws ResponseException {
        FacetProdutoList facetProdutoList = produtoService.queryProduto(query);
        return Response.status(200).entity(facetProdutoList).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response upsertProduto(Produto p) throws ResponseException {
        if(p.getTitulo()==null || p.getDescricao() == null || p.getCategoria() == null)
             throw new ResponseException("Ha atributos nulos.");
        produtoService.addProduto(p);
        return Response.status(200).build();
    }
}
