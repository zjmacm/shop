Ext.define('Seller.view.index.East',{
    extend: 'Ext.panel.Panel',
    alias: 'widget.indexeast',
    frame: false,
    layout: 'fit',
    /*layoutConfig: {
        animate: true
    },*/
    /*width: 90,
    minWidth: 90,
    split: true,*/
    initComponent: function() {
        Ext.apply(this,{
            title: '消息通知',
            collapsed: true,
            collapsible: true,
            layout: 'vbox',
            items: [{
                xtype: 'label',
                text: '你有新通知',
                id: 'newNotificationLabel'
            },{
                xtype: 'button',
                text: '查看通知',
                handler: function() {
                    Ext.Msg.alert("now ,this function...");
                }
            }]
        });

        this.callParent(arguments);
    }
});