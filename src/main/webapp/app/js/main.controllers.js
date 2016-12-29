var wafepaApp = angular.module('wafepaApp.controllers', []);

wafepaApp.controller('booksCtrl', function ($http,$scope,$location) {

	//dodajem za NEXT PAGE
	$scope.page=0;
	$scope.changePage = function(i){
		$scope.page+=i;
		getBooks();
		getAuthors();
	}

/*GET BOOKS AND AUTHORS */
	var getBooks = function (){

		var parameters = {};    //ovo je page,name
		parameters.page = $scope.page; //saljem ovako page kao prvi parametar u parameters
		if($scope.search&&$scope.search.name){
			parameters.name=$scope.search.name;
		}

		$http.get('api/books',{params:parameters})
		.success(function (data, status) {
			$scope.books = data;
		})
		.error(function (data,status) {
			console.log(data,status);
		});
	}
	var getAuthors = function (){
		$http.get('api/authors')
		.success(function (data, status) {
			$scope.authors = data;
		})
		.error(function (data,status) {
			console.log(data,status);
		});
	}

	getBooks();   //ovde se prvi put prikazuje knjige
	getAuthors();   //ovde se prvi put prikazuju autori ali mora postojati funkcija formatAuthors
	

	$scope.formatAuthors = function(authors){
		var retVal='';
		for (var i = 0; i < authors.length; i++) {
			retVal += authors[i].firstName+' '+authors[i].lastName+', '
		};
		return retVal.substring(0, retVal.length-2);
	};

/* SAVE BOOK */	
	$scope.saveBook = function () {
		var bookDTO = {title:$scope.newBook.title};
		bookDTO.authors = [];
		for(var i = $scope.newBook.authors.length - 1; i >= 0; i--){
			bookDTO.authors.push({id:$scope.newBook.authors[i]});
		};

		if(!$scope.newBook.id){
			$http.post('api/books', $scope.newBook)
			.success(function () {
				getBooks();
			});
		}

		else{
			$http.put('api/books/'+$scope.newBook.id,$scope.newBook)
			.success(function () {
				getBooks()
			});	
		}
	};

/*DELETE BOOK*/
		getBooks();   
	getAuthors();
	$scope.deleteBook = function (id) {
		$http.delete('api/books/'+id)
		.success(function() {
			getBooks();   
		});
	}

/*EDIT BOOK*/
	$scope.editBook = function (book) {
		$scope.newBook = angular.copy(book);   //copy- da ne edituje i u tabeli istovremeno
	}

	$scope.formatAuthor =function (author) {  //bez ovog u listi autora je undefined
		return author.firstName + ' ' + author.lastName;
	};

/*EDIT BOOK IN NEW PAGE*/
	$scope.editNewPage = function(book){
	$location.path('editBook/' + book.id);
	}
/*FIND BOOKS*/
		$scope.findBooks = function(){
		getBooks();
	}

});  //end BookCtrl


wafepaApp.controller('editBookCtrl',function ($scope,$http,$routeParams,$location) {
	var getBooks = function () {
		$http.get('api/books/'+$routeParams.id)
		.success(function (data, status) {
			$scope.book = data;
		})
	};

	var getAuthors = function(){
		$http.get('api/authors')
		.success(function (data, status) {
			$scope.authors = data;
		})
		.error(function (data, status) {
			console.log(data);
			console.log(status);
		})
	};


	getBooks();
	getAuthors();

	$scope.formatAuthors = function(authors){
		var retVal = '';
		for (var i = 0; i < authors.length; i++) {
			retVal += authors[i].firstName + '' + authors[i].lastName + ', '; 
		};
		return retVal.substring(0, retVal.length-2);
	};

	$scope.formatAuthor =function (author) {  //bez ovog u listi autora je undefined
		return author.firstName + ' ' + author.lastName;
	};


	$scope.saveBook = function () {
		$http.put('api/books/'+$routeParams.id,$scope.book)
		.success(function () {
			$location.path('books');
		})
		.error(function (data, status) {
			console.log(data);
			console.log(status);
		});
	}



		

}); //end editBookCtrl