define(function(require, exports, module) {

  var $ = require('jquery');

  function varPosition() {

        var ch=$(window).height();
		var h=ch/2;
		var cw=$(window).width(); 
		var w=cw/2;
		$("#coverBox").css({'position':'absolute','left':w+'px','margin-left':'-180px'});//类名为center 距离左侧距离
		$("#coverBox").css({'position':'absolute','top':h+'px',"margin-top":"-240px"});//类名为center 距离左侧距离
		$("#bg").css({'height':ch+'px',"width":cw+"px"});
		alert("show");
  }

  module.exports = varPosition;
});

