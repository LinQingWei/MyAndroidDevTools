package DevTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
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
