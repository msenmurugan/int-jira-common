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

import com.google.gson.Gson;
import com.synopsys.integration.jira.common.rest.JiraHttpClient;
import com.synopsys.integration.log.IntLogger;
import com.synopsys.integration.rest.service.IntJsonTransformer;

public class CommonServiceFactory {
    private final JiraHttpClient httpClient;
    private final Gson gson;
    private final IntJsonTransformer jsonTransformer;
    private final JiraApiClient jiraApiClient;

    public CommonServiceFactory(IntLogger logger, JiraHttpClient httpClient, Gson gson) {
        this.httpClient = httpClient;
        this.gson = gson;
        this.jsonTransformer = new IntJsonTransformer(gson, logger);

        jiraApiClient = new JiraApiClient(gson, httpClient, jsonTransformer);
    }

    public IssuePropertyService createIssuePropertyService() {
        return new IssuePropertyService(gson, getJiraApiClient());
    }

    public IssueTypeService createIssueTypeService() {
        return new IssueTypeService(getJiraApiClient());
    }

    public PluginManagerService createPluginManagerService() {
        return new PluginManagerService(gson, getJiraApiClient());
    }

    public IssueMetaDataService createIssueMetadataService() {
        return new IssueMetaDataService(getJiraApiClient());
    }

    public JiraApiClient getJiraApiClient() {
        return jiraApiClient;
    }

    public JiraHttpClient getHttpClient() {
        return httpClient;
    }

    public Gson getGson() {
        return gson;
    }

    public IntJsonTransformer getJsonTransformer() {
        return jsonTransformer;
    }
}
