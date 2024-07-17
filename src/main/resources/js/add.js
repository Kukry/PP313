async function createNewUser(user) {
        await fetch("/api/users",
        {method: "POST", headers: {"Content-Type": "application/json"}, body: JSON.stringify(user)})
}

async function addNewUserForm() {
    const newUserForm = document.getElementById("newUser");

    newUserForm.addEventListener("submit", async function (event) {
        event.preventDefault();

        const name = newUserForm.querySelector("#name").value.trim()
        const surname = newUserForm.querySelector("#surname").value.trim()
        const age = newUserForm.querySelector("#age").value.trim()
        const email = newUserForm.querySelector("#email").value.trim()
        const password = newUserForm.querySelector("#password").value.trim()

        const rolesSelected = document.getElementById("roles")

          let roles = []
        for (let option of rolesSelected.selectedOptions) {
            if(option.value === "ROLE_USER") {
                roles.push("ROLE_USER")
            } else if (option.value === "ROLE_ADMIN") {
                roles.push("ROLE_ADMIN")
            }
        }

        const newUserData = {
            name: name,
            surname: surname,
            age: age,
            email:email,
            password: password,
            roles: roles
        }

        await createNewUser(newUserData)
        newUserForm.reset()

        document.querySelector('#nav-users-tab').click()
        await fillTableOfAllUsers()
    })
}