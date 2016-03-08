define(function(require, exports, module) {
function dataHanding(whichOne){
	if(whichOne==="username"){
	   $.ajax({
			type: 'POST',
			url:'x' ,
			data: "username=" + $('#username').val(),
			success: function(response){
				//console.log("lla"+response+"lla");
				response=response.replace(/^\s+|\s+$/g,"");
				//console.log("lla"+response+"lla");
/*				if(response === 'Correct'){
				   window.location = "home.php"
				}*/
				else if(response === 'napproved'){
						$('#username').next().empty()
						$('#username').append('�˻�δ����')
				}
				else{
						$('#username').next().empty()
						$('.messageText').append('δ֪����������')         
			   }
		   }  
		});
	}
	else if(whichOne==="password"){
	   $.ajax({
			type: 'POST',
			url:'s' ,
			data: "&password=" + $('#password').val(),
			success: function(response){
				//console.log("lla"+response+"lla");
				response=response.replace(/^\s+|\s+$/g,"");
				//console.log("lla"+response+"lla");
/*				if(response === 'Correct'){
				   window.location = ;
				}*/
				else if(response === 'Incorrect'){
						$('#password').next().empty()
						$('#password').append('�û������������')
													 
				}
				else{
						$('#password').next().empty()
						$('.messageText').append('δ֪����������')           
			   }
		   }  
		});
	}

	
}


	  module.exports = dataHanding;

});