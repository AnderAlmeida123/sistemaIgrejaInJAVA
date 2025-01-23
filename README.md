# Sistema de Gestão de Igreja em Java

Bem-vindo ao **Sistema de Gestão de Igreja**, um sistema desenvolvido em Java para auxiliar na gestão de informações e processos relacionados a igrejas, como cadastro de membros, controle de movimentação de produtos e gerenciamento de setores.

---

## 🚀 Funcionalidades

- **Cadastro de Membros**: Gerencie informações pessoais e organizacionais dos membros da igreja.
- **Controle de Produtos**: Realize movimentações de entrada e saída de produtos no estoque.
- **Gestão de Setores**: Organize e atribua setores para diferentes atividades e departamentos.
- **Autenticação e Autorização**: Controle de acesso seguro ao sistema (planejado/futuro).
- **Listagem e Relatórios**: Visualize e exporte informações de maneira prática.

---

## 🛠️ Tecnologias Utilizadas

- **Java** (Spring Boot, Hibernate, JPA)
- **Banco de Dados**: PostgreSQL
- **Frontend**: HTML5, CSS3, Bootstrap, Thymeleaf
- **Gestão de Dependências**: Maven
- **Controle de Versão**: Git e GitHub

---

## 📂 Estrutura do Projeto

```
sistemaIgrejaInJAVA/
├── src/
│   ├── main/
│   │   ├── java/com/projeto/sistemaIgreja/
│   │   │   ├── controller/         # Controladores MVC
│   │   │   ├── models/             # Modelos de Entidade
│   │   │   ├── repository/         # Repositórios (Camada de Persistência)
│   │   ├── resources/
│   │   │   ├── templates/          # Arquivos HTML (Thymeleaf)
│   │   │   ├── application.properties # Configurações do Spring Boot
├── pom.xml                         # Configurações e dependências do Maven
```

---

## 💻 Como Executar o Projeto

### Pré-requisitos

- **JDK 17 ou superior**
- **Maven** instalado
- **MySQL** configurado

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/AnderAlmeida123/sistemaIgrejaInJAVA.git
   ```
2. Configure o banco de dados:
   - Crie um banco de dados PostgreSQL com o nome `sistema_igreja`.
   - Atualize o arquivo `application.properties` com suas credenciais de acesso ao banco.
3. Compile e execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
4. Acesse o sistema no navegador:
   ```
   http://localhost:8080
   ```

---

## 📝 Planejamento Futuro

- Implementar autenticação e autorização.
- Melhorar a interface do usuário com design mais responsivo.
- Adicionar exportação de relatórios em PDF e Excel.
- Criar testes automatizados para aumentar a cobertura de código.

---

## 📖 Contribuição

Sinta-se à vontade para contribuir com melhorias ou novas funcionalidades. Para isso:

1. Faça um fork do repositório.
2. Crie uma branch para a sua funcionalidade:
   ```bash
   git checkout -b minha-feature
   ```
3. Realize o commit das suas alterações:
   ```bash
   git commit -m "Minha nova funcionalidade"
   ```
4. Envie para o repositório remoto:
   ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

---

## 🛡️ Licença

Este projeto está sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

---

## 📫 Contato

- **Autor**: Anderson Almeida  
- **GitHub**: [@AnderAlmeida123](https://github.com/AnderAlmeida123)  
- **Email**: [anderson_raft@hotmail.com]

---

**Desenvolvido com 💙 e Java.**
