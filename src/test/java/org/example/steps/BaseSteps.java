package org.example.steps;

import org.example.core.ScenarioBrowser;

public abstract class BaseSteps {

    protected final ScenarioBrowser scenarioBrowser;

    protected BaseSteps(final ScenarioBrowser scenarioBrowser) {
        this.scenarioBrowser = scenarioBrowser;
    }
}
