'use strict';

/**
 * @ngdoc function
 * @name simplemartApp.controller:BuscaCtrl
 * @description
 * # BuscaCtrl
 * Controller of the simplemartApp
 */
angular.module('simplemartApp')
  .controller('BuscaCtrl',['$scope','Produto',function ($scope,Produto) {

    $scope.produtos = [];

    $scope.facet = [];

    $scope.buscar = function(q){
        Produto.buscar(q).then(function(response){
           $scope.produtos = response.produtos;
           $scope.facet = response.facet;
        });
    };

  }]);
