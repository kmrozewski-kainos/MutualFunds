/* global malarkey:false, moment:false */
(function() {
  'use strict';

  angular
    .module('wfeSpa')
    .constant('APP_CONFIG', {
    	'API_URL': 'http://localhost:8080/',
    })
    .constant('INVESTMENT_STRATEGIES', {
    	'SAFE': 'bezpieczny',
    	'BALANCED': 'zrównoważony',
    	'AGGRESIVE': 'agresywny'
    })
    .constant('FUND_TYPES', {
        'POLISH': 'Polski',
        'FOREIGN': 'Zagraniczny',
        'MONEY': 'Pieniężny'
    });

})();
