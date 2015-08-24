angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.prefix = '';
    

    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };

    $scope.search = function () {
        bookService.search($scope.prefix).then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Książka została usunięta.', 'custom-class');
        });
    };
    
    
    $scope.editBook = function (book) {
    	 var modalInstance = $modal.open({
	            templateUrl: 'books/html/book-edit-modal.html',
	            controller: 'BookModalController',
	            size: 'lg'
	        });
    	 
    	 modalInstance.result.then(function(response){
    		
    		 book.title = response;
    		 bookService.saveBook(book); 
    		 Flash.create('success', 'Tytul zostal zmieniony.', 'custom-class');
    		     }, function () {
    		         Flash.create('danger', 'Wyjatek', 'custom-class');
    		     });
    	
    	
    };

    $scope.addBook = function () {
    	 $modal.open({
    		            templateUrl: 'books/html/book-modal.html',
    		            controller: 'BookModalController',
    		            size: 'lg'
    		        });
    	 
    };
    $scope.addAuthor = function () {
    	$location.url('/books/add-author');
    };

});
