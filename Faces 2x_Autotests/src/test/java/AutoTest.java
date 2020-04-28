import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import javax.swing.*;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class AutoTest {

    @BeforeClass
    public static void before(){
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void User_can_delete_an_unoccupied_position(){
        open("http://localhost:7000/");
        $ (By.id("login")).sendKeys("user");
        $ (By.id("pwd")).sendKeys("user");
        $ (By.id("signIn")).click();
        $ ("body > div > div > div > ul > li:nth-child(2)").click();
        $ (By.id("name")).sendKeys("Automation Engineer");
        $ ("#new-title-form > div > div > main > div > div > button:nth-child(1)").click();
        $ ("#new-title-form > div > div > main > table > tbody > tr:last-child > td:nth-child(3) > button.btn.btn.btn-dark\n").click();
        $ ("#new-title-form > div > div > main > table > tbody > tr:last-child > td:nth-child(2) > label").shouldNotHave(exactText("Automation Engineer"));
    }

    @Test
    public void User_cannot_delete_an_occupied_position(){
        open("http://localhost:7000/");
        $ (By.id("login")).sendKeys("user");
        $ (By.id("pwd")).sendKeys("user");
        $ (By.id("signIn")).click();
        $ ("body > div > div > div > ul > li:nth-child(2)").click();
        $ (By.id("name")).sendKeys("Most Valuable Player");
        $ ("#new-title-form > div > div > main > div > div > button:nth-child(1)").click();
        $ ("#new-title-form > div > div > div > ul > li:nth-child(1) > div > a > h4").click();
        $ ("body > div > div > main > div.btn-toolbar.justify-content-between.mb-4 > a").click();
        $ (By.id("firstName")).sendKeys("John");
        $ (By.id("lastName")).sendKeys("Connor");
        $ (By.id("titleForNewEmployee")).selectOption("Most Valuable Player");
        $ ("#employee-sample-data > div > button.btn.btn-primary.mt-3").click();
        $ ("body > div > div > div > ul > li:nth-child(2) > div > a > h4").click();
        $ ("#new-title-form > div > div > main > table > tbody > tr:last-child > td:nth-child(3) > button.btn.btn.btn-dark\n").click();
        $ ("#new-title-form > div > div > main > table > tbody > tr:last-child > td:nth-child(2) > label").shouldHave(exactText("Most Valuable Player"));
    }

    @Test
    public void Login_is_case_insensitive(){
        open("http://localhost:7000/");
        $ (By.id("login")).sendKeys("uSER");
        $ (By.id("pwd")).sendKeys("user");
        $ (By.id("signIn")).click();
        $ ("body > header > div > a").shouldHave(exactText("Logout"));
    }

    @Test
    public void User_can_remove_an_employee_card(){
        open("http://localhost:7000/");
        $ (By.id("login")).sendKeys("user");
        $ (By.id("pwd")).sendKeys("user");
        $ (By.id("signIn")).click();
        $ ("body > div > div > main > div.btn-toolbar.justify-content-between.mb-4 > a").click();
        $ (By.id("firstName")).sendKeys("Sarah");
        $ (By.id("lastName")).sendKeys("Connor");
        $ (By.id("titleForNewEmployee")).selectOption("Senior Software Engineer");
        $ ("#employee-sample-data > div > button.btn.btn-primary.mt-3").click();
        $ ("body > div > div > main > div.row > div.col-md-9.order-md-1 > div > div:last-child #delete-employees-form > button > svg > path").click();
        $ ("body > div > div > main > div.row > div.col-md-9.order-md-1 > div > div:last-child > div.card-header > h5").shouldNotHave(exactText("Sarah Connor"));
    }

    @Test
    public void Removed_position_cannot_be_found_via_search(){
        open("http://localhost:7000/");
        $ (By.id("login")).sendKeys("user");
        $ (By.id("pwd")).sendKeys("user");
        $ (By.id("signIn")).click();
        $ ("body > div > div > div > ul > li:nth-child(2)").click();
        $ (By.id("name")).sendKeys("DevOps Engineer");
        $ ("#new-title-form > div > div > main > div > div > button:nth-child(1)").click();
        $ ("#new-title-form > div > div > main > table > tbody > tr:last-child > td:nth-child(3) > button.btn.btn.btn-dark\n").click();
        $ (By.id("name")).sendKeys("DevOps Engineer");
        $ ("#new-title-form > div > div > main > div > div > button:nth-child(2)").click();
        $ ("#new-title-form > div > div > main > table > tbody").shouldNotHave(text("DevOps Engineer"));
    }

}
