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
import vms.api.togglz.features.Experimental;
import vms.api.togglz.features.Module;
import vms.api.togglz.features.Toggle;

@Configuration
public class TogglzConfiguration {

    @Bean
    StateRepository stateRepository(DataSource dataSource) {
        return new JDBCStateRepository(dataSource);
    }

    @Bean
    FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(Module.class, Toggle.class, Experimental.class);
    }

    @Bean
    UserProvider userProvider() {
        return () -> new SimpleFeatureUser("vova").setAttribute(USER_ATTRIBUTE_ROLES, Set.of("manager", "clerk"));
    }
}
