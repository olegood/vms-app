package vms.api.togglz.features;

import org.togglz.core.Feature;
import org.togglz.core.annotation.FeatureGroup;

@FeatureGroup("module")
public enum Module implements Feature {
    SIMULATE_USER,

    @FeatureGroup("integration")
    NOTIF_SVC
}
