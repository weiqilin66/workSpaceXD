package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableQ {
    /**
     * tableNameCN表中文名	tableNameUS表英文名
     * columCN字段中文名	    columUS字段英文名
     * columType字段类型	comment1启用标志
     */
    private String tableNameCN;
    private String tableNameUS;
    private String columCN;
    private String columUS;
    private String columType;
    private String comment1;
    private String comment2;
    public TableQ(String s1,String s2,String s3,String s4 ,String s5,String s6){
        this.tableNameCN = s1;
        this.tableNameUS = s2;
        this.columCN = s3;
        this.columUS = s4;
        this.columType = s5;
        this.comment1 = s6;
    }

}
