package com.sequenceiq.cloudbreak.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;

@ApiModel
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SecurityRuleRequest extends SecurityRuleBase {

    public SecurityRuleRequest() {
    }

    public SecurityRuleRequest(String subnet) {
        super(subnet);
    }
}
