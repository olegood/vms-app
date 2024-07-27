package vms.api.internal;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/feature-control")
public class FeatureController {

  private final Collection<String> features;

  FeatureController(Environment environment) {
    var values = environment.getProperty("FEATURE_CONTROL", "").split(",");
    features = Stream.of(values).map(String::trim).filter(not(String::isBlank)).collect(toSet());
  }

  @GetMapping
  Collection<String> getAll() {
    return features;
  }

  @GetMapping("/{name}")
  Map<String, Boolean> getFeature(@PathVariable String name) {
    return Map.of(name, features.contains(name));
  }
}
