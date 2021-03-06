/**
 * int-jira-common
 *
 * Copyright (c) 2020 Synopsys, Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.synopsys.integration.jira.common.cloud.service;

import java.util.List;

import com.synopsys.integration.exception.IntegrationException;
import com.synopsys.integration.jira.common.model.request.FieldRequestModel;
import com.synopsys.integration.jira.common.model.request.JiraRequestFactory;
import com.synopsys.integration.jira.common.model.response.FieldResponseModel;
import com.synopsys.integration.jira.common.rest.model.JiraRequest;
import com.synopsys.integration.jira.common.rest.service.JiraApiClient;
import com.synopsys.integration.rest.HttpUrl;

public class FieldService {
    public static final String API_PATH = "/rest/api/2/field";

    private final JiraApiClient jiraCloudService;

    public FieldService(JiraApiClient jiraCloudService) {
        this.jiraCloudService = jiraCloudService;
    }

    public List<FieldResponseModel> getUserVisibleFields() throws IntegrationException {
        JiraRequest request = JiraRequestFactory.createDefaultGetRequest(createApiUri());
        return jiraCloudService.getList(request, FieldResponseModel.class);
    }

    public FieldResponseModel createCustomField(FieldRequestModel fieldModel) throws IntegrationException {
        return jiraCloudService.post(fieldModel, createApiUri(), FieldResponseModel.class);
    }

    private HttpUrl createApiUri() throws IntegrationException {
        return new HttpUrl(jiraCloudService.getBaseUrl() + API_PATH);
    }

}
