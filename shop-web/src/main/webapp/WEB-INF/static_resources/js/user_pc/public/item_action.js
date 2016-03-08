define(function(require, exports, module) {
	
	function itemAction()
	{
		$(".item").hover(function(){
			$(this).find('.hide-row').show();
		},function(){
			$(this).find('.hide-row').hide();
		});
	}
	
	  module.exports = itemAction;
});