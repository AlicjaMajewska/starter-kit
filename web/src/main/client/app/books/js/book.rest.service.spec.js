describe('rest service', function() {
	 'use strict'; 
	 
    beforeEach(function() {
          module('app.main');
          module('app.books');
          
    });

    it('search should call create http request ', inject(function(bookRestService, $httpBackend) {
    	//given
    	var titlePrefix = 'T';
    	//when
    	bookRestService.search(titlePrefix);
    	//then
    	$httpBackend.expectGET('/rest/books/books-by-title?titlePrefix='+titlePrefix);
    }));
    
    it('deleteBook should call http request ', inject(function(bookRestService, $httpBackend) {
    	//given
    	var bookId = 1;
    	//when
    	bookRestService.deleteBook(bookId);
    	//then
    	$httpBackend.expectGET('rest/books/book/' + bookId);
    }));
    
    it('addBook should call http request ', inject(function(bookRestService, $httpBackend) {
    	//given
    	var title= 'Title';
    	var authorsTo = [{firstName : 'Ala',lastName : 'Maj'}, {firstName : 'Ala',lastName : 'Majew'}, {firstName : 'Ala',lastName : 'Majewska'}];
    	var  book =  { id: null,
              title: title,
              authorsTo: authorsTo
          };
    	//when
    	bookRestService.addBook(title, authorsTo);
    	//then
    	$httpBackend.expectPOST('rest/books/book/', book);
    }));
    
    it('updateBook should call http request ', inject(function(bookRestService, $httpBackend) {
    	//given
    	var  book =  { id: null,
    			title: 'Title',
    			authorsTo: [{firstName : 'Ala',lastName : 'Maj'}, {firstName : 'Ala',lastName : 'Majew'}, {firstName : 'Ala',lastName : 'Majewska'}]
    	};
    	//when
    	bookRestService.updateBook(book);
    	//then
    	$httpBackend.expectPOST('rest/books/book/', book);
    }));
    

    
    
});

   