package com.sequenceiq.it.cloudbreak.newway.testcase.mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.version.VersionCheckTestAction;
import com.sequenceiq.it.cloudbreak.newway.context.MockedTestContext;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.version.VersionCheckTestDto;
import com.sequenceiq.it.cloudbreak.newway.testcase.AbstractIntegrationTest;

public class VersionCheckTest extends AbstractIntegrationTest {

    @BeforeMethod
    public void beforeMethod(Object[] data) {
        createDefaultUser((TestContext) data[0]);
    }

    @Test(dataProvider = "contextWithTestContextAndInvalidVersionValue")
    public void testGetVersionByInvalidVersionNumber(MockedTestContext testContext, String invalidVersionValue) {
        testContext
                .given(VersionCheckTestDto.class)
                .withVersion(invalidVersionValue)
                .when(VersionCheckTestAction::getCheckClientVersion)
                .then(this::versionIsNotOk)
                .validate();
    }

    @DataProvider(name = "contextWithTestContextAndInvalidVersionValue")
    public Object[][] provideInvalidAttributes() {
        return new Object[][]{
                {getBean(MockedTestContext.class), ""},
                {getBean(MockedTestContext.class), "someOtherInvalidValue"}
        };
    }

    private VersionCheckTestDto versionIsNotOk(TestContext testContext, VersionCheckTestDto testDto, CloudbreakClient cloudbreakClient) {
        assertNotNull(testDto);
        assertNotNull("Response should be filled!", testDto.getResponse());
        assertFalse(testDto.getResponse().isVersionCheckOk());
        assertNotNull("Response message should be filled!", testDto.getResponse().getMessage());
        assertTrue(testDto.getResponse().getMessage().contains("not compatible"));
        return testDto;
    }

}
