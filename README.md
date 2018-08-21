# voice2word
一、这是啥？

 1. 一个 PC 版语音转文字的工具

二、能做什么？

 1. 把对着话筒说的话转换成文字，并且粘贴在当前窗口可以输入文字并且光标聚焦的地方
 2. 看看效果
![这里写图片描述](https://img-blog.csdn.net/20180821180555829?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L29jcDExNA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
 
三、怎么用？
	 
 1. 首先参考[上一篇博客](https://blog.csdn.net/ocp114/article/details/81911041)到讯飞开放平台创建相应的应用，得到下面箭头的文件以及一个appid
 ![这里写图片描述](https://img-blog.csdn.net/20180821180933573?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L29jcDExNA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

2.appid 填写到 config.properties 文件中，没有的话就创建一个，文件名和属性名不要改变哦
	 ![这里写图片描述](https://img-blog.csdn.net/20180821181205754?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L29jcDExNA==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
	 
3.把上面的东西导入到项目编译运行即可
	
[源码地址](https://github.com/wongtp/voice2word)

[编译后的 exe 文件地址](https://download.csdn.net/download/ocp114/10618249)
