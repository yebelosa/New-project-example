package com.clean.example.acceptance;

import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.TestState;
import com.googlecode.yatspec.state.givenwhenthen.WithTestState;
import org.junit.runner.RunWith;

@RunWith(SpecRunner.class)
/* package */ abstract class YatspecTest implements WithTestState {

    private final TestState testState = new TestState();

    @Override
    public TestState testState() {
        return testState;
    }

    protected <T> void log(String title, T value) {
        testState.log(title, value);
    }
}
