package com.itcast.crowdfunding.util;

public class AjaxResult {
    private boolean success;
    private String message;

    private Page page;
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", page=" + page +
                ", data=" + data +
                '}';
    }
}
