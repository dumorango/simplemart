'use strict';

/**
 * @ngdoc service
 * @name simplemartApp.Produto
 * @description
 * # Produto
 * Service in the simplemartApp.
 */
angular.module('simplemartApp')
  .service('Produto',['Restangular','$q',function(Restangular,$q) {

        this.buscar = function(q){
            var produtos = Restangular.one('produtos/search');
            return produtos.get({q:q?q:'*'});
        };
        this.cadastrar = function(produto){
            var def = $q.defer();
            Restangular.one('produtos').customPUT(produto).then(function(response){
                def.resolve(true);
            },function(){
                def.resolve(false);
            });
            return def.promise;
        };
  }]);
