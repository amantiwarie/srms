app.controller(
    "StudentController",
    function($scope,$http){

        $scope.students=[];

        loadStudents();

        function loadStudents(){

            $http.get(
                "http://localhost:8080/api/students"
            )
                .then(function(response){

                    $scope.students =
                        response.data.content;

                });

        }

        $scope.student={};

        $scope.errorMessages = [];

        $scope.addStudent = function () {

            $http.post(
                "http://localhost:8080/api/students",
                $scope.student
            )
                .then(function(response){

                    alert("Student Added");

                    $scope.student = {};

                    $scope.errorMessages = [];

                    loadStudents();

                })
                .catch(function(error){

                    console.log(error);

                    $scope.errorMessages = [];

                    if(error.data.fields){

                        angular.forEach(
                            error.data.fields,
                            function(message, field){

                                $scope.errorMessages.push(message);

                            }
                        );

                    }

                });

        };

        $scope.deleteStudent=function(id){

            $http.delete(
                "http://localhost:8080/api/students/" + id
            )
                .then(function(){

                    alert("Deleted");

                    loadStudents();

                });

        };

    });