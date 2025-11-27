package com.university.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {

    private String status; // "success" or "error"
    private T data;
    private ApiError error;
    private T meta;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data, null, null);
    }

    public static <T> ApiResponse<T> success(T data, T meta) {
        return new ApiResponse<>("success", data, null, meta);
    }

    public static <T> ApiResponse<T> error(ApiError err) {
        return new ApiResponse<>("error", null, err, null);
    }
}
