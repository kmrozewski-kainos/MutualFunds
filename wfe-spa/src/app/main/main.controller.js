(function() {
  'use strict';

  angular
    .module('wfeSpa')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController($scope, FUND_TYPES, INVESTMENT_STRATEGIES, FundService, StrategyService, InvestmentService) {

  	$scope.funds = [];
  	$scope.strategies = [];
  	$scope.selectedStrategy;
  	$scope.amount;

  	getFunds();
  	getStrategies();

  	$scope.fundClicked = function (fund) {
  		fund.selected = !fund.selected;
  	};

  	$scope.strategyClicked = function (strategyName) {
  		$scope.selectedStrategy = strategyName;
  	}

  	$scope.matchFundType = function (fundType) {
  		return FUND_TYPES[fundType];
  	};

  	$scope.matchInvestmentStrategy = function (strategy) {
  		return INVESTMENT_STRATEGIES[strategy];
  	}

  	$scope.calculatePlan = function() {
  		var request = {
  			amount: $scope.amount,
  			strategy: $scope.selectedStrategy,
  			funds: getSelectedFunds()
  		};

  		InvestmentService.calculate(request).$promise.then(function (response) {
  			console.log(response);
  			$scope.plan = response;
  		});
  	}

  	function getFunds() {
  		return FundService.getFunds().$promise.then(function (response) {
  			$scope.funds = response;
  		});
  	}

  	function getStrategies() {
  		return StrategyService.getStrategies().$promise.then(function (response) {
  			$scope.strategies = response;
  		})
  	}

  	function getSelectedFunds() {
  		return $scope.funds.filter(function(fund) {
  			return fund.selected === true;
  		});
  	}
  }
})();
