package org.example.steps;

import io.cucumber.java.en.Then;
import lombok.extern.slf4j.Slf4j;
import org.example.core.ScenarioBrowser;
import org.example.pageobjects.PersonalInformationPage;

@Slf4j
public class CheckOutSteps extends BaseSteps {

    private final PersonalInformationPage personalInformationPage;

    public CheckOutSteps(final ScenarioBrowser scenarioBrowser,
                         final PersonalInformationPage personalInformationPage) {
        super(scenarioBrowser);
        this.personalInformationPage = personalInformationPage;
    }

    @Then("The Personal information page is displayed")
    public void personalInformationPageDisplayed() {
        log.info("The Personal information page is displayed");
        personalInformationPage.isDisplayed();
    }
}
