# MyAndroidDevTools
// 初版，一些属性转换类型的方法

使用方法
 把相应java文件塞到您的工程中，以后也许会导出为jar包
 DevToolsFactory devTools = new DevToolsFactory();
 在需要用到的地方通过调用devTools.getData()等等getxxx方法来获得相应的工具类方法
 
目前拥有的方法：
  Factory:
   getData() 数据类处理方法，主要为转换
    
  DataConversion():
    请注意，以下关于0xXX均为用String.Format（"0x%02x",object）转换后得来，普通打印会打因为10进制
    
    byteArray2IntArray(byte[] byteArray):将byte[]转为int[]而不改变其原来的样貌，如0x10，一般来说是转成16的int，而这个方法则是将其转为10，也就是0~F原来只会被翻译为0~15，而不会进位，因此调用这个方法所得到的0x10将会被转为10同样，0X0A也会被转为10
    
    uniteBytes(byte src0, byte src1)：将两个ASCII结合成一个byte，如char a='A',char b='B'，调用方法后 返回的是 byte AB;(0xAB)
    
    HexString2Bytes(String src)：将一个Sring串转为两两结合的byte[]，如str={ABCDEF},调用方法后将会是byte[] b={0xAB,0xCD，0xEF}
    
     byte2StrEx(byte[] srcBytes, int type)：一个奇怪的无聊的方法，byte[]->String，用于将{0x00,0x0,0x02}转换为"00-01-02"或者"00:01:02"或者"00 01 02"或者"0x00-0x01-0x02"等等，这些类型只需要传入DataConversion.TYPE_IN_EMPTY或者其他的就可以了
 
