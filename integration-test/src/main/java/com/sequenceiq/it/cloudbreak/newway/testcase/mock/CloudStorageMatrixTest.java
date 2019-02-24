package com.sequenceiq.it.cloudbreak.newway.testcase.mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.storagematrix.CloudStorageMatrixTestAction;
import com.sequenceiq.it.cloudbreak.newway.context.MockedTestContext;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.storagematrix.CloudStorageMatrixTestDto;
import com.sequenceiq.it.cloudbreak.newway.testcase.AbstractIntegrationTest;

public class CloudStorageMatrixTest extends AbstractIntegrationTest {

    @BeforeMethod
    public void beforeMethod(Object[] data) {
        createDefaultUser((TestContext) data[0]);
    }

    @Test(dataProvider = TEST_CONTEXT_WITH_MOCK)
    public void testGetCloudStorageMatrix(MockedTestContext testContext) {
        testContext
                .given(CloudStorageMatrixTestDto.class)
                .when(CloudStorageMatrixTestAction::getCloudStorageMatrix)
                .then(this::responsesAreExists)
                .then(this::matrixIsNotEmpty)
                .validate();
    }

    private CloudStorageMatrixTestDto responsesAreExists(TestContext tc, CloudStorageMatrixTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity);
        assertNotNull(entity.getResponses());
        return entity;
    }

    private CloudStorageMatrixTestDto matrixIsNotEmpty(TestContext tc, CloudStorageMatrixTestDto entity, CloudbreakClient cc) {
        assertNotNull(entity);
        assertNotNull(entity.getResponses());
        assertFalse(entity.getResponses().isEmpty());
        return entity;
    }

}
