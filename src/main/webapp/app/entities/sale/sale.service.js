(function() {
    'use strict';
    angular
        .module('opticsApp')
        .factory('Sale', Sale);

    Sale.$inject = ['$resource', 'DateUtils'];

    function Sale ($resource, DateUtils) {
        var resourceUrl =  'api/sales/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.date = DateUtils.convertDateTimeFromServer(data.date);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
