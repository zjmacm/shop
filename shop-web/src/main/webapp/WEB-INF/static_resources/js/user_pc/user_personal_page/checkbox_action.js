define(function(require) {

  $(document).ready(function(){

    var checked_num = 0;
    var sum_price = 0.0;

    $('.select-all').click(function(){
      if($(this).is(':checked')){
        $("input[type=checkbox]").prop("checked",true);
      }
      else{
        $("input[type=checkbox]").prop("checked",false);
      }
    });

    $('.select-shopper').click(function(){
    if($(this).is(':checked')){
      $(this).parents(".shopper").find('input[type=checkbox]').prop("checked",true);
    }
    else{
      $(this).parents(".shopper").find('input[type=checkbox]').prop("checked",false);
    }
    });
    
    $('input[type=checkbox]').click(function(){
      checked_num = $(".select-one-item input:checked").length;
      sumPrice();
      if( ! $(this).is(':checked')){
        $(this).parents(".shopper").find(".select-shopper").prop("checked",false);
        $(".a-s-c1 input").prop("checked",false);
      }
      $(".a-s-item-num").text(checked_num);
    });

    function sumPrice(){
      $(".select-one-item input:checked").each(function(){
        sum_price += parseFloat($(this).parents(".o-i-row").find(".item-price").text());
      });
      $(".a-s-sum-price").text(sum_price);
      sum_price = 0;
    }

  });

});