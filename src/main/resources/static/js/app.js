window.addEventListener('load', function() {

    var idToken;
    var accessToken;
    var expiresAt;
    var logoutBtn = document.getElementById('btn-logout');

    let webAuth = new auth0.WebAuth({
        domain: 'ks-userapp.eu.auth0.com',
        clientID: 'l2r0jAyLBH90ngVTJQEkzJ-lV4RyxGGA',
        responseType: 'token id_token',
        scope: 'openid email',
        redirectUri: window.location.href
    });

    if (localStorage.getItem('isLoggedIn') !== 'true'){
        console.log(webAuth);
        webAuth.authorize();
        handleAuthentication();
    }

    logoutBtn.addEventListener('click', logout);

    function localLogin(authResult) {
        // Set isLoggedIn flag in localStorage
        localStorage.setItem('isLoggedIn', 'true');
        // Set the time that the access token will expire at
        expiresAt = JSON.stringify(
            authResult.expiresIn * 1000 + new Date().getTime()
        );
        accessToken = authResult.accessToken;
        idToken = authResult.idToken;

        localStorage.setItem('email', parseJwt(idToken).email);
    }



    function logout() {
        // Remove isLoggedIn flag from localStorage
        localStorage.removeItem('isLoggedIn');
        // Remove tokens and expiry time
        accessToken = '';
        idToken = '';
        expiresAt = 0;
    }

    function isAuthenticated() {
        // Check whether the current time is past the
        // access token's expiry time
        var expiration = parseInt(expiresAt) || 0;
        return localStorage.getItem('isLoggedIn') === 'true' && new Date().getTime() < expiration;
    }

    function handleAuthentication() {
        webAuth.parseHash(function(err, authResult) {
            if (authResult && authResult.accessToken && authResult.idToken) {
                window.location.hash = '';
                localLogin(authResult);
            } else if (err) {
                console.log(err);
                alert(
                    'Error: ' + err.error + '. Check the console for further details.'
                );
            }
        });
    }


});

function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace('-', '+').replace('_', '/');
    return JSON.parse(window.atob(base64));
}

