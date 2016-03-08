Ext.define('Seller.controller.MenuCtr',{
	extend : 'Ext.app.Controller',
    requires:['Seller.view.goods.GoodsAdd'],
	stores :['MenuTreeStore'],
	models :['MenuModel'],
	views :['index.West'],   //文件夹的写法
	init : function(){
		this.control({
			'indexwest':{
				itemmousedown: this.loadMenu
			}
		});
	},
	loadMenu : function(selModel,record) {
        
		if(record.get('leaf')){
			if(record.get('optype')=='window'){
				var win = Ext.getCmp(record.get('url'));
				if(!win){
					win = Ext.widget(record.get('url'));

				}
				win.show();
			}else{
				var panel = Ext.getCmp(record.get('url'));
                console.log('this is panel:'+panel);
				if(!panel){
                    console.log('id:' +record.get('url'));
					panel = {
						id :record.get('url'),
						title : record.get('text'),
						xtype : record.get('url'),
						closable :true

					};
					this.openTab(panel,record.get('url'));

				}else{
					var main = Ext.getCmp('centerPanel');
					main.setActiveTab(panel);
				}
			}
		}	
	
		
	},
	openTab : function(panel,id) {
		var o = (typeof panel == "string" ? panel : id || panel.id); 
        var main = Ext.getCmp("centerPanel"); 
        var tab = main.getComponent(o);
//        var deflaut = main.getCmp('goodsAddPanel' );
//        alert(deflaut);
//            main.setActiveTab(deflaut);
        if (tab) {
            main.setActiveTab(tab);  
        } else if(typeof panel!="string"){  
            panel.id = o;  
            var p = main.add(panel);  
            main.setActiveTab(p);  
        }


    }  
		
	
});
