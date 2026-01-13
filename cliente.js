const http = require('http');

console.log("--- Testando Cliente JavaScript (Node.js) ---");

// 1. Consultar Cardápio (GET)
http.get('http://localhost:8080/cardapio/comidas', (res) => {
    let data = '';
    res.on('data', (chunk) => { data += chunk; });
    res.on('end', () => {
        console.log('Cardápio recebido via JS:', JSON.parse(data));
        
        // 2. Após ver o cardápio, vamos consultar o status do pedido 1000
        http.get('http://localhost:8080/pedido/1000/status', (resStatus) => {
            let statusData = '';
            resStatus.on('data', (c) => { statusData += c; });
            resStatus.on('end', () => {
                console.log('Status do Pedido via JS:', JSON.parse(statusData));
            });
        });
    });
});