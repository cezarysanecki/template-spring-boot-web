package pl.cezarysanecki.templateforspringboot.infrastructure;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
class FormatValidationExceptionAdvice {

  private static final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
    Map<String, Object> objectBody = new LinkedHashMap<>();
    objectBody.put("current Timestamp", Instant.now());
    objectBody.put("status", HTTP_STATUS.value());

    List<FieldErrors> fieldsErrors = exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .collect(Collectors.groupingBy(
            FieldError::getField,
            Collectors.mapping(DefaultMessageSourceResolvable::getDefaultMessage, Collectors.toList())))
        .entrySet()
        .stream()
        .map(entry -> new FieldErrors(entry.getKey(), entry.getValue()))
        .toList();

    objectBody.put("errors", fieldsErrors);

    return new ResponseEntity<>(objectBody, HttpStatus.BAD_REQUEST);
  }

  private record FieldErrors(String name, List<String> errors) {
  }

}
