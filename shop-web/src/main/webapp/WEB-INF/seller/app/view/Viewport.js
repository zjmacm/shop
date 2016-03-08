Ext.define('Seller.view.Viewport', {
    extend: 'Ext.container.Viewport',
    layout:'border',
    id:'viewport',
	requires: [
        'Seller.view.index.North',
        'Seller.view.index.West',
        'Seller.view.index.South',
        'Seller.view.index.Center',
        'Seller.view.index.East'
    ],
    title: "Viewport",
    defaults:{
        bodyStyle: "background-color: #FFFFFF;",
        frame: true
    },
    items: [{
        region: 'north',
        items:[{
            xtype: 'indexnorth'
        }]
    },{
        region: 'center',
        
        items:[{
            xtype: 'indexcenter'
        }]
        
    },{
        title: '功能菜单',
        region: 'west',
        width: 180,
        split: true,
        collapisble: true,
        //这里是修改的部分
        items:[{
            xtype: 'indexwest'
        }]
    },{
        region: 'east',
        items:[{
            xtype: 'indexeast'
        }]
    },{
        region: 'south',
        items:[{
     //       xtype: 'indexsouth'
        }]
        
    }]
  
});
