package sk.martin.specpilot.common.exception;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.Map;

import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        @NonNull HttpHeaders headers,
        @NonNull HttpStatusCode status,
        @NonNull WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, "Request validation failed");
        problemDetail.setType(URI.create(ErrorCode.VALIDATION_ERROR.typeUri()));
        problemDetail.setTitle(ErrorCode.VALIDATION_ERROR.title());

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        problemDetail.setProperty("errors", fieldErrors);

        return ResponseEntity.status(status).headers(headers).body(problemDetail);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ProblemDetail> handleBusinessException(BusinessException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getStatus(), ex.getMessage());
        problemDetail.setType(URI.create(ex.getErrorCode().typeUri()));
        problemDetail.setTitle(ex.getErrorCode().title());
        return ResponseEntity.status(ex.getStatus()).body(problemDetail);
    }
}
