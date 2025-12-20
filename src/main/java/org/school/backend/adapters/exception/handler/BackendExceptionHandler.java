package org.school.backend.adapters.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.school.backend.adapters.exception.dto.ErrorResponseDto;
import org.school.backend.adapters.exception.error.ErrorBuilder;
import org.school.backend.adapters.exception.message.ErrorMessage;
import org.school.backend.application.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class BackendExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorBuilder errorBuilder;

    public BackendExceptionHandler(final ErrorBuilder errorBuilder) {
        this.errorBuilder = errorBuilder;
    }

    @ExceptionHandler(StudentDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponseDto handleStudentDataNotFoundException(final StudentDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(GradeDataNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponseDto handleGradeDataNotFoundException(final GradeDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParentDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponseDto handleParentDataNotFoundException(final ParentDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StudentGradeDataNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponseDto handleStudentGradeDataNotFoundException(final StudentGradeDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SubjectDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponseDto handleSubjectDataNotFoundException(final SubjectDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(SavingDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponseDto handleSavingDataNotFoundException(final SavingDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ActivityDataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ErrorResponseDto handleActivityDataNotFoundException(final SavingDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidPaymentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorResponseDto handleInvalidPaymentException(final InvalidPaymentException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.BAD_REQUEST);
    }

}
