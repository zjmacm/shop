define(function(require, exports, module) {
	
	var header = $('#header');
	var top = $(document).scrollTop();
	var flag = new Array( false, false, false );
	var n = 0;

	function headerAction()
	{
		changeStyle();
		$(document).scroll(function(){
			top = $(document).scrollTop();
			changeStyle();
		});
	}

	function changeStyle()
	{
		if( top > 85 )
			flag[n%2] = true;
		else
			flag[n%2] = false;
		n++;
		if( flag[0] != flag[1] ){
			if( flag[2] ){//hide hide-head
				$('.hide-head').hide();
				// $('.search').animate( {left: "+=80px", top: "+=20px"}, 0 );
				// $('#header').animate( {height: "150px"}, 0 ).removeClass("shadow");
				flag[2] = false;
			}
			else{//show hide-head
				// $('#header').animate( {height: "50px"}, 0 ).addClass("shadow");
				// $('.search').animate( {left: "-=80px", top: "-=20px"}, 0 );
				$('.hide-head').show();
				flag[2] = true;
			}
		}
	}

	  module.exports = headerAction;
});