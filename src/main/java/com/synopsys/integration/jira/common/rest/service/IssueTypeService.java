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
package com.synopsys.integration.jira.common.rest.service;

import java.util.List;

import com.synopsys.integration.exception.IntegrationException;
import com.synopsys.integration.jira.common.model.request.IssueTypeRequestModel;
import com.synopsys.integration.jira.common.model.request.JiraRequestFactory;
import com.synopsys.integration.jira.common.model.response.IssueTypeResponseModel;
import com.synopsys.integration.jira.common.rest.model.JiraRequest;
import com.synopsys.integration.rest.HttpUrl;

public class IssueTypeService {
    private static final String API_PATH = "/rest/api/2/issuetype";

    private final JiraApiClient jiraApiClient;

    public IssueTypeService(JiraApiClient jiraApiClient) {
        this.jiraApiClient = jiraApiClient;
    }

    public List<IssueTypeResponseModel> getAllIssueTypes() throws IntegrationException {
        HttpUrl uri = createApiUri();
        JiraRequest request = JiraRequestFactory.createDefaultGetRequest(uri);
        return jiraApiClient.getList(request, IssueTypeResponseModel.class);
    }

    public IssueTypeResponseModel createIssueType(IssueTypeRequestModel issueTypeRequestModel) throws IntegrationException {
        HttpUrl uri = createApiUri();
        return jiraApiClient.post(issueTypeRequestModel, uri, IssueTypeResponseModel.class);
    }

    private HttpUrl createApiUri() throws IntegrationException {
        return new HttpUrl(jiraApiClient.getBaseUrl() + API_PATH);
    }

}
