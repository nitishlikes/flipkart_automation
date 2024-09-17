package demo.wrappers;
import java.util.*;
import java.text.NumberFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {

    public static void enterText(WebDriver driver, By locator, String text) {
        boolean status;
        try {
            WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement search = driver.findElement(locator);
            search.clear();
            search.sendKeys(text);
            search.sendKeys(Keys.ENTER);
            status = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            status = false;
        }
    }

    public static void clickElement(WebDriver driver, By locator) {
        boolean status;
        try {
            WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            element.click();
            status = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            status = false;
        }
     }

     public static Boolean productRating(WebDriver driver, By locator, double rating) {
        int count = 0;
        boolean status;
        try {
            List<WebElement> products = driver.findElements(locator);
            for (WebElement product : products) {
                if (Double.parseDouble(product.getText()) <= rating)
                    count++;
            } status = true;
            System.out.println("Count of washing machines with rating less than or equal to " + rating + " : " + count);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            status = false;
        }
        return status;
     }

     public static Boolean productTitleAndDiscount(WebDriver driver, By locator, int discount) {
        boolean status;
        try {
            HashMap<String, String> map = new HashMap<>();
            List<WebElement> products = driver.findElements(locator);
            for(WebElement product : products) {
               String discountPercent = product.findElement(By.xpath(".//div[@class='UkUFwK']/span")).getText();
               int discountValue = Integer.parseInt(discountPercent.replaceAll("[^\\d]", ""));
               if (discountValue > discount) {
                   String productTitle = product.findElement(By.xpath(".//div[@class='KzDlHZ']")).getText();
                   map.put(discountPercent, productTitle);
               }
            }
            for (Map.Entry<String, String> productMap : map.entrySet()) 
                System.out.println("Discount percentage is " + productMap.getKey() + " and the title is " + productMap.getValue());
            
             status = true;       
        } catch(Exception e) {
            System.out.println(e.getMessage());
            status = false;
        }
            return status;
     }

     public static Boolean productTitleAndImage(WebDriver driver, By locator) {
        boolean status;
        try {
            Set<Integer> set = new HashSet<>();
            List<WebElement> reviews = driver.findElements(locator);
            for (WebElement review : reviews) {
                int userReview = Integer.parseInt(review.getText().replaceAll("[^\\d]", ""));
                set.add(userReview);
            }
            List<Integer> ReviewList = new ArrayList<>(set);
            Collections.sort(ReviewList,Collections.reverseOrder());
            System.out.println(ReviewList);
            NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
            LinkedHashMap<String, String> productDetailsMap = new LinkedHashMap<>();
            for (int i = 0; i < 5; i++) {
                String formattedUserReviewCount = "(" + numberFormat.format(ReviewList.get(i)) + ")";
                String productTitle = driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"
                        +formattedUserReviewCount+ "')]/../../a[@class='wjcEIp']")).getText();
                String productImgURL = driver.findElement(By.xpath("//div[@class='slAVV4']//span[contains(text(),'"
                        +formattedUserReviewCount+ "')]/../..//img[@class='DByuf4']")).getAttribute("src");
                String hightestReviewCountAndProductTitle = String.valueOf(i+1)+" highest review count: "+formattedUserReviewCount+" Title: "+productTitle;
                productDetailsMap.put(hightestReviewCountAndProductTitle, productImgURL);
            }
            //print title and image url
            for(Map.Entry<String,String> productDetails : productDetailsMap.entrySet()){
                System.out.println(productDetails.getKey()+" and Product image url : "+productDetails.getValue());
            }
            status = true;
        } catch(Exception e) {
            e.printStackTrace();
            status = false;
        }
        return status;
     }
}
