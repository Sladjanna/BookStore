var wafepaApp = angular.module('wafepaApp.routes', ['ngRoute','ui.bootstrap']);

wafepaApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : '/app/html/home.html'
        })
        .when('/editBook/:id', {
            templateUrl : '/app/html/editBook.html',
            controller: 'editBookCtrl'
        })
        .when('/books', {
            templateUrl : '/app/html/books.html',
            controller: 'booksCtrl'
        })
    .when('/login', {
            templateUrl : '/app/html/login.html',
           
        })
        .otherwise({
            redirectTo: '/',
        });
}]);