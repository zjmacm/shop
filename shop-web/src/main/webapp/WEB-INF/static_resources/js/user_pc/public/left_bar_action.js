define(function(require, exports, module) {
	
	function leftBarAction()
	{
		var top,left;

		$("#left-bar .bar-list").hover(function(){
			top = $(this).position().top;
			$(this).css("background-color","#FFAD00");
			$('#clear-list').css("top",top).show();
		},function(){
			$(this).css("background-color","#2D2D2D");
			$('#clear-list').hide();
		});
		$('#clear-list').hover(function(){
			$('#clear-list').css("top",top).show();
		},function(){
			$('#clear-list').hide();
		});
		$('#clear-list .list-entry').hover(function(){
			$(this).css("opacity","0.9");
		},function(){
			$(this).css("opacity","0.8");
		});
	}

	  module.exports = leftBarAction;

});
