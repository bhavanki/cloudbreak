package com.sequenceiq.it.cloudbreak.newway.testcase.mock;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sequenceiq.cloudbreak.api.endpoint.v4.util.requests.RepoConfigValidationV4Request;
import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.repoconfig.RepoConfigValidationTestAction;
import com.sequenceiq.it.cloudbreak.newway.context.MockedTestContext;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.entity.repoconfig.RepoConfigValidationTestDto;
import com.sequenceiq.it.cloudbreak.newway.testcase.AbstractIntegrationTest;

public class RepoConfigValidationTest extends AbstractIntegrationTest {

    private static final String DATA_PROVIDER_FOR_REPO_CONFIG_TEST = "contextAndTestData";

    private static final String DEFAULT_URL_VALUE = "http://someurl.com";

    @BeforeMethod
    public void beforeMethod(Object[] data) {
        createDefaultUser((TestContext) data[0]);
    }

    @Test(dataProvider = DATA_PROVIDER_FOR_REPO_CONFIG_TEST)
    public void testPostRepositoryConfigValidationAgainstDifferentlyParameterizedRequest(MockedTestContext testContext, TestData testData) {
        testContext
                .given(RepoConfigValidationTestDto.class)
                .withRequest(testData.request())
                .when(RepoConfigValidationTestAction::postRepositoryConfigValidation)
                .then(testData::resultValidation)
                .validate();
    }

    @DataProvider(name = DATA_PROVIDER_FOR_REPO_CONFIG_TEST)
    public Object[][] dataProvider() {
        var testDataValues = TestData.values();
        var data = new Object[testDataValues.length][2];
        var testContext = getBean(MockedTestContext.class);
        for (int i = 0; i < testDataValues.length; i++) {
            data[i][0] = testContext;
            data[i][1] = testDataValues[i];
        }
        return data;
    }

    private enum TestData {

        AMBARI_BASE_URL {
            @Override
            public RepoConfigValidationV4Request request() {
                var request = new RepoConfigValidationV4Request();
                request.setAmbariBaseUrl(DEFAULT_URL_VALUE);
                return request;
            }

            @Override
            public RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc) {
                entityAndResponseExists(entity);
                assertTrue(entity.getResponse().getAmbariBaseUrl());

                assertNull(entity.getResponse().getMpackUrl());
                assertNull(entity.getResponse().getStackBaseURL());
                assertNull(entity.getResponse().getUtilsBaseURL());
                assertNull(entity.getResponse().getAmbariGpgKeyUrl());
                assertNull(entity.getResponse().getVersionDefinitionFileUrl());
                return entity;
            }
        },

        AMBARI_REPO_GPG_KEY {
            @Override
            public RepoConfigValidationV4Request request() {
                var request = new RepoConfigValidationV4Request();
                request.setAmbariGpgKeyUrl(DEFAULT_URL_VALUE);
                return request;
            }

            @Override
            public RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc) {
                entityAndResponseExists(entity);
                assertTrue(entity.getResponse().getAmbariGpgKeyUrl());

                assertNull(entity.getResponse().getMpackUrl());
                assertNull(entity.getResponse().getStackBaseURL());
                assertNull(entity.getResponse().getUtilsBaseURL());
                assertNull(entity.getResponse().getAmbariBaseUrl());
                assertNull(entity.getResponse().getVersionDefinitionFileUrl());
                return entity;
            }
        },

        STACK_BASE_URL {
            @Override
            public RepoConfigValidationV4Request request() {
                var request = new RepoConfigValidationV4Request();
                request.setStackBaseURL(DEFAULT_URL_VALUE);
                return request;
            }

            @Override
            public RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc) {
                entityAndResponseExists(entity);
                assertTrue(entity.getResponse().getStackBaseURL());

                assertNull(entity.getResponse().getMpackUrl());
                assertNull(entity.getResponse().getUtilsBaseURL());
                assertNull(entity.getResponse().getAmbariBaseUrl());
                assertNull(entity.getResponse().getAmbariGpgKeyUrl());
                assertNull(entity.getResponse().getVersionDefinitionFileUrl());
                return entity;
            }
        },

        UTILS_BASE_URL {
            @Override
            public RepoConfigValidationV4Request request() {
                var request = new RepoConfigValidationV4Request();
                request.setUtilsBaseURL(DEFAULT_URL_VALUE);
                return request;
            }

            @Override
            public RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc) {
                entityAndResponseExists(entity);
                assertTrue(entity.getResponse().getUtilsBaseURL());

                assertNull(entity.getResponse().getMpackUrl());
                assertNull(entity.getResponse().getStackBaseURL());
                assertNull(entity.getResponse().getAmbariBaseUrl());
                assertNull(entity.getResponse().getAmbariGpgKeyUrl());
                assertNull(entity.getResponse().getVersionDefinitionFileUrl());
                return entity;
            }
        },

        VDF_URL {
            @Override
            public RepoConfigValidationV4Request request() {
                var request = new RepoConfigValidationV4Request();
                request.setVersionDefinitionFileUrl(DEFAULT_URL_VALUE);
                return request;
            }

            @Override
            public RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc) {
                entityAndResponseExists(entity);
                assertTrue(entity.getResponse().getVersionDefinitionFileUrl());

                assertNull(entity.getResponse().getMpackUrl());
                assertNull(entity.getResponse().getStackBaseURL());
                assertNull(entity.getResponse().getUtilsBaseURL());
                assertNull(entity.getResponse().getAmbariBaseUrl());
                assertNull(entity.getResponse().getAmbariGpgKeyUrl());
                return entity;
            }
        },

        MPACK_URL {
            @Override
            public RepoConfigValidationV4Request request() {
                var request = new RepoConfigValidationV4Request();
                request.setMpackUrl(DEFAULT_URL_VALUE);
                return request;
            }

            @Override
            public RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc) {
                entityAndResponseExists(entity);
                assertTrue(entity.getResponse().getMpackUrl());

                assertNull(entity.getResponse().getStackBaseURL());
                assertNull(entity.getResponse().getUtilsBaseURL());
                assertNull(entity.getResponse().getAmbariBaseUrl());
                assertNull(entity.getResponse().getAmbariGpgKeyUrl());
                assertNull(entity.getResponse().getVersionDefinitionFileUrl());
                return entity;
            }
        },

        NONE {
            @Override
            public RepoConfigValidationV4Request request() {
                return new RepoConfigValidationV4Request();
            }

            @Override
            public RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc) {
                assertNull(entity.getResponse().getMpackUrl());
                assertNull(entity.getResponse().getStackBaseURL());
                assertNull(entity.getResponse().getUtilsBaseURL());
                assertNull(entity.getResponse().getAmbariBaseUrl());
                assertNull(entity.getResponse().getAmbariGpgKeyUrl());
                assertNull(entity.getResponse().getVersionDefinitionFileUrl());
                return entity;
            }
        };

        public abstract RepoConfigValidationV4Request request();

        public abstract RepoConfigValidationTestDto resultValidation(TestContext tc, RepoConfigValidationTestDto entity, CloudbreakClient cc);

        private static void entityAndResponseExists(RepoConfigValidationTestDto entity) {
            assertNotNull(entity);
            assertNotNull(entity.getResponse());
        }

    }

}
