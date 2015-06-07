$(document).ready(function() {
    $('h1').hide();
    var view = $('#view');
    var backButton = $('#backButton');
    var forwardButton = $('#forwardButton');
    var currentCase;
    var situations = [];
    var locationX = null;
    var locationY = null;
    window.loadStart = function () {
        view.load('templates/start.html', function () {
            backButton.hide();
            forwardButton.hide();
            //events
            $('#health').on('click', function (e) {
                loadCategory('health')
            });
            $('#attack').on('click', function (e) {
                loadCategory('attack')
            });
            $('#fire').on('click', function (e) {
                loadCategory('fire')
            });
            $('#carAccident').on('click', function (e) {
                loadCategory('carAccident')
            });
        });
    };
    loadStart();
    window.loadCategory = function(templateName){
        view.load('templates/categories/' + templateName + '.html', function () {

            backButton.show();
            backButton[0].onclick = null;
            backButton.on('click', function(e){
                loadStart();
            });

            $('#drugDealing').on('click', function (e) {
                loadCase('drugDealing', 'attack');
                forwardButton.show();
            });
        });
    };

    window.loadCase = function(caseName, categoryName) {
        currentCase = caseName;
        view.load('templates/cases/' + caseName + '.html');
        backButton.show();
        backButton[0].onclick = null;
        backButton.on('click', function (e) {
            loadCategory(categoryName);
        });
        forwardButton.on('click', function (e) {
            $('input[name=' + caseName + ']:checked').each(function () {
                situations.push($(this).attr('id'));
            });
            loadSend();
        })
    };

    window.loadSend = function () {
        $('.redButton').hide();
        view.load('templates/send.html', function () {
            console.log(situations);
            $('#sendBtn').show();
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position){
                    locationX = position.coords.longitude;
                    locationY = position.coords.latitude;
                });
            } else {
                alert("Geolocation is not supported by this browser.");
            }

            $('#sendBtn').on('click', function (e) {
                var data = {
                    "CallerId": 1,
                    "EmergencyType": currentCase,
                    "LocationX": locationX,
                    "LocationY": locationY,
                    "Situations": situations,
                    "Message": $('#msg').val()
                };

                var url = 'http://192.168.101.136:420';

                $.ajax({
                    url: url + '/api/request',
                    data: data,
                    method: "post",
                    success: function(data) {
                        alert(data.Message);
                    },
                    error: function(error) {
                        alert("error kappa");
                    }
                });
            });
        });
    }
});