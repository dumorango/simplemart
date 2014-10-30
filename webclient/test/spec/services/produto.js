'use strict';

describe('Service: Produto', function () {

  // load the service's module
  beforeEach(module('simplemartApp'));

  // instantiate service
  var Produto,httpBackend,query='query';
  beforeEach(inject(function (_Produto_,$q,$httpBackend) {
      Produto = _Produto_;
      httpBackend = $httpBackend;
  }));

  it('deve realizar http get e retornar o resultado', function () {
      var expectedResponse = "response";
      httpBackend.whenGET("produtos/search?q="+query).respond("response")
      Produto.buscar(query).then(function(response){
            expect(response).toBe(expectedResponse);
      });
  });

    it('deve realizar um put com o produto', function () {
        var produto = {titulo:'titulo',categoria:'categoria',descricao:'descricao'};
        httpBackend.whenPUT("produtos/").respond(function(method, url, data, headers){
            if(data==produto)
                return [200];
            else
                return [500];
        });
        Produto.cadastrar(produto).then(function(success){
            expect(success).toBe(true);
        });
        Produto.cadastrar(produto).then(function(success){
            expect(success).toBe(false);
        });
    });

});
