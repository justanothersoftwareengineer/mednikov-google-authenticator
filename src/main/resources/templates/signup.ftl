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
                        <input class="input" type="text" id="signup-username-field">
                    </div>
                    <div class="field">
                        <label class="label">Password</label>
                        <input class="input" type="password" id="signup-password-field">
                    </div>
                    <div class="field">
                        <label class="label">Repeat password</label>
                        <input class="input" type="password" id="signup-confirm-field">
                    </div>
                    <button class="button is-primary" onclick="signup()">Sign up</button>
                </div>
                <div class="column">
                    <p id="signup-code-text"></p>
                </div>
            </div>
        </div>
    </body>

    <!--Script-->
    <script>
        function signup(){
            var password = $("#signup-password-field").val();
            var confirm = $("#signup-confirm-field").val();
            var username = $("#signup-username-field").val();
            if (password != confirm){
                alert("Passwords don't match!");
            } else {
                var payload = {
                    "username": username,
                    "password": password
                }

                var data = JSON.stringify(payload);
                
                $.ajax({
                    url: "http://localhost:4567/api/signup", 
                    data: data,
                    type: 'POST',
                    success: function(res){
                        $("#signup-code-text").text(res);
                    }
                });
            }
        }
    </script>
</html>

