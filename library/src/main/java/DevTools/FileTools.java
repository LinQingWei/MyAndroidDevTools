package DevTools;

import android.content.Context;

import java.io.IOException;

/**
 * Created by razerdp on 2015/7/27. 文件类接口
 */
public interface FileTools {
	public void initialize(Context context);
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
	public String createFolder(String rootPaht, String folderName);

	/**
	 *
	 * @param fromFile
	 *            源文件路径，如"/sdcard/xxx/xxx.txt"
	 * @param toFile
	 *            目标文件路径，如"xxx/xxx/xxx.txt"
	 * @param rewrite
	 *            可否重写，如果为ture，则会覆盖已经存在文件.否则，在已经存在的文件名字后面加上-new
	 * @return String信息
	 * @throws IOException
	 *             异常处理
	 */
	public String copyfile(String fromFile, String toFile, Boolean rewrite) throws IOException;
}
