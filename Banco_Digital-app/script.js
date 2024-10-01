document.getElementById('form-criar-cliente').addEventListener('submit', function (event) {
    event.preventDefault();

    const nome = document.getElementById('nome').value;
    const email = document.getElementById('email').value;
    const telefone = document.getElementById('telefone').value;

    fetch('http://localhost:8080/api/clientes', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            nome: nome,
            email: email,
            telefone: telefone
        })
    })
    .then(response => response.json())
    .then(data => {
        alert('Cliente criado com sucesso!');
    })
    .catch(error => console.error('Erro:', error));
});

document.getElementById('form-criar-conta').addEventListener('submit', function (event) {
    event.preventDefault();

    const clienteId = document.getElementById('clienteId').value;
    const saldo = document.getElementById('saldo').value;

    fetch('http://localhost:8080/api/contas/corrente', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            cliente: {
                id: clienteId
            },
            saldo: saldo
        })
    })
    .then(response => response.json())
    .then(data => {
        alert('Conta Corrente criada com sucesso!');
    })
    .catch(error => console.error('Erro:', error));
});

document.getElementById('form-pagamento').addEventListener('submit', function (event) {
    event.preventDefault();

    const clienteId = document.getElementById('clienteIdPagamento').value;
    const contaId = document.getElementById('contaIdPagamento').value;
    const valor = document.getElementById('valorPagamento').value;
    const tipoConta = document.getElementById('tipoContaPagamento').value;

    fetch('http://localhost:8080/api/pagamentos/deposito', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            clienteId: clienteId,
            contaId: contaId,
            valor: valor,
            tipoConta: tipoConta
        })
    })
    .then(response => response.json())
    .then(data => {
        alert('Pagamento processado com sucesso!');
    })
    .catch(error => console.error('Erro:', error));
});

document.getElementById('form-consultar-saldo').addEventListener('submit', function (event) {
    event.preventDefault();

    const contaId = document.getElementById('contaIdSaldo').value;

    fetch(`http://localhost:8080/api/contas/corrente/${contaId}/saldo`, {
        method: 'GET',
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('resultado').innerText = `Saldo: R$ ${data}`;
    })
    .catch(error => console.error('Erro:', error));
});
