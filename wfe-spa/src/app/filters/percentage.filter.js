(function() {
  'use strict';

  angular
      .module('wfeSpa')
      .filter('percentage', percentage);

  /** @ngInject */
  function percentage($filter) {
  	return function (input, decimals) {
    	return $filter('number')(input * 100, decimals) + '%';
  	};
  }

})();
