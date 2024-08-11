package vms.api.togglz;

import static org.togglz.core.activation.UserRoleActivationStrategy.USER_ATTRIBUTE_ROLES;

import java.util.Set;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;
import vms.api.togglz.features.Core;
import vms.api.togglz.features.Probe;
import vms.api.togglz.features.Togglz;

@Configuration
public class TogglzConfiguration {

    @Bean
    StateRepository stateRepository(DataSource dataSource) {
        return new JDBCStateRepository(dataSource);
    }

    @Bean
    FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(Core.class, Togglz.class, Probe.class);
    }

    @Bean
    UserProvider userProvider() {
        return () -> new SimpleFeatureUser("vova").setAttribute(USER_ATTRIBUTE_ROLES, Set.of("manager", "clerk"));
    }
}
