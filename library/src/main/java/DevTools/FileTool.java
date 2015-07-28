package DevTools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
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
	public String createFolder(String rootPath, String folderName) {
		String ROOT_PATH = "";
		// 判断SD卡
		boolean isSdCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		// 默认根路径
		if (!rootPath.equals("")) {
			if (rootPath.valueOf(rootPath.length()).equals("/")) {
				ROOT_PATH = rootPath;
			} else {
				ROOT_PATH = rootPath + "/";
			}
		} else {
			if (isSdCard) {
				ROOT_PATH = Environment.getExternalStorageDirectory() + File.separator;
			} else {
				ROOT_PATH = "/data/data/" + mContext.getPackageName() + "/";
			}
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

	/**
	 * @param fromFile
	 *            源文件路径，如"/sdcard/xxx/xxx.txt"
	 * @param toFile
	 *            目标文件路径，如"xxx/xxx/xxx.txt"
	 * @param rewrite
	 *            可否重写，如果可以，则会覆盖文件.
	 * @return String信息
	 * @throws IOException
	 *             异常处理
	 */
	public String copyfile(String fromFile, String toFile, Boolean rewrite) throws IOException {
		File from = new File(fromFile);
		File to = new File(toFile);
		if (!from.isFile()) {
			return "错误，请注意填写路径";
		}
		if (!from.exists()) {
			return "错误，文件不存在，请注意填写路径";
		}
		if (!from.canRead()) {
			return "错误，文件不可读，请注意权限";
		}
		// 判断目标路径的父文件夹是否存在，不存在就建一个
		if (!to.getParentFile().exists()) {
			to.getParentFile().mkdir();
		}
		// 判断目标文件是否存在以及是否可以复写，如果都满足，则删除原来的目标文件，否则，则在原文件后面加上-new
		if (to.exists() && rewrite) {
			to.delete();
		} else if (to.exists() && !rewrite) {
			String newToFile = getFileNameNoEx(toFile) + "-new." + getExtensionName(toFile);
			File newFile = new File(newToFile);
			to = newFile;
		}

		BufferedInputStream fromInPut = null;
		BufferedOutputStream toOutPut = null;
		// 以下为为复制
		try {
			fromInPut = new BufferedInputStream(new FileInputStream(from));
			toOutPut = new BufferedOutputStream(new FileOutputStream(to));
			int reader = 0;
			int bytesCopied = 0;
			while ((reader = fromInPut.read()) != -1) {
				toOutPut.write((byte) reader);
				bytesCopied++;
			}
			return "成功复制文件，一共复制了:" + bytesCopied + "bytes" + '\n' + "源路径：" + from.getAbsolutePath() + '\n' + "目标路径："
					+ to.getAbsolutePath();
		} finally {
			fromInPut.close();
			toOutPut.close();
		}

	}

	// 获取后缀名
	private static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	// 获取无扩展名的文件名
	private static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

}
