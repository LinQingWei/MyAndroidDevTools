package DevTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Path;
import android.os.Environment;

/**
 * Created by razerdp on 2015/7/27. 文件工具类 请添加权限： --往sdcard中写入数据的权限
 * android.permission.WRITE_EXTERNAL_STORAGE --在sdcard中创建/删除文件的权限
 * android.permission.MOUNT_UNMOUNT_FILESYSTEMS
 */
public class FileTool implements FileTools {
	private Context mContext;
	@Override
	public void initialize(Context context) {
		this.mContext = context;
	}

	/**
	 * 创建文件夹，最多支持3级目录
	 * 
	 * 
	 * @param folderName
	 *            目录名字，如果创建多个目录，传入的参数请这样写 "XXX/XXX/XXx/XXX"
	 *            默认情况下，有SD卡的时候，默认目录为SD卡下，已经初始化好，因此传参无需写入SD啊目录 允许在文件夹存在时创建
	 *
	 * @return 创建好的目录的路径
	 */
	@Override
	public String createFolder(String folderName) {
		String ROOT_PATH = "";
		// 判断SD卡
		boolean isSdCard = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
		// 默认根路径
		if (isSdCard) {
			ROOT_PATH = Environment.getExternalStorageDirectory() + File.separator;
		} else {
			ROOT_PATH = "/data/data/" + mContext.getPackageName() + "/";
		}
		// 将String路径根据“/”抽取
		String[] PATHS = folderName.split("/");
		List<String> pathsList = new ArrayList<>();
		// 将path处理后加到list里面
		for (int i = 0; i < PATHS.length; i++) {
			if (i == 0 && !PATHS[0].equals("")) {
				pathsList.add(0, ROOT_PATH + PATHS[0]);
			} else if (i > 0 && !PATHS[i].equals("")) {
				pathsList.add(i, pathsList.get(i - 1) + "/" + PATHS[i]);
			}
		}
		for (int i = 0; i < pathsList.size(); i++) {
			if (i == 0) {
				File firstFolder = new File(pathsList.get(0));
				if (!firstFolder.exists()) {
					firstFolder.mkdir();
				}
			} else {
				File preFolder = new File(pathsList.get(i - 1));
				File curFolder = new File(pathsList.get(i));
				if (preFolder.exists()) {
					curFolder.mkdir();
				} else {
					preFolder.mkdir();
					curFolder.mkdir();
				}
			}

		}
		String str = "";
		for (int i = 0; i < pathsList.size(); i++) {
			str += "成功创建文件夹" + i + ",该文件夹路径为：" + pathsList.get(i) + '\n';
		}
		return str;

	}

}
