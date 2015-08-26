angular.module('app.books').factory('bookService', function (bookRestService) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return bookRestService.search(titlePrefix);
        },
        deleteBook: function (bookId) {
            return bookRestService.deleteBook(bookId);
        },
        addBook: function (title, athorsTo) {
        	return bookRestService.addBook(title, athorsTo);
        },
        updateBook: function (book) {
        	return bookRestService.updateBook(book);
        }
    };
});
