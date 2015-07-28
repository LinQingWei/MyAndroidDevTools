package DevTools;

import android.content.Context;

/**
 * Created by razerdp on 2015/7/27. 文件类接口
 */
public interface FileTools {
	public void initialize(Context context);
	public String createFolder(String rootPaht, String folderName);
}
