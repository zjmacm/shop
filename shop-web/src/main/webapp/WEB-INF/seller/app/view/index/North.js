Ext.define('Seller.view.index.North',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.indexnorth',
    frame: false,
    layout: 'hbox',
    height: 100,
    minHeight: 100,
    split: true,
    collapsible: false,
    layoutConfig: {
        align:'middle'
    },
    defaults:{margins:'0 5 0 0'},
            
    initComponent: function() {
        Ext.apply(this,{
            items:[{
                xtype:'panel',
                width:350,
                height:120,
                border:0,
                //style:'background-image:url(/seller/1.jpg);background-repeat: no-repeat;border:0;',
                html: '<img src="/images/seller/1.jpg"/>',
                readOnly:true
            },{
                xtype:'tbspacer',
                flex:1
            },{
                xtype:'label',
                text: 'welcome yqf'
            },{
                xtype:'tbspacer',
                flex:0.1
            },{
                xtype:'button',
                text: '退出',
                margins:'0'
            }]

        });

        this.callParent(arguments);
    }
});