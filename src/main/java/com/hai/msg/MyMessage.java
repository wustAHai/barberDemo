package com.hai.msg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by chenz at 14:51 on 2021/2/27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyMessage {
    int code;
    Object data;
    String msg;
}
