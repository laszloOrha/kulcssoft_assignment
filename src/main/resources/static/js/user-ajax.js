let listUsersButton = document.getElementById('list');
listUsersButton.addEventListener('click', getAllUsers);


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
                    `<td><img id="del-${user.id}" class="delete-icon" src="/img/delete-icon.png"></td>` +
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

function throwAlert (type, message) {
    $("#alert-area").append($(`<div id="deletion-alert" class="alert alert-${type}" role="alert">${message}</div>`));
    setTimeout(function () {
        $("#deletion-alert").remove();
    }, 2000);
}
