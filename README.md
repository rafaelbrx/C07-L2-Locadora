# 🚗 Sistema de Locadora de Veículos

### 📌 Descrição do Projeto
Gerenciamento de uma **locadora de veículos**: cadastrar clientes, veículos, categorias de veículos, realizar reservas e registrar pagamentos.  

---

## 🔗 Relacionamentos

 **1:N** → Cliente ↔ Reserva  
- **Cliente (1) → (N) Reserva**  
  Um cliente pode fazer várias reservas, mas cada reserva pertence a apenas um cliente.  


 **1:1** → Reserva ↔ Pagamento  
- **Reserva (1) → (1) Pagamento**  
  Cada reserva gera apenas um pagamento e cada pagamento está vinculado a uma reserva.  


 **N:M** → Veículo ↔ Categoria  
- **Veículo (N) ↔ (M) Categoria**  
  Um veículo pode ser enquadrado em várias categorias e uma categoria pode incluir vários veículos.  


---

## 🗂️ Entidades do Sistema

### 🔹 Cliente
- id_cliente (PK; int)  
- nome  (string)
- cpf  (string)
- telefone  (string)
- email  (string)

### 🔹 Veículo
- id_veiculo (PK; int)  
- modelo  (string)
- placa  (string)
- ano  (int)
- disponibilidade  (boolean)

### 🔹 Reserva
- id_reserva (PK; int)  
- data_inicio  (date)
- data_fim  (date)
- id_cliente (FK; int)  
- id_veiculo (FK; int)  

### 🔹 Pagamento
- id_pagamento (PK; int)  
- valor  (double)
- forma_pagamento (string)  
- status  (boolean)
- id_reserva (FK; int)  

### 🔹 Categoria
- id_categoria (PK; int)  
- nome  (string)
- descricao  (string)

---


#### 👥 Integrante C07 - L2:  Rafael Braga Santos  
