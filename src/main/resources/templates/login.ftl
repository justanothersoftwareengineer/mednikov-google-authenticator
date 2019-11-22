<!DOCTYPE html>
<html>
    <head>
        <title>Title</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script
        			  src="https://code.jquery.com/jquery-3.4.1.min.js"
        			  integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        			  crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.css" integrity="sha256-ujE/ZUB6CMZmyJSgQjXGCF4sRRneOimQplBVLu8OU5w=" crossorigin="anonymous" />
    </head>
    <body>
        <div>
            <div class="columns">
                <div class="column">
                    <div class="field">
                        <label class="label">Email</label>
                        <input class="input" type="text" id="login-username-field">
                    </div>
                    <div class="field">
                        <label class="label">Password</label>
                        <input class="input" type="password" id="login-password-field">
                    </div>
                    <button class="button is-primary" onclick="login()">Log in</button>
                </div>
                <div class="column">
                    <div class="field" id="login-code-form">
                        <label class="label">Please insert one-time code</label>
                        <input class="input" type="text" id="login-code-field">
                    </div>
                    <div class="notification is-success" id="login-success-notification">
                        <p>Code is correct, access granted</p>
                    </div>
                    <div class="notification is-danger" id="login-error-notification">
                        <p>Code is incorrect!</p>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>

        $(document).ready(function(){
            $("#login-success-notification").hide();
            $("#login-error-notification").hide();
        });

        function login(){
            var code = $("#login-code-field").val();
            var username = $("#login-username-field").val();
            var password = $("#login-password-field").val();

            var payload = {"username": username, "password": password, "code":code};
            $.ajax({
                url: 'http://localhost:4567/api/login',
                data: JSON.stringify(payload),
                method: 'POST',
                statusCode: {
                    200: function(xhr){
                        $("#login-success-notification").show();
                        $("#login-error-notification").hide();
                    },
                    403: function(xhr){
                        $("#login-success-notification").hide();
                        $("#login-error-notification").show();
                    },
                    404: function(xhr){
                        $("#login-success-notification").hide();
                        $("#login-error-notification").show();
                    }
                }
            });
        }
    </script>
</html>