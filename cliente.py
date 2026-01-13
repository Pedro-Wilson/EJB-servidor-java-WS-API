import requests

# URL base da sua API Java (SparkJava)
BASE_URL = "http://localhost:8080"

def testar_api():
    try:
        print("--- 1. CONSULTANDO CARDÁPIO ---")
        response = requests.get(f"{BASE_URL}/cardapio/comidas")
        print("Cardápio:", response.json())

        print("\n--- 2. REALIZANDO UM PEDIDO ---")
        # Parâmetros: cliente, id da comida (0 a 3) e observação
        params = {'cliente': 'Pedro Wilson', 'id': 0, 'obs': 'Sem pimenta'}
        pedido = requests.post(f"{BASE_URL}/pedir/comida", params=params)
        print("Pedido Confirmado:", pedido.json())

        # Pega o ID do pedido que o servidor gerou (ex: 1000)
        id_pedido = pedido.json().get('idPedido')

        print(f"\n--- 3. RASTREANDO PEDIDO #{id_pedido} ---")
        status = requests.get(f"{BASE_URL}/pedido/{id_pedido}/status")
        print("Status atual:", status.json())

    except Exception as e:
        print(f"Erro ao conectar no servidor: {e}")

if __name__ == "__main__":
    testar_api()