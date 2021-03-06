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
package com.synopsys.integration.jira.common.cloud.model;

import java.util.List;

import com.synopsys.integration.jira.common.model.EntityProperty;
import com.synopsys.integration.jira.common.model.request.builder.IssueRequestModelFieldsMapBuilder;

public class IssueCreationRequestModel {
    private final String reporterEmail;
    private final String issueTypeName;
    private final String projectName;
    private final IssueRequestModelFieldsMapBuilder fieldsBuilder;
    private final List<EntityProperty> properties;

    public IssueCreationRequestModel(String reporterEmail, String issueTypeName, String projectName, IssueRequestModelFieldsMapBuilder fieldsBuilder, List<EntityProperty> properties) {
        this.reporterEmail = reporterEmail;
        this.issueTypeName = issueTypeName;
        this.projectName = projectName;
        this.fieldsBuilder = fieldsBuilder;
        this.properties = properties;
    }

    public String getReporterEmail() {
        return reporterEmail;
    }

    public String getIssueTypeName() {
        return issueTypeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public IssueRequestModelFieldsMapBuilder getFieldsBuilder() {
        return fieldsBuilder;
    }

    public List<EntityProperty> getProperties() {
        return properties;
    }

}
