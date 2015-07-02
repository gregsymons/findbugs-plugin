package hudson.plugins.findbugs;

import java.util.Collection;
import java.util.Collections;

import jenkins.tasks.SimpleBuildStep;

import hudson.model.AbstractBuild;
import hudson.model.Action;
import hudson.model.Run;
import hudson.plugins.analysis.core.HealthDescriptor;
import hudson.plugins.analysis.core.PluginDescriptor;
import hudson.plugins.analysis.core.AbstractResultAction;

/**
 * Controls the live cycle of the FindBugs results. This action persists the
 * results of the FindBugs analysis of a build and displays the results on the
 * build page. The actual visualization of the results is defined in the
 * matching <code>summary.jelly</code> file.
 * <p>
 * Moreover, this class renders the FindBugs result trend.
 * </p>
 *
 * @author Ulli Hafner
 */
public class FindBugsResultAction extends AbstractResultAction<FindBugsResult> implements SimpleBuildStep.LastBuildAction {
    /**
     * Creates a new instance of {@link FindBugsResultAction}.
     *  @param owner
     *            the associated build of this action
     * @param healthDescriptor
     *            health descriptor to use
     * @param result
     */
    public FindBugsResultAction(final Run<?, ?> owner, final HealthDescriptor healthDescriptor, final FindBugsResult result) {
        super(owner, new FindBugsHealthDescriptor(healthDescriptor), result);
    }

    @Override
    public String getDisplayName() {
        return Messages.FindBugs_ProjectAction_Name();
    }

    @Override
    protected PluginDescriptor getDescriptor() {
        return new FindBugsDescriptor();
    }

    @Override
    public Collection<? extends Action> getProjectActions() {
        return Collections.singleton(new FindBugsProjectAction(getOwner().getParent(), FindBugsResultAction.class));
    }
}
