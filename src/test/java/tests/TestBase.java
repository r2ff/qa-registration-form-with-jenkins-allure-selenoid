package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.Credentials;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static helpers.Attach.getSessionId;

public class TestBase {
    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.startMaximized = true;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;

        //Configuration.remote = "https://"+ Credentials.cc.login() + ":" + Credentials.cc.password() + "@" + System.getProperty("url") + "/wd/hub/";
        Configuration.remote = String.format("https://%s:%s@%s/wd/hub/", Credentials.cc.login(), Credentials.cc.password(), System.getProperty("url"));
    }

    @AfterEach
    public void tearDown() {
        String sessionId = getSessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        closeWebDriver();

        Attach.addVideo(sessionId);
    }



}
