package es.unican.munidiff.rama.config;

/**
 * Result of loading RAMA configuration, including any warning that should be shown to the reviewer.
 *
 * @param config the configuration selected for the current execution
 * @param warning a user-facing warning, or {@code null} when repository configuration was loaded successfully
 */
public record RamaConfigLoadResult(
        RamaConfig config,
        String warning
) {
}
