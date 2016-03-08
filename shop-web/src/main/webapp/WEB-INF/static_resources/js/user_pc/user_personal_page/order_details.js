define(function(require) {

  $(document).ready(function(){
    $('.u-b-r2-c5').click(function(){
      $('.order-details').show();
    });
    $('.o-d-back').click(function(){
    	$('.order-details').hide();
    });
  });

});