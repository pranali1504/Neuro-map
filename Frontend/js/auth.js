// Runs automatically when login page opens
document.addEventListener("DOMContentLoaded", function () {

    const params = new URLSearchParams(window.location.search);
    const msg = document.getElementById("logoutMessage");

    console.log("URL Params:", window.location.search);

    if (params.get("logout") === "true") {
        if (msg) {
            msg.style.display = "block";
        }
    }
});

function login() {

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  fetch("http://localhost:8081/api/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      email: email,
      password: password
    })
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("Login failed");
    }
    return response.text();
  })
  .then(token => {
    // STORE JWT
    localStorage.setItem("token", token);

    document.getElementById("message").innerText = "Login successful";

    // Redirect later
     window.location.href = "dashboard.html";
  })
  .catch(error => {
    document.getElementById("message").innerText = "Invalid email or password";
  });
}


function signup() {

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  fetch("http://localhost:8081/api/auth/signup", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      email: email,
      password: password
    })
  })
  .then(response => {
    if (!response.ok) {
      throw new Error("Signup failed");
    }
    return response.text();
  })
  .then(message => {
    document.getElementById("message").innerText = "Signup successful. Please login.";
    
    // redirect after short delay
    setTimeout(() => {
      window.location.href = "index.html";
    }, 1500);
  })
  .catch(error => {
    document.getElementById("message").innerText = "Signup failed. Email may already exist.";
  });
}

