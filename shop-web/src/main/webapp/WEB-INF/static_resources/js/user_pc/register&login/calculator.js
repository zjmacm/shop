define(function(require, exports, module) {

  var $ = require('jquery');

  function calculator() {
		var s = ""; 
		s += " ��ҳ�ɼ������"+ document.body.clientWidth; 
		s += " ��ҳ�ɼ�����ߣ�"+ document.body.clientHeight; 
		s += " ��ҳ�ɼ������"+ document.body.offsetWidth + " (�������ߺ͹������Ŀ�)"; 
		s += " ��ҳ�ɼ�����ߣ�"+ document.body.offsetHeight + " (�������ߵĿ�)"; 
		s += " ��ҳ����ȫ�Ŀ�"+ document.body.scrollWidth; 
		s += " ��ҳ����ȫ�ĸߣ�"+ document.body.scrollHeight; 
		s += " ��ҳ����ȥ�ĸ�(ff)��"+ document.body.scrollTop; 
		s += " ��ҳ����ȥ�ĸ�(ie)��"+ document.documentElement.scrollTop; 
		s += " ��ҳ����ȥ����"+ document.body.scrollLeft; 
		s += " ��ҳ���Ĳ����ϣ�"+ window.screenTop; 
		s += " ��ҳ���Ĳ�����"+ window.screenLeft; 
		s += " ��Ļ�ֱ��ʵĸߣ�"+ window.screen.height; 
		s += " ��Ļ�ֱ��ʵĿ�"+ window.screen.width; 
		s += " ��Ļ���ù������߶ȣ�"+ window.screen.availHeight; 
		s += " ��Ļ���ù�������ȣ�"+ window.screen.availWidth; 
		s += " �����Ļ������ "+ window.screen.colorDepth +" λ��ɫ"; 
		s += " �����Ļ���� "+ window.screen.deviceXDPI +" ����/Ӣ��"; 
		alert (s); 
  }

  module.exports = calculator;



});

