'use strict';

describe('Controller: MainCtrl', function () {

  // load the controller's module
  beforeEach(module('simplemartApp'));

  var MainCtrl,
    myService,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
      console.log($controller);
      console.log($rootScope);
      scope = $rootScope.$new();
      myService = jasmine.createSpyObj('myService', ['getZero']);
      console.log(myService);
      myService.getZero = function(){ return 0 };
      MainCtrl = $controller('MainCtrl', {
          $scope: scope,
          myService: myService
      });
  }));


  it('should attach a list of awesomeThings to the scope', function () {
    expect(scope.getZero()).toBe(0);
  });
});
