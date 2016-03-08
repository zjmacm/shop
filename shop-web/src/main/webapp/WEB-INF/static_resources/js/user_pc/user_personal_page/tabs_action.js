define(function(require, exports, module) {

	function tabsAction()
	{
		$('.panel').click(function(){
			var p_id = $(this).attr("id");
			var tab = "#tab" + p_id[8];
			var display = $(tab).css("display");
			// alert("你点了"+tab+",display:"+display);
			if( display == "none" ){
				var i = 1;
				while( i < 5 ){
					if($("#tab"+i).css("display") == "block")
						break;
					i++;
				}
				// alert("现在显示的是"+i);
				$("#tab"+i).css("display","none");
				$(tab).css("display","block");
			}
		});
	}

	module.exports = tabsAction;
});