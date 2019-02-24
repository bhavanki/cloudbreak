package com.sequenceiq.it.cloudbreak.newway.testcase.mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.securityrule.SecurityRulesTestAction;
import com.sequenceiq.it.cloudbreak.newway.context.MockedTestContext;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.securityrule.SecurityRulesTestDto;
import com.sequenceiq.it.cloudbreak.newway.testcase.AbstractIntegrationTest;

public class SecurityRulesTest extends AbstractIntegrationTest {

    private static final String DATA_PROVIDER_FOR_SECURITY_RULES_TEST = "contextAndBoolean";

    @BeforeMethod
    public void beforeMethod(Object[] data) {
        createDefaultUser((TestContext) data[0]);
    }

    @Test(dataProvider = DATA_PROVIDER_FOR_SECURITY_RULES_TEST)
    public void testGetSecurityRules(MockedTestContext testContext, Boolean knoxEnabled) {
        testContext
                .given(SecurityRulesTestDto.class)
                .withKnoxEnabled(knoxEnabled)
                .when(SecurityRulesTestAction::getDefaultSecurityRules)
                .then(this::responseExist)
                .then(this::coreIsNotEmpty)
                .then(this::gatewayIsNotEmpty)
                .validate();
    }

    @DataProvider(name = DATA_PROVIDER_FOR_SECURITY_RULES_TEST)
    public Object[][] dataProvider() {
        var testContext = getBean(MockedTestContext.class);
        return new Object[][] {
                {testContext, false},
                {testContext, true},
                {testContext, null}
        };
    }

    private SecurityRulesTestDto responseExist(TestContext tc, SecurityRulesTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity);
        assertNotNull(entity.getResponse());
        return entity;
    }

    private SecurityRulesTestDto coreIsNotEmpty(TestContext tc, SecurityRulesTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity.getResponse().getCore());
        assertFalse(entity.getResponse().getCore().isEmpty());
        return entity;
    }

    private SecurityRulesTestDto gatewayIsNotEmpty(TestContext tc, SecurityRulesTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity.getResponse().getGateway());
        assertFalse(entity.getResponse().getGateway().isEmpty());
        return entity;
    }

}
