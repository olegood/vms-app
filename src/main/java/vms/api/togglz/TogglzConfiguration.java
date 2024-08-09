package vms.api.togglz;

import static org.togglz.core.activation.UserRoleActivationStrategy.USER_ATTRIBUTE_ROLES;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

import java.util.Set;

@Configuration
public class TogglzConfiguration {

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(Features.class);
    }

    @Bean
    public UserProvider userProvider() {
        return () -> new SimpleFeatureUser("vova").setAttribute(USER_ATTRIBUTE_ROLES, Set.of("manager", "clerk"));
    }
}
