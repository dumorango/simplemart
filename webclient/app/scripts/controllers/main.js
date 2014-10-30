'use strict';

/**
 * @ngdoc function
 * @name testeKarmaApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the testeKarmaApp
 */
angular.module('simplemartApp')
    .controller('MainCtrl',['$scope','myService', function ($scope,Produto) {
        //Produto.getZero();
        $scope.awesomeThings = [
            'HTML5 Boilerplate',
            'AngularJS',
            'Karma'
        ];

        $scope.getZero = function(){
            return Produto.getZero();
        };
    }]);
