(function() {
  'use strict';

  angular
      .module('wfeSpa')
      .service('FundService', fundService);

  /** @ngInject */
  function fundService($resource, Fund) {

    this.getFunds = getFunds;

    function getFunds() {
      return Fund.get();
    }
  }

})();
