package app.netlify.bugbank.pages;

import app.netlify.bugbank.driver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPage extends BasePage {
    private final WebDriver driver;

    @FindBy(css = "div.card__login div [name=\"email\"]")
    private WebElement campoEmail;

    @FindBy(css = "div.card__login div [name=\"password\"]")
    private WebElement campoSenha;

    @FindBy(xpath = "//button[contains(text(),'Acessar')]")
    private WebElement botaoAcessar;

    @FindBy(xpath = "//button[contains(text(),'Registrar')]")
    private WebElement botaoRegistrar;

    @FindBy(css = "div.card__register div [name=\"email\"]")
    private WebElement campoEmailCadastro;

    @FindBy(css = "div.card__register div [name=\"name\"]")
    private WebElement campoNomeCadastro;

    @FindBy(css = "div.card__register div [name=\"password\"]")
    private WebElement campoSenhaCadastro;

    @FindBy(css = "div.card__register div [name=\"passwordConfirmation\"]")
    private WebElement campoConfirmacaoSenhaCadastro;

    @FindBy(css = " div.card__register > form > div:nth-child(6) > label")
    private WebElement opcaoCadastrarComSaldo;

    @FindBy(xpath = "//button[contains(text(),'Cadastrar')]")
    private WebElement botaoCadastrar;

    @FindBy(id = "modalText")
    private WebElement textoConfirmacaoCadastro;

    @FindBy(id = "btnCloseModal")
    private WebElement botaoFecharAlertaCadastro;


    public LoginPage() {
        this.driver = DriverManager.startDriver();
        PageFactory.initElements(driver, this);
    }

    public void acessarbugBank() {
        driver.get("https://bugbank.netlify.app/");
    }

    public String cadastrarConta(String email, String senha, String nome) {
        acessarbugBank();
        clicar(botaoRegistrar);
        limparEpreencher(campoEmailCadastro, email);
        limparEpreencher(campoNomeCadastro, nome);
        limparEpreencher(campoSenhaCadastro, senha);
        limparEpreencher(campoConfirmacaoSenhaCadastro, senha);
        clicar(opcaoCadastrarComSaldo);
        clicar(botaoCadastrar);
        String conta = pegarConta();
        clicar(botaoFecharAlertaCadastro);
        return conta;
    }

    private String pegarConta() {
        elementoContemTexto(textoConfirmacaoCadastro, "criada com sucesso");
        String textoConta = textoConfirmacaoCadastro.getText();
        Pattern padraoNumeroConta = Pattern.compile("\\b\\d+-\\d+\\b");
        Matcher matcher = padraoNumeroConta.matcher(textoConta);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    public void fazerLogin(String email, String senha) {
        preencher(campoEmail, email);
        preencher(campoSenha, senha);
        clicar(botaoAcessar);
    }


}
