package com.sequenceiq.it.spark.spi;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.sequenceiq.cloudbreak.cloud.model.CloudInstance;
import com.sequenceiq.cloudbreak.cloud.model.CloudInstanceMetaData;
import com.sequenceiq.cloudbreak.cloud.model.CloudVmInstanceStatus;
import com.sequenceiq.cloudbreak.cloud.model.CloudVmMetaDataStatus;
import com.sequenceiq.cloudbreak.cloud.model.InstanceStatus;
import com.sequenceiq.it.spark.ITResponse;

public class CloudMetaDataStatuses extends ITResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudMetaDataStatuses.class);
    private String mockServerAddress;
    private int sshPort;

    public CloudMetaDataStatuses(String mockServerAddress, int sshPort) {
        this.mockServerAddress = mockServerAddress;
        this.sshPort = sshPort;
    }

    private List<CloudVmMetaDataStatus> createCloudVmMetaDataStatuses(List<CloudInstance> cloudInstances) {
        List<CloudVmMetaDataStatus> cloudVmMetaDataStatuses = new ArrayList<>();
        for (int i = 0; i < cloudInstances.size(); i++) {
            CloudInstance cloudInstance = cloudInstances.get(i);
            CloudInstance cloudInstanceWithId = new CloudInstance(Integer.toString(i), cloudInstance.getTemplate());
            CloudVmInstanceStatus cloudVmInstanceStatus = new CloudVmInstanceStatus(cloudInstanceWithId, InstanceStatus.STARTED);
            CloudInstanceMetaData cloudInstanceMetaData = new CloudInstanceMetaData("192.168.1." + (i + 1), mockServerAddress, sshPort, "MOCK");
            CloudVmMetaDataStatus cloudVmMetaDataStatus = new CloudVmMetaDataStatus(cloudVmInstanceStatus, cloudInstanceMetaData);
            cloudVmMetaDataStatuses.add(cloudVmMetaDataStatus);
        }
        return cloudVmMetaDataStatuses;
    }

    @Override
    public Object handle(spark.Request request, spark.Response response) throws Exception {
        List<CloudInstance> cloudInstances = new Gson().fromJson(request.body(), new TypeToken<List<CloudInstance>>() {
        }.getType());
        return createCloudVmMetaDataStatuses(cloudInstances);
    }
}
