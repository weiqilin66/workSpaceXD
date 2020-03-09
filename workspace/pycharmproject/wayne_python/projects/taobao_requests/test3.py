import jsonpath
import json

json_dic = json.load(open('half_auto_taobao.txt','r',encoding='utf8'))
print(json_dic)

# title = jsonpath.jsonpath(json_dic,'$.')
print('-'* 50)

good_items = json_dic['mods']['itemlist']['data']['auctions']
good_list = []
for good_item in good_items:
    goods={
        'title': good_item['raw_title'],
        'price': good_item['view_price'],
        'location': good_item['item_loc'],
        'sales': good_item['view_sales'],
        # 'comment_url': good_item['comment_url']
    }
    good_list.append(goods)
print(good_list)
