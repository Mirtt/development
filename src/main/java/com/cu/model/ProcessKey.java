package com.cu.model;

/**
 * 处理过程关键字（包含优先级和对应关系）
 *
 * @authur Zyq
 * @create 2017/10/23.
 */
public class ProcessKey {
    private int process_key_id; //处理过程关键字id
    private String process_key; //处理过程关键字
    private String reason; //故障原因
    private int process_priority; //处理过程优先级
    private int content_key_id; //关联的申告内容关键字id

    public int getProcess_key_id() {
        return process_key_id;
    }

    public void setProcess_key_id(int process_key_id) {
        this.process_key_id = process_key_id;
    }

    public String getProcess_key() {
        return process_key;
    }

    public void setProcess_key(String process_key) {
        this.process_key = process_key;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getProcess_priority() {
        return process_priority;
    }

    public void setProcess_priority(int process_priority) {
        this.process_priority = process_priority;
    }

    public int getContent_key_id() {
        return content_key_id;
    }

    public void setContent_key_id(int content_key_id) {
        this.content_key_id = content_key_id;
    }
}
