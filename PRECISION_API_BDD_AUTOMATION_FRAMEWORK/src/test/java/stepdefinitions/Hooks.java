package stepdefinitions;

import base.BaseTest;
import io.cucumber.java.Before;

public class Hooks {

    @Before
    public void setup() {
        System.out.println("HOOK EXECUTED");
        BaseTest.setup();
    }
}