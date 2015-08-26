describe('service', function () {
    'use strict';
    
    beforeEach(function () {
        module('app.main');
        module('app.authors');
    });


    it('search should call authorRestService.search', inject(function (authorRestService, authorService) {
    	//given
    	spyOn(authorRestService, 'search');
    	// when
    	authorService.search();
        // then
        expect(authorRestService.search).toHaveBeenCalled();
    }));

});
