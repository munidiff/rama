package es.unican.munidiff.rama.render;

import org.eclipse.epsilon.modiff.munidiff.Munidiff;

public record RenderedMunidiff(
        Munidiff munidiff,
        String unifiedDiff,
        String plantuml,
        String svg
) {
}
