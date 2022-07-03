import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

public class Run {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        invokeBrowser();
        selectSingleCheckBox(By.id("male"));
        selectAllCheckBoxes(By.cssSelector("input[type='checkbox'][class*='form-check-input']"));
        selectByChoice(By.cssSelector("input[type='checkbox']+label[class*='custom']"),"selenium","testng");
        Thread.sleep(2000);

    }

    public  static void selectByChoice(By locator,String ...value) {
        List<WebElement>myList=getElements(locator);
        if(!value[0].equalsIgnoreCase("All")){
            for(WebElement element:myList){
                String str=element.getAttribute("for");
                for(String s:value){
                    if(str.equalsIgnoreCase(s)){
                        element.click();
                        break;
                    }
                }
            }

        }else{
            for(WebElement element:myList){
                element.click();
            }
        }
    }

    private static void invokeBrowser() {
        try {
            driver = WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.get("https://itera-qa.azurewebsites.net/home/automation");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSelected(By locator) {
        boolean status = getElement(locator).isSelected();
        return status;
    }

    public static WebElement getElement(By locator) {
        WebElement element;
        try {
            element = driver.findElement(locator);
            return element;

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<WebElement> getElements(By locator) {
        List<WebElement> list;
        try {
            list = driver.findElements(locator);
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void selectSingleCheckBox(By locator) {
        boolean status = isSelected(locator);
        if (!status) {
            getElement(locator).click();
        }
    }

    public static void selectAllCheckBoxes(By locator) {
        List<WebElement> myList = getElements(locator);
        for (WebElement item : myList) {
            item.click();
        }
    }
}
