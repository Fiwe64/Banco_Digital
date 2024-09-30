// URL da API
const apiUrl = "http://localhost:8080/api/banco";

// Seleção de elementos do formulário
const formulario = document.querySelector("#clienteForm");
const Inome = document.querySelector("#clienteNome");
const Iemail = document.querySelector("#clienteEmail");
const Itelefone = document.querySelector("#clienteTelefone");
const contaForm = document.querySelector("#contaForm");
const saldoForm = document.querySelector("#visualizarSaldoForm");
const visualizarClientesBtn = document.querySelector("#visualizarClientesBtn");
const visualizarContasBtn = document.querySelector("#visualizarContasBtn");

// Função para cadastrar um novo cliente
function cadastrar() {
  const clienteData = {
    nome: Inome.value,
    email: Iemail.value,
    telefone: Itelefone.value
  };

  console.log(clienteData); // Para ver os dados que estão sendo enviados

  fetch(`${apiUrl}/clientes`, {
    method: "POST",
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(clienteData)
  })
  .then(res => {
    if (res.ok) {
      return res.json();
    } else {
      return res.text().then(text => {
        throw new Error(`Erro: ${res.status} - ${text}`);
      });
    }
  })
  .then(data => {
    console.log("Cadastro realizado:", data);
    alert("Cadastro realizado com sucesso!");
    listarClientes(); // Atualiza a lista de clientes
    limpar(); // Limpa os campos do formulário
  })
  .catch(error => {
    console.error("Erro:", error);
    alert("Erro ao realizar o cadastro: " + error.message);
  });
}

// Função para listar clientes
function listarClientes() {
  fetch(`${apiUrl}/clientes`)
    .then(response => {
      if (!response.ok) {
        throw new Error('Erro ao buscar lista de clientes');
      }
      return response.json();
    })
    .then(clientes => {
      console.log("Lista de clientes:", clientes);
      // Gerar o HTML da lista de clientes
      const listaHTML = clientes.map(cliente => `
        <ul>
          <li>Nome: ${cliente.nome}</li>
          <li>Email: ${cliente.email}</li>
          <li>Telefone: ${cliente.telefone}</li>
          <button onclick="editarClientes(${cliente.id})">Editar</button>
          <button onclick="deletarClientes(${cliente.id})">Deletar</button>
        </ul>`).join("");

      // Inserir a lista gerada no elemento HTML com o id "clientesResultado"
      document.getElementById("clientesResultado").innerHTML = listaHTML;
    })
    .catch(error => {
      console.error("Erro ao listar clientes:", error);
    });
}

// Função para visualizar saldo
saldoForm.addEventListener('submit', function(event) {
  event.preventDefault(); // Previne o comportamento padrão do formulário
  const contaId = document.getElementById("contaIdSaldo").value;

  fetch(`${apiUrl}/contas/${contaId}/saldo`)
    .then(response => {
      if (!response.ok) {
        throw new Error('Erro ao visualizar saldo');
      }
      return response.json();
    })
    .then(saldo => {
      document.getElementById("saldoResultado").innerText = `Saldo: R$ ${saldo}`;
    })
    .catch(error => {
      console.error("Erro ao visualizar saldo:", error);
      alert("Erro ao visualizar saldo: " + error.message);
    });
});

// Adicionar evento de submit ao formulário de cliente
formulario.addEventListener('submit', function(event) {
  event.preventDefault(); // Previne o comportamento padrão do formulário
  cadastrar(); // Chama a função de cadastro
});

// Listar clientes ao carregar a página
window.onload = listarClientes;
visualizarClientesBtn.addEventListener('click', listarClientes);
visualizarContasBtn.addEventListener('click', listarContas);
