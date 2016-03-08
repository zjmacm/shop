Ext.Loader.setConfig({enabled: true});
Ext.application({
    name : 'Seller',

     paths : {
         //'Ext': 'app',
         'Ext.ux':'app/ux'
     },
    appFolder: 'app',
    controllers:[
        'MenuCtr',
        'GoodsAddCtr',
        'MessageCtr',
        'OrderCtr',
        //'ShopCtr',
        //'PersonCtr'
        //'MethodCtr'
    ],
    autoCreateViewport: true


});
