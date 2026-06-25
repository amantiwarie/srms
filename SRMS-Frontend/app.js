var app =
    angular.module(
        "srmsApp",
        ["ngRoute"]
    );

app.config(function($routeProvider){

    $routeProvider

        .when("/",{
            templateUrl:
                "views/home.html"
        })





        .when("/addTeacher",{
            templateUrl:
                "views/addTeacher.html",
            controller:"TeacherController"

        })

        .when("/teachers",{
            templateUrl:
                "views/teachers.html",
            controller:"TeacherController"
        })
        .when("/students",{
        templateUrl:"views/student/students.html",
        controller:"StudentController"
    })

        .when("/addStudent",{
            templateUrl:"views/student/addStudent.html",
            controller:"StudentController"
        })

        .otherwise({
            redirectTo:"/"
        });

});