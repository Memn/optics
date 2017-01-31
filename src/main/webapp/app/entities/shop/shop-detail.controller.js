(function() {
    'use strict';

    angular
        .module('opticsApp')
        .controller('ShopDetailController', ShopDetailController);

    ShopDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Shop', 'Customer', 'User'];

    function ShopDetailController($scope, $rootScope, $stateParams, previousState, entity, Shop, Customer, User) {
        var vm = this;

        vm.shop = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('opticsApp:shopUpdate', function(event, result) {
            vm.shop = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
