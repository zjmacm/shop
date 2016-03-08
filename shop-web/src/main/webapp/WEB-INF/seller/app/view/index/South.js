Ext.define('Seller.view.index.South',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.indexsouth',
    title: "south",
    layout:"fit",
    frame:false,
    layoutConfig: {
        animate: true
    },
    
    split: true,
    collapsible: false,
    bbar : [ {
        xtype : "label"
        }, '->', 'Version 1.0', 'shop_seller' 
    ],

    initComponent: function() {
        Ext.apply(this,{
            height: 100,
            minHeight: 100,
            collapsible: false,
            border: false,
            bbar : [{xtype: "tbfill"},'->', 'Version 1.0', 'Shop ' ]

        });

      this.callParent(arguments);  
    }

});
