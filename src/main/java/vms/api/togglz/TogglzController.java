package vms.api.togglz;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;

@AllArgsConstructor
@RestController
public class TogglzController {

    private final FeatureManager featureManager;

    @GetMapping("/togglz/{name}")
    ResponseEntity<TogglzState> getTogglzState(@PathVariable final String name) {
        return featureManager.getFeatures().stream()
                .filter(feature -> feature.name().equals(name))
                .findAny()
                .map(TogglzState::create)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    record TogglzState(boolean active) {
        static TogglzState create(Feature feature) {
            return new TogglzState(feature.isActive());
        }
    }
}
