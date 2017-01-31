(function() {
    'use strict';

    angular
        .module('opticsApp')
        .factory('SaleSearch', SaleSearch);

    SaleSearch.$inject = ['$resource'];

    function SaleSearch($resource) {
        var resourceUrl =  'api/_search/sales/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
