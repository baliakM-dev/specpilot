package sk.martin.specpilot.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleBusinessException_returnsProblemDetailWithErrorCodeFields() {
        BusinessException exception =
                new BusinessException(ErrorCode.INTERNAL_ERROR, HttpStatus.CONFLICT, "something went wrong") {
                };

        ResponseEntity<ProblemDetail> response = handler.handleBusinessException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        ProblemDetail body = response.getBody();
        assertThat(body).isNotNull();
        assert body.getType() != null;
        assertThat(body.getType().toString()).isEqualTo(ErrorCode.INTERNAL_ERROR.typeUri());
        assertThat(body.getTitle()).isEqualTo(ErrorCode.INTERNAL_ERROR.title());
        assertThat(body.getDetail()).isEqualTo("something went wrong");
        assertThat(body.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }
}
