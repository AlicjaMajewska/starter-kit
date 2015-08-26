angular.module('app.books').controller(
		'BookModalController',
		function($scope, $modal, $modalInstance, bookService, Flash) {
			'use strict';

			$scope.newTitle = '';
			$scope.title = '';
			$scope.authorsTo = [ {
				firstName : '',
				lastName : ''
			} ];

			$scope.changeTitle = function() {
				$modalInstance.close($scope.newTitle);
			};

			$scope.addBook = function() {
				if ($scope.addForm.$invalid) {
					$scope.submitted = true;
				} else {
					$modalInstance.close($scope.title, $scope.authorsTo);

					$modalInstance.result.then(function() {

						bookService.addBook($scope.title, $scope.authorsTo);
						Flash.create('success', 'Ksiazka zostala dodana.',
								'custom-class');
					}, function() {
						Flash.create('danger', 'Wyjatek', 'custom-class');
					});
				}

			};

			$scope.addNextAuthor = function() {
				$scope.authorsTo.push({
					firstName : '',
					lastName : ''
				});
			};
			$scope.deleteAuthor = function(index) {
				if ($scope.authorsTo.length > 1) {
					$scope.authorsTo.splice(index, 1);
				}

			};

		});