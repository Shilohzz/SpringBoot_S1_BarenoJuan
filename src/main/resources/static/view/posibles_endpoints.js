/**
 * Cambié todas las URLs de /api/profesion a /api/profesiones
 * para que coincidan con el @RequestMapping("/api/profesiones") del ProfesionController
 */

function apiFetch(url, options = {}) {

    const token = localStorage.getItem("token");

    if (!token) {
        alert("Debes iniciar sesión");
        return Promise.reject("No token");
    }

    return fetch(url, {
        ...options,
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token,
            ...options.headers
        }
    })
    .then(response => {

        if (response.status === 401) {
            localStorage.removeItem("token");
            throw new Error("Sesión expirada");
        }

        if (!response.ok) {
            throw new Error("Error en la petición");
        }

        // El DELETE devuelve 204 sin body, si intento leer .json() explota
        // Por eso reviso si hay contenido antes de parsearlo
        const contentType = response.headers.get("content-type");
        if (contentType && contentType.includes("application/json")) {
            return response.json();
        }
        return null;
    });
}

// Cambié /api/profesion/{id} → /api/profesiones/{id}
function getProfesionById(id) {
    return apiFetch(`http://localhost:8080/api/profesiones/${id}`);
}

// Cambié /api/profesion → /api/profesiones
function listar() {
    return apiFetch(`http://localhost:8080/api/profesiones`);
}

// Cambié /api/profesion → /api/profesiones
function crearProfesion(nombre_profesion, descripcion_profesion) {

    const nuevaProfesion = {
        nombre: nombre_profesion,
        descripcion: descripcion_profesion
    };

    return apiFetch("http://localhost:8080/api/profesiones", {
        method: "POST",
        body: JSON.stringify(nuevaProfesion)
    });
}

// Cambié /api/profesion/{id} → /api/profesiones/{id}
function actualizarProfesionPersonalizada(id, nombre_profesion, descripcion_profesion) {

    const datosActualizados = {
        nombre: nombre_profesion,
        descripcion: descripcion_profesion
    };

    return apiFetch(`http://localhost:8080/api/profesiones/${id}`, {
        method: "PUT",
        body: JSON.stringify(datosActualizados)
    });
}

// Cambié /api/profesion/{id} → /api/profesiones/{id}
function eliminarProfesion(id) {
    return apiFetch(`http://localhost:8080/api/profesiones/${id}`, {
        method: "DELETE"
    });
}