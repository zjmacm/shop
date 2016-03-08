Ext.define('Seller.view.index.West', {
	extend : 'Ext.tree.Panel',
    requires:['Seller.store.MenuTreeStore'],   //异步加载

	border :true,
	alias : 'widget.indexwest', 
	initComponent : function() {
		var westMenuTreeStore = Ext.create('Seller.store.MenuTreeStore');
		var moduleTree = this;
		Ext.apply(this, {
			store : westMenuTreeStore,
			dockedItems : [{
				dock : 'top',
				xtype : 'toolbar',
			    layout : 'column',
				border : false,
				items : [{
					columnWidth : 1 / 3,
					text : '展开',
					handler : function() {
						moduleTree.expandAll();
						}
					},{
						columnWidth : 1 / 3,
						text : '缩起',
						handler : function() {
						moduleTree.collapseAll();
						}
					},{

						text : '刷新',
						handler : function() {
							moduleTree.store.load();
						}
				}]
			}]
		});
	this.callParent(arguments);
	}
});
