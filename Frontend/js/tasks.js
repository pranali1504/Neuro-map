let tasks = [];

function addTask() {
  const input = document.getElementById("taskInput");
  if (input.value === "") return;

  tasks.push(input.value);
  input.value = "";
  renderTasks();
}

function renderTasks() {
  const list = document.getElementById("taskList");
  list.innerHTML = "";

  tasks.forEach((task, index) => {
    const li = document.createElement("li");
    li.innerHTML = `
      ${task}
      <button onclick="deleteTask(${index})">❌</button>
    `;
    list.appendChild(li);
  });
}

function deleteTask(index) {
  tasks.splice(index, 1);
  renderTasks();
}
