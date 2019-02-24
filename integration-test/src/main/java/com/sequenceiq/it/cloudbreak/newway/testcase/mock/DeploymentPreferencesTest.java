package com.sequenceiq.it.cloudbreak.newway.testcase.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sequenceiq.cloudbreak.api.endpoint.v4.credentials.providers.CloudPlatform;
import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.deploymentpref.DeploymentPreferencesTestAction;
import com.sequenceiq.it.cloudbreak.newway.context.MockedTestContext;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.deploymentpref.DeploymentPreferencesTestDto;
import com.sequenceiq.it.cloudbreak.newway.testcase.AbstractIntegrationTest;

public class DeploymentPreferencesTest extends AbstractIntegrationTest {

    @BeforeMethod
    public void beforeMethod(Object[] data) {
        createDefaultUser((TestContext) data[0]);
    }

    @Test(dataProvider = TEST_CONTEXT_WITH_MOCK)
    public void testGetDeploymentPreferences(MockedTestContext testContext) {
        testContext
                .given(DeploymentPreferencesTestDto.class)
                .when(DeploymentPreferencesTestAction::getDeployment)
                .then(this::responseExists)
                .then(this::supportedExternalDatabasesExists)
                .then(this::platformEnablementValid)
                .validate();
    }

    private DeploymentPreferencesTestDto responseExists(TestContext tc, DeploymentPreferencesTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity);
        assertNotNull(entity.getResponse());
        return entity;
    }

    private DeploymentPreferencesTestDto supportedExternalDatabasesExists(TestContext tc, DeploymentPreferencesTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity.getResponse().getSupportedExternalDatabases());
        assertFalse(entity.getResponse().getSupportedExternalDatabases().isEmpty());
        return entity;
    }

    private DeploymentPreferencesTestDto platformEnablementValid(TestContext tc, DeploymentPreferencesTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity.getResponse().getPlatformEnablement());
        assertFalse(entity.getResponse().getPlatformEnablement().isEmpty());
        var cloudPlatforms = Set.of(CloudPlatform.values());
        assertEquals(cloudPlatforms.size(), entity.getResponse().getPlatformEnablement().size());
        entity.getResponse().getPlatformEnablement().keySet().forEach(platform -> assertTrue(cloudPlatforms.contains(CloudPlatform.valueOf(platform))));
        return entity;
    }

}
