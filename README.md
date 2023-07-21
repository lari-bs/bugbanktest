# Bugbank Test

Este é um projeto de testes automatizados utilizando Selenium em Java para testar o Bugbank, um sistema de simulação de banco.

## Estrutura de Pastas

```
bugbanktest
├── report.html
├── screenshots
│   └── 2023-07-21_00-23
│       └── test1
│           └── 0.png
├── settings.gradle
└── src
    └── test
        ├── java
        │   └── app
        │       └── netlify
        │           └── bugbank
        │               ├── data
        │               │   └── TestData.java
        │               ├── driver
        │               │   └── DriverManager.java
        │               ├── pages
        │               │   ├── BasePage.java
        │               │   ├── HomePage.java
        │               │   ├── LoginPage.java
        │               │   └── TransferPage.java
        │               ├── settings
        │               │   └── ExtentReportSetup.java
        │               └── tests
        │                   └── SeleniumTest.java
        └── resources
```

- A pasta `screenshots` contém capturas de tela tiradas durante a execução dos testes. As capturas de tela estão organizadas por data e cada teste possui uma pasta separada.

- A pasta `src/test/java` contém os arquivos de código-fonte dos testes e classes relacionadas.

- O arquivo `report.html` contém o report para ser aberto no browser.

## Configuração do Ambiente

O projeto utiliza o Selenium WebDriver para automação de testes de interface web. Para executar os testes, você precisa ter o ChromeDriver instalado. Certifique-se de ter a versão correta do ChromeDriver compatível com a versão do navegador Chrome instalado na sua máquina.

Você pode fazer o download do ChromeDriver no seguinte link: [https://sites.google.com/a/chromium.org/chromedriver/downloads](https://sites.google.com/a/chromium.org/chromedriver/downloads)

Após o download, extraia o arquivo e adicione o local do executável do ChromeDriver ao PATH do sistema operacional.

### Gradle
O projeto utiliza o Gradle como gerenciador de dependências e para executar os testes.

## Executando os Testes

Para executar os testes, abra o terminal na raiz do projeto e execute o seguinte comando:

```
./gradlew clean test
```

Este comando limpa os resultados anteriores, compila o projeto e executa os testes automatizados.

## Relatório de Testes

Após a execução dos testes, o relatório é gerado em: `file://$rootDir/report.html`. Você pode abrir este arquivo no seu navegador para visualizar os resultados detalhados dos testes.

## Dependências

O projeto utiliza o Gerenciador de Dependências Gradle para gerenciar suas dependências. As dependências estão configuradas no arquivo `build.gradle` na raiz do projeto.

As principais dependências incluídas são:
- JUnit: Framework para escrever e executar testes unitários em Java.
- Selenium WebDriver: Biblioteca para automação de testes de interface web.
- ExtentReports: Biblioteca para report em html.

## Outras Observações

- O projeto segue uma estrutura básica de Page Object Model (POM) para organizar as interações com as páginas web.
