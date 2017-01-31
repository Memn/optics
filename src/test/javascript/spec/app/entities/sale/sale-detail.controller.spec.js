'use strict';

describe('Controller Tests', function() {

    describe('Sale Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockSale, MockProduct, MockCustomer, MockShop;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockSale = jasmine.createSpy('MockSale');
            MockProduct = jasmine.createSpy('MockProduct');
            MockCustomer = jasmine.createSpy('MockCustomer');
            MockShop = jasmine.createSpy('MockShop');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Sale': MockSale,
                'Product': MockProduct,
                'Customer': MockCustomer,
                'Shop': MockShop
            };
            createController = function() {
                $injector.get('$controller')("SaleDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'opticsApp:saleUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
