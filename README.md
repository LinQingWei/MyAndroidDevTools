# MyAndroidDevTools
// 这里是一些工具类，虽然现在的工具不多，但我会坚持更新的，另外，我是个注释狂魔，如果看源码因为注释而打扰到您，这里我表示非常的抱歉，真的对不起- -
=====
使用方法
----
   如您所见，把jar包包下载，导入，作为依赖包就可以了-V-<br>
   在需要用到的地方通过调用DevToolsFactory.getDataTools()等等getxxx方法来获得相应的工具类方法
目前拥有的方法：
---
##Factory:<br>
   直接调用它里面的getXXX()就好了-V-如 DevToolsFactory.getFileTools().initialize(context);
####getDataTools():<br> 
   数据类处理方法，主要为转换
####getFileTools():<br>
   文件类处理方法，主要为创建，复制等，在使用这个工具里面的方法前，请务必要初始化DevToolsFactory.getFileTools().initialize(context);<br>
####getViewTools():<br>
   View相关的方法，目前有一大堆网上通用的单位转换，获取状态栏高度，获取标题栏高度，获取当前Activity截图<br>还有通过指定x,y位置，指定半径来获取圆形截图。
####getBitmapTools():<br>
   目前拥有的方法是缩放图片，合成蒙板图片。<br>
 
 ---
// 2015/07/27 增加创建文件夹方法，该方法在FileTool.java下
---
  使用方法
  ---
```java      
      DevToolsFactory.getFileTools().createFolder(String rootPath,String PATH)
```      
rootPath代表着您要创建的根路径，如果创建于SD卡上，请确保SD卡存在，否则可能会崩溃。<br>
当传入""空字符串时，代表着默认的路径，即有SD卡的时候默认根路径为SD卡路径，无SD卡时，默认为包内路径。<br>

传入的PATH格式应为"A/B/C/D/E"这样该工具类会自动创建 SD卡/A/B/C/D/E(如果SD卡存在)或者是 /data/data/packagename/A/B/C/D/E（如果SD卡不存在）
当然，您也可以在当前文件夹存在的时候进行创建。
 
 ---
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
<br>
 
 ---
//2015/07/30 增加了BitmapTools和ViewTools<br>
------
使用方法
---
如之前一样，直接调用<br>
```java
   DevToolsFactory.getBitmapTools()
或者
   DevToolsFactory.getViewTools()
```
里面的方法吧-V- <br>
###getBitmapTools()：<br>
该方法含有
```java
	public Bitmap zoomBitmap(Bitmap bitmap, int width, int height); //缩放bitmap
	public void saveBitmap(Bitmap bm, String path, String picName) throws IOException;//保存bit到本地（jpg）
	public Bitmap zoomBitmap(Bitmap bitmap, int width, int height); 
```
该方法传递bitmap对象，path传入需要存放的路径，picname则是传入保存的文件地址，请务必加上后缀名，避免二次更名操作
###getViewTools()：<br>
该方法含有
```java
   public int px2dip(Context context, float pxValue);//将px值转换为dip或dp值，保证尺寸大小不变
   public int dip2px(Context context, float dipValue);//将dip或dp值转换为px值，保证尺寸大小不变
   public int px2sp(Context context, float pxValue);//将px值转换为sp值，保证文字大小不变
   public int sp2px(Context context, float spValue);// 将sp值转换为px值，保证文字大小不变
   public int getStateBarHeight(Activity activity);//获取当前Activity的状态栏高度
   public int getTitleBarHeight(Activity activity);//获取当前Activity的标题栏高度
   public int[] getScreenWH(Activity activity);//获取当前屏幕宽高（px）,width=int[0],height=int[1]
   public Bitmap takeScreenShot(Activity activity);//截取当前屏幕图片，返回Bitmap
   public Bitmap getBitmapRound(Activity activity, int centerX, int centerY, int radius);//截取以x,y为中心，半径为radius的圆形图片
   public Bitmap takeScreenShot(Activity activity);//截取当前屏幕图片，返回Bitmap
```
takeScreenShot方法主要是利用view的cache来获取当前屏幕图片，返回的是bitmap，可以结合上面的saveBitmap来保存到本地哦，请务必注意，传入的参数是Activity，请勿传入getApplicationContext()<br>
```java
   public Bitmap getBitmapRound(Activity activity, int centerX, int centerY, int radius);//截取以x,y为中心，半径为radius的圆形图片
```
该方法用于截取指定位置，指定半径的view，返回的是bitmap，可以结合上面的savebitmap来保存到本地，详情见demo。<br>
该方法传入的几个参数分别是：<br>
#####Activity<br>
#####int x:您需要裁剪的圆心的x坐标<br>
#####int y:您需要裁剪的圆心的y坐标<br>
#####int radius:您需要裁剪的圆的半径<br>
####当x或者y大于半径（请注意y是包含有状态栏的高度），执行方法后会返回一个bitmap，可以结合savebitmap来保存到本地，若x或者y小于半径，则返回null，即无法裁剪出一个完整的圆，所以使用的时候请务必判断方法返回值是否为null，否则可能会异常而挂掉哦，详情见demo<br>
   以上。。。。<br>
    
   ---
//2015/08/01 增加了通过蒙板合成前景和背景为一张图的方法createBitmapWithAlphaMatte(Context context, Bitmap maskPic, Bitmap bgPic, boolean hasAlpha)<br>
ps:暂时来说我就这么有更新就写更新日志吧，以后有空再好好的整理这个README.....见谅
------
```java
    DevToolsFactory.getBitmapTools().createBitmapWithAlphaMatte(Context context, Bitmap maskPic, Bitmap bgPic, boolean hasAlpha)
```
因为这个方法是对图片的像素进行操作，所以建议您将方法放到异步线程里面做，避免阻塞UI线程。Demo因为为了偷懒，所以就没有放到子线程里面做。。。。<br>
关于这个方法的参数如下：<br>
```java
    Context context:上下文对象
    Bitmap maskPic:传入蒙板层的Bitmap
    Bitmap bgPic:传入背景层的Bitmap
    boolean hasAlpha:蒙板层是否含有Alpha(透明度通道)，请注意这个，一定要区分好哦，一般而言，jpg不含alpha通道的，所以对于jpg请选择false，对于png，请注意看是否有alpha通道，辨别很简单，请右键您的图片，查看属性是否>24深度，因为ARGB=4*8=32位，而RGB=3*8=24位，所以请看准是否含有alpha通道哦
```
方法返回的是一个合成好的bitmap，如果传入的bitmap为空，返回是空的哦，所以请判断一下是否返回空值，详情请看demo<br>
以上。。。。
 
---

