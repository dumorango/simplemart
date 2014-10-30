'use strict';

describe('Controller: BuscaCtrl', function () {

    // load the controller's module
    beforeEach(module('simplemartApp'));

    var BuscaCtrl,
        Produto,
        scope,
        q,
        query = 'query',
        produtos = {produtos:'produtos',facet:'facet'};

    // Initialize the controller and a mock scope
    beforeEach(inject(function ($controller, $rootScope,$q) {
        console.log($controller);
        console.log($rootScope);
        q = $q;
        scope = $rootScope.$new();
        Produto = jasmine.createSpyObj('Produto',['buscar']);
        Produto.buscar = function(queryIn){
            if(queryIn==query) {
                var def = q.defer();
                def.resolve(produtos);
                return def.promise;
            }
        };
        BuscaCtrl = $controller('BuscaCtrl', {
            $scope: scope,
            Produto: Produto
        });
    }));

    it('deve trazer uma promise com resultando nos elementos da busca e adicionar ao escopo', function () {
        scope.buscar(query);
        expect(scope.produtos,produtos.produtos);
        expect(scope.facet,produtos.facet);
    });


});
