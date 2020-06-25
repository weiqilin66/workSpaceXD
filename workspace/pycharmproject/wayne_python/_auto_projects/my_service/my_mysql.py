import pymysql


class MySql2(object):
    def __init__(self):
        self.conn = pymysql.connect('localhost', 'root', 'root', 'vhr')
        self.cursor = self.conn.cursor()

    def commit(self, sql, args):  # tuple参数
        if args:
            self.cursor.execute(sql, args)
        else:
            self.cursor.execute(sql)
        self.conn.commit()

    def query(self, query_sql,args):
        if args:
            self.cursor.execute(query_sql,args)
        else:
            self.cursor.execute(query_sql)

        return self.cursor.fetchall()
