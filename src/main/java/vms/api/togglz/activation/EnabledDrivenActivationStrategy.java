package vms.api.togglz.activation;

import org.springframework.stereotype.Component;
import org.togglz.core.activation.Parameter;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

@Component
public class EnabledDrivenActivationStrategy implements ActivationStrategy {

    @Override
    public boolean isActive(FeatureState state, FeatureUser user) {
        return true;
    }

    @Override
    public String getId() {
        return "enabled-driven";
    }

    @Override
    public String getName() {
        return "Enabled-Driven";
    }

    @Override
    public Parameter[] getParameters() {
        return new Parameter[] {};
    }
}
