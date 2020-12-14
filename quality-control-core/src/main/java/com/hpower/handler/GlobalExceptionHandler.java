package com.hpower.handler;

import com.hpower.errorcode.ApiErrorCode;
import com.hpower.errorcode.IErrorCode;
import com.hpower.exception.ApiException;
import com.hpower.vo.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.PersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.*;

/**
 * 全局异常拦截器
 *
 * @author yangyang.jiang
 * @date 2020/03/23
 * @since 1.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    /**
     * <p>
     * 自定义 REST 业务异常
     * <p>
     *
     * @param e 异常类型
     * @return 错误消息
     */
    @ExceptionHandler(value = ApiException.class)
    public R apiException(ApiException e) {
        IErrorCode errorCode = e.getErrorCode();
        if (null != errorCode) {
            log.debug("Rest request failed, {}", errorCode.toString());
            return R.failed(errorCode);
        }
        log.debug("Rest request failed, {}", e.getMessage());
        return R.failed(e.getMessage());
    }

    /**
     * 无注解model参数校验异常
     *
     * @param e 异常类型
     * @return 错误消息
     */
    @ExceptionHandler(value = BindException.class)
    public R bindException(BindException e) {
        return bindingResult(e.getBindingResult());
    }

    /**
     * {@link org.springframework.web.bind.annotation.RequestBody}参数校验异常
     *
     * @param e 异常类型
     * @return 错误消息
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException e) {
        return bindingResult(e.getBindingResult());
    }

    /**
     * @param bindingResult 绑定结果
     * @return 错误消息
     */
    private R bindingResult(BindingResult bindingResult) {
        if (null != bindingResult && bindingResult.hasErrors()) {
            List<String> errorMsgs = new ArrayList<>();
            List<Object> jsonList = new ArrayList<>();
            bindingResult.getFieldErrors().forEach(fieldError -> {
                String message = fieldError.getDefaultMessage();
                //数据绑定错误
                if (fieldError.isBindingFailure()) {
                    String[] codes = fieldError.getCodes();
                    if (codes != null && codes.length > 0) {
                        message = messageSource.getMessage(codes[0], new Object[]{fieldError.getField()},
                                "参数[{0}]解析错误", LocaleContextHolder.getLocale());
                    }
                }
                Map<String, Object> jsonObject = new HashMap<>(3);
                jsonObject.put("name", fieldError.getField());
                jsonObject.put("msg", message);
                jsonObject.put("invalidValue", fieldError.getRejectedValue());
                jsonList.add(jsonObject);
                errorMsgs.add(message);
            });
            return R.r(IErrorCode.CODE_INVALID_PARAMETER, StringUtils.collectionToCommaDelimitedString(errorMsgs), jsonList);
        }
        return R.failed(ApiErrorCode.INVALID_PARAMETER);
    }

    /**
     * {@link org.springframework.web.bind.annotation.RequestParam}参数校验异常
     *
     * @param e 异常类型
     * @return 错误消息
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R constraintViolationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        log.debug("violation message is {}", violations.toString());
        List<String> violationMessages = new ArrayList<>();
        List<Object> jsonList = new ArrayList<>();
        for (ConstraintViolation<?> violation : violations) {
            StringBuilder name = new StringBuilder();
            violation.getPropertyPath().forEach((Path.Node node) -> {
                if (node.getKind() == ElementKind.PARAMETER) {
                    name.append(node.getName());
                    return;
                }
            });
            violationMessages.add(violation.getMessage());
            Map<String, Object> jsonObject = new HashMap<>(3);
            jsonObject.put("name", name.toString());
            jsonObject.put("msg", violation.getMessage());
            jsonObject.put("invalidValue", violation.getInvalidValue());
            jsonList.add(jsonObject);
        }
        if (violationMessages.size() > 0) {
            return R.r(IErrorCode.CODE_INVALID_PARAMETER, StringUtils.collectionToCommaDelimitedString(violationMessages), jsonList);
        }
        return R.failed(ApiErrorCode.INVALID_PARAMETER);
    }

    /**
     * http请求方法异常
     *
     * @param e 异常类型
     * @return 错误消息
     * @see org.springframework.http.HttpMethod
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public R httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        List<Object> jsonList = new ArrayList<>();
        Map<String, Object> jsonObject = new HashMap<>(3);
        jsonObject.put("name", "Request Method");
        jsonObject.put("msg", e.getMessage());
        jsonObject.put("invalidValue", e.getMethod());
        jsonList.add(jsonObject);
        String message = messageSource.getMessage("globalExceptionHandler.httpRequestMethodNotSupportedException",
                new Object[]{StringUtils.arrayToCommaDelimitedString(e.getSupportedMethods())},
                "接口只允许[{0}]请求方法", LocaleContextHolder.getLocale());
        return R.r(IErrorCode.CODE_INVALID_PARAMETER, message, jsonList);
    }

    /**
     * http媒体类型异常
     *
     * @param e 异常类型
     * @return 错误消息
     * @see org.springframework.http.MediaType
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public R httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        List<Object> jsonList = new ArrayList<>();
        Map<String, Object> jsonObject = new HashMap<>(3);
        jsonObject.put("name", "Content-Type");
        jsonObject.put("msg", e.getMessage());
        jsonObject.put("invalidValue", e.getContentType());
        jsonList.add(jsonObject);
        String message = messageSource.getMessage("globalExceptionHandler.httpMediaTypeNotSupportedException",
                new Object[]{StringUtils.collectionToCommaDelimitedString(e.getSupportedMediaTypes())},
                "接口只允许[{0}]媒体类型", LocaleContextHolder.getLocale());
        return R.r(IErrorCode.CODE_INVALID_PARAMETER, message, jsonList);
    }

    /**
     * http请求消息转换异常
     *
     * @param e 异常类型
     * @return 错误消息
     * @see org.springframework.http.converter.HttpMessageConverter
     */
    @ExceptionHandler(HttpMessageConversionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public R httpMessageConversionException(HttpMessageConversionException e) {
        List<Object> jsonList = new ArrayList<>();
        Map<String, Object> jsonObject = new HashMap<>(2);
        jsonObject.put("name", "body");
        jsonObject.put("msg", e.getMessage());
        jsonList.add(jsonObject);
        String message = messageSource.getMessage("globalExceptionHandler.httpMessageConversionException",
                null,
                "接口请求消息转换失败", LocaleContextHolder.getLocale());
        return R.r(IErrorCode.CODE_INVALID_PARAMETER, message, jsonList);
    }

    /**
     * 数据库操作异常
     *
     * @param e 异常类型
     * @return 错误消息
     * @see DataAccessException
     */
    @ExceptionHandler({DataAccessException.class})
    public R dataAccessException(DataAccessException e) {
        log.error("数据库操作异常StackTrace:", e);
        List<Object> jsonList = new ArrayList<>();
        Map<String, Object> jsonObject = new HashMap<>(2);
        jsonObject.put("name", "database");
        jsonObject.put("msg", e.getMessage());
        jsonList.add(jsonObject);
        String message = messageSource.getMessage("globalExceptionHandler.dataAccessException",
                null,
                "数据库操作失败", LocaleContextHolder.getLocale());
        return R.r(IErrorCode.CODE_INVALID_PARAMETER, "操作失败，请联系管理员！", jsonList);
    }

    /**
     * 数据库查询异常
     *
     * @param e 异常类型
     * @return 错误消息
     * @see PersistenceException
     */
    @ExceptionHandler({PersistenceException.class})
    public R persistenceException(PersistenceException e) {
        log.error("数据库操作异常StackTrace:", e);
        List<Object> jsonList = new ArrayList<>();
        Map<String, Object> jsonObject = new HashMap<>(2);
        jsonObject.put("name", "database");
        jsonObject.put("msg", e.getMessage());
        jsonList.add(jsonObject);
        String message = messageSource.getMessage("globalExceptionHandler.dataAccessException",
                null,
                "Mybatis数据库操作失败", LocaleContextHolder.getLocale());
        return R.r(IErrorCode.CODE_INVALID_PARAMETER, "查询失败，请联系管理员！", jsonList);
    }

    /**
     * 全局异常
     *
     * @param e 异常
     * @return 错误消息
     */
    @ExceptionHandler(Exception.class)
    public R exception(Exception e) {
        /*
          系统内部异常，打印异常栈
         */
        log.error("系统内部异常StackTrace:", e);
        return R.failed(ApiErrorCode.FAILED);
    }
}
