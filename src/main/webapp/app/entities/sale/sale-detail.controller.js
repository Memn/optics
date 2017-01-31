(function() {
    'use strict';

    angular
        .module('opticsApp')
        .controller('SaleDetailController', SaleDetailController);

    SaleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Sale', 'Product', 'Customer', 'Shop'];

    function SaleDetailController($scope, $rootScope, $stateParams, previousState, entity, Sale, Product, Customer, Shop) {
        var vm = this;

        vm.sale = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('opticsApp:saleUpdate', function(event, result) {
            vm.sale = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
