document.addEventListener("DOMContentLoaded", () => {

    const logoutBtn = document.getElementById("logoutBtn");

    console.log("Logout script loaded");   // <-- testing line

    logoutBtn.addEventListener("click", () => {
        console.log("Logout clicked");

        localStorage.removeItem("token");
        localStorage.removeItem("user");

        window.location.href = "index.html?logout=true";
    });

});
