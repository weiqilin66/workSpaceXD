import pymysql

# 链接mysql
conn = pymysql.connect('localhost', 'root', 'root', 'vhr')
# 创建游标
cursor = conn.cursor()


# sql语句
insert_sql = """
        insert into goods(shop,title,price,sales,freight,etl_date,etl_time,kw,detail_url,img_url) VALUES(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)
        """
select_sql = """
        select * from shop
"""
#  查询
i=cursor.execute(select_sql)
print(cursor.fetchall())
# 提交，不进行提交无法保存到数据库
conn.commit()


# 关闭游标和连接
cursor.close()
conn.close()