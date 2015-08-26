describe('service', function () {
    'use strict';
    
    
    beforeEach(function () {
        module('app.main');
        module('app.books');
        
    });


    it('search should call bookRestService.search', inject(function (bookRestService, bookService) {
    	//given
    	spyOn(bookRestService, 'search');
    	var prefix = 'T';
    	// when
    	bookService.search(prefix);
        // then
        expect(bookRestService.search).toHaveBeenCalledWith(prefix);
    }));
    
    it('deleteBook should call bookRestService.deleteBook', inject(function (bookRestService, bookService) {
    	//given
    	spyOn(bookRestService, 'deleteBook');
    	var bookId= 1;
    	// when
    	bookService.deleteBook(bookId);
    	// then
    	expect(bookRestService.deleteBook).toHaveBeenCalledWith(bookId);
    }));
    
    it('addBook should call bookRestService.addBook', inject(function (bookRestService, bookService) {
    	//given
    	spyOn(bookRestService, 'addBook');
    	var title= 'Title';
    	var authorsTo = [{firstName : 'Ala',lastName : 'Maj'}, {firstName : 'Ala',lastName : 'Majew'}, {firstName : 'Ala',lastName : 'Majewska'}];
    	// when
    	bookService.addBook(title, authorsTo);
    	// then
    	expect(bookRestService.addBook).toHaveBeenCalledWith(title, authorsTo);
    }));
    
    it('updateBook should call bookRestService.updateBook', inject(function (bookRestService, bookService) {
    	//given
    	spyOn(bookRestService, 'updateBook');
    	var  book =  { id: null,
                title: 'Title',
                authorsTo: [{firstName : 'Ala',lastName : 'Maj'}, {firstName : 'Ala',lastName : 'Majew'}, {firstName : 'Ala',lastName : 'Majewska'}]
            };
    	// when
    	bookService.updateBook(book);
    	// then
    	expect(bookRestService.updateBook).toHaveBeenCalledWith(book);
    }));
    
    
   
    

});
