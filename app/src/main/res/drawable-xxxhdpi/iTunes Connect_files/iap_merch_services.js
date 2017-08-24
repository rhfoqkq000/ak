define(['sbl!app'], function (itcApp) {
	itcApp.factory('iapMerchServices',function($http){
		var myService = {
			getIapsOverview: function(adamId) {
          		return $http.get(global_itc_path + '/ra/apps/' + adamId + '/iaps/overview',{cache:false}).then(function (response) {
					return response.data;
				}, function(error) { return error; });
			},
			getMerchIaps: function(adamId) {
          		return $http.get(global_itc_path + '/ra/apps/' + adamId + '/iaps/merch',{cache:false}).then(function (response) {
					return response.data;
				}, function(error) { return error; });
			},
			postMerchIaps: function(adamId,merchData) {
				return $http.post(global_itc_path + '/ra/apps/' + adamId + '/iaps/merch/',merchData).success(function(response) {
					return response.data;
				}).error(function(data, status) {
					return status;
				});
			}
		};
		return myService;
	});
});
