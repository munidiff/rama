package es.unican.munidiff.rama.render;

import es.unican.munidiff.Munidiff;

public record RenderedMunidiff(
        Munidiff munidiff,
        String unifiedDiff,
        String plantuml,
        String svg
) {
}
