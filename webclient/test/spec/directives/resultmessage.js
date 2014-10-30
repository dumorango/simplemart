'use strict';

describe('Directive: resultMessage', function () {

  // load the directive's module
  beforeEach(module('simplemartApp'));

  var element,
    scope;

  beforeEach(inject(function ($rootScope) {
    scope = $rootScope.$new();
  }));

  it('should make hidden element visible', inject(function ($compile) {
    element = angular.element('<result-message></result-message>');
    element = $compile(element)(scope);
    expect(element.text()).toBe('this is the resultMessage directive');
  }));
});
