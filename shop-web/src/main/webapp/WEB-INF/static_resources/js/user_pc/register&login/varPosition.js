define(function(require, exports, module) {

  var $ = require('jquery');

  function varPosition() {

        var ch=$(window).height();
		var h=ch/2;
		var cw=$(window).width(); 
		var w=cw/2;
		$("#coverBox").css({'position':'absolute','left':w+'px','margin-left':'-180px'});//����Ϊcenter ����������
		$("#coverBox").css({'position':'absolute','top':h+'px',"margin-top":"-240px"});//����Ϊcenter ����������
		$("#bg").css({'height':ch+'px',"width":cw+"px"});
		alert("show");
  }

  module.exports = varPosition;
});

