package vms.api.togglz;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.util.NamedFeature;

@AllArgsConstructor
@RestController
@RequestMapping("/feature")
public class TogglzController {

    private final FeatureManager featureManager;

    @GetMapping
    Collection<FeatureState> getFeatureStates() {
        return featureManager.getFeatures().stream()
                .map(featureManager::getFeatureState)
                .collect(Collectors.toSet());
    }

    @GetMapping("/{name}")
    Map<String, Boolean> getActive(@PathVariable final String name) {
        return Map.of(name, featureManager.isActive(Features.valueOf(name)));
    }

    @GetMapping("/{feature}/state")
    FeatureState getFeatureState(@PathVariable String feature) {
        return featureManager.getFeatureState(Features.valueOf(feature));
    }
}
