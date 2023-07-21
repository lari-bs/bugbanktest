package app.netlify.bugbank.pages;

import app.netlify.bugbank.driver.DriverManager;
import app.netlify.bugbank.settings.ExtentReportSetup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class BasePage extends ExtentReportSetup {

    protected void elementoContemTexto(WebElement element, String texto) {
        WebDriverWait wait = new WebDriverWait(DriverManager.startDriver(), Duration.ofSeconds(15));
        DriverManager.tirarPrint();
        wait.until(ExpectedConditions.textToBePresentInElement(element, texto));
    }

    protected void clicar(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.startDriver(), Duration.ofSeconds(15));
        DriverManager.tirarPrint();
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void preencher(WebElement element, String value) {
        element.sendKeys(value);
    }

    public void limparEpreencher(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }
}