package com.hpower.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Classname LoginLogDto
 * @Description TODO
 * @Date 2020/3/27 8:36 上午
 * @Created yangyang.jiang
 */
@Data
@Builder
public class LoginLogDto extends BaseDto {

    public static final String LOGIN = "1";

    public static final String LOGIN_OUT = "0";

    private String sessionId;

    private String userName;

    private Long userId;

    private Long qualityId;

    private Long hospitalId;

    private LocalDateTime loginTime;

    private LocalDateTime loginoutTime;

    private String idAddress;

    private String province;

    private String city;

    private String area;

    private String browser;

    private String os;

    private String userAgent;

    private String type;
}
