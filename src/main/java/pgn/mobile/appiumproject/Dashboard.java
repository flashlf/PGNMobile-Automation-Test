/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pgn.mobile.appiumproject;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pgn.mobile.appiumproject.constants.AppConstants;
/**
 *
 * @author REDHAT
 * TODO :
 * -[ ] Logic Add Id Pelanggan
 */
public class Dashboard {
    
    public static <Integer, String> void tourGuide(HashMap<Integer, String> mapTour) throws Exception {
        System.out.println(Main.ANSI_GREEN + "Test Case Tour Guide starting..." + Main.ANSI_RESET);
        WebDriverWait wait = new WebDriverWait(Main.driver, Duration.ofSeconds(3));
        WebElement tourContent;
        String xpathElem;
        for (Map.Entry<Integer, String> entry : mapTour.entrySet()) {
            
            int key = (int) entry.getKey();
            String value = entry.getValue();
            System.out.println(entry.getKey() + ": " + entry.getValue());
            System.out.println("Var => " + key + ": " + entry.getValue());
            switch (value.toString()) {
                case "skip" :
                    xpathElem = (String) AppConstants.XPATH_TOUR_STEP_SKIP[key];                    
                    break;
                case "back" :
                    xpathElem = (String) AppConstants.XPATH_TOUR_STEP_BACK[key];
                    break;
                case "continue" :
                default:
                    xpathElem = (String) AppConstants.XPATH_TOUR_STEP_CONTINUE[key];                    
                    break;
            }
//            Thread.sleep(3000);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathElem.toString()))).isDisplayed();
            Main.driver.findElement(By.xpath(xpathElem.toString())).click();
            
        }
    }
    
    public static void addIdPelanggan(Boolean skip) {
        System.out.println(Main.ANSI_GREEN + "Test Case Add ID Pelanggan starting..." + Main.ANSI_RESET);
        WebDriverWait wait = new WebDriverWait(Main.driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(AppConstants.XPATH_STRING_ADD_ID_PEL))).isDisplayed();
        
        if (skip) {            
            WebElement btnLewati = Main.driver.findElement(By.xpath(AppConstants.XPATH_SKIP_ADD_ID_PEL));
            btnLewati.clear();
        } else { // Add Id Pelanggan
            WebElement btnAddPel = Main.driver.findElement(By.xpath(AppConstants.XPATH_ADD_ID_PEL));
            btnAddPel.click();
        }
    }
}
