package org.school.backend.adapters.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.school.backend.adapters.exception.dto.ErrorResponseDto;
import org.school.backend.adapters.exception.error.ErrorBuilder;
import org.school.backend.adapters.exception.message.ErrorMessage;
import org.school.backend.application.exception.GradeDataNotFoundException;
import org.school.backend.application.exception.ParentDataNotFoundException;
import org.school.backend.application.exception.StudentDataNotFoundException;
import org.school.backend.application.exception.StudentGradeDataNotFoundException;
import org.school.backend.application.exception.SubjectDataNotFoundException;
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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponseDto handleStudentDataNotFoundException(final StudentDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GradeDataNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponseDto handleGradeDataNotFoundException(final GradeDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParentDataNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponseDto handleParentDataNotFoundException(final ParentDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(StudentGradeDataNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponseDto handleStudentGradeDataNotFoundException(final StudentGradeDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(SubjectDataNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody ErrorResponseDto handleSubjectDataNotFoundException(final SubjectDataNotFoundException exception, final HttpServletRequest request) {
        return errorBuilder.createError(ErrorMessage.exchangeRequestError(exception.getMessage()), exception.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
