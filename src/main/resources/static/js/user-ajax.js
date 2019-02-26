let listUsersButton = document.getElementById('list');
listUsersButton.addEventListener('click', getAllUsers);


function getAllUsers() {
    let table = document.getElementById('user-table');
    fetch("/api/allusers")
        .then( function (response) {
            return response.json();
        })

        .then(function (users) {
            clearTable();
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
            document.getElementById("table-div").style.display='block';
        })
}

function clearTable() {
    document.getElementById('user-table').innerHTML = '<tr>\n' +
                                                '              <th>ID</th>\n' +
                                                '              <th>Username</th>\n' +
                                                '              <th>E-mail address</th>\n' +
                                                '              </tr>'
}
