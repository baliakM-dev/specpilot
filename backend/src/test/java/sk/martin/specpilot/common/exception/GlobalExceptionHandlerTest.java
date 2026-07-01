package sk.martin.specpilot.common.exception;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
        assertThat(body.getType()).isNotNull();
        assertThat(body.getType().toString()).isEqualTo(ErrorCode.INTERNAL_ERROR.typeUri());
        assertThat(body.getTitle()).isEqualTo(ErrorCode.INTERNAL_ERROR.title());
        assertThat(body.getDetail()).isEqualTo("something went wrong");
        assertThat(body.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    void handleMethodArgumentNotValid_returnsProblemDetailWithFieldErrors() throws NoSuchMethodException {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "request");
        bindingResult.addError(new FieldError("request", "name", "must not be blank"));

        Method method = SampleController.class.getMethod("handle", String.class);
        MethodParameter methodParameter = new MethodParameter(method, 0);
        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<Object> response =
                handler.handleMethodArgumentNotValid(exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        ProblemDetail body = (ProblemDetail) response.getBody();
        assertThat(body).isNotNull();
        assertThat(body.getType()).isNotNull();
        assertThat(body.getType().toString()).isEqualTo(ErrorCode.VALIDATION_ERROR.typeUri());
        assertThat(body.getTitle()).isEqualTo(ErrorCode.VALIDATION_ERROR.title());
        assertThat(body.getDetail()).isEqualTo("Request validation failed");
        assertThat(body.getProperties()).containsEntry("errors", Map.of("name", "must not be blank"));
    }

    private static final class SampleController {
        public void handle(String name) {
        }
    }
}
