angular.module('app.authors').controller('AuthorController', function ($scope, $window, $filter, $location, authorService, Flash) {
    'use strict';

    $scope.authorsTo = [];
    $scope.gridOptions = { data: 'authorsTo' };
    $scope.prefix = '';
    
//    $filter('myFilter'{ return function (input) { if(author.firstName.toLowerCase().substr(0,
//		namePrefix.length) === namePrefix.toLowerCase() ) { input = author; } return input; };
//    
//    
//    });	
    	
    

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
