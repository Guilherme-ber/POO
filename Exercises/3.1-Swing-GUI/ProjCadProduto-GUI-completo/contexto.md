# Projeto Cadastro de Produto (CadProduto)

Este projeto é um exercício prático da disciplina de Programação Orientada a Objetos para o curso de Ciência da Computação. O principal objetivo é modelar um sistema de cadastro de **Produtos**, introduzindo a construção de Interfaces Gráficas (GUI) com **Java Swing**.

## 🎯 Objetivo Principal
Demonstrar na prática a construção de uma aplicação visual e a importância da **separação de responsabilidades** (Princípio de Responsabilidade Única - SRP). O projeto evidencia como isolar a lógica de gerenciamento e armazenamento de dados da lógica de apresentação (telas).

## 🏗️ Estrutura e Decisões Arquiteturais

A arquitetura do projeto foi desenhada para facilitar a transição de aplicações de terminal para aplicações com interface gráfica. A principal decisão foi a adoção explícita de uma classe Gerenciadora.

### 1. A Boa Prática do `GerenciadorProduto`
Diferente de modelagens onde as coleções são atributos orgânicos de outras entidades (como em uma relação Empresa-Funcionário), aqui a lista de produtos atua como a base de dados da aplicação. Para evitar que a interface gráfica assuma essa responsabilidade, utilizamos o `GerenciadorProduto`:

- **Encapsulamento e Delegação:** A classe `GerenciadorProduto` encapsula a `List<Produto>` e disponibiliza métodos limpos para operações de CRUD (Adicionar, Remover, Buscar, Atualizar).
- **Desacoplamento da View:** A interface `FrProduto` (Java Swing) não sabe como a lista é manipulada. Ela apenas coleta os dados digitados nos campos de texto (`edtNome`, `edtPreco`, etc.) e os repassa para o Gerenciador.
- **Preparação para o Futuro:** Se futuramente o sistema for evoluído para salvar os dados em um Banco de Dados SQL, a interface `FrProduto` permanecerá praticamente intacta. Apenas o `GerenciadorProduto` precisará ser adaptado.

### 2. Classes Principais
- **`Produto`:** Entidade de domínio que armazena as propriedades fundamentais (Código, Nome, Custo e Preço).
- **`GerenciadorProduto`:** Classe controladora responsável por manter a lista de produtos em memória e fornecer a lógica de busca, inserção, remoção e listagem.
- **`FrProduto`:** A interface gráfica (View). Contém os componentes visuais (botões, campos de texto) e trata os eventos do usuário, delegando a ação real para o Gerenciador.
- **`ProjetoCadProduto`:** O ponto de entrada da aplicação (`main`), responsável unicamente por instanciar a tela principal e torná-la visível.

### 3. Regra de Ouro da Disciplina para Interfaces
> **"A Interface Gráfica não deve gerenciar dados, apenas exibi-los e capturá-los."**

Essa diretriz ensina aos alunos que eventos de cliques (como o do botão `btnSalvarActionPerformed`) devem ser curtos e focados: instanciar um objeto com os dados da tela e entregá-lo para uma classe especialista (`GerenciadorProduto`), evitando que a View se torne um "Deus" (God Class) cheia de regras de negócio.

## 🚀 Como Executar
O ponto de entrada do sistema encontra-se na classe `ProjetoCadProduto.java`, que contém a função `main`. Ao ser executada, a aplicação abrirá a janela interativa, permitindo que o usuário cadastre, pesquise, altere e exclua produtos visualmente, refletindo todas as alterações em tempo real por meio da listagem gerenciada nos bastidores.
