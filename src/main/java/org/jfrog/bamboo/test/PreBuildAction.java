package org.jfrog.bamboo.test;

import com.atlassian.bamboo.build.BuildLoggerManager;
import com.atlassian.bamboo.build.CustomPreBuildAction;
import com.atlassian.bamboo.build.logger.BuildLogger;
import com.atlassian.bamboo.repository.Repository;
import com.atlassian.bamboo.repository.RepositoryDefinition;
import com.atlassian.bamboo.security.StringEncrypter;
import com.atlassian.bamboo.v2.build.BaseConfigurableBuildPlugin;
import com.atlassian.bamboo.v2.build.BuildContext;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.jetbrains.annotations.NotNull;
import org.shaded.eclipse.jgit.transport.Transport;
import org.shaded.eclipse.jgit.transport.TransportProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.List;

/**
 * @author Noam Y. Tenne
 */
public class PreBuildAction extends BaseConfigurableBuildPlugin implements CustomPreBuildAction {

    private BuildLoggerManager buildLoggerManager;

    @NotNull
    public BuildContext call() throws InterruptedException, Exception {
        List<TransportProtocol> transportProtocols = Transport.getTransportProtocols();
        BuildLogger logger = buildLoggerManager.getBuildLogger(buildContext.getPlanResultKey());
        for (TransportProtocol transportProtocol : transportProtocols) {
            logger.addBuildLogEntry("##### Found JGit transport protocol: " + transportProtocol.getName());
        }

        return buildContext;
    }

    public void setBuildLoggerManager(BuildLoggerManager buildLoggerManager) {
        this.buildLoggerManager = buildLoggerManager;
    }
}
