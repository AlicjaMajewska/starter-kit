angular.module('app.authors').filter('prefixSearch', function() {
    'use strict';
    
    function strStartsWith(str, prefix) {
        return (str+'').indexOf(prefix) === 0;
       }

    return function( authorsTo, prefix) {


        var filtered = [];

        angular.forEach(authorsTo, function(authorTo) {
          if(strStartsWith(authorTo.firstName.toLowerCase(), prefix.toLowerCase()) || strStartsWith(authorTo.lastName.toLowerCase(), prefix.toLowerCase())){
            filtered.push(authorTo);
          }
        });

        return filtered;
      };
    });

       