
define(function(require, exports, module) {

	var calculator=require('./calculator');
	var funPlaceholder =require('./funPlaceholder');
	var formValidate =require('./formValidate');
	function regiAction()
	{
				$("#calculate").click(function(){
					calculator();
				});
				$(".inputBox").click(function(){
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
				});
				$('#register').click(function(){
					var ch=$(window).height();
					var h=ch/2;
					var cw=$(window).width(); 
					var w=cw/2;
					$("#coverBox").css({'position':'absolute','left':w+'px','margin-left':'-180px'});//类名为center 距离左侧距离
					$("#coverBox").css({'position':'absolute','top':h+'px',"margin-top":"-240px"});//类名为center 距离左侧距离
					$("#bg").css({'height':ch+'px',"width":cw+"px"});
		            $('#bg').fadeIn(800);
					$('#coverBox').fadeIn(800);
				});
				$('#closeBg').click(function(){
					$('#bg').fadeOut(800);
					$('#coverBox').fadeOut(800);
				});
				
				$(window).resize(function(){			  
					var ch=$(window).height();
					var h=ch/2;
					var cw=$(window).width(); 
					var w=cw/2;
					$("#coverBox").css({'position':'absolute','left':w+'px','margin-left':'-180px'});//类名为center 距离左侧距离
					$("#coverBox").css({'position':'absolute','top':h+'px',"margin-top":"-240px"});//类名为center 距离左侧距离
					$("#bg").css({'height':ch+'px',"width":cw+"px"});
				});
				funPlaceholder(document.getElementById("username"));
				funPlaceholder(document.getElementById("emailAddress"));
				funPlaceholder(document.getElementById("password"));
				funPlaceholder(document.getElementById("repassword"));
				formValidate();
	}
	  module.exports = regiAction;

});

