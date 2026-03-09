let token = null;

function login() {

    fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            username: document.getElementById("username").value,
            password: document.getElementById("password").value
        })
    })
    .then(response => {

        if (!response.ok) {
            throw new Error("Credenciales no válidas");
        }

        return response.json();
    })
    .then(data => {

        localStorage.setItem("token", data.token);

        console.log("Token guardado:", data.token);
        alert("Login exitoso");
    })
    .catch(error => {

        localStorage.removeItem("token");

        alert(error.message);
        console.error(error);
    });
}

function getProfesiones() {

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión primero");
        return;
    }

    // Cambié /api/profesion por /api/profesiones para que coincida con el @RequestMapping del controller
    fetch("http://localhost:8080/api/profesiones", {
        method: "GET",
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(response => {

        if (response.status === 401) {
            throw new Error("No autenticado. Token inválido o vencido.");
        }

        if (response.status === 403) {
            throw new Error("No tienes permisos para acceder.");
        }

        if (!response.ok) {
            throw new Error("Error al consultar la API");
        }

        return response.json();
    })
    .then(data => {
        document.getElementById("resultado").textContent =
            JSON.stringify(data, null, 2);
    })
    .catch(error => {
        alert(error.message);
        console.error(error);
    });
}