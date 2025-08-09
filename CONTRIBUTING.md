# Guia de Contribuição — TypedVO

Muito obrigado por querer contribuir com o TypedVO! Este projeto busca facilitar a integração tipada entre Kotlin e Sankhya JAPE, promovendo produtividade, segurança e código idiomático.

## 🧑‍💻 Como contribuir

### 1. Sugestões e melhorias

- **Sugestões de novas funcionalidades, melhorias, exemplos ou documentação** são sempre bem-vindas.
- Antes de abrir uma issue, verifique se já existe uma discussão ou proposta semelhante.

### 2. Reportando bugs

- Forneça o máximo de detalhes possível: versão, contexto, passos para reproduzir, logs ou trechos de código.
- Indique a versão do TypedVO e do Kotlin/Sankhya JAPE utilizada.

### 3. Criando Pull Requests

- Faça um fork do projeto e crie sua branch a partir de `main`.
- Prefira nomes descritivos para a branch, como `fix/issue-123` ou `feature/delegates-boolean`.
- Siga o padrão do projeto em relação à organização, nomes e estilo de código.
- Adicione exemplos, documentação e testes sempre que possível.
- Descreva claramente no PR o que foi alterado e o motivo.
- Relacione a issue resolvida (se houver) usando `Fixes #123`.

### 4. Boas práticas

- Para VOs tipados, mantenha nomes de variáveis **iguais** aos das colunas do banco.
- Utilize os delegates apropriados (`Delegate`, `DelegateNotNull`, etc.) conforme o tipo da coluna.
- Ao criar extensões ou novos delegates, inclua exemplos de uso no README.
- Prefira código idiomático Kotlin e aproveite as funcionalidades nativas do `DynamicVO`.

### 5. Documentação

- Se sua contribuição alterar ou adicionar funcionalidades, atualize o `README.md` com instruções claras e exemplos de uso.
- Explique os motivos e benefícios da sua mudança.

### 6. Testes

- Sempre que possível, inclua testes automatizados para validar o comportamento.
- Certifique-se de que os testes existentes continuam passando.

## 📝 Licença e direitos autorais

TypedVO é distribuído sob a [Apache License 2.0](LICENSE). Ao contribuir, você concorda que seu código será incorporado sob os termos dessa licença.

## 🤝 Código de Conduta

Respeite os colaboradores e mantenha um ambiente acolhedor e profissional.

---

Dúvidas ou sugestões? Abra uma issue ou participe das discussões!