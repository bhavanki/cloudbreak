package com.sequenceiq.periscope.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sequenceiq.periscope.model.Priority;
import com.sequenceiq.periscope.model.SchedulerApplication;
import com.sequenceiq.periscope.registry.Cluster;

@Service
public class AppService {

    @Autowired
    private ClusterService clusterService;

    public List<ApplicationReport> getApplicationReports(String clusterId) throws IOException, YarnException, ClusterNotFoundException {
        Cluster cluster = clusterService.get(clusterId);
        YarnClient yarnClient = cluster.getYarnClient();
        return yarnClient.getApplications();
    }

    public void setPriorityToHighRandomly(String clusterId) throws ClusterNotFoundException {
        Cluster cluster = clusterService.get(clusterId);
        Map<ApplicationId, SchedulerApplication> applications = cluster.getApplications(Priority.NORMAL);
        int i = 0;
        for (ApplicationId applicationId : applications.keySet()) {
            if (i++ % 2 == 0) {
                cluster.setApplicationPriority(applicationId, Priority.HIGH);
            }
        }
    }

    public Cluster allowAppMovement(String clusterId, boolean allow) throws ClusterNotFoundException {
        Cluster cluster = clusterService.get(clusterId);
        cluster.allowAppMovement(allow);
        return cluster;
    }

}
