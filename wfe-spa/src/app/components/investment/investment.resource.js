(function() {
  'use strict';

  angular
      .module('wfeSpa')
      .service('Investment', investment);

  /** @ngInject */
  function investment($resource, APP_CONFIG) {
      return $resource(APP_CONFIG.API_URL + 'investment', {}, {
        calculate: {
          method: 'POST'
        }
      });
  }

})();
