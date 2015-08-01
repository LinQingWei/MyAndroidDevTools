package DevTools;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2015/7/30. bitmap图像工具类
 */
public interface BitmapTools {
	/**
	 * bitmap缩放工具
	 * 
	 * @param bitmap
	 *            bitmap对象
	 * @param width
	 *            缩放后的宽度
	 * @param height
	 *            缩放后的高度
	 * @return 缩放后的bitmap
	 */
	public Bitmap zoomBitmap(Bitmap bitmap, int width, int height);

	/**
	 * 保存bitmap到本地，格式为jpg，压缩10%
	 * 
	 * @param bm
	 *            bitmap对象
	 * @param path
	 *            存储路径
	 * @param picName
	 *            存储名字
	 * @throws IOException
	 *             异常捕获
	 */
	public void saveBitmap(Bitmap bm, String path, String picName) throws IOException;
	/**
	 * 将两个bitmap通过蒙板合成为一个
	 * 
	 * @param context
	 *            Context
	 * @param maskPic
	 *            前景Bitmap(即蒙板)
	 * @param bgPic
	 *            背景Bitmap（即要显示的内容）
	 * @param hasAlpha
	 *            蒙板是否含有通道
	 * @return 合成后的Bitmap
	 */
	public Bitmap createBitmapWithAlphaMatte(Context context, Bitmap maskPic, Bitmap bgPic, boolean hasAlpha);
}
