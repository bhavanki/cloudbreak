package com.sequenceiq.it.cloudbreak;

public class TemplateAddition {

    private final String groupName;

    private final int nodeCount;

    private final String type;

    public TemplateAddition(String groupName, int nodeCount, String type) {
        this.groupName = groupName;
        this.nodeCount = nodeCount;
        this.type = type;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getNodeCount() {
        return nodeCount;
    }

    public String getType() {
        return type;
    }
}
