//JS code for handling ajax calls
function init() {
    let listUsersButton = document.getElementById('list');
    listUsersButton.addEventListener('click', getAllUsers);

    let addNewUserButton = document.getElementById("new-user-submit");
    addNewUserButton.addEventListener('click', addNewUser);
}

init();

function addDeleteListeners() {
    let deleteButtons = document.querySelectorAll(".delete-icon");
    deleteButtons.forEach(function (deleteButton) {
        deleteButton.addEventListener("click", function () {
            let id = this.id.split("-")[1];
            deleteUser(id);
        });
    });
}

function getAllUsers() {
    let table = document.getElementById('user-table');
    fetch("/api/allusers", {
        method: "POST",
        body: localStorage.getItem("email")
    })
        .then( function (response) {
            return response.json();
        })

        .then(function (users) {
            document.getElementById('user-table').innerHTML = '';
            users.forEach(function (user) {
                let newRow = '<tr>' +
                    `<td>${user.id}</td>` +
                    `<td>${user.userName}</td>` +
                    `<td>${user.userEmail}</td>` +
                    `<td><img id="del-${user.id}" class="delete-icon" src="https://cdn4.iconfinder.com/data/icons/colicon/24/close_delete-128.png
"></td>` +
                    '</tr>';
                table.insertAdjacentHTML('beforeend', newRow);
            });
            $('#table-div').fadeIn(1000);
            addDeleteListeners();
        })
}

function deleteUser(id) {
    fetch(`/api/${id}`, {
        method: "DELETE"
    })
        .then( function (response) {
            return response.json();
        })
        .then(function (isDeletionSuccessful) {
            if(isDeletionSuccessful === true) {
                throwAlert('success', "Deletion successful!");
            }else {
                throwAlert('danger', "Couldn't delete user!");
            }
        })
        .then(getAllUsers)
}

function addNewUser() {
    let newUserNameField = document.getElementById("new-user-name");
    let newUserEmailField = document.getElementById("new-user-email");
    let data = `{"userName":"${newUserNameField.value}", "userEmail":"${newUserEmailField.value}","adminEmail":"${localStorage.getItem("email")}"}`;
    fetch("/api/newuser", {
        method: "POST",
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(function (response) {
            return response.json();
        })
        .then(function (isAdditionSuccessful) {
            if(isAdditionSuccessful === true) {
                throwAlert('success', "New user saved!");
            }else {
                throwAlert('danger', "Couldn't add new user!");
            }
        })
        .then(function () {
            console.log("clear");
            newUserNameField.value = '';
            newUserEmailField.value = '';
        })
        .then(getAllUsers)
}

function throwAlert (type, message) {
    $("#update-alert").remove();
    $("#alert-area").append($(`<div id="update-alert" class="alert alert-${type}" role="alert">${message}</div>`));
    setTimeout(function () {
        $("#update-alert").remove();
    }, 2000);
}
