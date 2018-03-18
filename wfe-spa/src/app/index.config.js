(function() {
  'use strict';

  angular
    .module('wfeSpa')
    .config(config);

  /** @ngInject */
  function config($logProvider) {
    // Enable log
    $logProvider.debugEnabled(true);
  }

})();
