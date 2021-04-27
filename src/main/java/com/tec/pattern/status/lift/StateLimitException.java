package com.tec.pattern.status.lift;

/**
 * @ClassName StateLimitException
 * @Description TODO
 * @Author jason
 * @Date 2021/4/23 16:03
 **/
public class StateLimitException extends RuntimeException {

    public StateLimitException() {
        super();
    }

    public StateLimitException(String message) {
        super(message);
    }
}
