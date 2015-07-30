package DevTools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;

/**
 * Created by Administrator on 2015/7/30. view工具类
 */
public class ViewTool implements ViewTools {
	public static final int TYPE_ROUND = 0;
	public static final int TYPE_RECTANGLE = 1;

	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	// 获取状态栏高度
	public int getStateBarHeight(Activity activity) {
		int statusBarHeight = 0;
		int titleBarHeight = 0;
		// 定义区域
		Rect titleFrame = new Rect();
		// 得到高度
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(titleFrame);
		statusBarHeight = titleFrame.top;
		return statusBarHeight;
	}
	@Override
	public int getTitleBarHeight(Activity activity) {
		int titleBarHeight = 0;
		int contenttop = activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
		titleBarHeight = contenttop - getStateBarHeight(activity);
		return titleBarHeight;
	}
	@Override
	public int[] getScreenWH(Activity activity) {
		int[] WH = {0, 0};
		DisplayMetrics metrics = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
		WH[0] = metrics.widthPixels;
		WH[1] = metrics.heightPixels;
		return WH;
	}

	// 获取Activity的截屏
	// 参考文章http://blog.sina.com.cn/s/blog_726322c80101c0r9.html
	public Bitmap takeScreenShot(Activity activity) {
		View view = activity.getWindow().getDecorView();// 得到顶层的view
		view.setDrawingCacheEnabled(true);// view通过缓存机制保存为bitmap
		view.buildDrawingCache();
		Bitmap cacheBitmap = view.getDrawingCache();
		Bitmap returnBitmap = Bitmap.createBitmap(cacheBitmap);
		view.destroyDrawingCache();
		view.setDrawingCacheEnabled(false);// 销毁，释放内存
		return returnBitmap;
	}

	// 得到圆形截图,先获得规定区域的矩形截图，然后将矩形截图转换为圆形截图
	public Bitmap getBitmapRound(Activity activity, int centerX, int centerY, int radius) {
		// 得到截图
		Bitmap screenBitmap = takeScreenShot(activity);
		// x开始位置
		int startX = centerX - radius;
		// y开始位置
		int startY = centerY - radius + getStateBarHeight(activity);// 需要考虑状态栏
		// 得到目标圆形图片的外接矩形图片
		if (startX > 0 && startY > 0) {
			Bitmap sourceRectangleBitmap = Bitmap.createBitmap(screenBitmap, startX, startY, 2 * radius, 2 * radius);
			// 获得圆形截图
			return toRoundBitmap(sourceRectangleBitmap);
		} else {
			return null;
		}

	}

	/**
	 * 转换图片成圆形
	 *
	 * @param bitmap
	 *            传入Bitmap对象
	 * @return
	 */
	public Bitmap toRoundBitmap(Bitmap bitmap) {
		// 得到宽度，高度
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		// 半径
		float radius;
		// 原绘图区域
		float left, top, right, bottom;
		// 目标绘图区域
		float dst_left, dst_top, dst_right, dst_bottom;
		// 分情况进行
		if (width <= height) {
			// 半径
			radius = width / 2;
			// 上边界和左边界为0，下边界和右边界为直径
			top = 0;
			bottom = 2 * radius;
			left = 0;
			right = 2 * radius;
			height = width;
			dst_left = 0;
			dst_top = 0;
			dst_right = width;
			dst_bottom = width;
		} else {
			// 半径
			radius = height / 2;
			// 因为width>height，因此左边界绝对无法为0，肯定与原点有一定差距
			// 这个差距=（宽-高）/2，因为两边都有，所以除以2
			float clip = (width - height) / 2;
			// 左边界=0+差距，右边界=宽-差距，上边界=0，下边界=半径
			left = 0 + clip;
			right = width - clip;
			top = 0;
			bottom = 2 * radius;
			width = height;
			dst_left = 0;
			dst_top = 0;
			dst_right = height;
			dst_bottom = height;
		}
		// 蒙板
		Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final Paint paint = new Paint();
		// 原绘图区域
		final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
		// 新绘图区域
		final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
		final RectF rectF = new RectF(dst);
		// 抗锯齿
		paint.setAntiAlias(true);
		// 填充透明黑
		canvas.drawARGB(0, 0, 0, 0);
		// 绘制圆角矩形，x,y方向圆角均为圆的半径，得出一个圆形
		canvas.drawRoundRect(rectF, radius, radius, paint);
		// 蒙板，PorterDuff.Mode.SRC_IN:两层相交，取上层
		// 蒙板参考网站：http://407827531.iteye.com/blog/1470519
		// 这里指：原绘图区域为矩形，比新绘图区域大，而且画布已经被填充了透明黑canvas.drawARGB(0, 0, 0, 0);
		// 而原绘图区域是矩形，绘制的是传进来的bitmap
		// 通过绘制顺序，先绘制含有图像的，再绘制新的区域，两者显示上层，剩下的就是被填充的透明黑
		// 就可以 得到裁剪后的圆形图片
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, src, dst, paint);
		// 回收
		bitmap.recycle();
		return output;
	}
}
