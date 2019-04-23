package kr.hs.dgsw.web02blog.Protocol;

public class ResponseFormat {
    private int code;
    private String desc;
    private Object data;

    public ResponseFormat(ResponseType rt, Object data, Object option) {
        this.code = rt.code();
        this.desc = option instanceof Long || option instanceof String
                ? String.format(rt.desc(), option) : rt.desc();
        this.data = data;
    }

    public ResponseFormat(ResponseType rt, Object data) {
        this(rt, data, null);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
