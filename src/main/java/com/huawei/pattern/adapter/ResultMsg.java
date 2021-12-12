package com.huawei.pattern.adapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:21
 * @Description：ResultMsg
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultMsg
{
    private int code;
    private String msg;
    private Object data;
}
