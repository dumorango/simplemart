'use strict';

/**
 * @ngdoc function
 * @name simplemartApp.controller:CadastroCtrl
 * @description
 * # CadastroCtrl
 * Controller of the simplemartApp
 */
angular.module('simplemartApp')
  .controller('CadastroCtrl', ['$scope','Produto',function ($scope,Produto) {
        $scope.update = function(produto) {
            Produto.cadastrar(produto).then(function(worked){
                $scope.success = worked;
                if(worked) $scope.produto = {};
            });
        };

        $scope.reset = function() {
            $scope.produto = angular.copy({});
            $scope.success = null;
        };

        $scope.hasRequired = function(f){ return f.$error.required};
        $scope.reset();
    }]);
