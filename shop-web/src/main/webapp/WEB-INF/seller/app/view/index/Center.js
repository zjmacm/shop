 Ext.define('Seller.view.index.Center',{
    extend: 'Ext.tab.Panel',
    alias: 'widget.indexcenter',
    id: 'centerPanel',
    //autoScroll: true,
    //enableTabScroll: true,
    //collapsed: true,
    //collapsible: true,
    initComponent: function() {	
    var homeTabPanel = Ext.create('Ext.tab.Panel', {
	title : '首页',
	//iconCls : 'House'
	layout: "fit"
    }
    );

   /* var centerPanel = Ext.create('Ext.tab.Panel', {
	    id : 'centerTabPanel',
	    region : 'center',
	    activeTab : 0,
	    defaults : {
		    frame : true,
		    border : 5
	    // margin:10
	    },
	    items : [homeTabPanel]

    });	*/
    Ext.apply(this,{
        activeTab: 0,
        defaults: {
            autoScroll: true,
            autoHeight:true,
            style: "padding:5"
        },
		items : [homeTabPanel]
					
	});

    this.callParent(arguments);
				
	}

		
});
