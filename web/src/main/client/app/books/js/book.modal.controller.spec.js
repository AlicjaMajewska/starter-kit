describe('modal controller', function () {
    'use strict';
    
    var modalInstance;
    
    beforeEach(function () {
        module('app.main');
        module('app.books');
        module('flash');
        module('ui.bootstrap');
        
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
        modalInstance = {                    // Create a mock object using spies
                close: jasmine.createSpy('modalInstance.close'),
                dismiss: jasmine.createSpy('modalInstance.dismiss'),
                result: {
                  then: jasmine.createSpy('modalInstance.result.then')
                }};
    }));
    

    it('changeTitle is called with new title', inject(function ($controller) {
        //given
    	$controller('BookModalController', {$scope: $scope, $modalInstance: modalInstance });
    	$scope.newTitle = 'new Tile';
    	// when
    	$scope.changeTitle();
        // then
        expect(modalInstance.close).toHaveBeenCalledWith($scope.newTitle);
    }));
    
    it('addBook is called with valid form,  title and authorsTo', inject(function ($controller, $modal, $q, bookService, Flash) {
    	//given
    	var addBookDeferred = $q.defer();
    	
    	spyOn(bookService, 'addBook').and.returnValue(addBookDeferred.promise);
    	spyOn(Flash, 'create');
    	
    	$controller('BookModalController', {$scope: $scope, $modalInstance: modalInstance });
    	$scope.title = 'new Tile';
    	$scope.authorsTo = [ {firstName : 'Alicja',lastName : 'Majewska'} ];
    	$scope.addForm = {
    			  $valid: true,
    			  $invalid: false
    			};
    	modalInstance.result.then = function(){
    		bookService.addBook($scope.title, $scope.authorsTo);
				Flash.create('success', 'Ksiazka zostala dodana.',
						'custom-class');
    	};
    	$scope.addBook();
    	
    	// then
    	expect(bookService.addBook).toHaveBeenCalledWith($scope.title, $scope.authorsTo);
    	expect(modalInstance.close).toHaveBeenCalledWith($scope.title,  $scope.authorsTo);
    	expect(Flash.create).toHaveBeenCalledWith('success', 'Ksiazka zostala dodana.',	'custom-class');
    	
    }));
    
    it('addBook is called with invalid form', inject(function ($controller) {
    	//given
    	$controller('BookModalController', {$scope: $scope, $modalInstance: modalInstance });
    	$scope.addForm = {
    			  $invalid: true
    			};
    	// when
    	$scope.addBook();
    	// then
    	 expect($scope.submitted).toBeTruthy();
    }));
    
    it('addNextAuthor increases number of authors ', inject(function ($controller) {
    	//given
    	$controller('BookModalController', {$scope: $scope, $modalInstance: modalInstance});
    	$scope.authorsTo = [{firstName : 'Ala',lastName : 'Maj'}, {firstName : 'Ala',lastName : 'Majew'}, {firstName : 'Ala',lastName : 'Majewska'}];
    	var authorsToNumber = $scope.authorsTo.length; 
    	// when
    	$scope.addNextAuthor();
    	// then
    	 expect($scope.authorsTo.length).toEqual(authorsToNumber + 1);
    	 expect($scope.authorsTo[3].firstName).toEqual('');
    	 expect($scope.authorsTo[3].lastName).toEqual('');
    }));
    
    it('deleteAuthor decreases number of authors if more tham one', inject(function ($controller) {
    	//given
    	$controller('BookModalController', {$scope: $scope, $modalInstance: modalInstance});
    	$scope.authorsTo = [{firstName : 'Ala',lastName : 'Maj'}, {firstName : 'Ala',lastName : 'Majew'}, {firstName : 'Ala',lastName : 'Majewska'}];
    	var authorsToNumber = $scope.authorsTo.length; 
    	// when
    	$scope.deleteAuthor();
    	// then
    	expect($scope.authorsTo.length).toEqual(authorsToNumber -1);
    }));
    
    it('deleteAuthor does not decreas number of authors if one', inject(function ($controller) {
    	//given
    	$controller('BookModalController', {$scope: $scope, $modalInstance: modalInstance});
    	$scope.authorsTo = [{firstName : 'Ala',lastName : 'Maj'}];
    	var authorsToNumber = $scope.authorsTo.length; 
    	// when
    	$scope.deleteAuthor();
    	// then
    	expect($scope.authorsTo.length).toEqual(authorsToNumber);
    }));
    

});
