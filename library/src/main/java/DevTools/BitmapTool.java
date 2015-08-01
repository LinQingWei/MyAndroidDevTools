package DevTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.util.Log;

/**
 * Created by Administrator on 2015/7/30. bitmap工具类
 */
public class BitmapTool implements BitmapTools {

	@Override
	public Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
		return newbmp;
	}

	@Override
	public Bitmap createBitmapWithAlphaMatte(Context context, Bitmap maskPic, Bitmap bgPic, boolean hasAlpha) {
		// 宽高
		int width;
		int height;
		Bitmap zoomedBgBitmap;
		Bitmap resultBitmap = null;
		// 判断，是否存在位图或者前景和后景位图是否相等，符合则返回null，即无法合成
		if (maskPic == null || maskPic.isRecycled() || maskPic == bgPic) {
			return null;
		} else {
			// 将背景图缩放到与mask一致
			width = maskPic.getWidth();
			height = maskPic.getHeight();
			zoomedBgBitmap = Bitmap.createScaledBitmap(bgPic, width, height, false);
		}
		int edgeColor = maskPic.getPixel(1, 1);
		int centerColor = maskPic.getPixel(width / 2, height / 2);
		Log.d("TAG", "edgeColor = " + Integer.toHexString(edgeColor) + ", centerColor = "
				+ Integer.toHexString(centerColor));
		// 获取两者的像素
		int[] mskPx = new int[width * height];
		int[] bgPx = new int[width * height];
		// 合成结果bitmap的像素数组
		int[] resultPx = new int[width * height];
		// 这里的getPixels()通过百度找到的api解释参考：http://ranlic.iteye.com/blog/1313735
		maskPic.getPixels(mskPx, 0, width, 0, 0, width, height);
		zoomedBgBitmap.getPixels(bgPx, 0, width, 0, 0, width, height);
		// 对象素处理
		int alpha = 0x00;// Alpha通道
		int color = 0x000000;// color色值

		int r;
		int g;
		int b;
		int a;
		int type = (true == hasAlpha) ? 0 : 1;

		switch (type) {
			case 0 :// 自带alpha通道
				for (int i = 0; i < mskPx.length; i++) {

					if (mskPx[i] == 0xff000000) {
						// 如果当前像素为不透明的全黑，则将bgPx像素置0，因为蒙板的黑色是不允许显示
						resultPx[i] = 0;
					} else if (mskPx[i] == 0x00000000) {
						// 全透明，则什么都不做
					} else if (mskPx[i] == 0xffffffff) {
						// 纯白，显示
						resultPx[i] = bgPx[i];
					} else {
						// 剩下的就是不透明的颜色或者带有透明度的颜色
						// ----获取当前像素的Alpha通道,即将颜色位去掉，跟ff000000相与然后相减
						alpha = 0xff000000 - (mskPx[i] & 0xff000000);
						// ----获取当前像素的颜色位，然后与alpha相加
						color = alpha | (bgPx[i] & 0x00ffffff);
						resultPx[i] = color;
					}
				}
				break;
			case 1 :// 不带alpha通道
				for (int i = 0; i < mskPx.length; i++) {
					if (mskPx[i] == 0xff000000) {
						// 如果当前像素为不透明的全黑，则将bgPx像素置0，因为蒙板的黑色是不允许显示
						resultPx[i] = 0;
					} else if (mskPx[i] == 0x00000000) {
						// 全透明，则什么都不做
					} else if (mskPx[i] == 0xffffffff) {
						// 纯白，显示
						resultPx[i] = bgPx[i];
					} else {
						// 剩下的就是不透明的颜色或者带有透明度的颜色
						// ----获取当前像素的Alpha通道,即将颜色位去掉，跟ff000000相与然后相减
						// alpha = 0xff000000 - (mskPx[i] & 0xff000000);
						r = Color.red(mskPx[i]);
						g = Color.green(mskPx[i]);
						b = Color.blue(mskPx[i]);
						a = ((r + g + b) / 3) << 24;
						// ----获取当前像素的颜色位，然后与alpha相加
						color = a | (bgPx[i] & 0x00ffffff);
						resultPx[i] = color;
					}

				}
				break;
			default :
				break;
		}
		// 初始化
		if (resultBitmap == null) {
			resultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		}

		// 设置像素
		resultBitmap.setPixels(resultPx, 0, width, 0, 0, width, height);

		return resultBitmap;
	}
	@Override
	public void saveBitmap(Bitmap bm, String path, String picName) throws IOException {
		File f = new File(path, picName); // 创建file对象
		if (f.exists()) { // 判断文件是否存在
			f.delete(); // 存在则删除
		}

		FileOutputStream out = null;
		try {
			out = new FileOutputStream(f); // 输出流
			bm.compress(Bitmap.CompressFormat.JPEG, 90, out); // 图片压缩，格式为JPG，压缩率10%，这里的
																// 90代表(100-90)，因此压缩率为10%
		} catch (FileNotFoundException e) { // 异常捕获，这里并没有写处理异常的相关方法，这可能会导致一些问题，最简单的处理就是加个Toast
			Log.e("filenotfound", "File not found");
			e.printStackTrace();
		} finally {
			out.flush();
			out.close();
		}
	}

}
