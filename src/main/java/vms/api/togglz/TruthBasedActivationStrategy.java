package vms.api.togglz;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.util.Strings;

@Component
public class TruthBasedActivationStrategy implements ActivationStrategy {

    public static final String ID = "truth-based";

    public static final String PARAM_ACTIVE = "active";

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return "Truth Based";
    }

    @Override
    public boolean isActive(FeatureState state, FeatureUser ignored) {
        return Optional.ofNullable(state.getParameter(PARAM_ACTIVE))
                .map(Strings::toBoolean)
                .orElse(false);
    }

    @Override
    public Parameter[] getParameters() {
        return new Parameter[] {
            ParameterBuilder.create(PARAM_ACTIVE)
                    .optional()
                    .label("Property Name")
                    .description("The name of the property to be used to determine whether the feature is active.")
        };
    }
}
