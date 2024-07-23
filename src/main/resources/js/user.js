document.addEventListener("DOMContentLoaded", async function () {
    await showHeader()
    await showTable()
});

//-----------функция получения данных о пользователе------------------------
async function userData() {
    const response = await fetch("/api/user/auth")
    return await response.json()
}

//--------------------------------------------------------------------------

//-----------функция заполнения таблицы о пользователе----------------------
async function showTable(){
    const userTable = document.getElementById("userTable");
    const user = await userData()
    let shortRoles = []
    for (let role of user.roles) {
        shortRoles.push(role.substring(5, role.length))
    }
    let userTableHTML = ""

    userTableHTML +=
        `<tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.age}</td>
            <td>${user.email}</td>
            <td>${shortRoles.join(' ')}</td>
        </tr>`
    userTable.innerHTML = userTableHTML
}
//--------------------------------------------------------------------------

//-----------функция заполнения Header--------------------------------------
async function showHeader() {
    const userHeader = document.getElementById("userHeader")
    const user = await userData()
    let shortRoles = []
    for (let role of user.roles) {
        shortRoles.push(role.substring(5, role.length))
    }
    userHeader.innerHTML =
                `${user.email}
                 <label class="text-white h5 fw-normal" >with roles:</label>
                 <label class="text-white h5 fw-normal" >${shortRoles.join(' ')}</label>`
}
//--------------------------------------------------------------------------