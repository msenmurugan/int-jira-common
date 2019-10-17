package com.synopsys.integration.jira.common.cloud.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.synopsys.integration.exception.IntegrationException;
import com.synopsys.integration.jira.common.model.response.InstalledAppsResponseModel;
import com.synopsys.integration.jira.common.model.response.PluginResponseModel;
import com.synopsys.integration.rest.request.Response;

public class JiraAppServiceTest extends JiraCloudServiceTest {
    private static final String APP_KEY = "com.synopsys.integration.alert";
    private static final String APP_URI = "https://blackducksoftware.github.io/blackduck-alert/JiraCloudApp/1.0.0/atlassian-connect.json";

    @AfterEach
    public void waitForUninstallToFinish() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void installMarketplaceAppTest() throws Exception {
        validateConfiguration();
        JiraCloudServiceFactory serviceFactory = createServiceFactory();
        JiraAppService jiraAppService = serviceFactory.createJiraAppService();

        String userEmail = getEnvUserEmail();
        String apiToken = getEnvApiToken();

        Response installResponse = jiraAppService.installMarketplaceApp(APP_KEY, userEmail, apiToken);
        assertTrue(installResponse.isStatusCodeOkay(), "Expected a 2xx response code, but was: " + installResponse.getStatusCode());
        Thread.sleep(1000);
        Response uninstallResponse = jiraAppService.uninstallApp(APP_KEY, userEmail, apiToken);
        assertTrue(uninstallResponse.isStatusCodeOkay(), "Expected a 2xx response code, but was: " + uninstallResponse.getStatusCode());
    }

    @Test
    @Disabled
    // Disabled because development mode will likely not be turned on most of the time.
    public void installDevelopmentAppTest() throws Exception {
        validateConfiguration();
        JiraCloudServiceFactory serviceFactory = createServiceFactory();
        JiraAppService jiraAppService = serviceFactory.createJiraAppService();

        String userEmail = getEnvUserEmail();
        String apiToken = getEnvApiToken();

        Response installResponse = jiraAppService.installDevelopmentApp(
            "Test",
            APP_URI,
            userEmail,
            apiToken
        );
        assertTrue(installResponse.isStatusCodeOkay(), "Expected a 2xx response code, but was: " + installResponse.getStatusCode());
        Thread.sleep(1000);
        Response uninstallResponse = jiraAppService.uninstallApp(APP_KEY, userEmail, apiToken);
        assertTrue(uninstallResponse.isStatusCodeOkay(), "Expected a 2xx response code, but was: " + uninstallResponse.getStatusCode());
    }

    @Test
    public void getInstalledAppsTest() throws IntegrationException {
        validateConfiguration();
        JiraCloudServiceFactory serviceFactory = createServiceFactory();
        JiraAppService jiraAppService = serviceFactory.createJiraAppService();

        String userEmail = getEnvUserEmail();
        String apiToken = getEnvApiToken();

        Optional<PluginResponseModel> fakeApp = jiraAppService.getInstalledApp(userEmail, apiToken, "not.a.real.key");
        assertFalse(fakeApp.isPresent(), "Expected app to not be installed");

        Response installResponse = jiraAppService.installMarketplaceApp(APP_KEY, userEmail, apiToken);
        installResponse.throwExceptionForError();

        InstalledAppsResponseModel installedApps = jiraAppService.getInstalledApps(userEmail, apiToken);
        List<PluginResponseModel> allInstalledPlugins = installedApps.getPlugins();
        List<PluginResponseModel> userInstalledPlugins = allInstalledPlugins
                                                             .stream()
                                                             .filter(plugin -> plugin.getUserInstalled())
                                                             .collect(Collectors.toList());
        assertTrue(userInstalledPlugins.size() < allInstalledPlugins.size(), "Expected fewer user-installed plugins than total plugins");

        Response uninstallResponse = jiraAppService.uninstallApp(APP_KEY, userEmail, apiToken);
        uninstallResponse.throwExceptionForError();
    }

}