angular.module('app.books').factory('bookRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return $http.get(currentContextPath.get() + 'rest/books/books-by-title', {params: {titlePrefix: titlePrefix}});
        },
        deleteBook: function (bookId) {
            return $http.delete(currentContextPath.get() + 'rest/books/book/' + bookId);
        }, 
        
        addBook: function (title, firstName, lastName) { // tutaj tablica autorow
        	var  book =  { id: null,
                title: title,
                authorsTo: [ {id:null, firstName: firstName, lastName: lastName}]
            };
//        	var request = $http({
//                method: 'post',
//                url: currentContextPath.get() + 'rest/books/book/' book: {
//                    'id': null,
//                    'title': title, 
//                    'authorsTo': [ {'firstName': firstName, 'lastName': lastName, 'id': null}]
//                }
//                data: {
//                    
//                }
//            });
//        	   return request;
        	  return $http.post(currentContextPath.get() + 'rest/books/book/', book);
        }, 
        saveBook: function (book) {
        	var  bookToSent=  { id: book.id,
                    title: book.title,
                    authorsTo: book.authorsTo
                };
        	 return $http.post(currentContextPath.get() + 'rest/books/book/', bookToSent);
        }
 
    };
});
