describe('rest service', function () {
    'use strict';
    
    beforeEach(function () {
        module('app.main');
        module('app.authors');
    });

        it('search should call create http request ', inject(function(authorRestService, $httpBackend) {
        	//given
        	//when
        	authorRestService.search();
        	//then
        	$httpBackend.expectGET('rest/authors/authors-by-name');
        }));

});
