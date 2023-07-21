package app.netlify.bugbank.driver;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;

public class DriverManager {

    public static WebDriver driver;
    private static String evidenceFolderPath;
    private static Integer testIndex = 1;
    private static Integer evidenceIndex;

    public static WebDriver startDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            return driver;
        }
        else
            return driver;
    }

    public static void inicializarPastaDeEvidencias() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm");
        evidenceFolderPath = "./screenshots/" + dateFormat.format(Calendar.getInstance().getTime())
                + File.separator + "test" + testIndex;
        File evidenceFolder = new File(evidenceFolderPath);
        evidenceFolder.mkdir();
        testIndex++;
        evidenceIndex = 0;
    }

    public static void tirarPrint() {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(evidenceFolderPath + File.separator + evidenceIndex + ".png"));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        evidenceIndex++;
    }

    public static void killDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}