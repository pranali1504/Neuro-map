const token = localStorage.getItem("token");

if (!token) {
    // user not logged in
    alert("Please login first!");
    window.location.href = "login.html";
}
