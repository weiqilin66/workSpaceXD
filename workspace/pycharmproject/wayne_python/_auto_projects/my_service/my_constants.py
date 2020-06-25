my_constants = {
    # 黑鲨3
    'shark3': {
        '我的': (0.897, 0.957),
        '设置': (0.901, 0.04),
        '退出登录': (0.491, 0.951),
        '确定退出': (0.705, 0.558),
        '支付宝登录': (0.54, 0.66),
        '立即登录': (0.45, 0.396),
        '输入法': 'com.baidu.input_heisha/.ImeService',
        '最低价': (0.443, 0.438),
        '最高价': (0.796, 0.439),
        '筛选确定': 'adb shell input tap 790 2333',
        '双击间隔': 110,  # ms
        '上滑加载': (531, 2269, 540, 0),
        '启动三连': ('adb shell input tap 541 1537', 'adb shell input tap 544 1342', 'adb shell input tap 555 1260'),
        '长按聊天框': 'adb shell input swipe 550 2313 551 2314 1500',  # adb命令长按,差1的位移
        '单击聊天框': 'adb shell input tap 949 2314',
        '发送': 'adb shell input tap 949 2300',
        '立即购买': 'adb shell input tap 1037 341',
        '黏贴': 'adb shell input tap 149 2196',
        '返回': 'adb shell input tap 59 138',
        'pos1': {'x': 300, 'y': 730},
        'pos2': {'x': 800, 'y': 730},
        'pos3': {'x': 300, 'y': 1400},
        'pos4': {'x': 800, 'y': 1400},
        'pos5': {'x': 300, 'y': 2100},
        'pos6': {'x': 800, 'y': 2100},
    },
    '界面': {
        '包名': 'com.taobao.idlefish',
        '首页': 'com.taobao.fleamarket.home.activity.MainActivity',
        '搜索页': 'com.idlefish.flutterbridge.flutterboost.IdleFishFlutterActivity',
        '详情页': 'com.idlefish.flutterbridge.flutterboost.IdleFishFlutterActivity',
        '聊天页': 'com.idlefish.flutterbridge.flutterboost.IdleFishFlutterActivity',

    },
    'codeMap': {
        '0': 7,
        '1': 8,
        '2': 9,
        '3': 10,
        '4': 11,
        '5': 12,
        '6': 13,
        '7': 14,
        '8': 15,
        '9': 16,
        'home': 3,
        'back': 4,
    },
    'ps留言': ('盘无划痕吗 盒子有损坏吗 老铁', '老铁 盒子有损吗 盘有划痕吗'),
    'switch留言': ('老铁 卡带盒子无损吗', '卡带盒子有损坏吗 老铁'),
    '中文': '',
    '港版': '',
}