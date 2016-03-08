Ext.define('Seller.model.MenuModel', { 
    extend: 'Ext.data.Model', 
    fields: [
        /*{name:"text",type:'string'},
        {name:"test",type:'string'},
        {name:"cls",type:'string'},
        {name:"leaf",type:'boolean',default:true},
        {name:"url",type:'string'},
        {name:"expanded",type:"boolean",default: true}*/
        'test', 'text','cls','columns','url','expanded','optype'
        //'text','test','cls','leaf','url','expanded','children'
    ]
});
