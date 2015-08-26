angular.module('app.authors').controller('AuthorController', function ($scope, $window, $filter, $location, authorService, Flash) {
    'use strict';

    $scope.authorsTo = [];
    $scope.gridOptions = { data: 'authorsTo' };
    $scope.prefix = '';

    $scope.$on('$viewContentLoaded', function() {
    	authorService.search().then(function (response) {
            angular.copy(response.data, $scope.authorsTo);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    });
    
    $scope.search = function () {
        authorService.search().then(function (response) {
            angular.copy(response.data, $scope.authorsTo);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };
    


});
