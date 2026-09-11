package es.unican.munidiff.rama.config;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class RamaConfigTest {

    @Test
    void modelExtensionsAreRelevantFiles() {
        RamaConfig config = new RamaConfig(List.of(".model"), List.of());

        assertTrue(config.isRelevantFile("models/example.model"));
    }

    @Test
    void metamodelExtensionsAreRelevantFiles() {
        RamaConfig config = new RamaConfig(List.of(".model"), List.of());

        assertTrue(config.isRelevantFile("metamodels/example.ecore"));
    }

    @Test
    void unrelatedExtensionsAreNotRelevantFiles() {
        RamaConfig config = new RamaConfig(List.of(".model"), List.of());

        assertFalse(config.isRelevantFile("README.md"));
    }

    @Test
    void nullFilenameIsNotRelevantFile() {
        RamaConfig config = new RamaConfig(List.of(".model"), List.of());

        assertFalse(config.isRelevantFile(null));
    }

    @Test
    void onlyMetamodelExtensionsAreMetamodelFiles() {
        RamaConfig config = new RamaConfig(List.of(".model"), List.of());

        assertTrue(config.isMetamodelFile("metamodels/example.ecore"));
        assertFalse(config.isMetamodelFile("models/example.model"));
    }

    @Test
    void nullFilenameIsNotMetamodelFile() {
        RamaConfig config = new RamaConfig(List.of(".model"), List.of());

        assertFalse(config.isMetamodelFile(null));
    }

    @Test
    void nullExtensionListsAreHandledSafely() {
        RamaConfig config = new RamaConfig(null, List.of());

        assertFalse(config.isRelevantFile("models/example.model"));
        assertTrue(config.isMetamodelFile("metamodels/example.ecore"));
    }

    @Test
    void nullMetamodelsReturnEmptyMetamodelPaths() {
        RamaConfig config = new RamaConfig(List.of(".model"), null);

        assertTrue(config.metamodels().isEmpty());
    }

    @Test
    void configuredMetamodelsAreReturnedAsMetamodelPaths() {
        List<String> metamodels = List.of("metamodels/library.ecore", "/opt/shared/base.ecore");
        RamaConfig config = new RamaConfig(List.of(".model"), metamodels);

        assertSame(metamodels, config.metamodels());
    }
}
