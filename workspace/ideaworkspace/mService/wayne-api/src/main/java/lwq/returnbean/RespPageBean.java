package lwq.returnbean;

import lombok.Data;

import java.util.List;

/**
 * @Description:
 * @author: LinWeiQi
 */
@Data
public class RespPageBean {
    private long total;
    private List<?> data;
}
