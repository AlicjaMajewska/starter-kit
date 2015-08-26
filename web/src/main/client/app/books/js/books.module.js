angular.module('app.books', [ 'ngRoute', 'ui.bootstrap']).config(function($routeProvider) {
	'use strict';
	$routeProvider.when('/books/book-list', {
		templateUrl : 'books/html/book-list.html',
		controller : 'BookCRUDController'
	});

});