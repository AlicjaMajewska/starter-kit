describe('author controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.authors');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));
    
    
    
    it('search calls authorService.search', inject(function ($controller, authorService, $q) {
    	//given
    	$controller('AuthorController', {$scope: $scope});
    	var searchDeferred = $q.defer();
    	spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
        // when
    	 $scope.search();
        // then
        expect(authorService.search).toHaveBeenCalled();
    }));


    
});
