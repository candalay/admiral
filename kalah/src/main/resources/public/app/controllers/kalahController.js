app.controller("kalahController", function($scope, $http, $location, $timeout) {

	var boardStatusURL = "/game/v1/board";
	var playBaseURL = "/game/v1/play/";

	init();

	function init(){

     	$http.get(boardStatusURL).then(function(resp){
			$scope.board = resp.data;
			$scope.msg = "Turn North Player";
            $scope.pointsNorth = $scope.board[0];
            $scope.pointsSouth = $scope.board[7];
		});

	}

	 $scope.move = function (pit) {

            if ($scope.board[14] === 3){
                return;
            }

            $http.post(playBaseURL + pit).then(function(resp){
            	
                $scope.board = resp.data.board;
                $scope.turn = resp.data.turn;
                $scope.gameOver = resp.data.gameOver;

                if ($scope.turn == '1') {
                	$scope.msg = "Turn North Player";
                } else {
                	$scope.msg = "Turn South Player";
                }

                if($scope.gameOver) {
                    $scope.msg = "Game Over.";
                    $scope.board[0] = $scope.board[0]
                                    + $scope.board[8]
                                    + $scope.board[9]
                                    + $scope.board[10]
                                    + $scope.board[11]
                                    + $scope.board[12]
                                    + $scope.board[13];
                    $scope.board[7] = $scope.board[7]
                                    + $scope.board[1]
                                    + $scope.board[2]
                                    + $scope.board[3]
                                    + $scope.board[4]
                                    + $scope.board[5]
                                    + $scope.board[6];

                    $scope.board[8] = $scope.board[9] = $scope.board[10] = $scope.board[11] = $scope.board[12] = $scope.board[13] = 0;
                    $scope.board[1] = $scope.board[2] = $scope.board[3] = $scope.board[4] = $scope.board[5] = $scope.board[6] = 0;
                }


             });

        };

});
