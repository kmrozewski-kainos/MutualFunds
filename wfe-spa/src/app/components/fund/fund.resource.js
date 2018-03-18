(function() {
  'use strict';

  angular
      .module('wfeSpa')
      .service('Fund', fund);

  /** @ngInject */
  function fund($resource, APP_CONFIG) {
      return $resource(APP_CONFIG.API_URL + 'fund', {}, {
        get: {
          method: 'GET',
          isArray: true
        }
      });
  }

})();
