let listUsersButton = document.getElementById('list');
listUsersButton.addEventListener('click', getAllUsers);


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
                console.log(user);
                let newRow = '<tr>' +
                    `<td>${user.id}</td>` +
                    `<td>${user.userName}</td>` +
                    `<td>${user.userEmail}</td>` +
                    '</tr>';
                table.insertAdjacentHTML('beforeend', newRow);
            });
            console.log("finished");
            $('#table-div').fadeIn(1000);
        })
}
