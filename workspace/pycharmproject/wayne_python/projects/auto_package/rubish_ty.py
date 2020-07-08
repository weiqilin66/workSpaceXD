import os
import sched
import time
from datetime import datetime
"""
lwq:定时打包任务
"""
schedule = sched.scheduler(time.time, time.sleep)

def doPackage(cmd,path):
    os.chdir(path)
    p = os.system(cmd)
    return p

def package1(cmd,path):
    print('开始打包--------')
    count = 0
    try:
        while 1==1:
            p = doPackage(cmd,path)
            if p==0:
                print('打包成功')
                return
            count= count+1
            print('打包失败重新打包第 ',count,' 次')
            time.sleep(5)
    except:
        print('打包失败')
    # os.popen('npm run build:prod')

def main():
    cmd = 'npm run build'
    path =r'D:\workSpaceXD\workspace\webstormproject\vuehr'
    # schedule.enter(0, 0, package1(cmd,path))
    # schedule.run()
    # if cmd is None or cmd=='':
    tip1 = '打包命令%s'%cmd
    a = input(tip1)
    print(a)
    package1(cmd,path)


main()