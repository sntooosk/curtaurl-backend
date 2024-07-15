# CurtaURL

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![JUnit](https://img.shields.io/badge/junit-%23525C69.svg?style=for-the-badge&logo=junit5&logoColor=white)

CurtaURL é um encurtador de links desenvolvido em Java com Spring Boot, projetado para redirecionar links encurtados para suas páginas originais.

## Funcionalidades

- Encurtar URLs longas e gerar links curtos.
- Redirecionar links curtos para as URLs originais.
- Persistência dos links no banco de dados H2.
- Definir prazo de validade para os links encurtados.

## Instruções de Uso

1. Faça uma requisição POST para o endpoint, passando no corpo da requisição o seguinte JSON:
    ```json
    {
        "urlLonga": "seusiteaqui"
    }
    ```

2. O encurtador de URLs gera um link curto composto por um mínimo de 5 e um máximo de 10 caracteres, contendo apenas letras e números.

3. A URL encurtada é salva no banco de dados com um prazo de validade configurável.

4. Ao acessar uma URL encurtada, por exemplo, `https://xxx.com/DXB6V`, o serviço redireciona para a URL original salva no banco de dados. Se a URL não for encontrada ou estiver expirada, o serviço retorna um status `HTTP 404 (Not Found)`.

## Requisitos

- **Java**
- **Spring Boot**
- **JUnit**
- **Banco de Dados H2**

## Executando o Projeto

1. Clone o repositório:
    ```bash
    git clone https://github.com/seu-usuario/CurtaURL.git
    ```
2. Navegue até o diretório do projeto:
    ```bash
    cd CurtaURL
    ```
3. Execute o projeto usando Maven:
    ```bash
    mvn spring-boot:run
    ```

## Contribuição

Sinta-se à vontade para contribuir com o projeto. Para isso, siga os passos abaixo:

1. Faça um fork do repositório.
2. Crie uma nova branch:
    ```bash
    git checkout -b minha-nova-funcionalidade
    ```
3. Faça as alterações desejadas e commit:
    ```bash
    git commit -m "Adiciona nova funcionalidade"
    ```
4. Envie para o repositório remoto:
    ```bash
    git push origin minha-nova-funcionalidade
    ```
5. Abra um Pull Request para revisão.
