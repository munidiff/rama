package es.unican.munidiff.rama.comparison;

public record ModelComparisonInput(
        String filename,
        String previousFilename,
        String sourceContent,
        String targetContent,
        String baseContent,
        boolean hasLineConflicts
) {
    public ModelComparisonInput(
            String filename,
            String sourceContent,
            String targetContent,
            String baseContent
    ) {
        this(filename, null, sourceContent, targetContent, baseContent, false);
    }

    public boolean isRename() {
        return previousFilename != null && !previousFilename.isBlank();
    }
}
