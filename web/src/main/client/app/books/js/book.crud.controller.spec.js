describe('book controller', function() {
	'use strict';

	beforeEach(function() {
		module('app.main');
		module('flash');
		module('app.books');
	});

	var $scope;
	beforeEach(inject(function($rootScope) {
		$scope = $rootScope.$new();
	}));


	it('edit book should call bookService.updateBook(book);', inject(function(
			$controller, $modal, $httpBackend, $q, Flash, bookService) {
		// given
		$controller('BookCRUDController', {
			$scope : $scope
		});

		var editBookfakeModal = {
			result : {
				then : function(response) {
					book.title = response;
					bookService.updateBook(book);
					Flash.create('success', 'Tytul zostal zmieniony.',
							'custom-class');
				}
			},
			close : function() {
			},
			dismiss : function() {
			}
		};

		var editDeferred = $q.defer();
		spyOn(bookService, 'updateBook').and.returnValue(editDeferred.promise);
		spyOn($modal, 'open').and.returnValue(editBookfakeModal);
		var book = {
			id : 1,
			title : 'test'
		};
		// when
		$scope.editBook(book);
		// then
		expect(bookService.updateBook).toHaveBeenCalledWith(book);

	}));

	it('add book should create fake modal ;', inject(function($controller,
			$modal, $httpBackend, $q, Flash, bookService) {
		// given
		$controller('BookCRUDController', {
			$scope : $scope
		});

		var addBookfakeModal = {

			result : {
				then : function() {
					bookService.addBook($scope.title, $scope.authorsTo);
					Flash.create('success', 'Ksiazka zostala dodana.',
							'custom-class');
				}
			},
			close : function() {
			},
			dismiss : function() {
			}
		};

		spyOn($modal, 'open').and.returnValue(addBookfakeModal);
		// when
		$scope.addBook();
		//then
		expect($modal.open).toHaveBeenCalled();

	}));

	it('search book should call bookService.search', inject(function(
			$controller, $q, bookService) {
		// given
		$controller('BookCRUDController', {
			$scope : $scope
		});

		var searchDeferred = $q.defer();
		$scope.prefix = 'T';
		spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
		// when
		$scope.search();
		searchDeferred.resolve({
			data : [ {
				id : 1,
				title : 'test'
			} ]
		});
		$scope.$digest();
		// then
		expect(bookService.search).toHaveBeenCalledWith($scope.prefix);
		expect($scope.books.length).toBe(1);
		expect($scope.books[0].id).toBe(1);
		expect($scope.books[0].title).toBe('test');

	}));

	it('delete book should call bookService.deleteBook', inject(function(
			$controller, $q, bookService, Flash) {
		// given
		$controller('BookCRUDController', {
			$scope : $scope
		});

		var bookId = 1;
		$scope.books = [ {
			id : bookId,
			title : 'test'
		} ];
		var deleteDeferred = $q.defer();
		spyOn(bookService, 'deleteBook').and
				.returnValue(deleteDeferred.promise);
		spyOn(Flash, 'create');
		// when
		$scope.deleteBook(bookId);
		deleteDeferred.resolve();
		$scope.$digest();
		// then
		expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
		expect(Flash.create).toHaveBeenCalledWith('success',
				'Książka została usunięta.', 'custom-class');
		expect($scope.books.length).toBe(0);
	}));
});
