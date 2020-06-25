from my_appium.fish import MySql2

MySql2().commit('update fish_kw_price set price = %s where kw = %s',(1,'怪物猎人'))
