'use strict';

/**
 * @ngdoc function
 * @name simplemartApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the simplemartApp
 */
angular.module('simplemartApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
