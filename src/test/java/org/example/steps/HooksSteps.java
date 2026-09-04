package org.example.steps;

import io.cucumber.java.After;
import lombok.extern.slf4j.Slf4j;
import org.example.core.ScenarioBrowser;

@Slf4j
public class HooksSteps extends BaseSteps {

    public HooksSteps(final ScenarioBrowser browser) {
        super(browser);
    }

    @After(order = 0)
    public void closeBrowser() {
        log.info("Close browser {}", scenarioBrowser);
        scenarioBrowser.close();
    }
}
