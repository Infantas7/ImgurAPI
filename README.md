# Projeto Relatório

Este projeto consiste em um serviço de relatórios que busca posts consumindo a  API Imgur com base em uma tag específica. Criando um relatório com os 500 posts com o maoior numero de UpVotes.

---
## Endpoints

### 1. Buscar Posts por Tag

- **Endpoint:** `GET /posts?tag={tag}`
- **Descrição:** Retorna posts relacionados à tag especificada.
- **Parâmetros:**
  - `tag` (obrigatório): A tag para a busca dos posts.
---
## Como Rodar o Projeto



## Observações

- Certifique-se de que o cabeçalho de autorização (`Authorization`) esteja configurado corretamente no método `buscarPosts` do `RelatorioService`. Caso necessário, atualize o valor do token de acesso.
```bash
Linha 90: header(HttpHeaders.AUTHORIZATION, "Client-ID asdsadasdasd") -> Adicionar seu Client-ID
```
