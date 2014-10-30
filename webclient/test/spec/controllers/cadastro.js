'use strict';

describe('Controller: CadastroCtrl', function () {

  // load the controller's module
  beforeEach(module('simplemartApp'));

  var CadastroCtrl,
      Produto,
     produto={titulo:'titulo',descricao:'descricao',categoria:'categoria'},
    scope,
    q;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope,$q) {
    q = $q;
    scope = $rootScope.$new();
    Produto = jasmine.createSpyObj('Produto',['cadastrar','cadastrados']);
    Produto.cadastrados = [];
    Produto.cadastrar = function(produto){
        var def = $q.defer();
        this.cadastrados.push(produto);
        def.resolve(true);
        return def.promise;
    };
    CadastroCtrl = $controller('CadastroCtrl', {
      $scope: scope,
      Produto:Produto
    });
  }));

    it('deve adicionar um produto no cadastro e zerar o formulário (produto)', function () {
        scope.update(produto);
        expect(Produto.cadastrados).toContain(produto);
        expect(scope.produto).toEqual({});

    });

    it('deve zerar o formulário (produto)', function () {
        scope.reset();
        expect(scope.produto).toEqual({});
        expect(scope.success).toBeNull();
        expect(!_.some(scope.produto,undefined)).toBe(true);

    });
});
