package com.sequenceiq.cloudbreak.api.endpoint.v4.recipes.responses;

import java.util.Set;

import com.google.common.collect.Sets;
import com.sequenceiq.cloudbreak.api.endpoint.v4.common.responses.GeneralCollectionV4Response;

import io.swagger.annotations.ApiModel;

@ApiModel
public class RecipeV4Responses extends GeneralCollectionV4Response<RecipeV4Response> {
    public RecipeV4Responses(Set<RecipeV4Response> responses) {
        super(responses);
    }

    public RecipeV4Responses() {
        super(Sets.newHashSet());
    }
}
