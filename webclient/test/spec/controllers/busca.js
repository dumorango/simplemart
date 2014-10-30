'use strict';

describe('Controller: BuscaCtrl', function () {

  // load the controller's module
  beforeEach(module(['simplemartApp','restangular']));

  var BuscaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    BuscaCtrl = $controller('BuscaCtrl', {
      $scope: scope
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.awesomeThings.length).toBe(3);
  });
});
