package com.synopsys.integration.jira.common.rest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.synopsys.integration.exception.IntegrationException;
import com.synopsys.integration.jira.common.model.response.IssueCreateMetadataResponse;
import com.synopsys.integration.jira.common.model.response.IssueTypeResponseModel;
import com.synopsys.integration.jira.common.model.response.ProjectIssueCreateMetadataResponse;
import com.synopsys.integration.jira.common.rest.service.IssueMetaDataService;
import com.synopsys.integration.jira.common.rest.service.JiraService;

public class IssueMetaDataServiceTest {

    @Test
    public void issueTypeInProjectTest() throws IntegrationException {
        JiraService jiraService = Mockito.mock(JiraService.class);
        IssueCreateMetadataResponse response = createResponse();
        Mockito.when(jiraService.get(Mockito.any(), Mockito.eq(IssueCreateMetadataResponse.class))).thenReturn(response);
        IssueMetaDataService service = new IssueMetaDataService(jiraService);

        assertTrue(service.doesProjectContainIssueType("TEST", "VALID_TYPE_1"));
        assertTrue(service.doesProjectContainIssueType("TEST", "VALID_TYPE_2"));
        assertFalse(service.doesProjectContainIssueType("TEST", "INVALID_TYPE"));
    }

    private IssueCreateMetadataResponse createResponse() {
        IssueTypeResponseModel issueTypeResponseModel = Mockito.mock(IssueTypeResponseModel.class);
        Mockito.when(issueTypeResponseModel.getName()).thenReturn("VALID_TYPE_1");
        List<IssueTypeResponseModel> issueTypes = new ArrayList<>();
        issueTypes.add(issueTypeResponseModel);
        issueTypeResponseModel = Mockito.mock(IssueTypeResponseModel.class);
        Mockito.when(issueTypeResponseModel.getName()).thenReturn("VALID_TYPE_2");
        issueTypes.add(issueTypeResponseModel);

        ProjectIssueCreateMetadataResponse project = new ProjectIssueCreateMetadataResponse("expand", "self", "1", "TEST", "TEST", issueTypes);

        List<ProjectIssueCreateMetadataResponse> projects = new ArrayList<>();
        projects.add(project);
        IssueCreateMetadataResponse response = new IssueCreateMetadataResponse("projects", projects);
        return response;
    }
}