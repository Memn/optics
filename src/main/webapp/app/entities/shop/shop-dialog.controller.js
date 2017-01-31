(function() {
    'use strict';

    angular
        .module('opticsApp')
        .controller('ShopDialogController', ShopDialogController);

    ShopDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Shop', 'Customer', 'User', 'Product'];

    function ShopDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Shop, Customer, User, Product) {
        var vm = this;

        vm.shop = entity;
        vm.clear = clear;
        vm.save = save;
        vm.customers = Customer.query();
        vm.users = User.query();
        vm.products = Product.query();

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.shop.id !== null) {
                Shop.update(vm.shop, onSaveSuccess, onSaveError);
            } else {
                Shop.save(vm.shop, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('opticsApp:shopUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
