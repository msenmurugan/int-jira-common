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

import java.util.Map;

import com.synopsys.integration.rest.component.IntRestComponent;

public class FieldUpdateOperationComponent extends IntRestComponent {
    private Map<String, Object> add;
    private Map<String, Object> set;
    private Map<String, Object> remove;
    private Map<String, Object> edit;

    public FieldUpdateOperationComponent() {
    }

    public FieldUpdateOperationComponent(final Map<String, Object> add, final Map<String, Object> set, final Map<String, Object> remove, final Map<String, Object> edit) {
        this.add = add;
        this.set = set;
        this.remove = remove;
        this.edit = edit;
    }

    public Map<String, Object> getAdd() {
        return add;
    }

    public Map<String, Object> getSet() {
        return set;
    }

    public Map<String, Object> getRemove() {
        return remove;
    }

    public Map<String, Object> getEdit() {
        return edit;
    }

}
