async function getUserDataById(userId) {
    const response = await fetch(`/api/user/${userId}`)
    return await response.json()
}

async function fillModal(modal) {

    modal.addEventListener("show.bs.modal", async function(event) {

        const userId = event.relatedTarget.dataset.userId
        const user = await getUserDataById(userId)

        const modalBody = modal.querySelector(".modal-body")

        const idInput = modalBody.querySelector("input[data-user-id='id']")
        const nameInput = modalBody.querySelector("input[data-user-id='name']")
        const surnameInput = modalBody.querySelector("input[data-user-id='surname']")
        const ageInput = modalBody.querySelector("input[data-user-id='age']")
        const emailInput = modalBody.querySelector("input[data-user-id='email']")

        idInput.value = user.id
        nameInput.value = user.name
        surnameInput.value = user.surname
        ageInput.value = user.age
        emailInput.value = user.email

        let rolesSelect = HTMLSelectElement

        let rolesSelectDelete = modalBody.querySelector("select[data-user-id='rolesDelete']")
        let rolesSelectEdit = modalBody.querySelector("select[data-user-id='rolesEdit']")
        let userRolesHTML = ""

        if (rolesSelectDelete !== null) {
            rolesSelect = rolesSelectDelete
            for (let i = 0; i < user.roles.length; i++) {
                userRolesHTML +=
                    `<option value="${user.roles[i]}">${user.roles[i].substring(5, user.roles[i].length)}</option>`
            }
        } else if (rolesSelectEdit !== null) {
            rolesSelect = rolesSelectEdit
            userRolesHTML +=
                `<option value="ROLE_USER">USER</option>
                 <option value="ROLE_ADMIN">ADMIN</option>`
        }

        rolesSelect.innerHTML = userRolesHTML
    })
}
