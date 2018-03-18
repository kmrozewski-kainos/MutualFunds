(function() {
  'use strict';

  angular
      .module('wfeSpa')
      .service('StrategyService', strategyService);

  /** @ngInject */
  function strategyService($resource, Strategy) {

    this.getStrategies = getStrategies;

    function getStrategies() {
      return Strategy.get();
    }
  }

})();
