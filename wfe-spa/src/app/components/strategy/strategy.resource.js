(function() {
  'use strict';

  angular
      .module('wfeSpa')
      .service('Strategy', strategy);

  /** @ngInject */
  function strategy($resource, APP_CONFIG) {
      return $resource(APP_CONFIG.API_URL + 'strategy', {}, {
        get: {
          method: 'GET',
          isArray: true
        }
      });
  }

})();
