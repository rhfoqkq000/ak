require.config({
    waitSeconds: 0,
    paths: {
        'app': '/itc/js/ng-app/app',
        'sbl': _sb_getHashFilename('/itc/static-resources/sbl.js').replace('.js',''),
        'bootstrapItcFooter': _sb_getHashFilename('/itc/bower_components/itc-footer/dist/itc-footer.angular.min.js').replace('.js',''),
        'itc-header-angular':  _sb_getHashFilename('/itc/bower_components/itc-header/dist/itc-header-angular.all.min.js').replace('.js',''),
    }
});

var thisWoaMatch = window.location.pathname.match(/^\/WebObjects\/([^\/]+)\//);
var thisWoa;
if(thisWoaMatch.length < 2) {
    thisWoa = 'iTunesConnect.woa';
} else {
    thisWoa = thisWoaMatch[1];
}

window.global_itc_path = "/WebObjects/" + thisWoa;
window.global_itc_home_url = global_itc_path + '/ra/ng';
$('base').attr('href', global_itc_home_url + '/');

require.onError = function(err) {
    window.location.href = window.global_itc_path + '/wa/defaultError';
}

require([global_itc_path+'/wa/LCHomePage/serverInit'], function(serverInit) {
    require(serverInit.initRequireModules, function(app,bootstrapItcFooter) {
        bootstrapItcFooter(angular);
        angular.bootstrap(document, [
            'itcApp',
            'ngAnimate',
            'angulartics',
            'mgo-angular-wizard',
            'angulartics.adobe.analytics',
            'vs-repeat',
            'pasvaz.bindonce',
            'angular-cache'
        ]);
    });
});
