/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package selenium_project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author Vipul Chandoor
 */
public class Selenium_Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {

        Scanner scanid = new Scanner(new File("NorthwestIDs.txt"));
        Scanner scanpassword = new Scanner(new File("password.txt"));
        PrintWriter pwrt = new PrintWriter(new File("output.txt"));

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\S530459\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://nwmissouri.instructure.com/login/ldap");

        while (scanid.hasNext()) {

            WebElement searchbox = driver.findElement(By.id("pseudonym_session_unique_id"));
            String str = scanid.next();
            searchbox.sendKeys(str);
            System.out.println(str);
//            Thread.sleep(1000);
            WebElement searchbox1 = driver.findElement(By.id("pseudonym_session_password"));
            searchbox1.sendKeys(scanpassword.next());
//            Thread.sleep(1000);
            WebElement searchbox2 = driver.findElement(By.tagName("Button"));
            searchbox2.click();
            Thread.sleep(2000);
            pwrt.println("https://nwmissouri.instructure.com/login/ldap".equals(driver.getCurrentUrl()));
            if (!"https://nwmissouri.instructure.com/login/ldap".equals(driver.getCurrentUrl())) {
                driver.findElement(By.cssSelector("h2[title$='GRAD DIRECT PROJECT II 03FA18']")).click();
                driver.findElement(By.partialLinkText("Workshops")).click();
                Thread.sleep(1000);
            }

//            pwrt.println("https://nwmissouri.instructure.com/login/ldap".equals(driver.getCurrentUrl()));
            driver.get("https://nwmissouri.instructure.com/login/ldap");
//              Thread.sleep(1000);
            
        }
        pwrt.close();
        scanid.close();
        scanpassword.close();
        driver.quit();

    }

}
