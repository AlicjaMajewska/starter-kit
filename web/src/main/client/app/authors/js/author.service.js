angular.module('app.authors').factory(
		'authorService',
		function(authorRestService) {
			'use strict';

			return {
				search: function(namePrefix) {
					return authorRestService.search();
				}
			};
		});
