package vms.api.togglz.features;

import org.togglz.core.Feature;
import org.togglz.core.activation.UserRoleActivationStrategy;
import org.togglz.core.activation.UsernameActivationStrategy;
import org.togglz.core.annotation.ActivationParameter;
import org.togglz.core.annotation.DefaultActivationStrategy;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.FeatureGroup;
import org.togglz.spring.activation.SpringEnvironmentPropertyActivationStrategy;

@FeatureGroup("feature")
public enum Toggle implements Feature {
    @EnabledByDefault
    @DefaultActivationStrategy(
            id = SpringEnvironmentPropertyActivationStrategy.ID,
            parameters = {
                @ActivationParameter(name = SpringEnvironmentPropertyActivationStrategy.PARAM_NAME, value = ""),
                @ActivationParameter(
                        name = SpringEnvironmentPropertyActivationStrategy.PARAM_PROPERTY_VALUE,
                        value = "true")
            })
    MY_FEATURE,

    @EnabledByDefault
    @DefaultActivationStrategy(
            id = SpringEnvironmentPropertyActivationStrategy.ID,
            parameters = {
                @ActivationParameter(
                        name = SpringEnvironmentPropertyActivationStrategy.PARAM_NAME,
                        value = "new_template"),
                @ActivationParameter(
                        name = SpringEnvironmentPropertyActivationStrategy.PARAM_PROPERTY_VALUE,
                        value = "2025")
            })
    TEMPLATE_2025,

    @EnabledByDefault
    @DefaultActivationStrategy(id = UsernameActivationStrategy.ID)
    VIP_USER,

    @EnabledByDefault
    @DefaultActivationStrategy(id = UserRoleActivationStrategy.ID)
    VIP_ROLE,

    @EnabledByDefault
    @DefaultActivationStrategy(
            id = SpringEnvironmentPropertyActivationStrategy.ID,
            parameters = {
                @ActivationParameter(
                        name = SpringEnvironmentPropertyActivationStrategy.PARAM_NAME,
                        value = "best_impl.ep"),
                @ActivationParameter(
                        name = SpringEnvironmentPropertyActivationStrategy.PARAM_PROPERTY_VALUE,
                        value = "true")
            })
    BEST_IMPL_EARLY_PREVIEW,

    DAY_X,
    CUSTOM_FEATURE
}
