'use strict';

/**
 * @ngdoc overview
 * @name simplemartApp
 * @description
 * # simplemartApp
 *
 * Main module of the application.
 */
angular
  .module('simplemartApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
    'restangular'
  ])
  .config(['$routeProvider','RestangularProvider',function ($routeProvider,RestangularProvider) {

    RestangularProvider.setBaseUrl('simplemart/api');

    $routeProvider
      .when('/', {
        templateUrl: 'views/busca.html',
        controller: 'BuscaCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/busca', {
        templateUrl: 'views/busca.html',
        controller: 'BuscaCtrl'
      })
      .when('/cadastro', {
        templateUrl: 'views/cadastro.html',
        controller: 'CadastroCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  }]);
