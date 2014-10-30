'use strict';

/**
 * @ngdoc directive
 * @name simplemartApp.directive:resultMessage
 * @description
 * # resultMessage
 */
angular.module('simplemartApp')
  .directive('resultMessage', function () {
    return {
      templateUrl: 'views/resultmessage.html',
      restrict: 'E',
      scope:{
          success:'='
      },
      link: function postLink(scope, element, attrs) {

      }
    };
  });
