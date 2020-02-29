package lwq.api;

import lwq.utils.CamelFormatUtil;
import lwq.utils.POIUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/poi")
public class POI {
    /**
     * @TODO 非通用解析excel成json =>写zh文件和vue的el-table重复组件元素
     * @date 2020/2/22
     */
    @PostMapping("/excel2JSON")
    public String excel2JSON(MultipartFile file) throws Exception {

        StringBuilder str = new StringBuilder(); //builder 线程不安全优势速度快
        String fileName = file.getOriginalFilename();
        //通用读取excel 每个sheet页第二行开始取数
        List<List<Object>> list = POIUtils.myImportExcel(file.getInputStream(), fileName);
        assert fileName != null;
        int lastDo = fileName.lastIndexOf(".");
        // 抽取对象名
        fileName = fileName.substring(0,lastDo);
        str.append(fileName).append(":{\n");

        // 遍历两列为json的key和value 约定表头key value为 table1 : 'tableName'
        int column = 1;
        for (List<Object> list2 : list) {
            for (Object obj : list2) {
                String s = String.valueOf(obj);
                s = CamelFormatUtil.ToCamel(s);
                if (column % 2 == 0) {//偶数为value
                    str.append("`").append(s).append("`,").append("\n");
                } else {
                    str.append("\t").append(s).append(": ");
                }
                column++;
            }
        }
        int lastDo2 = str.lastIndexOf(",");
        str.delete(lastDo2,lastDo2+1);
        str.append("}");
        System.out.println(str.toString()); //zh

        //写vue
        StringBuilder vueStr = new StringBuilder();
        int index = 1;
        String tableIndex;
        for (List<Object> list2 : list) {
            for (Object obj : list2) {
                if (index % 2 == 0) {//偶数为value
                    index++;
                    continue; // 跳过本次循环
                }
                String s = String.valueOf(obj);
                s = CamelFormatUtil.ToCamel(s);
                if (s.length()>=5 && s.substring(0, 5).equals("table")) {
                    vueStr.append("\n<!--").append(list2.get(1).toString()).append("-->\n\n");
                   // tableIndex = s.substring(5,6);
                } else {
                    /**
                     <el-table-column
                     prop="endContract"
                     width="95"
                     align="left"
                     label="合同截止日期">
                     </el-table-column>
                     <el-table-column
                     width="100"
                     align="left"
                     label="合同期限">
                     <template slot-scope="scope">
                     <el-tag>{{scope.row.contractTerm}}</el-tag>
                     年
                     </template>
                     </el-table-column>
                     */
                    vueStr.append("<el-table-column").append("\n").append("\t")
                            .append("prop=").append("\"").append(s).append("\"").append("\n").append("\t")
                            .append("width=\"130\"").append("\n").append("\t")
                            .append(":label=\"$t('").append(fileName+".").append(s +"')\"").append("\n").append("\t")
                            .append("align=\"center\"").append(">").append("\n")
                            .append("</el-table-column>\n");
                }
                index++;
            }
        }
        System.out.println(vueStr);
        str.append("\n").append(vueStr);
        return str.toString();
    }
}
