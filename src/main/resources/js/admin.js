document.addEventListener("DOMContentLoaded", async function () {
    await showHeader()
    await fillTableOfAllUsers()
    await fillTableAboutCurrentUser()
    await addNewUserForm()
    await DeleteModalHandler()
    await EditModalHandler()
})

//-----------функция заполнения Header--------------------------------------
async function showHeader() {
    const userHeader = document.getElementById("userHeader")
    const user = await dataAboutCurrentUser()
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