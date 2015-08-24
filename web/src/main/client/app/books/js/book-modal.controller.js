angular.module('app.books').controller('BookModalController', function ($scope, $modalInstance, bookService) {
    'use strict';

    $scope.newTitle = '';
    $scope.title = '';
    $scope.firstName = '';
    $scope.lastName= '';
    
    $scope.changeTitle = function() {
    	$modalInstance.close($scope.newTitle);
    };
    
    $scope.addBook = function() {
    	$modalInstance.close($scope.title, $scope.firstName, $scope.lastName);
    	bookService.addBook($scope.title, $scope.firstName, $scope.lastName); 
    };
    
});