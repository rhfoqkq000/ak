define(['sbl!app'], function (itcApp) {

  itcApp.factory('r2d2Services',function($http){
      var service = {

          loadReviewsSummary: function(adamId, selectedPlatform, selectedVersion, selectedStorefront) {
              return $http.get(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews/summary', {cache:false,
                  params: {
                      versionId: selectedVersion,
                      storefront: selectedStorefront
                  }}).then(function (response) {
                      return response.data;
                  }, function(error) { return error; });
              },

          loadReviews: function(adamId, selectedPlatform, selectedVersion, selectedStorefront, selectedRating, selectedReviewType, selectedSort, currentIndex, currentLimit) {
              var args = {};
              if (!_.isUndefined(selectedVersion) && !_.isNull(selectedVersion) && selectedVersion !== 'all') args.versionId = selectedVersion;
              if (!_.isUndefined(selectedStorefront) && !_.isNull(selectedStorefront) && selectedStorefront !== 'all' ) args.storefront = selectedStorefront;
              if (!_.isUndefined(selectedRating) && !_.isNull(selectedRating) && selectedRating !== 'all') args.rating = 'RATING_' + selectedRating;
              if (!_.isUndefined(selectedReviewType) && !_.isNull(selectedReviewType) && selectedReviewType!== 'all') args.review = selectedReviewType;
              if (!_.isUndefined(selectedSort) && !_.isNull(selectedSort) && selectedSort !== 'MOST_RECENT') args.sort = selectedSort;
              if (!_.isUndefined(currentIndex) && !_.isNull(currentIndex)) args.index = currentIndex;
              if (!_.isUndefined(currentLimit) && !_.isNull(currentLimit)) args.limit = currentLimit;

              return $http.get(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews', {cache:false, params: args})
                .then(function (response) {
                  return response.data;
                }, function(error) { return error; });
          },

          loadReviewById: function(adamId, selectedPlatform, reviewId) {
              var args = {};
              args.reviewId = reviewId;

              return $http.get(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews', {cache:false, params: args})
                .then(function (response) {
                  return response.data;
                }, function(error) { return error; });
          },

          loadReviewsRef: function(adamId, selectedPlatform) {
              return $http.get(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews/ref', {cache:false}).then(function (response) {
                  return response.data;
              }, function(error) { return error; });
          },

          createResponse: function(adamId, selectedPlatform, selectedReviewId, body) {
              var payload = {};
              payload.responseText = body;
              payload.reviewId = selectedReviewId;
              return $http.post(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews/' + selectedReviewId + '/responses', payload)
              .then(
                  function (response) { return response.data },
                  function(error) { return error; }
              );
          },

          deleteResponse: function(adamId, selectedPlatform, selectedReviewId, responseId) {
              return $http.delete(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews/' + selectedReviewId + '/responses/' + responseId)
              .then(
                  function (response) { return response.data },
                  function(error) { return error; }
              );
          },

          updateResponse: function(adamId, selectedPlatform, selectedReviewId, responseId, responseText) {
              var body = {};
              body.responseText = responseText;
              return $http.put(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews/' + selectedReviewId + '/responses/' + responseId, body)
              .then(
                  function (response) { return response.data },
                  function(error) { return error; }
              );
          },

          createConcern: function(adamId, selectedPlatform, selectedReviewId, reportConcernData) {
              var body = {};
              body.concernType = reportConcernData.concern;
              body.concernExplanation = reportConcernData.explanation;

              return $http.post(global_itc_path + '/ra/apps/' + adamId + '/platforms/' + selectedPlatform + '/reviews/' + selectedReviewId + '/concerns', body)
              .then(
                  function (response) { return response.data },
                  function(error) { return error; }
              );
          }

      };
      return service;
  });

});
