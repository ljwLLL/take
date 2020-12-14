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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.annotation.ElementType.*;

/**
 * <p>
 *     密码验证器
 * </p>
 *
 * @author yangyang.jiang
 * @date 2020/03/27
 * @since 1.0
 */
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {Password.PasswordValidator.class})
@Documented
public @interface Password {

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Password.PasswordFormat format() default  PasswordFormat.NUMBER;

    class PasswordValidator implements ConstraintValidator<Password, Object> {

        private String pattern;

        @Override
        public void initialize(Password constraintAnnotation) {
            pattern = constraintAnnotation.format().getValue();
        }

        @Override
        public boolean isValid(Object value, ConstraintValidatorContext context) {
            if (value == null || value.toString().length() < 6) {
                return false;
            }
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(value.toString());
            return matcher.find();
        }
    }

    enum PasswordFormat implements IEnum<String> {

        /**
         * 只包含数字
         */
        NUMBER("^?\\d+$", "只包含数字"),

        /**
         * 同时包含数字和字母
         */
        NUMBER_LETTER("^(?![a-zA-z]+$)(?!\\d+$)(?![!@#$%^&*]+$)[a-zA-Z\\d!@#$%^&*]+$", "同时包含数字和字母"),

        /**
         * 同时包含数字字母特殊字符
         */
        NUMBER_LETTER_CHAR("^^(?![a-zA-z]+$)(?!\\d+$)(?![~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]+$)(?![a-zA-z\\d]+$)(?![~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]+$)(?![\\d~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]+$)[a-zA-Z\\d~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]+$"
                , "同时包含数字字母特殊字符"),
        ;

        private final String value;
        private final String label;
        private final String remark;

        PasswordFormat(final String value, final String label) {
            this(value, label, null);
        }

        PasswordFormat(final String value, final String label, final String remark) {
            this.value = value;
            this.label = label;
            this.remark = remark;
        }

        /**
         * @return 数据值
         */
        @Override
        public String getValue() {
            return value;
        }

        /**
         * @return 标签名
         */
        @Override
        public String getLabel() {
            return label;
        }

        /**
         * @return 备注
         */
        @Override
        public String getRemark() {
            return remark;
        }
    }
}
