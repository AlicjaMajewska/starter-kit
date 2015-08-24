angular.module('app.books').factory('bookService', function (bookRestService) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return bookRestService.search(titlePrefix);
        },
        deleteBook: function (bookId) {
            return bookRestService.deleteBook(bookId);
        },
        editBook: function (bookId, newTitle) {
        	 return bookRestService.editBook(bookId, newTitle);
        },
        addBook: function (title, firstName, lastName) {
//        	return bookRestService.editBook(bookId, newTitle);
//        	bookService.editBook($scope.title, $scope.title, $scope.lastName); 
        	return bookRestService.addBook(title, firstName, lastName);
        },
        saveBook: function (book) {
        	return bookRestService.saveBook(book);
        }
    };
});
