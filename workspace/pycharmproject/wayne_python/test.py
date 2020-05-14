import time

# print(time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()))
# print(time.strftime("%Y-%m-%d", time.localtime()))
# print(time.strftime("%H:%M:%S", time.localtime()))
string = '33人付款'
s = string.strip('人款收货')
print(int(s))