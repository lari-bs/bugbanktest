package app.netlify.bugbank.pages;

import app.netlify.bugbank.driver.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
    private final WebDriver driver;
    int saldoAtualRecebedor;
    int saldoAtualEmissor;

    @FindBy(id = "textName")
    private WebElement textoNome;

    @FindBy(id = "textBalance")
    private WebElement textoSaldo;

    @FindBy(id = "btnExit")
    private WebElement botaoSair;

    @FindBy(id = "btn-TRANSFERÊNCIA")
    private WebElement botaoTransferencia;

    public HomePage() {
        this.driver = DriverManager.startDriver();
        PageFactory.initElements(driver, this);
    }

    public void fazerLogout() {
        clicar(botaoSair);
    }

    public void acessarTransferencia() {
        clicar(botaoTransferencia);
    }

    public void pegarSaldoRecebedor() {
        saldoAtualRecebedor = Integer.parseInt(textoSaldo.getText().replaceAll("[^0-9]", ""));
        extentTest.info("Saldo atual do recebedor: " + saldoAtualRecebedor);
    }

    public void pegarSaldoEmissor() {
        saldoAtualEmissor = Integer.parseInt(textoSaldo.getText().replaceAll("[^0-9]", ""));
        extentTest.info("Saldo atual do emissor: " + saldoAtualEmissor);
    }

    public void verificarNomeUsuario(String saudacao) {
        Assertions.assertEquals(saudacao, textoNome.getText());
    }

    public void verificarSaldoEmissor(String valor) {
        Assertions.assertEquals(saldoAtualEmissor - Integer.parseInt(valor), Integer.parseInt(textoSaldo.getText().replaceAll("[^0-9]", "")));
        extentTest.info("Saldo atual do emissor após transferencia: " + Integer.parseInt(textoSaldo.getText().replaceAll("[^0-9]", "")));
        DriverManager.tirarPrint();
    }

    public void verificarSaldoRecebedor(String valor) {
        Assertions.assertEquals(saldoAtualRecebedor + Integer.parseInt(valor), Integer.parseInt(textoSaldo.getText().replaceAll("[^0-9]", "")));
        extentTest.info("Saldo atual do recebedor após transferencia: " + Integer.parseInt(textoSaldo.getText().replaceAll("[^0-9]", "")));
        DriverManager.tirarPrint();
    }


}
