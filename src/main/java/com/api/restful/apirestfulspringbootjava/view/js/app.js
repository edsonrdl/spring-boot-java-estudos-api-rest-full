// const tasksEndpoint = "http://localhost:8080/task/user";

// function hideLoader() {
//   document.getElementById("loading").style.display = "none";
// }

// function generateTaskTable(data) {
//   let tab = `<thead>
//             <th scope="col">#</th>
//             <th scope="col">Description</th>
//         </thead>`;

//   for (let task of data) {
//     tab += `
//             <tr>
//                 <td scope="row">${task.id}</td>
//                 <td>${task.description}</td>
//             </tr>
//         `;
//   }

//   document.getElementById("tasks").innerHTML = tab;
// }

// async function getTasks() {
//   let key = "Authorization";
//   const response = await fetch(tasksEndpoint, {
//     method: "GET",
//     headers: new Headers({
//       Authorization: localStorage.getItem(key),
//     }),
//   });

//   var data = await response.json();
//   console.log(`Verificar o que tem em data ${data}`);
//   if (response) hideLoader();
//   generateTaskTable(data);
// }

// document.addEventListener("DOMContentLoaded", function (event) {
//   if (!localStorage.getItem("Authorization"))
//     window.location = "../login.html";
// });

// getTasks();

const tasksEndpoint = "http://localhost:8080/task/user";
const loadingElement = document.getElementById("loading");
const tasksElement = document.getElementById("tasks");

function hideLoader() {
  loadingElement.style.display = "none";
}

function generateTaskTable(tasks) {
  return `
    <thead>
        <th scope="col">#</th>
        <th scope="col">Description</th>
    </thead>
    ${tasks.map(task => `
      <tr>
          <td scope="row">${task.id}</td>
          <td>${task.description}</td>
      </tr>
    `).join('')}
  `;
}

async function getTasks() {
  const key = "Authorization";
  const token = localStorage.getItem(key);

  if (!token) {
    window.location = "/view/login.html";
    return;
  }

  try {
    const response = await fetch(tasksEndpoint, {
      method: "GET",
      headers: new Headers({ Authorization: token }),
    });

    if (!response.ok) {
      throw new Error("Failed to fetch tasks.");
    }

    const data = await response.json();
    console.log(data);
    hideLoader();
    tasksElement.innerHTML = generateTaskTable(data);
  } catch (error) {
    console.error(error);
  }
}

document.addEventListener("DOMContentLoaded", () => {
  getTasks();
});

