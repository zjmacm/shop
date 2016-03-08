/**
 * Created by ldz on 19/12/14.
 */

Ext.onReady(function(){
   var window = new Ext.Window({
      title:"人员信息",
      closable:true,
      width:500,
      hegiht:500,
      minimizable:true,
      maximizable:true,
      layout:'fit',
       tools:[{id:'save'},{id:'unpin'},{id:'plus'}],
       items:[{}]
   });
    window.show(this);
});