'use strict';

describe('Service: Produto', function () {

  // load the service's module
  beforeEach(module('simplemartApp'));

  // instantiate service
  var Produto;
  beforeEach(inject(function (_Produto_) {
    Produto = _Produto_;
  }));

  it('should do something', function () {
    expect(!!Produto).toBe(true);
  });

});
