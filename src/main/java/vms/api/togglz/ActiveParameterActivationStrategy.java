package vms.api.togglz;

import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Component;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

@Component
public class ActiveParameterActivationStrategy implements ActivationStrategy {

    public static final String ID = "active-parameter";

    public static final String PARAM_NAME_ACTIVE = "active";

    @Override
    public boolean isActive(FeatureState state, FeatureUser ignored) {
        return Optional.ofNullable(state.getParameter(PARAM_NAME_ACTIVE))
                .map(String::toLowerCase)
                .map(String::trim)
                .stream()
                .anyMatch(this::trueValue);
    }

    private boolean trueValue(String value) {
        return Set.of("1", "true", "on", "yes").contains(value);
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public String getName() {
        return "Active Parameter";
    }

    @Override
    public Parameter[] getParameters() {
        return new Parameter[] {
            ParameterBuilder.create(PARAM_NAME_ACTIVE)
                    .label("Active Parameter")
                    .description("Value of this parameter is used to determine whether the feature is active.")
        };
    }
}
