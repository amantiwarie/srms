console.log("TeacherController file loaded");
app.controller("TeacherController",function($scope,$http){
    $scope.teachers=[];

    loadteachers();

    function loadteachers(){

        $http.get("http://localhost:8080/api/teachers")
            .then(function(response){
                $scope.teachers = response.data;
            });
    }
    $scope.teacher={};

    $scope.errorMessages=[];

    $scope.addTeacher=function(){

        $http.post("http://localhost:8080/api/teachers",$scope.teacher)
        .then(function(response){
            alert("Teacher added successfully.");
            $scope.teachers=[];
            $scope.errorMessages=[];
            loadteachers();
        })
            .catch(function(error){
                console.log(error);
                $scope.errorMessages=[];

                if(error.data.fields){
                angular.forEach(
                    error.data.fields,
                    function(message, field){

                        $scope.errorMessages.push(message);

                    }
                );

            }
            })
    };


    $scope.deleteTeacher =function (id){

        $http.delete("http://localhost:8080/api/teachers/"+id)
            .then(function(){
                alert("Teacher deleted successfully.");
                loadteachers();
            });
    };
});