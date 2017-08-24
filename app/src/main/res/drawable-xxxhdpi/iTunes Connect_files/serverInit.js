'use strict';


define([], function() {
    window.localeCode = (function() {
        var valid_codes = new RegExp(['da','de-de','el','en-au','en-ca','en-gb','en-us','es-es','es-mx','fi-fi','fr-ca','fr-fr','id-id','it-it','ja-jp','ko-kp','ko-kr','ms-latn-my','nl-nl','no-no','pt-br','pt-pt','ru-ru','sv-se','th-th','tr-tr','vi-vn','zh-cn','zh-tw'].join('|'), 'i');
        var code = "ko-kr";
        if (code === '' || !valid_codes.test(code)) code = 'en-us';
        return code;
    })();
    
    window.isProd = true;
    
    
    window.getGlobalPath = _sb_getHashFilename;

    return {
        initRequireModules: [
            'sbl!app',
            'bootstrapItcFooter', //THIS LINE MUST BE SECOND IN THE LIST - DO NOT REORDER
            
            
            
            'sbl!/itc/js/ng-app/services/prerelease_services.js',
            'sbl!/itc/js/ng-app/directives/app_iap_directives.js',
            'sbl!/itc/js/ng-app/directives/app_directives.js',
            'sbl!/itc/js/ng-app/filters/app_filters.js',
            'sbl!/itc/js/ng-app/directives/build_directives.js',
            'sbl!/itc/bower_components/itc-rankgrouper/dist/itc-rankgrouper.min.js',
            'sbl!/itc/js/ng-app/directives/drop_directive.js',
            'sbl!/itc/js/ng-app/directives/snapshot_directive.js',
            'sbl!/itc/js/ng-app/directives/media_image_directive.js',
            
            'sbl!/itc/js/ng-app/directives/media_image_directive2.js',
            'sbl!/itc/js/ng-app/directives/media_stack_directive2.js',
            'sbl!/itc/js/ng-app/directives/video_snapshot_grabber2.js',
            'sbl!/itc/js/ng-app/directives/media_image_slideshow_directive2.js',
            
            
            'sbl!/itc/js/ng-app/directives/media_stack_directive.js',
            'sbl!/itc/js/ng-app/directives/media_image_slideshow_directive.js',
            'sbl!/itc/js/ng-app/directives/centering_directives.js',
            'sbl!/itc/js/ng-app/directives/menu_item_pill_directive.js',
            'sbl!/itc/js/ng-app/directives/image_slideshow_directive.js',
            'sbl!/itc/js/ng-app/directives/lsr_slideshow_directive.js',
            'sbl!/itc/js/ng-app/directives/simple_image_directive.js',
            'sbl!/itc/js/ng-app/directives/simple_drop_directive.js',
            'sbl!/itc/js/ng-app/directives/simple_image_drop_combo_directive.js',
            'sbl!/itc/js/ng-app/directives/simple_fileicon_directive.js',
            'sbl!/itc/js/ng-app/directives/simple_filedrop_directive.js',
            'sbl!/itc/js/ng-app/directives/simple_file_drop_combo_directive.js',
            'sbl!/itc/js/ng-app/directives/snapshot_errors_directive.js',
            'sbl!/itc/js/ng-app/directives/res_center_thread_summary.js',
            'sbl!/itc/js/ng-app/directives/prerelease_directives.js',
            
            'sbl!/itc/js/ng-app/locales/angular-locale_'+window.localeCode+'.js',
            'sbl!/itc/js/ng-app/directives/form_elements.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/directives/global_directives.js', //not tied to itcApp
            // 'sbl!/itc/js/ng-app/services/logger.js',
            'sbl!/itc/js/ng-app/services/uiRouteResolver.js',
            'sbl!/itc/js/ng-app/services/itc_services.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/services/global_services.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/filters/global_filters.js', //not tied to itcApp
            // 'sbl!/itc/js/ng-app/lib/stacktrace.js',
            'sbl!/itc/js/ng-app/lib/angular-file-upload2/angular-file-upload-shim.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/lib/angular-file-upload2/angular-file-upload.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/lib/ai-imageservice.min.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/lib/angular-wizard.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/lib/angular-file-upload2/upload.js', //not tied to itcApp
            'sbl!/itc/js/lib/angular-animate.js', //not tied to itcApp
            'sbl!/itc/js/ng-app/controllers/page-wrapper_cntrl.js',
            'sbl!/itc/js/ng-app/services/app_services.js', //used in pagewrapper - must load
            
            'sbl!/itc/js/ng-app/services/iap_merch_services.js',
            
            
            'sbl!/itc/js/ng-app/services/r2d2_services.js',
            
            
            'sbl!/itc/js/ng-app/services/phased_release_services.js',
            
            'sbl!/itc/js/ng-app/directives/homepage_directives.js',
            'sbl!/itc/js/ng-app/bindonce.js',
            'sbl!/itc/js/ng-app/directives/ui-sortable.js',
            'sbl!/itc/js/ng-app/filters/angular-filter.js',
            'sbl!/itc/js/ng-app/services/manage_users_services.js',
            'sbl!/itc/js/ng-app/directives/ng-infinite-scroll.min.js',
            'sbl!/itc/js/ng-app/utilities.js',
            'sbl!/itc/js/ng-app/lib/angulartics.js',       // Angular analytics library
            'sbl!/itc/js/ng-app/lib/angulartics-adobe.js', // Plugin for Omniture
            'sbl!/itc/js/lib/angular-sanitize.js',
            'sbl!/itc/js/ng-app/services/localStorageService.js',
            'sbl!/itc/js/ng-app/services/localizeService.js',
            'sbl!/itc/js/ng-app/services/simpleDateService.js',
            'sbl!/itc/js/ng-app/directives/appClickaway.js',
            'sbl!/itc/js/ng-app/directives/appPopover.js',
            'sbl!/itc/js/ng-app/directives/simpleDatePicker.js',
            'sbl!/itc/js/ng-app/services/univ_purchase_services.js',
            'sbl!/itc/bower_components/itc-app-switcher/build/itc-app-switcher.js',
            'sbl!/itc/bower_components/its-angular-top-nav/dist/its-angular-top-nav.min.js',
            'sbl!/itc/bower_components/its-angular-logger/dist/its-angular-logger.min.js',
            'sbl!/itc/bower_components/itc-timings/lib/itc-timings.js',
            'sbl!/itc/bower_components/tidbits-modal/dist/tidbits-modal.min.js',
            'sbl!/itc/bower_components/tf/dist/tf.tidbits.min.js',
            'sbl!/itc/js/ng-app/ng-text-truncate.js',
            
        ],

        initUrlRouterProvider: function($urlRouterProvider) {
            
            $urlRouterProvider.when('/resources_page',function($injector, $location)  {
                window.location = 'https://itunespartner.apple.com/';
            });
            
        },

        initAngularRequires: function(angularRequires) {
            
            
            angularRequires.push('itc-rank-grouper');
            
            
            angularRequires.push('tf');
            
            
        },

        initLogger: function(logger) {
            logger.enabled = true;
        },

        initStateProvider: function($stateProvider, route) {

        $stateProvider
            .state('home', {
                url: "/",
                templateUrl: _sb_getHashFilename('/itc/views/shared/homepage.html'),
                controller: 'homepageController',
                resolve: route.resolve(
                    'homepage_cntrl',
                    { trackingConfig: { pageName: 'iTC Home Redesign', channel: 'iTC Main' } }
                )
                
                , onEnter: ["$window", function($window) {
                    $window.location.href = "/";
                }]
                
            })
            .state('contract_interstital',{
                url: '/contractinfo',
                templateUrl: _sb_getHashFilename('/itc/views/shared/contractinterstitial.html'),
                controller: 'contractInterstitialController',
                resolve: route.resolve(
                    'contract_interstitial_cntrl',
                    { trackingConfig: { pageName: 'iTC Contract Info', channel: 'iTC Main', hier5: '' } }
                )
            })
            .state('news_request',{
                url: '/news_request',
                templateUrl: _sb_getHashFilename('/itc/views/shared/newsRequest.html'),
                controller: 'newsRequestController',
                resolve: route.resolve(
                    'news_request_cntrl',
                    { trackingConfig: { pageName: 'iTC News Request Info', channel: 'iTC Main', hier5: '' } }
                )
            })
            .state('provider_chooser_interstital',{
                url: '/login_chooser',
                templateUrl: _sb_getHashFilename('/itc/views/shared/provider_chooser_interstitial.html'),
                controller: 'providerChooserController',
                resolve: route.resolve(
                    'provider_chooser_cntrl',
                    { trackingConfig: { pageName: 'iTC Provider Chooser', channel: 'iTC Main', hier5: '' } }
                )
            })
            .state('account_activated',{
                url: '/accountActivated',
                templateUrl: _sb_getHashFilename('/itc/views/shared/accountActivated.html'),
                controller: 'accountActivatedController',
                resolve: route.resolve(
                    'account_activated_cntrl',
                    { trackingConfig: { pageName: 'iTC Account Activated', channel: 'iTC Main', hier5: '' } }
                )
            })
            .state('switch',{
                url: '/switch/:newproviderid',
                templateUrl: _sb_getHashFilename('/itc/views/shared/switchProvider.html'),
                controller: 'switchProviderController',
                resolve: route.resolve(
                    'switch_provider_cntrl',
                    { trackingConfig: { pageName: 'iTC Provider Switch Control', channel: 'iTC Main', hier5: '' } }
                )
            })

            


            .state('prerelese_builds',{
                url:'/app/:adamId/pre/builds',
                templateUrl: _sb_getHashFilename('/itc/views/prerelease/builds.html'),
                controller: 'prereleaseBuildsPageController',
                resolve: route.resolve(
                    [ 'prerelease_builds_cntrl','app/app_header_cntrl','prerelease/submit_for_beta_review_cntrl' ],
                    { trackingConfig: { pageName: 'Prerelease - Builds', channel: 'Manage Apps', hier5: '01' } }
                )
            })
            .state('build_details',{
                url: '/app/:adamId/platforms/:platform/trains/:trainVersion/build/:buildVersion',
                templateUrl: _sb_getHashFilename('/itc/views/build/index.html'),
                controller: 'prereleaseBuildDetailsPageController',
                resolve: route.resolve([
                        'build/build_main_cntrl',
                        'build/build_test_information_cntrl',
                        'build/build_details_cntrl',
                        'build/export_compliance_cntrl',
                        'build/build_testers_cntrl',
                        'build/modal_displayProcessedFileSizes_cntrl'
                    ],
                    { trackingConfig: { pageName: 'Prerelease - Build Details', channel: 'Manage Apps', hier5: '01' } }
                )
            })


            .state('prerelease_internal_testers',{
                url: '/app/:adamId/pre/testers/internal',
                templateUrl: _sb_getHashFilename('/itc/views/prerelease/internal_testers.html'),
                controller: 'prereleaseInternalTestersPageController',
                resolve: route.resolve(
                    ['prerelease_internal_testers_cntrl','app/app_header_cntrl'],
                    { trackingConfig: { pageName: 'Prerelease - Internal Testers', channel: 'Manage Apps', hier5: '01' } }
                )
            })
            .state('prerelease_beta_testers',{
                url: '/app/:adamId/pre/testers/beta',
                templateUrl: _sb_getHashFilename('/itc/views/prerelease/beta_testers.html'),
                controller: 'prereleaseBetaTestersPageController',
                resolve: route.resolve(
                    ['prerelease_beta_testers_cntrl','app/app_header_cntrl'],
                    { trackingConfig: { pageName: 'Prerelease - External Testers', channel: 'Manage Apps', hier5: '01' } }
                )
            })
            .state('prerelease_add_beta_testers',{
                url: '/app/:adamId/pre/testers/beta/addTesters',
                templateUrl: _sb_getHashFilename('/itc/views/prerelease/add_new_beta_testers.html'),
                controller: 'addNewBetaTestersPageController',
                resolve: route.resolve(
                    'add_new_beta_testers_cntrl',
                    {
                        trackingConfig: { pageName: 'Prerelease - Add New Beta Testers', channel: 'Manage Apps', hier5: '01' },
                        pageConfig: { context: 'app', returnLink: '/app/{adamId}/pre/testers/beta' }
                    }
                )
            })

            .state('prerelease_add_existing_beta_testers',{
                url: '/app/:adamId/pre/testers/beta/addExistingTesters',
                templateUrl: _sb_getHashFilename('/itc/views/prerelease/add_existing_beta_testers.html'),
                controller: 'addExistingBetaTestersPageController',
                resolve: route.resolve(
                    'add_existing_beta_testers_cntrl',
                    { trackingConfig: { pageName: 'Prerelease - Add Existing Beta Testers', channel: 'Manage Apps', hier5: '01' } }
                )
            })
            .state('prerelease_export_compliance',{
                url: '/app/:adamId/pre/trains/:trainVersion/build/:buildVersion/export',
                templateUrl: _sb_getHashFilename('/itc/views/prerelease/build_export_compliance.html'),
                controller: 'buildExportCompliancePageController',
                resolve: route.resolve(
                    'prerelease_build_export_cntrl',
                    { trackingConfig: { pageName: 'Prerelease - Export Compliance', channel: 'Manage Apps', hier5: '01' } }
                )
            })


                .state('bundle_resolution_center',{
                    url: '/bundle/:adamId/resolutioncenter',
                    templateUrl: _sb_getHashFilename('/itc/views/app/res_center.html'),
                    controller: 'resCenterController',
                    resolve: route.resolve(
                        'app/res_center_ctrl',
                        { trackingConfig: { pageName: 'Resolution Center', channel: 'Manage Apps', hier5: '01' } }
                    ),
                    params: { simpleHeader: true, appOrBundle: 'bundle' }
                })
                .state('bundle_resolution_center_ver',{
                    url: '/bundle/:adamId/resolutioncenter/:ver',
                    templateUrl: _sb_getHashFilename('/itc/views/app/res_center.html'),
                    controller: 'resCenterController',
                    resolve: route.resolve(
                        'app/res_center_ctrl',
                        { trackingConfig: { pageName: 'Resolution Center', channel: 'Manage Apps', hier5: '01' } }
                    ),
                    params: { simpleHeader: true, appOrBundle: 'bundle' }
                })


            

             /**/

                    .state('app_overview',{
                        url: '/app/:adamId',
                        templateUrl: _sb_getHashFilename('/itc/views/app/app_overview.html'),
                        controller: 'appOverviewController',
                        abstract:true,
                        resolve: route.resolve(
                            'app/app_overview_cntrl'
                        )
              })

                        
                        .state('app_overview.mediaManager',{
                            url: '/:platform/mediaManager',
                            templateUrl: _sb_getHashFilename('/itc/views/app/media_manager.html'),
                            controller: 'mediaManagerController',
                            resolve: route.resolve([
                                    'app/media_manager_cntrl'
                                ],{
                                    trackingConfig: { pageName: 'Media Manager', channel: 'Manage Apps', hier5: '01' },
                                    //useDefaultPath: false
                                }
                            )
                        })
                        

                        .state('app_overview.resolution_center',{
                            url: '/platform/:platform/resolutioncenter',
                            templateUrl: _sb_getHashFilename('/itc/views/app/res_center.html'),
                            controller: 'resCenterController',
                            resolve: route.resolve(
                                'app/res_center_ctrl',
                                { trackingConfig: { pageName: 'Resolution Center', channel: 'Manage Apps', hier5: '01' } }
                            ),
                            params: { appOrBundle: 'app' }
                        })
                        .state('app_overview.resolution_center_ver',{
                            url: '/platform/:platform/versions/:ver/resolutioncenter',
                            templateUrl: _sb_getHashFilename('/itc/views/app/res_center.html'),
                            controller: 'resCenterController',
                            resolve: route.resolve(
                                'app/res_center_ctrl',
                                { trackingConfig: { pageName: 'Resolution Center', channel: 'Manage Apps', hier5: '01' } }
                            ),
                            params: { appOrBundle: 'app' }
                        })

                        .state('app_overview.store',{
                            url: '',
                            templateUrl: _sb_getHashFilename('/itc/views/app/app_store_nav.html'),
                            controller: 'appStoreNavController',
                            resolve: route.resolve(
                                'app/app_store_nav_cntrl'
                            ),
                            abstract: true
                        })
                            .state('app_overview.store.appinfo',{
                                url: '', //inherits URL from parent
                                templateUrl: _sb_getHashFilename('/itc/views/app/app_info.html'),
                                controller: 'appInfoController',
                                resolve: route.resolve(
                                    'app/app_info_cntrl',
                                    {
                                        trackingConfig: { pageName: 'App Info', channel: 'Manage Apps', hier5: '01' }
                                    }
                                )
                            })
                            .state('app_overview.store.appinfo.errorstate',{
                                url: '/errors', //inherits URL from parent
                                templateUrl: _sb_getHashFilename('/itc/views/app/app_info.html'),
                                controller: 'appInfoController',
                                reloadOnSearch : false,
                                resolve: route.resolve(
                                    'app/app_info_cntrl',
                                    {
                                        trackingConfig: { pageName: 'App Info - Error State', channel: 'Manage Apps', hier5: '01' }
                                    }
                                )
                        })


                            
                                .state('app_overview.store.versioninfo',{
                                    url: '/:platform/versioninfo',
                                    templateUrl: _sb_getHashFilename('/itc/views/app/app_version_univ.html'),
                                    controller: 'appVersionInfoController',
                                    resolve: route.resolve([
                                            '/itc/js/ng-app/controllers/app/app_version_univ_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/submit_for_review_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/media_manager_cntrl.js',
                                            '/itc/js/ng-app/controllers/build/modal_displayProcessedFileSizes_cntrl.js',
                                            global_itc_path + '/ra/apps/views/ssController',
                                            global_itc_path + '/ra/apps/views/vgController',
                                            
                                            '/itc/js/ng-app/controllers/app/phased_release_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/phased_release_pricing_cntrl.js',
                                            
                                        ],{
                                            trackingConfig: { pageName: 'App Version', channel: 'Manage Apps', hier5: '01' },
                                            useDefaultPath: false
                                        }
                                    )
                                })
                                .state('app_overview.store.versioninfo_deliverable',{
                                    url: '/:platform/versioninfo/deliverable',
                                    templateUrl: _sb_getHashFilename('/itc/views/app/app_version_univ.html'),
                                    controller: 'appVersionInfoController',
                                    resolve: route.resolve([
                                            '/itc/js/ng-app/controllers/app/app_version_univ_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/submit_for_review_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/media_manager_cntrl.js',
                                            '/itc/js/ng-app/controllers/build/modal_displayProcessedFileSizes_cntrl.js',
                                            global_itc_path + '/ra/apps/views/ssController',
                                            global_itc_path + '/ra/apps/views/vgController',
                                            
                                            '/itc/js/ng-app/controllers/app/phased_release_cntrl.js',
                                            
                                        ],{
                                            trackingConfig: { pageName: 'App Version - deliverable', channel: 'Manage Apps', hier5: '01' },
                                            useDefaultPath: false
                                        }
                                    )
                                })

                            
                            

                            .state('app_overview.store.pricing',{
                                url: '/pricing', //inherits URL from parent
                                templateUrl: _sb_getHashFilename('/itc/views/app/pricing.html'),
                                controller: 'pricingController',
                                resolve: route.resolve(
                                    'app/pricing_cntrl',
                                    { trackingConfig: { pageName: 'App Pricing', channel: 'Manage Apps', hier5: '01' } }
                                )
                            })
                            .state('app_overview.store.promoart',{
                                url: '/promoart', //inherits URL from parent
                                templateUrl: _sb_getHashFilename('/itc/views/app/promoart.html'),
                                controller: 'promoartController',
                                resolve: route.resolve(
                                    'app/promoart_cntrl',
                                    { trackingConfig: { pageName: 'App Promo Art', channel: 'Manage Apps', hier5: '01' } }
                                )
                            })
                        
                        // App: TestFlight
                        .state( 'app_overview.testflight', {
                            url: '/testflight',
                            templateUrl: _sb_getHashFilename('/itc/views/prerelease/multiTrain.html'),
                            controller: 'testflightOverviewController',
                            reloadOnSearch: false,
                            resolve: route.resolve('prerelease/testflight_multitrain'),
                            abstract:true,
                            onEnter: function(tfInitService) {
                                
                            }
                        })
                        .state('app_overview.testflight.testInformation', {
                            url: '',
                            pageName: 'TestFlight'
                        })
                        
                        
                        
                        
                            //App: Features Tab
                            .state( 'app_overview.features', {
                                url: '',
                                templateUrl: global_itc_path + '/ra/apps/views/addOnMerchandisingFeatureNav',
                                controller: 'featuresNavController', // make a new controller, etc.
                                resolve: route.resolve('app/features_nav_cntrl'),
                                abstract:true
                            })
                        
                            // Newsstand
                            .state( 'app_overview.features.newsstand', {
                                url: '/newsstand',
                                templateUrl: _sb_getHashFilename('/itc/views/app/newsstand.html'),
                                controller: 'newsstandController',
                                resolve: route.resolve('app/newsstand_cntrl', { trackingConfig: { pageName: 'Features - Newsstand', channel: 'Manage Apps', hier5: '01' } })
                            })
                            .state( 'app_overview.features.newsstand_previousissues', {
                                url: '/newsstand/previous',
                                templateUrl: _sb_getHashFilename('/itc/views/app/newsstand_previous.html'),
                                controller: 'newsstandPreviousController',
                                resolve: route.resolve('app/newsstand_previous_cntrl', { trackingConfig: { pageName: 'Features - Newsstand - Previous Issues', channel: 'Manage Apps', hier5: '01' } })
                            })
                            // Game Center
                            .state( 'app_overview.features.game_center', {
                                url: '/gamecenter',
                                templateUrl: _sb_getHashFilename('/itc/views/app/game_center.html'),
                                controller: 'gameCenterController',
                                resolve: route.resolve('app/game_center_cntrl', { trackingConfig: { pageName: 'Features - Game Center', channel: 'Manage Apps', hier5: '01' } })
                            })

                            // In App Purchase
                            .state( 'app_overview.features.addons', {
                                url: '/addons',
                                templateUrl: _sb_getHashFilename('/itc/views/app/iap.html'),
                                controller: 'iapController',
                                resolve: route.resolve([
                                        'app/iap_cntrl',
                                        'app/iap_createNewSub_cntrl'
                                    ], {
                                        trackingConfig: { pageName: 'Features - IAP', channel: 'Manage Apps', hier5: '01' }
                                })
                            })
                                
                                
                                    .state( 'app_overview.features.promotions', {
                                        url: '/addons/promotions',
                                        templateUrl: _sb_getHashFilename('/itc/views/app/iap_promotions.html'),
                                        controller: 'iapPromotionsController',
                                        resolve: route.resolve(['app/iap_promotions_cntrl'], { trackingConfig: { pageName: 'Features - IAP - Promotions', channel: 'Manage Apps', hier5: '01' } })
                                    })
                                    .state( 'app_overview.features.addons_create', {
                                        url: '/addons/create/:addonType',
                                        templateUrl: global_itc_path + '/ra/apps/views/addOnMerchandisingHtml',
                                        controller: 'iapDetailsController',
                                        resolve: route.resolve([
                                            global_itc_path + '/ra/apps/controllers/addOnMerchandisingController',
                                            '/itc/js/ng-app/controllers/app/iap_sub_pricing_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/iap_sub_addpricing_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/iap_sub_editpricing_cntrl.js'], { trackingConfig: { pageName: 'Features - IAP - Create', channel: 'Manage Apps', hier5: '01' }, useDefaultPath: false })
                                    })
                                    .state( 'app_overview.features.addons_detail', {
                                        url: '/addons/:iapAdamId',
                                        templateUrl: global_itc_path + '/ra/apps/views/addOnMerchandisingHtml',
                                        controller: 'iapDetailsController',
                                        resolve: route.resolve([
                                            global_itc_path + '/ra/apps/controllers/addOnMerchandisingController',
                                            '/itc/js/ng-app/controllers/app/iap_sub_pricing_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/iap_sub_addpricing_cntrl.js',
                                            '/itc/js/ng-app/controllers/app/iap_sub_editpricing_cntrl.js'], { trackingConfig: { pageName: 'Features - IAP - Edit', channel: 'Manage Apps', hier5: '01' }, useDefaultPath: false })
                                    })
                                

                                .state( 'app_overview.features.addon_family_detail', {
                                    url: '/addongroups/:familyId',
                                    templateUrl: _sb_getHashFilename('/itc/views/app/family_details.html'),
                                    controller: 'familyDetailsController',
                                    resolve: route.resolve([
                                        'app/family_details_cntrl',
                                        'app/iap_createNewSub_cntrl'
                                    ], {
                                        trackingConfig: { pageName: 'Features - IAP - Family Details', channel: 'Manage Apps', hier5: '01' }
                                    })
                                })
                                .state( 'app_overview.features.addon_families', {
                                    url: '/addongroups',
                                    templateUrl: _sb_getHashFilename('/itc/views/app/groups.html'),
                                    controller: 'groupsController',
                                    resolve: route.resolve('app/groups_cntrl', { trackingConfig: { pageName: 'Features - IAP - Groups List', channel: 'Manage Apps', hier5: '01' } })
                                })
                                // IAP pricing matrix
                                .state('iap_pricing_matrix',{
                                    url: '/app/:adamId/pricingMatrix/:iapType',
                                    templateUrl: _sb_getHashFilename('/itc/views/app/pricingMatrix.html'),
                                    controller: 'pricingMatrixController',
                                    resolve: route.resolve('app/pricing_matrix_cntrl', { trackingConfig: { pageName: 'App Addon Pricing Matrix', channel: 'Manage Apps', hier5: '01' } })
                                })

                            // Encryption
                            .state( 'app_overview.features.encryption', {
                                url: '/encryption',
                                templateUrl: _sb_getHashFilename('/itc/views/app/encryption.html'),
                                controller: 'encryptionController',
                                resolve: route.resolve('app/encryption_cntrl', { trackingConfig: { pageName: 'Features - Encryption', channel: 'Manage Apps', hier5: '01' } })
                            })

                            .state( 'app_overview.features.promoCodes', {
                                url: '/promo_codes',
                                templateUrl: _sb_getHashFilename('/itc/views/app/promoCodes/promoCode.html'),
                                controller: 'promoCodeController',
                                resolve: route.resolve(['app/promoCode_cntrl','app/promoCodes_modal_displayCodes']),
                                abstract: true
                            })
                                .state( 'app_overview.features.promoCodes.generate', {
                                    url: '/generate',
                                    templateUrl: _sb_getHashFilename('/itc/views/app/promoCodes/promoCode_generate.html'),
                                    controller: 'promoCodeGenerateController',
                                    resolve: route.resolve('app/promoCode_generate_cntrl', { trackingConfig: { pageName: 'Features - PromoCodes - Generate Codes', channel: 'Manage Apps', hier5: '01' } })
                                })
                                .state( 'app_overview.features.promoCodes.history', {
                                    url: '/history',
                                    templateUrl: _sb_getHashFilename('/itc/views/app/promoCodes/promoCode_history.html'),
                                    controller: 'promoCodeHistoryController',
                                    resolve: route.resolve('app/promoCode_history_cntrl', { trackingConfig: { pageName: 'Features - PromoCodes - History', channel: 'Manage Apps', hier5: '01' } })
                                })

                        .state( 'app_overview.ratingsResponses', {
                            url: '/:platform/ratingsResponses?reviewId',
                            templateUrl: _sb_getHashFilename('/itc/views/app/activity/activity_ratings_r2d2.html'),
                            controller: 'ActivityRatingsResponsesController',
                            resolve: route.resolve('app/activity/activity_ratings_r2d2_cntrl', { trackingConfig: { pageName: 'Activity - Ratings Responses', channel: 'Manage Apps', hier5: '01' } })
                        })
                        .state( 'app_overview.ratings', {
                            url: '/:platform/ratings',
                            templateUrl: _sb_getHashFilename('/itc/views/app/activity/activity_ratings.html'),
                            controller: 'ActivityRatingsController',
                            resolve: route.resolve('app/activity/activity_ratings_cntrl', { trackingConfig: { pageName: 'Activity - Ratings', channel: 'Manage Apps', hier5: '01' } })
                        })

                        // App: Activity tab
                        .state( 'app_overview.activity', {
                            url: '/activity',
                            templateUrl: _sb_getHashFilename('/itc/views/app/activity/activity.html'),
                            controller: 'activityPageController',
                            resolve: route.resolve('app/activity/activity_cntrl'),
                            abstract:true
                        })

                            // Versions (state) history
                            .state( 'app_overview.activity.versions', {
                                url: '/:platform/versions',
                                templateUrl: _sb_getHashFilename('/itc/views/app/activity/activity_versions.html'),
                                controller: 'ActivityVersionHistoryPageController',
                                resolve: route.resolve('app/activity/activity_versions_cntrl', { trackingConfig: { pageName: 'Activity - App Store Versions', channel: 'Manage Apps', hier5: '01' } })
                            })
                            // Build history
                            .state( 'app_overview.activity.builds', {
                                url: '/:platform/builds',
                                templateUrl: _sb_getHashFilename('/itc/views/app/activity/activity_builds.html'),
                                controller: 'ActivityBuildHistoryPageController',
                                resolve: route.resolve([
                                        'app/activity/activity_builds_cntrl',
                                        'build/modal_displayProcessedFileSizes_cntrl'
                                    ],
                                    { trackingConfig: { pageName: 'Activity - Builds', channel: 'Manage Apps', hier5: '01' } }
                                )
                            })
                            // Ratings and Reviews
                            .state( 'app_overview.activity.ratings', {
                                url: '/:platform/ratings',
                                templateUrl: _sb_getHashFilename('/itc/views/app/activity/activity_ratings.html'),
                                controller: 'ActivityRatingsController',
                                resolve: route.resolve('app/activity/activity_ratings_cntrl', { trackingConfig: { pageName: 'Activity - Ratings', channel: 'Manage Apps', hier5: '01' } })
                            })
                            
                            // Dev Responses (R2D2)
                            .state( 'app_overview.activity.ratingsResponses', {
                                url: '/:platform/ratingsResponses?reviewId',
                                templateUrl: _sb_getHashFilename('/itc/views/app/activity/activity_ratings_r2d2.html'),
                                controller: 'ActivityRatingsResponsesController',
                                resolve: route.resolve('app/activity/activity_ratings_r2d2_cntrl', { trackingConfig: { pageName: 'Activity - Ratings Responses', channel: 'Manage Apps', hier5: '01' } })
                            })
                            

                            // Activity: View Individual Build
                            .state( 'app_overview.activity.buildView', {
                                url: '/:platform/builds/:train/:build',
                                templateUrl: _sb_getHashFilename('/itc/views/prerelease/build/build_view.html'),
                                controller: 'testflightViewBuildController',
                                resolve: route.resolve([
                                    'prerelease/build/build_view_cntrl',
                                    'build/modal_displayProcessedFileSizes_cntrl'
                                ]),
                                abstract: true
                            })
                                // Test Details
                                .state( 'app_overview.activity.buildView.testDetails', {
                                    url: '/testing',
                                    templateUrl: _sb_getHashFilename('/itc/views/prerelease/build/build_test_info.html'),
                                    controller: 'tfBuildTestInfoController',
                                    resolve: route.resolve('prerelease/build/build_test_info_cntrl', { trackingConfig: { pageName: 'Activity - Build Test Information', channel: 'Manage Apps', hier5: '01' } })
                                })
                                // Testers
                                .state( 'app_overview.activity.buildView.testers', {
                                    url: '/testers',
                                    templateUrl: _sb_getHashFilename('/itc/views/prerelease/build/build_testers.html'),
                                    controller: 'tfBuildTestersController',
                                    resolve: route.resolve('prerelease/build/build_testers_cntrl', { trackingConfig: { pageName: 'Activity - Build Testers', channel: 'Manage Apps', hier5: '01' } })
                                })
                                // Build Details
                                .state( 'app_overview.activity.buildView.details', {
                                    url: '/details',
                                    templateUrl: _sb_getHashFilename('/itc/views/prerelease/build/build_details.html'),
                                    controller: 'tfBuildDetailsController',
                                    resolve: route.resolve('prerelease/build/build_details_cntrl', { trackingConfig: { pageName: 'Activity - Build Details', channel: 'Manage Apps', hier5: '01' } })
                                })

                    // pricing matrix
                    .state('pricing_matrix',{
                        url: '/app/:adamId/pricingMatrix',
                        templateUrl: _sb_getHashFilename('/itc/views/app/pricingMatrix.html'),
                        controller: 'pricingMatrixController',
                        resolve: route.resolve('app/pricing_matrix_cntrl', { trackingConfig: { pageName: 'App Pricing Matrix', channel: 'Manage Apps', hier5: '01' } })
                    })
                    // pricing matrix
                    .state('pricing_matrix2',{
                        url: '/pricingMatrix/recurring',
                        templateUrl: _sb_getHashFilename('/itc/views/app/pricingMatrix.html'),
                        controller: 'pricingMatrixController',
                        resolve: route.resolve('app/pricing_matrix_cntrl', { trackingConfig: { pageName: 'App Pricing Matrix 2', channel: 'Manage Apps', hier5: '01' } })
                    })
                    .state('app_ratings',{
                        url: '/app_ratings',
                        templateUrl: _sb_getHashFilename('/itc/views/app/app_ratings.html'),
                        controller: 'appRatingsController',
                        resolve: route.resolve('app/app_ratings_cntrl', { trackingConfig: { pageName: 'App Ratings', channel: 'Manage Apps', hier5: '01' } })
                    })
                    .state('my_apps',{
                        url: '/app',
                        templateUrl: _sb_getHashFilename('/itc/views/app/manage_apps_univ.html'),
                        controller: 'manageAppsControllerUniversal',
                        resolve: route.resolve([
                                'app/manage_apps_univ_cntrl',
                                'app/create_new_app_univ_cntrl'
                            ], {
                                trackingConfig: { pageName: 'Manage Your App Redesign', channel: 'Manage Apps', hier5: '01' }
                            }
                        )
                    })
            //END: IsAppProvider
            

            
                .state('new_bundle',{
                    url: '/bundle',
                    templateUrl: _sb_getHashFilename('/itc/views/app/bundle.html'),
                    controller: 'bundleController',
                    resolve: route.resolve(['app/bundle_cntrl', 'app/app_header_cntrl'], { trackingConfig: { pageName: 'Bundle Redesign', channel: 'Manage Apps', hier5: '01' } })
                })
                .state('bundle',{
                    url: '/bundle/:adamId',
                    templateUrl: _sb_getHashFilename('/itc/views/app/bundle.html'),
                    controller: 'bundleController',
                    resolve: route.resolve(['app/bundle_cntrl', 'app/app_header_cntrl'], { trackingConfig: { pageName: 'Bundle Redesign', channel: 'Manage Apps', hier5: '01' } })
                })
            //END: AppBundlesIsEnabled
            

                
                    .state('users_roles',{
                        url: '/users_roles',
                        templateUrl: _sb_getHashFilename('/itc/views/manageusers/itc_users.html'),
                        controller: 'itcUsersController',
                        resolve: route.resolve('manageusers/itc_users_cntrl', { trackingConfig: { pageName: 'Manage Users - iTunes Connect Users', channel: 'Manage Users' } })
                    })
                    .state('users_roles_new',{
                        url: '/users_roles/new',
                        templateUrl: _sb_getHashFilename('/itc/views/manageusers/createNewItcUser.html'),
                        controller: 'newItcUserController',
                        resolve: route.resolve('manageusers/new_itcUser_cntrl', { trackingConfig: { pageName: 'Manage Users - iTunes Connect Users - New', channel: 'Manage Users' } })
                    })
                    
                        .state('users_roles_internal_testers',{
                            url: '/users_roles/testflight_internal',
                            templateUrl: _sb_getHashFilename('/itc/views/manageusers/testflight_internal.html'),
                            controller: 'testflightInternalController',
                            resolve: route.resolve('manageusers/testflight_internal_cntrl', { trackingConfig: { pageName: 'Manage Users - TestFlight Testers - Internal', channel: 'Manage Users' } })
                        })
                        
                            .state('users_roles_external_testers',{
                                url: '/users_roles/testflight_external',
                                templateUrl: _sb_getHashFilename('/itc/views/manageusers/testflight_external.html'),
                                controller: 'testflightExternalController',
                                resolve: route.resolve('manageusers/testflight_external_cntrl', { trackingConfig: { pageName: 'Manage Users - TestFlight Testers - External', channel: 'Manage Users' } }),
                                onEnter: function($rootScope) {
                                    
                                        $rootScope.tfDl = function(providerId) {
                                            return '<a href=\'/testflight/v1/providers/' + providerId + '/groupsExport\'><span class=\'tb-icon--download\'></span></a>';
                                        };
                                    
                                    
                                }
                            })
                            .state('users_roles_add_external_testers',{
                                url: '/users_roles/testflight_external/addTesters',
                                templateUrl: _sb_getHashFilename('/itc/views/prerelease/add_new_beta_testers.html'),
                                controller: 'addNewBetaTestersPageController',
                                resolve: route.resolve(
                                    'add_new_beta_testers_cntrl',
                                    {
                                        trackingConfig: { pageName: 'Manage Users - TestFlight Testers - External', channel: 'Manage Users' },
                                        pageConfig: { context: 'provider', returnLink: '/users_roles/testflight_external' }
                                    }
                                )
                            })
                        //END: ExternalTestingIsEnabled
                        
                    //END: BetaAllowed
                    

                    
                        .state('users_roles_sandboxusers',{
                            url: '/users_roles/sandbox_users',
                            templateUrl: _sb_getHashFilename('/itc/views/manageusers/sandbox_users.html'),
                            controller: 'sandboxUsersController',
                            resolve: route.resolve(
                                'manageusers/sandbox_users_cntrl', {
                                    trackingConfig: { pageName: 'Manage Users - iTunes Connect Users - Sandbox Users', channel: 'Manage Users' }
                            })
                        })
                        .state('users_roles_new_sandboxuser',{
                            url: '/users_roles/sandbox_users/new',
                            templateUrl: _sb_getHashFilename('/itc/views/manageusers/new_sandbox_user.html'),
                            controller: 'newSandboxUserController',
                            resolve: route.resolve(
                                'manageusers/new_sandbox_user_cntrl', {
                                    trackingConfig: { pageName: 'Manage Users - iTunes Connect Users - New Sandbox User', channel: 'Manage Users' }
                            })
                        })
                    //END: IsAppProvider
                    

                    //WITH USERNAME
                    
                        
                            .state('users_roles_tester_details',{
                                url: '/users_roles/:emailAddress/testerDetails',
                                templateUrl: _sb_getHashFilename('/itc/views/manageusers/edit_tester_details.html'),
                                controller: 'editTesterDetailsController',
                                resolve: route.resolve('manageusers/edit_tester_details_cntrl', { trackingConfig: { pageName: 'Manage Users - Edit External Tester Details', channel: 'Manage Users' } })
                            })
                            .state('users_roles_testerprofile',{
                                url: '/users_roles/:username/tester',
                                templateUrl: _sb_getHashFilename('/itc/views/manageusers/edit_user_testerprofile.html'),
                                controller: 'editUserTesterprofileController',
                                resolve: route.resolve(
                                    'manageusers/edit_user_testerprofile_cntrl',
                                    {
                                        trackingConfig: { pageName: 'Manage Users - Edit Tester Profile', channel: 'Manage Users' },
                                        pageConfig: { context: 'itcuser' } //options itcuser, extTester, extAppTester, intAppTester
                                    }
                                )
                            })
                        //END: ExternalTestingIsEnabled
                        
                    //END: BetaAllowed
                    

                    .state('users_roles_roles',{
                        url: '/users_roles/:username/roles',
                        templateUrl: _sb_getHashFilename('/itc/views/manageusers/edit_user_roles.html'),
                        controller: 'editUserRolesController',
                        resolve: route.resolve('manageusers/edit_user_roles_cntrl', { trackingConfig: { pageName: 'Manage Users - Edit User Roles', channel: 'Manage Users' } })
                    })
                    .state('users_roles_notifications',{
                        url: '/users_roles/:username/notifications',
                        templateUrl: _sb_getHashFilename('/itc/views/manageusers/edit_user_notifications.html'),
                        controller: 'editUserNotificationsController',
                        resolve: route.resolve('manageusers/edit_user_notifications_cntrl', { trackingConfig: { pageName: 'Manage Users - Edit User Notifications', channel: 'Manage Users' } })
                    })
                    

                    

                //END: UserHasReadorReadWriteAccessManageUsers
                
                .state('users_roles_profile',{
                    url: '/users_roles/:username',
                    templateUrl: _sb_getHashFilename('/itc/views/manageusers/edit_user_details.html'),
                    controller: 'editUserDetailsController',
                    resolve: route.resolve('manageusers/edit_user_details_cntrl', { trackingConfig: { pageName: 'Manage Users - Edit User Details', channel: 'Manage Users' } })
                })
                
                    .state('auto_analytics_email_signup',{
                        url: '/analytics_email_signup',
                        templateUrl: _sb_getHashFilename('/itc/views/shared/switchProvider.html'),
                        controller: 'analyticsEmailSignupController',
                        resolve: route.resolve('manageusers/analytics_email_signup_cntrl', { trackingConfig: { pageName: 'Manage Users - Analytics Email Signup', channel: 'Manage Users' } })
                    });
            
        }
    };
});
