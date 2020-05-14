package lwq.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @author: LinWeiQi
 */
public class actList {
    /**
     * @TODO   快速比较两个集合的差异(主键)
     * @return
     * @param
     * @date   2020/5/12
     */
    private static List<String> getDIffList(List<String> allOpenidList, List<String> dbOpenidList) {
        if (dbOpenidList != null && !dbOpenidList.isEmpty()) {
            Map<String, String> dataMap = new HashMap<String, String>();
            for (String id : dbOpenidList) {
                dataMap.put(id, id);
            }

            List<String> newList = new ArrayList<String>();
            for (String id : allOpenidList) {
                if (!dataMap.containsKey(id)) {
                    newList.add(id);
                }
            }
            return newList;
        } else {
            return allOpenidList;
        }
    }
}
