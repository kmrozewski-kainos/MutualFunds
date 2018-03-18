(function() {
  'use strict';

  angular
    .module('wfeSpa')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log) {

    $log.debug('runBlock end');
  }

})();
