angular.module("HelpApp", [])
    .value('urlBase', 'http://localhost:8080/callcenter/rest/')
    .controller("CallController", function($http, urlBase) {
    
    var self = this;
    self.user = 'Silvio 458 !';
    self.call = undefined;
  
    self.save = function() {
      var method = 'POST';
      if (self.call.id) {
          method = 'PUT'
      }
      console.log(method);
      console.log(urlBase + 'calls/');
      console.log(self.call);
      let headers = {
            'Content-Type': 'application/json'
      };
      let options = { headers: headers };

      $http({
          method: method,
          url: urlBase + 'calls/',
          data: self.call,
          headers: headers
      }).then(function successCallBack(response) {
          self.updateTable();
      }, function errorCallBack(response) {
          console.log(response);
          self.catchError();
      });
  }
  
  self.new = function() {
      self.call = {};
      
  }
  
  calls = [];
  
  self.edit = function(call) {
      self.call = call;
  }
  
  self.delete = function(call) {
      self.call = call;
      $http({method: 'DELETE', url: urlBase + 'calls/' + self.call.id + "/"
      }).then(function successCallback(response) {
          self.updateTable();
      }, function errorCallBack(response) {
          self.catchError();
      });
  }
  
  self.conclude = function(call) {
      self.call = call;
      
      $http({
          method: 'POST',
          url: urlBase + 'calls/' + self.call.id + "/"
      }).then(function successCallback(response) {
          self.updateTable();
      }, function errorCallBack(response) {
          self.catchError();
      });
  }
  
  self.catchError = function() {
      alert("Ocorreu um erro!");
  }
  self.updateTable = function() {
      $http({
          method: 'GET',
          url: urlBase + 'calls/'
      }).then(function successCallback(response) {
          console.log('updateTable: ', response.data);
          self.calls = response.data;
          self.call = undefined;
      });
  }
  self.activate = function() {
      self.updateTable();
  }
  self.activate();
});
