import com.thief.dao.ProductDao;
import com.thief.entity.Product;
import lwq.generator.SqlGenerator;

import java.io.IOException;
import java.util.Map;

/**
 * @Description: 描述
 * @author: LinWeiQi
 */
public class TEST {
    public static void main(String[] args) throws IOException {
        Map<String,String> sqlmap = SqlGenerator.createTable(Product.class, null);
        ProductDao dao = new ProductDao();
        dao.createTable(sqlmap.get("drop"));
    }
}
