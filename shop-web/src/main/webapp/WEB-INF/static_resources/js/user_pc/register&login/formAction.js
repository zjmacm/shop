define(function(require, exports, module) {

  var $ = require('jquery');

  function formAction() {
		 var oriValue= $(this).val();
		 $(this).val("");
		 $(this).focus();
		 $(this).blur(function(){
             if ($(this).val()=='') 
			 {
		         $(this).css("text-align","center");
				 $(this).val(oriValue);
			 }
			 else{
			 }	   
	    });
  }

  module.exports = formAction;
});