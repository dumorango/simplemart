    'use strict';

/**
 * @ngdoc function
 * @name simplemartApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the simplemartApp
 */
angular.module('simplemartApp')
  .controller('MainCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
