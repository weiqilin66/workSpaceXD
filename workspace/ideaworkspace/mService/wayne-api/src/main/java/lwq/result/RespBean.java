package lwq.result;

import lombok.Data;

/**
 * @Description:
 * @author: LinWeiQi
 */
@Data
public class RespBean {
    private Integer status; // code
    private String message;
    private Object data;

    public RespBean() {
    }

    public RespBean(Integer status, String message, Object obj) {
        this.status = status;
        this.message = message;
        this.data = obj;
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }
    public static RespBean ok() {
        return new RespBean(200, null, null);
    }
    public static RespBean error() {
        return new RespBean(500, null, null);
    }
    public static RespBean ok(String msg, Object o) {
        return new RespBean(200, msg, o);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object o) {
        return new RespBean(500, msg, o);
    }
}
