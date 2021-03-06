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
package com.synopsys.integration.jira.common.cloud.configuration;

import java.net.URL;

import com.google.gson.Gson;
import com.synopsys.integration.builder.Buildable;
import com.synopsys.integration.jira.common.cloud.service.JiraCloudServiceFactory;
import com.synopsys.integration.jira.common.rest.JiraCredentialHttpClient;
import com.synopsys.integration.jira.common.rest.JiraHttpClient;
import com.synopsys.integration.log.IntLogger;
import com.synopsys.integration.rest.proxy.ProxyInfo;
import com.synopsys.integration.rest.support.AuthenticationSupport;
import com.synopsys.integration.util.Stringable;

public class JiraCloudRestConfig extends Stringable implements Buildable {
    private final URL jiraUrl;
    private final int timeoutSeconds;
    private final String authUserEmail;
    private final String apiToken;
    private final ProxyInfo proxyInfo;
    private final boolean alwaysTrustServerCertificate;
    private final Gson gson;
    private final AuthenticationSupport authenticationSupport;

    public JiraCloudRestConfig(URL jiraUrl, int timeoutSeconds, ProxyInfo proxyInfo, boolean alwaysTrustServerCertificate, Gson gson, AuthenticationSupport authenticationSupport, String authUserEmail, String apiToken) {
        this.jiraUrl = jiraUrl;
        this.timeoutSeconds = timeoutSeconds;
        this.authUserEmail = authUserEmail;
        this.apiToken = apiToken;
        this.proxyInfo = proxyInfo;
        this.alwaysTrustServerCertificate = alwaysTrustServerCertificate;
        this.gson = gson;
        this.authenticationSupport = authenticationSupport;
    }

    public static final JiraCloudRestConfigBuilder newBuilder() {
        return new JiraCloudRestConfigBuilder();
    }

    public JiraHttpClient createJiraHttpClient(IntLogger logger) {
        return new JiraCredentialHttpClient(logger, timeoutSeconds, alwaysTrustServerCertificate, proxyInfo, jiraUrl.toString(), authenticationSupport, authUserEmail, apiToken);
    }

    public JiraCloudServiceFactory createJiraCloudServiceFactory(IntLogger logger) {
        return new JiraCloudServiceFactory(logger, createJiraHttpClient(logger), gson);
    }

    public URL getJiraUrl() {
        return jiraUrl;
    }

    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }

    public String getAuthUserEmail() {
        return authUserEmail;
    }

    public String getApiToken() {
        return apiToken;
    }

    public ProxyInfo getProxyInfo() {
        return proxyInfo;
    }

    public boolean isAlwaysTrustServerCertificate() {
        return alwaysTrustServerCertificate;
    }

    public Gson getGson() {
        return gson;
    }

    public AuthenticationSupport getAuthenticationSupport() {
        return authenticationSupport;
    }

}
