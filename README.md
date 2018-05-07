开启摄像头拍照 OCR文字识别
==============

## 前言
最近遇到一个项目需求，需要进行拍照，并且识别图片中的文字。拿到这个需求的的时候，自己有点懵，因为对这一块目前了解还太少。不过想着之前自己做过的一些项目和老师说过：我们现在很多时候都是面向service编程。于是就信心满满的开始了探索之旅。由于时间紧迫，一共花了一个晚上和一个上午的时间完成了这个需求。

## 项目目录

```
picture-text-demo
|
├── src                              
|  ├── demo               		   
|  ├── main.java                       #文字识别测试类
|  ├── test.java                       #base64生成图片测试类
|  ├── Picture.java                    #图片类(里面有文字识别方法和base64生成图片的方法)
|  ├── servlet.java                    #servlet请求处理
├── WebRoot                                
|  ├── index.jsp 					   #页面展示
```

## 项目图片
![image](https://github.com/deng1234/picture-text-demo/blob/master/_img/1.jpg)