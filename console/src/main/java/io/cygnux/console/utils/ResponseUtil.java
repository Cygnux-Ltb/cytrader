package io.cygnux.console.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtil {

    /**
     * Http Status Code: 400 Bad Request
     *
     * @return ResponseEntity<T>
     */
    public static <T> ResponseEntity<T> badRequest() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    /**
     * Http Status Code: 500 Internal Server Error
     *
     * @return
     */
    public static <T> ResponseEntity<T> internalServerError() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * Http Status Code: 200 ok
     *
     * @return
     */
    public static <T> ResponseEntity<T> ok() {
        return ResponseEntity.ok().build();
    }

    /**
     * 生成JSON Response
     *
     * @param <T>
     * @param t
     * @return
     */
    public static <T> ResponseEntity<T> responseOf(T t) {
        return t == null ? internalServerError() : ResponseEntity.ok(t);
    }


}
