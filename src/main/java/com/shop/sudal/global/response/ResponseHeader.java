package com.shop.sudal.global.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseHeader {
    private Integer code;
    private Boolean isSuccess;
    private String message;

    public ResponseHeader(ResponseCode responseCode) {
        this.code = responseCode.getHttpStatusCode();
        this.isSuccess = responseCode.getIsSuccess();
        this.message = responseCode.getMessage();
    }
}
