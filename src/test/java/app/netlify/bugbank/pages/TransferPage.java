package app.netlify.bugbank.pages;

import app.netlify.bugbank.driver.DriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransferPage extends BasePage {
    private final WebDriver driver;

    @FindBy(css = "[name=\"accountNumber\"]")
    private WebElement campoConta;

    @FindBy(css = "[name=\"digit\"]")
    private WebElement campoDigito;

    @FindBy(css = "[name=\"transferValue\"]")
    private WebElement campoValor;

    @FindBy(css = "[name=\"description\"]")
    private WebElement campoDescricao;

    @FindBy(css = "[type=\"submit\"]")
    private WebElement botaoTransferir;

    @FindBy(id = "modalText")
    private WebElement textoAlertaTransferencia;

    @FindBy(id = "btnCloseModal")
    private WebElement botaoFecharAlerta;

    @FindBy(id = "btnBack")
    private WebElement botaoVoltar;

    public TransferPage() {
        this.driver = DriverManager.startDriver();
        PageFactory.initElements(driver, this);
    }

    public void realizarTransferencia(String conta, String digito, String valor, String descricao) {
        preencher(campoConta, conta);
        preencher(campoDigito, digito);
        preencher(campoValor, valor);
        preencher(campoDescricao, descricao);
        clicar(botaoTransferir);
    }

    public void verificarTransferenciaSucesso() {
        elementoContemTexto(textoAlertaTransferencia, "Transferencia realizada com sucesso");
        clicar(botaoFecharAlerta);
        clicar(botaoVoltar);
    }


}
