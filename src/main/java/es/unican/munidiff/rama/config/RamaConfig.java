package es.unican.munidiff.rama.config;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

// JSON shape of rama.json.
public record RamaConfig(
        @JsonProperty("model_extensions") List<String> modelExtensions,
        @JsonProperty("metamodels") List<String> metamodels
) {
    public static final List<String> METAMODEL_EXTENSIONS = List.of(".ecore");

    public RamaConfig(List<String> modelExtensions, List<String> metamodels) {
        this.modelExtensions = modelExtensions == null ? List.of() : modelExtensions;
        this.metamodels = metamodels == null ? List.of() : metamodels;
    }

    public boolean isRelevantFile(String filename) {
        if (filename == null) {
            return false;
        }

        return modelExtensions().stream().anyMatch(filename::endsWith)
                || METAMODEL_EXTENSIONS.stream().anyMatch(filename::endsWith);
    }

    public boolean isMetamodelFile(String filename) {
        if (filename == null) {
            return false;
        }

        return METAMODEL_EXTENSIONS.stream().anyMatch(filename::endsWith);
    }
}
