package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <E> {
    private Integer code;   // 业务状态码    0-成功 1-失败
    private String message; // 提示信息
    private E data;         // 响应数据

    // 返回操作成功响应结果（带响应数据）
    public static <E> Result <E> success(String message, E data) {
        return new Result<>(0, message, data);
    }

    // 返回操作失败响应结果
    public static Result error(String message) {
        return new Result<>(1, message, null);
    }
}
