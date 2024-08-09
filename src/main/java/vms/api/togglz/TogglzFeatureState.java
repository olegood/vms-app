package vms.api.togglz;

import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.togglz.core.activation.UserRoleActivationStrategy;
import org.togglz.core.activation.UsernameActivationStrategy;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

@AllArgsConstructor
@Component
public class TogglzFeatureState {

    private final Environment environment;
    private final FeatureManager featureManager;

    @PostConstruct
    void postConstruct() {
        featureManager.getFeatures().stream()
                .map(featureManager::getFeatureState)
                .filter(FeatureState::isEnabled)
                .filter(this::properActivationStrategy)
                .forEach(fs -> {
                    var feature = fs.getFeature();
                    var users = environment.getProperty(
                            "togglz_bp_%s.%s".formatted(feature, UsernameActivationStrategy.PARAM_USERS));
                    var roles = environment.getProperty(
                            "togglz_bp_%s.%s".formatted(feature, UserRoleActivationStrategy.PARAM_ROLES_NAME));

                    var state = fs.copy()
                            .setParameter(UsernameActivationStrategy.PARAM_USERS, users)
                            .setParameter(UserRoleActivationStrategy.PARAM_ROLES_NAME, roles);
                    featureManager.setFeatureState(state);
                });
    }

    private boolean properActivationStrategy(FeatureState featureState) {
        return List.of(UsernameActivationStrategy.ID, UserRoleActivationStrategy.ID)
                .contains(featureState.getStrategyId());
    }
}
