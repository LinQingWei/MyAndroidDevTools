package DevTools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;

/**
 * Created by Administrator on 2015/7/30. view相关工具
 */
public interface ViewTools {
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public int px2dip(Context context, float pxValue);

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 *            （DisplayMetrics类中属性density）
	 * @return
	 */
	public int dip2px(Context context, float dipValue);

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public int px2sp(Context context, float pxValue);

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public int sp2px(Context context, float spValue);

	/**
	 * 获取当前Activity的状态栏高度
	 * 
	 * @param activity
	 *            当前Activity，不可以传入getApplicationContext()，必须是Activity.this
	 * @return int高度
	 */
	public int getStateBarHeight(Activity activity);

	/**
	 * 获取当前Activity的标题栏高度
	 * 
	 * @param activity
	 *            当前Activity，不可以传入getApplicationContext()，必须是Activity.this
	 * @return int高度
	 */
	public int getTitleBarHeight(Activity activity);

	/**
	 * 获取当前屏幕宽高（px）
	 * 
	 * @param activity
	 *            Activity.this
	 * @return int[]，其中int[0]=width,int[1]=height
	 */
	public int[] getScreenWH(Activity activity);

	/**
	 * 截取当前屏幕图片，返回Bitmap
	 * 
	 * @param activity
	 *            Activity.this
	 * @return Bitmap
	 */
	public Bitmap takeScreenShot(Activity activity);

	/**
	 * 截取以x,y为中心，半径为radius的圆形图片
	 * 
	 * @param activity
	 *            Activity.this
	 * @param centerX
	 *            x中心
	 * @param centerY
	 *            y中心
	 * @param radius
	 *            半径
	 * @return 圆形bitmap，当x,y中心小于0，则返回null
	 */
	public Bitmap getBitmapRound(Activity activity, int centerX, int centerY, int radius);
}
