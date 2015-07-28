# MyAndroidDevTools
// 初版，一些属性转换类型的方法
=====

使用方法
----
   如您所见，把jar包包下载，导入，作为依赖包就可以了-V-<br>
   
   在需要用到的地方通过调用DevToolsFactory.getDataTools()等等getxxx方法来获得相应的工具类方法
 
目前拥有的方法：
---
##Factory:<br>
   使用的时候只需要new这个对象，其他不建议new
####getDataTools():<br> 
   数据类处理方法，主要为转换
####getFileTools():<br>
   文件类处理方法，主要为创建，删除，复制等，目前仅实现了创建<br>
##其他的类:<br>
   `请勿new这些类`
######DataConversion():
 请注意，以下关于0xXX均为用String.Format（"0x%02x",object）转换后得来，普通打印会打因为10进制<br>
```java    
    byteArray2IntArray(byte[] byteArray)
``` 
    将byte[]转为int[]而不改变其原来的样貌，如0x10，一般来说是转成16的int，而这个方法则是将其转为10，也就是0~F原来只会被翻译为0~15，而不会进位，因此调用这个方法所得到的0x10将会被转为10同样，0X0A也会被转为10
```java    
    uniteBytes(byte src0, byte src1)
```    
    将两个ASCII结合成一个byte，如char a='A',char b='B'，调用方法后 返回的是 byte AB;(0xAB)
```java    
    HexString2Bytes(String src)
```    
    将一个Sring串转为两两结合的byte[]，如str={ABCDEF},调用方法后将会是byte[] b={0xAB,0xCD，0xEF}
```java
     byte2StrEx(byte[] srcBytes, int type)
```     
     一个奇怪的无聊的方法:<br>
```java
byte2StrEx(byte[] srcBytes, int type)
```
     byte[]->String，用于将{0x00,0x0,0x02}转换为"00-01-02"或者"00:01:02"或者"00 01 02"或者"0x00-0x01-0x02"等等，这些类型只需要传入DataConversion.TYPE_IN_EMPTY或者其他的就可以了

// 2015/07/27 增加创建文件夹方法，该方法在FileTool.java下
---

  使用方法
  ---
    在您需要的activity中首先加入下面代码初始化
```java   
      DevToolsFactory.getFileTools().initialize(context);
```      
      然后就可以调用
```java      
      DevToolsFactory.getFileTools().createFolder(String rootPath,String PATH)
```      
rootPath代表着您要创建的根路径，如果创建于SD卡上，请确保SD卡存在，否则可能会崩溃。<br>
当传入""空字符串时，代表着默认的路径，即有SD卡的时候默认根路径为SD卡路径，无SD卡时，默认为包内路径。<br>

传入的PATH格式应为"A/B/C/D/E"这样该工具类会自动创建 SD卡/A/B/C/D/E(如果SD卡存在)或者是 /data/data/packagename/A/B/C/D/E（如果SD卡不存在）
当然，您也可以在当前文件夹存在的时候进行创建。

//2015/7/28 增加了复制文件的方法，该方法使用如下：<br>
---
```java
      DevToolsFactory.getFileTools().copyfile(String fromPath, String toPath, Boolean rewrite)
```
在您需要用的地方请调用它，然后传入源文件的路径和目标文件的路径，并设置是否可以覆盖目标文件<br>
请务必保证路径的绝对性以及加上文件的后缀<br>
例如fromPath="sdcard/xxx/xxx.txt"<br>
同样，目标文件例子：toPath="sdcard/xxx/xxx.txt"<br>
当rewrite为true时，若目标文件存在，则会覆盖目标文件。<br>
当rewrite为false时，若目标文件存在，则会在原目标文件的末尾加上-new。<br>
使用前请务必加上必要的权限。<br>
以上......

 
