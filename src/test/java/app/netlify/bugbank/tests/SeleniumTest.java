package app.netlify.bugbank.tests;

import app.netlify.bugbank.data.TestData;
import app.netlify.bugbank.driver.DriverManager;
import app.netlify.bugbank.pages.HomePage;
import app.netlify.bugbank.pages.LoginPage;
import app.netlify.bugbank.pages.TransferPage;
import app.netlify.bugbank.settings.ExtentReportSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SeleniumTest extends ExtentReportSetup {
    private LoginPage loginPage;
    private HomePage homePage;
    private TransferPage transferPage;

    @BeforeEach
    public void setup() {
        setupExtentReports();
        DriverManager.startDriver();
        DriverManager.inicializarPastaDeEvidencias();
        loginPage = new LoginPage();
        homePage = new HomePage();
        transferPage = new TransferPage();
    }

    @AfterEach
    public void teardown() {
        DriverManager.killDriver();
    }

    @Test
    void testeRealizarTransferenciaSucesso() {
        try {
            extentTest = extent.createTest("Teste de transferencia realizada com sucesso");
            loginPage.cadastrarConta(TestData.USER_1, TestData.SENHA, TestData.NOME_1);
            String[] conta = loginPage.cadastrarConta(TestData.USER_2, TestData.SENHA, TestData.NOME_2).split("-");
            extentTest.info("Conta do recebedor criada: " + conta[0] + "-" + conta[1]);
            loginPage.fazerLogin(TestData.USER_2, TestData.SENHA);
            homePage.verificarNomeUsuario("Olá " + TestData.NOME_2 + ",");
            homePage.pegarSaldoRecebedor();
            homePage.fazerLogout();
            loginPage.fazerLogin(TestData.USER_1, TestData.SENHA);
            homePage.verificarNomeUsuario("Olá " + TestData.NOME_1 + ",");
            homePage.pegarSaldoEmissor();
            homePage.acessarTransferencia();
            transferPage.realizarTransferencia(conta[0], conta[1], TestData.VALOR, TestData.DESCRICAO);
            transferPage.verificarTransferenciaSucesso();
            homePage.verificarSaldoEmissor(TestData.VALOR.replace(".", ""));
            homePage.fazerLogout();
            loginPage.fazerLogin(TestData.USER_2, TestData.SENHA);
            homePage.verificarSaldoRecebedor(TestData.VALOR.replace(".", ""));
        } catch (AssertionError e) {
            extentTest.fail("Teste falhou: " + e.getMessage());
            Assertions.fail("Teste falhou: " + e.getMessage());
        }
    }
}
