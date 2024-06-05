package com.icad.shop.retailservice.util.logger;

import com.icad.shop.retailservice.constant.logger.IconConstant;
import com.icad.shop.retailservice.constant.logger.MessageConstant;
import com.icad.shop.retailservice.constant.logger.StatusConstant;
import com.icad.shop.retailservice.dto.response.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {
    private ResponseUtil() {
    }

    public static <T> ResponseDto<T> success() {
        return success(
                StatusConstant.SUCCESS,
                MessageConstant.CommonResponse.DEFAULT_RESPONSE,
                IconConstant.SUCCESS,
                null
        );
    }

    public static <T> ResponseDto<T> success(String code, String message, String icon) {
        return success(code, message, icon, null);
    }

    public static <T> ResponseDto<T> success(String code, String message, T result) {
        return success(code, message, null, result);
    }

    public static <T> ResponseDto<T> success(String code, String message, String icon, T result) {
        ResponseDto<T> response = new ResponseDto<>();
        response.setCode(code);
        response.setMessage(message);
        response.setIcon(icon);
        response.setResult(result);
        return response;
    }
}
