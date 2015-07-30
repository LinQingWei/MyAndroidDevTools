package DevTools;

import android.graphics.Bitmap;

import java.io.IOException;

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
}
