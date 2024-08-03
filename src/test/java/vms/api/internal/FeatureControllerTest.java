package vms.api.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;

@ExtendWith(MockitoExtension.class)
class FeatureControllerTest {

    @Mock
    Environment environment;

    @BeforeEach
    void setUp() {
        when(environment.getProperty("FEATURE_CONTROL", "")).thenReturn("FooEarlyPreview,HideAppBar");
    }

    @Test
    void shouldReturnNoFeaturesIfNoEnvironmentPropertySet() {
        // given
        when(environment.getProperty("FEATURE_CONTROL", "")).thenReturn("");

        // when
        Collection<String> features = new FeatureController(environment).getAll();

        // then
        assertThat(features).isEmpty();
    }

    @Test
    void shouldReturnFeaturesIfEnvironmentPropertySet() {
        // when
        Collection<String> features = new FeatureController(environment).getAll();

        // then
        assertThat(features).containsOnly("FooEarlyPreview", "HideAppBar");
    }

    @Test
    void shouldReturnFeatureDisabledIfNoFeaturePresent() {
        // when
        Map<String, Boolean> feature = new FeatureController(environment).getFeature("NewUI");

        // then
        assertThat(feature).containsExactly(Map.entry("NewUI", false));
    }

    @Test
    void shouldReturnFeatureEnabledIfFeaturePresent() {
        // when
        Map<String, Boolean> feature = new FeatureController(environment).getFeature("HideAppBar");

        // then
        assertThat(feature).containsExactly(Map.entry("HideAppBar", true));
    }
}
