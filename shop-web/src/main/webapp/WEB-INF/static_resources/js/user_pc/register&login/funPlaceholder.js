define(function(require, exports, module) {


function hasPlaceholderSupport() {
var input = document.createElement('input');
return ('placeholder' in input);
}
	
	
	
	
	
	
var funPlaceholder = function(element) {
    //����Ƿ���Ҫģ��placeholder
    var placeholder = '';
/*			alert (element);
			alert (!("placeholder" in document.createElement("input")));
			alert ((placeholder = element.getAttribute("placeholder")));*/
    if (element && !("placeholder" in document.createElement("input")) && (placeholder = element.getAttribute("placeholder"))) {
        //��ǰ�ı��ؼ��Ƿ���id, û���򴴽�
        var idLabel = element.id ;
		//alert (idLabel);
        if (!idLabel) {
            idLabel = "placeholder_" + new Date().getTime();
            element.id = idLabel;
        }

        //����labelԪ��
        var eleLabel = document.createElement("label");
        eleLabel.htmlFor = idLabel;
		//label.setAttribute("for","id");
        eleLabel.style.position = "absolute";
        eleLabel.style.textAlign="center";
        //�����ı���ʵ�ʳߴ��޸������marginֵ
        //eleLabel.style.margin = "5px auto 5px auto";
		//eleLabel.style.padding = "0 auto";
        eleLabel.style.color = "graytext";
        eleLabel.style.cursor = "text";
		eleLabel.style.lineHeight="38px";
		//eleLabel.style.paddingLeft=(width-line-width)/2
		//eleLabel.style.align="center";
        //���봴����labelԪ�ؽڵ�
        element.parentNode.insertBefore(eleLabel, element);
        //�¼�
        element.onfocus = function() {
            eleLabel.innerHTML = "";
        };
        element.onblur = function() {
            if (this.value === "") {
                eleLabel.innerHTML = placeholder;  
            }
        };

        //��ʽ��ʼ��
        if (element.value === "") {
            eleLabel.innerHTML = placeholder;   
        }
    }	
};
/*alert("hi");
alert(document.getElementById("email"));
alert("hi");*/

	  module.exports = funPlaceholder;

});