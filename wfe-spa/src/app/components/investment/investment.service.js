(function() {
  'use strict';

  angular
      .module('wfeSpa')
      .service('InvestmentService', investmentService);

  /** @ngInject */
  function investmentService($resource, Investment) {

    this.calculate = calculate;

    function calculate(investmentPlanRequest) {
      return Investment.calculate(investmentPlanRequest);
    }
  }

})();
