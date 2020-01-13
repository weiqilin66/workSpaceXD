f = open('d:/test/io.txt', 'r')

try:
    f.read()
except ValueError as e:
    print(e)
else:
    print('no error')
finally:
    if f:
       f.close()
    print('end')

with open('d:/test/io.txt', 'r') as f2:  # 不用close
    f2.read()
# 调用read()会一次性读取文件的全部内容，如果文件有10G，内存就爆了，所以，要保险起见，可以反复调用read(size)方法，每次最多读取size个字节的内容。另外，调用readline()可以每次读取一行内容，调用readlines()一次读取所有内容并按行返回list。因此，要根据需要决定怎么调用。
with open('d:/test/io.txt', 'w') as f3:
    try:
        f3.write('写入')
    finally:
        print('with open close自动')


import os
os.path.abspath('.')
try:
    os.mkdir('d:/test/test2')
except OSError as e:
    print('oseero',e)
else:
    os.rmdir('d:/test/test2')
# 把两个路径合成一个时，不要直接拼字符串，而要通过os.path.join()函数