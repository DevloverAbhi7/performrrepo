/*
 * package com.embhaicorp.utils; import java.util.HashMap; import java.util.Map;
 * import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.MethodArgumentNotValidException; import
 * org.springframework.web.bind.annotation.ControllerAdvice; import
 * org.springframework.web.bind.annotation.ExceptionHandler; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.embhaicorp.model.CommonResponse;
 * 
 * @ControllerAdvice(annotations = RestController.class)
 * 
 * @RequestMapping(produces = "application/json") public class
 * ValidationControllerAdvice {
 * 
 * @ExceptionHandler(MethodArgumentNotValidException.class) public
 * ResponseEntity< CommonResponse>
 * notFoundException(MethodArgumentNotValidException e) { return error(e,
 * HttpStatus.BAD_REQUEST, e.getLocalizedMessage()); }
 * 
 * private <E extends MethodArgumentNotValidException>
 * ResponseEntity<CommonResponse> error(final E exception, final HttpStatus
 * httpStatus, final String logRef) { String fieldName ="message"; Map<String,
 * String> errors = new HashMap<>();
 * exception.getBindingResult().getAllErrors().forEach((error) -> { String
 * errorMessage = error.getDefaultMessage(); errors.put(fieldName,
 * errorMessage); } ); return ResponseEntity.ok(new CommonResponse("400",
 * errors.get(fieldName),"")); }
 * 
 * }
 */