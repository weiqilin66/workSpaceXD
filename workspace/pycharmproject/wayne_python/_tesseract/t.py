import pytesseract.pytesseract
from PIL import Image

# 打开图片
img_path = 'd:/driverAndPlugs/giv6j.jpg'
img = Image.open(img_path)
# 转化灰度图片
img = img.convert('L')
# 弹出图片
# img.show()

#二值化处理
threshold = 140
table=[]
for i in range(256):
    if i<threshold:
        table.append(0)
    else:
        table.append(1)
out = img.point(table,'1')
# out.show()
img = img.convert('RGB')

# 识别图片
string = pytesseract.image_to_string(img)
print(string)
# fail 解决办法：windows的需要下载Tesseract-OCR安装包 懒得解决
