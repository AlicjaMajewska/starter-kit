angular.module('app.books').factory('bookRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return $http.get(currentContextPath.get() + 'rest/books/books-by-title', {params: {titlePrefix: titlePrefix}});
        },
        deleteBook: function (bookId) {
            return $http.delete(currentContextPath.get() + 'rest/books/book/' + bookId);
        }, 
        
        addBook: function (title, authorsTo) { 
        	var  book =  { id: null,
                title: title,
                authorsTo: authorsTo
            };
        	  return $http.post(currentContextPath.get() + 'rest/books/book/', book);
        }, 
        updateBook: function (book) {
        	var  bookToSent=  { id: book.id,
                    title: book.title,
                    authorsTo: book.authorsTo
                };
        	 return $http.post(currentContextPath.get() + 'rest/books/book/', bookToSent);
        }
 
    };
});
