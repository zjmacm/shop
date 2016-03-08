function hasPlaceholderSupport() {
var input = document.createElement('input');
return ('placeholder' in input);
}
	
	
	
	
	
	
var funPlaceholder = function(element) {
    //检测是否需要模拟placeholder
    var placeholder = '';
/*			alert (element);
			alert (!("placeholder" in document.createElement("input")));
			alert ((placeholder = element.getAttribute("placeholder")));*/
    if (element && !("placeholder" in document.createElement("input")) && (placeholder = element.getAttribute("placeholder"))) {
        //当前文本控件是否有id, 没有则创建
        var idLabel = element.id ;
		//alert (idLabel);
        if (!idLabel) {
            idLabel = "placeholder_" + new Date().getTime();
            element.id = idLabel;
        }

        //创建label元素
        var eleLabel = document.createElement("label");
        eleLabel.htmlFor = idLabel;
		//label.setAttribute("for","id");
        eleLabel.style.position = "absolute";
        //根据文本框实际尺寸修改这里的margin值
        eleLabel.style.margin = "2px 0 0 3px";
        eleLabel.style.color = "graytext";
        eleLabel.style.cursor = "text";
        //插入创建的label元素节点
        element.parentNode.insertBefore(eleLabel, element);
        //事件
        element.onfocus = function() {
            eleLabel.innerHTML = "";
        };
        element.onblur = function() {
            if (this.value === "") {
                eleLabel.innerHTML = placeholder;  
            }
        };

        //样式初始化
        if (element.value === "") {
            eleLabel.innerHTML = placeholder;   
        }
    }	
};
/*alert("hi");
alert(document.getElementById("email"));
alert("hi");*/
funPlaceholder(document.getElementById("email"));

