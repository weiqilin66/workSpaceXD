import time

s = '1/13'
strr = s.split('/')
print(strr[1])
etl_date = time.strftime("%Y%m%d", time.localtime())
print(etl_date)
