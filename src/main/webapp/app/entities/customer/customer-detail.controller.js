(function() {
    'use strict';

    angular
        .module('opticsApp')
        .controller('CustomerDetailController', CustomerDetailController);

    CustomerDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Customer', 'Shop', 'Sale', 'Payment'];

    function CustomerDetailController($scope, $rootScope, $stateParams, previousState, entity, Customer, Shop, Sale, Payment) {
        var vm = this;

        vm.customer = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('opticsApp:customerUpdate', function(event, result) {
            vm.customer = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
