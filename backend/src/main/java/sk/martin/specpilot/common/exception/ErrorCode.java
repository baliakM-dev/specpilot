package sk.martin.specpilot.common.exception;

public enum ErrorCode {

    VALIDATION_ERROR("https://specpilot.dev/errors/validation-error", "Validation failed"),
    INTERNAL_ERROR("https://specpilot.dev/errors/internal-error", "Internal error");

    private final String typeUri;
    private final String title;

    ErrorCode(String typeUri, String title) {
        this.typeUri = typeUri;
        this.title = title;
    }

    public String typeUri() {
        return typeUri;
    }

    public String title() {
        return title;
    }
}
