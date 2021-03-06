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
package com.synopsys.integration.jira.common.model.components;

import java.util.List;

import com.synopsys.integration.rest.component.IntRestComponent;

public class IssueIncludedFieldsComponent extends IntRestComponent {
    private List<String> included;
    private List<String> excluded;
    private List<String> actuallyIncluded;

    public IssueIncludedFieldsComponent() {
    }

    public IssueIncludedFieldsComponent(final List<String> included, final List<String> excluded, final List<String> actuallyIncluded) {
        this.included = included;
        this.excluded = excluded;
        this.actuallyIncluded = actuallyIncluded;
    }

    public List<String> getIncluded() {
        return included;
    }

    public List<String> getExcluded() {
        return excluded;
    }

    public List<String> getActuallyIncluded() {
        return actuallyIncluded;
    }

}
