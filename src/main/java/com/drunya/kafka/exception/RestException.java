package com.drunya.kafka.exception;

import com.drunya.kafka.dto.error.RestErrorDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestException extends RuntimeException {

    private HttpStatus statusCode;

    public RestException(RestErrorDto restErrorDto) {
        super(restErrorDto.getMessage());
        this.statusCode = restErrorDto.getStatus();
    }
}
