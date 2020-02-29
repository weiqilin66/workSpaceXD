import re
string = '<div>love forever</div>'

print(re.compile(r'<div>(.*?)</div>').findall(string))