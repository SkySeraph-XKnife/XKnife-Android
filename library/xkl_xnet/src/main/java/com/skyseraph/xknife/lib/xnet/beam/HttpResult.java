package com.skyseraph.xknife.lib.xnet.beam;

/**
 * Created by SkySeraph on 2016/5/4.
 * 统一封装返回数据格式（暂未用）
 *
 * @param <T> the type parameter
 */
public class HttpResult<T> {

    /**
     * 通用数据格式
     * {
     * "resultCode": 0,
     * "resultMessage": "成功",
     * "data": {}
     * }
     */

    private int resultCode;

    private String resultMessage;

    private T data;

    /**
     * Gets result code.
     *
     * @return the result code
     */
    public int getResultCode() {
        return resultCode;
    }

    /**
     * Gets result message.
     *
     * @return the result message
     */
    public String getResultMessage() {
        return resultMessage;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }
}