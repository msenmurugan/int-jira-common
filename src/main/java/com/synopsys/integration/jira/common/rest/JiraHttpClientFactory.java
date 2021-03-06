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
package com.synopsys.integration.jira.common.rest;

import com.google.api.client.auth.oauth.OAuthParameters;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.synopsys.integration.jira.common.rest.oauth1a.JiraOAuthHttpClient;
import com.synopsys.integration.log.IntLogger;
import com.synopsys.integration.rest.proxy.ProxyInfo;

public class JiraHttpClientFactory {
    public static final String JIRA_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

    public JiraHttpClient createJiraOAuthClient(String jiraUrl, OAuthParameters oAuthParameters) {
        return new JiraOAuthHttpClient(jiraUrl, createHttpRequestFactory(oAuthParameters));
    }

    public JiraHttpClient createJiraCloudCredentialClient(IntLogger logger, int timeout, boolean alwaysTrustServerCertificate, ProxyInfo proxyInfo, String baseUrl, String authUserEmail, String apiToken) {
        return JiraCredentialHttpClient.cloud(logger, timeout, alwaysTrustServerCertificate, proxyInfo, baseUrl, authUserEmail, apiToken);
    }

    public JiraHttpClient createJiraServerCredentialClient(IntLogger logger, int timeout, boolean alwaysTrustServerCertificate, ProxyInfo proxyInfo, String baseUrl, String username, String password) {
        return JiraCredentialHttpClient.server(logger, timeout, alwaysTrustServerCertificate, proxyInfo, baseUrl, username, password);
    }

    private HttpRequestFactory createHttpRequestFactory(OAuthParameters oAuthParameters) {
        return new NetHttpTransport().createRequestFactory(oAuthParameters);
    }

}

