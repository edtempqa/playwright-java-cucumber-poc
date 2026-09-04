package org.example.pageobjects;

import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.core.ScenarioBrowser;

import java.util.List;
import java.util.function.Function;
import static java.util.stream.IntStream.range;

@Slf4j
public abstract class BasePage {

    protected final Page page;

    protected BasePage(final ScenarioBrowser scenarioBrowser) {
        this.page = scenarioBrowser.page();
    }

    protected FrameLocator getFrameLocator() {
        return page.frameLocator("iframe#framelive");
    }

    protected <T> List<T> map(
            final Locator locator,
            final Function<Locator, T> mapper) {

        return range(0, locator.count())
                .mapToObj(locator::nth)
                .map(mapper)
                .toList();
    }

}
