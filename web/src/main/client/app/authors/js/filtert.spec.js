       describe('filter', function () {
    'use strict';
    
    var  authorsTo = [{firstName : 'Ala',lastName : 'Maj'}, 
    	             {firstName : 'OOOO',lastName : 'Majew'}, 
    	             {firstName : 'Maja',lastName : 'KKKKK'}, 
    	             {firstName : 'KKKK',lastName : 'Majew'}, 
    	             {firstName : 'kkkk',lastName : 'AAAA'}, 
    	             {firstName : 'KKKK',lastName : 'OOO'}];
    
    beforeEach(function () {
        module('app.main');
        module('app.authors');
     
    });

        it('filter should return a list of authors when name starts with prefix Maj', inject(function(prefixSearchFilter) {
        	//given
        	var prefix = 'Maj';
        	var  expectedAuthorsTo = [{firstName : 'Ala',lastName : 'Maj'}, 
        	                          {firstName : 'OOOO',lastName : 'Majew'}, 
        	                          {firstName : 'Maja',lastName : 'KKKKK'}, 
        	                          {firstName : 'KKKK',lastName : 'Majew'}];
        	//when
        	var filtered = prefixSearchFilter(authorsTo, prefix);
        	//then
        	expect(filtered).toEqual(expectedAuthorsTo);
        }));
        
        it('filter should return a list of authors when name starts with prefix k', inject(function(prefixSearchFilter) {
        	//given
        	var prefix = 'k';
        	var  expectedAuthorsTo = [{firstName : 'Maja',lastName : 'KKKKK'}, 
        	        	             {firstName : 'KKKK',lastName : 'Majew'}, 
        	        	             {firstName : 'kkkk',lastName : 'AAAA'}, 
        	        	             {firstName : 'KKKK',lastName : 'OOO'}];
        	//when
        	var filtered = prefixSearchFilter(authorsTo, prefix);
        	//then
        	expect(filtered).toEqual(expectedAuthorsTo);
        }));
        it('filter should return empty list of authors when name starts with prefix R', inject(function(prefixSearchFilter) {
        	//given
        	var prefix = 'R';
        	//when
        	var filtered = prefixSearchFilter(authorsTo, prefix);
        	//then
        	expect(filtered.length).toEqual(0);
        }));

});