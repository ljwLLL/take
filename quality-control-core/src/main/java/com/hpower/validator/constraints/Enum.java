package com.hpower.validator.constraints;

import com.hpower.enums.IEnum;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.annotation.ElementType.*;

/**
 * 枚举验证器注解
 * <p>
 * 当验证值为null时默认返回验证成功，如果需要验证null请再加相关的验证器。
 * </p>
 *
 * @author yangyang.jiang
 * @date 2020/03/27
 * @since 1.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {Enum.EnumValidator.class})
@Documented
public @interface Enum {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 给定的枚举类型必须继承{@link IEnum}
     *
     * @return 枚举数组
     */
    Class<? extends java.lang.Enum>[] target() default {};


    /**
     * 枚举验证器
     *
     * @author yangyang.jiang
     * @date 2020/03/27
     * @since 1.0
     */
    class EnumValidator implements ConstraintValidator<Enum, Object> {

        /**
         * 枚举类
         */
        private Class<?>[] cls;

        @Override
        public void initialize(Enum constraintAnnotation) {
            cls = constraintAnnotation.target();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (value == null) {
                return true;
            }
            if (cls.length > 0) {
                for (Class<?> cl : cls) {
                    try {
                        if (cl.isEnum() && IEnum.class.isAssignableFrom(cl)) {
                            //枚举类验证
                            Object[] objects = cl.getEnumConstants();
                            Method method = cl.getMethod("getValue");
                            for (Object object : objects) {
                                Object code = method.invoke(object, null);
                                if (value.equals(code)) {
                                    return true;
                                }
                            }
                        }
                    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                return true;
            }
            return false;
        }
    }
}
