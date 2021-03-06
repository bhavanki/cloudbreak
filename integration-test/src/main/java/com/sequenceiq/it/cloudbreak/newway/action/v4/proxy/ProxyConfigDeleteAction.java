package com.sequenceiq.it.cloudbreak.newway.action.v4.proxy;

import static com.sequenceiq.it.cloudbreak.newway.log.Log.log;
import static com.sequenceiq.it.cloudbreak.newway.log.Log.logJSON;
import static java.lang.String.format;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sequenceiq.it.cloudbreak.newway.CloudbreakClient;
import com.sequenceiq.it.cloudbreak.newway.action.Action;
import com.sequenceiq.it.cloudbreak.newway.context.TestContext;
import com.sequenceiq.it.cloudbreak.newway.dto.proxy.ProxyTestDto;

public class ProxyConfigDeleteAction implements Action<ProxyTestDto> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProxyConfigDeleteAction.class);

    @Override
    public ProxyTestDto action(TestContext testContext, ProxyTestDto testDto, CloudbreakClient client) throws Exception {
        log(LOGGER, format(" Name: %s", testDto.getRequest().getName()));
        logJSON(LOGGER, " Proxy config delete request:\n", testDto.getRequest());
        testDto.setResponse(
                client.getCloudbreakClient()
                        .proxyConfigV4Endpoint()
                        .delete(client.getWorkspaceId(), testDto.getName()));
        logJSON(LOGGER, " Proxy config was deleted successfully:\n", testDto.getResponse());
        log(LOGGER, format(" ID: %s", testDto.getResponse().getId()));
        return testDto;
    }

}