package com.lwq.hr.controller.sta;

import com.lwq.hr.entity.Goods;
import com.lwq.hr.entity.TbKw;
import com.lwq.hr.mapper.GoodsMapper;
import com.lwq.hr.mapper.ShopMapper;
import com.lwq.hr.mapper.TbKwMapper;
import com.lwq.hr.service.ChartService;
import lwq.returnbean.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description:
 * @author: LinWeiQi
 */
@RestController
@RequestMapping("/statistics/chart")
public class ChartController {

    @Resource
    GoodsMapper goodsMapper;
    @Resource
    ShopMapper shopMapper;
    @Autowired
    ChartService chartService;
    @Resource
    TbKwMapper tbKwMapper;

    String filePath = "D:\\workSpaceXD\\MyTarget\\goods.xlsx";

    /**
     * @return 读取Excel获取宝贝关键字存入数据库
     * @TODO
     * @date 2020/5/13
     */
    @GetMapping("/goodList")
    public RespBean loadExcelData() {
        chartService.loadExcelData(filePath);
        //返回前加工type
        // ...

        List<TbKw> list = tbKwMapper.selAll();
        return RespBean.build().setData(list);
    }

    /**
     * @param [title, date]
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @TODO 折线图接口
     * @date 2020/5/13
     */
    @GetMapping("/byTitle")
    public Map<String, Object> byTitle(String title, int date) {
        Map<String, Object> resMap = new HashMap<>();
        // 检索关键字优化
        String[] arr = title.split("\\s+");
        String condition = "";
        try {
            title = arr[0] + "%" + arr[1] + "%" + arr[2];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            resMap.put("error", "未按规则输入检索条件!");
            return resMap;
        }
        if (arr[2].contains("!")) {// ! 非  过滤标题
            String[] cd = arr[2].split("!");
            condition = cd[1];
            title = arr[0] + "%" + arr[1] + "%" + cd[0];

        }
        List<String> days = getDaysBetwwen(date - 1);
        List<String> shops = shopMapper.selShopName();
        resMap.put("shops", shops);
        for (String shop : shops) {
            List<Goods> goods = goodsMapper.byTitle(shop, title, days, condition);
            List<String> times = new ArrayList<>();//所有日期
            for (Goods good : goods) {
                times.add(good.getEtlDate());
            }
            // 得到两个日期数组差异 加工没获取到数据price为0
            List<String> newList = getDIffList(days, times);
            for (String s : newList) {
                Goods good = new Goods();//添加到list中每次都要new新对象 如果是重复set 在集合中上一个对象的值也会被改掉
                good.setEtlDate(s);
                good.setPrice((float) 0);
                goods.add(good);
            }
            float[] price = new float[date];
            // 根据etl_date重新排序集合
            Collections.sort(goods, new Comparator<Goods>() {
                @Override
                public int compare(Goods o1, Goods o2) {
//                    return o1.getEtlDate().compareTo(o2.getEtlDate()); //升序
                    return o2.getEtlDate().compareTo(o1.getEtlDate()); //降序
                }
            });
            for (int j = 0; j < goods.size(); j++) {
                price[j] = goods.get(j).getPrice();
            }

            resMap.put(shop, price);
        }
        return resMap;
    }

    /**
     * @TODO 查当日价格差超过20的宝贝
     * @date 2020/3/11
     */
    @GetMapping("/maxAndMin")
    public RespBean getMaxAndMin() {
        // 日期字符串
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = dateFormat.format(new Date());
        List<Goods> total = goodsMapper.selTotal(now);
        if (total.size() == 0) {
            return RespBean.error("今天还没爬取数据!");
        }
        List<Map<String, Object>> maxMin = chartService.getMaxMin(now);
        if (maxMin.get(0).get("error")!=null) {
            return RespBean.error(maxMin.get(0).get("error").toString());
        }
        return RespBean.build().setData(maxMin);
    }


    /**
     * @param [days]
     * @return java.util.Date
     * @TODO 获取近days-1天的日期
     * @date 2020/5/12
     */
    private static Date getDateAdd(int days) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -days);
        return c.getTime();
    }

    private static List<String> getDaysBetwwen(int days) { //最近几天日期
        List<String> dayss = new ArrayList<>();
        Calendar start = Calendar.getInstance();
        start.setTime(getDateAdd(days));
        Long startTIme = start.getTimeInMillis();
        Calendar end = Calendar.getInstance();
        end.setTime(new Date());
        Long endTime = end.getTimeInMillis();
        Long oneDay = 1000 * 60 * 60 * 24l;
        Long time = startTIme;
        while (time <= endTime) {
            Date d = new Date(time);
            DateFormat df = new SimpleDateFormat("yyyyMMdd");
//            System.out.println(df.format(d));
            dayss.add(df.format(d));
            time += oneDay;
        }
        return dayss;
    }

    /**
     * @param
     * @return
     * @TODO 快速比较两个集合的差异(主键)
     * @date 2020/5/12
     */
    private List<String> getDIffList(List<String> allOpenidList, List<String> dbOpenidList) {
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
