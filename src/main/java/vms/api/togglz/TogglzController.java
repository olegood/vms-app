package vms.api.togglz;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.util.NamedFeature;

@AllArgsConstructor
@RestController
public class TogglzController {

    private final FeatureManager featureManager;

    @GetMapping("/togglz/{name}")
    ResponseEntity<Map<String, Boolean>> getFeature(@PathVariable final String name) {
        return featureManager.getFeatures().stream()
                .map(Feature::name)
                .filter(name::equals)
                .findAny()
                .map(NamedFeature::new)
                .map(featureManager::getFeatureState)
                .map(FeatureState::getFeature)
                .map(feature -> Map.of(feature.name(), feature.isActive()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
