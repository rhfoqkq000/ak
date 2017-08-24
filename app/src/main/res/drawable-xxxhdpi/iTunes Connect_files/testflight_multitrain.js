define(['sbl!app'], function (itcApp) {
    var testflightOverviewController = function($scope, $q, $rootScope, $stateParams, tfInitService, tfBuildsService, tfLocalizationService, tfUserService) {
        $scope.isLoaded = false;
        window.scope = $scope;
        window.root = $rootScope;
        $scope.$broadcast("TestFlightOverviewLoaded");
        var locWatcher = $scope.$parent.$parent.$watch('l10n', function(data) {
            if (data === undefined || !('ITC.Accept.Button' in data)) return;
            $scope.$parent.$parent.$watch('user', function(user) {
                if (user === undefined) return;
                $scope.$parent.$parent.$parent.$watch('referenceData', function (rData) {
                    if (rData === undefined) return;
                    tfBuildsService.initializeUrlGenerator(rData.imageServiceBaseUrl);
                    tfInitService.setAppProvider($stateParams.adamId, user.contentProviderId);
                    // This is an override to ensure I get the payload for localization proper within TF
                    tfUserService.permissions = user.permittedActivities;
                    tfLocalizationService.keys = data;
                    tfLocalizationService.detailLocales = $scope.$parent.referenceData.detailLocales;
                    $scope.isLoaded = true;
                    if (!$scope.$$phase && !$scope.$root.$$phase) $scope.$apply();
                    locWatcher();
                });
            });
        });
    }
    itcApp.register.controller('testflightOverviewController', [ '$scope', '$q', '$rootScope', '$stateParams', 'tfInitService', 'tfBuildsService', 'tfLocalizationService', 'tfUserService', testflightOverviewController ]);
});