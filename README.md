# financial-parser

 Financial  Parser API

Uma API REST desenvolvida em Java com Spring Boot para importação, processamento e normalização de transações financeiras a partir de arquivos no formato ** (80 caracteres)**.

---

 Tecnologias Utilizadas

* Java 17
* Spring Boot 
* Spring Data JPA
* PostgreSQL
* Maven

---

Arquitetura e Decisões de Projeto

O sistema foi desenhado para processar arquivos de texto formatados em largura fixa (80 caracteres por linha):

1. **Processamento via Buffer (`BufferedReader`):** Para evitar alto consumo de memória, o arquivo é lido linha por linha através de streams de I/O.
2. **Modelo de Transações:** Cada linha é convertida em um objeto de domínio contendo:
   * **Tipo da Transação:** Mapeado via Enum (`DEBITO`, `BOLETO`, `FINANCIAMENTO`, etc.) com natureza (Entrada/Saída) e sinal (`+`/`-`).
   * **Data e Hora:** Convertidas para os tipos nativos `LocalDate` (`yyyyMMdd`) e `LocalTime` (`HHmmss`).
   * **Valor:** Armazenado em centavos no arquivo e convertido com precisão decimal (dividido por 100).
   * **CPF, Cartão, Dono da Loja e Nome da Loja.**

---

 Formato do Arquivo 

Cada linha do arquivo possui exatamente 80 caracteres estruturados da seguinte forma:

| Posição | Tamanho | Campo | Descrição |
| :---: | :---: | :--- | :--- |
| **1** | 1 | Tipo | Tipo da transação (1 a 9) |
| **2-9** | 8 | Data | Data da ocorrência (AAAAMMDD) |
| **10-19** | 10 | Valor | Valor da movimentação em centavos |
| **20-30** | 11 | CPF | CPF do beneficiário |
| **31-42** | 12 | Cartão | Cartão utilizado na transação |
| **43-48** | 6 | Hora | Hora da ocorrência (HHMMSS) |[Uploading Posicoes.txt…]()

| **49-62** | 14 | Dono da Loja | Nome do representante da loja |
| **63-80** | 19 | Nome da Loja | Nome do estabelecimento comercial |

---

 Endpoints da API

1. Upload de Arquivo 
* **`POST /transacoes/arqivos`
* **Body:** `multipart/form-data`
* **Parâmetro:** `file` (Arquivo `.txt`)
* **Descrição:** Lê o arquivo, processa linha por linha e persiste as transações no banco de dados.

2. Listar Transações**
* **`GET /transacoes`**
* **Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "tipo": "FINANCIAMENTO",
    "data": "2019-03-01",
    "valor": 142.00,
    "cpf": "96206760174",
    "cartao": "753****13200",
    "hora": "13:20:00",
    "donoLoja": "JOAO PEDRO THIAGO",
    "nomeLoja": "MERCADO DA LOJA"
  }
```
---
*ARQUIVO*

```src/
└── main/
    └── resources/
        └── arquivos/
            └── teste.txt
```
