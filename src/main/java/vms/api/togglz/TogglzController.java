package vms.api.togglz;

import static java.util.stream.Collectors.toSet;

import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

@AllArgsConstructor
@RestController
@RequestMapping("/togglz")
public class TogglzController {

    private final FeatureManager featureManager;

    @GetMapping("/{name}")
    ResponseEntity<Map<String, Boolean>> getActive(@PathVariable final String name) {
        var features = featureManager.getFeatures().stream().map(Feature::name).collect(toSet());
        if (features.contains(name)) {
            return ResponseEntity.ok(Map.of(name, featureManager.isActive(new NamedFeature(name))));
        }
        return ResponseEntity.notFound().build();
    }
}
