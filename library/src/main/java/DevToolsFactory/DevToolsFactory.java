package DevToolsFactory;

import DevTools.BitmapTool;
import DevTools.BitmapTools;
import DevTools.DataConversion;
import DevTools.DataTools;
import DevTools.FileTool;
import DevTools.FileTools;
import DevTools.ViewTool;
import DevTools.ViewTools;

/**
 * 工具箱工厂
 */
public class DevToolsFactory {

	private static final DataConversion DATA_CONVERSION = new DataConversion();
	private static final FileTool FILE_TOOL = new FileTool();
	private static final ViewTool VIEW_TOOL = new ViewTool();
	private static final BitmapTool BITMAP_TOOL = new BitmapTool();

	// 数据类
	public static DataTools getDataTools() {
		return DATA_CONVERSION;
	}
	// 文件类
	public static FileTools getFileTools() {
		return FILE_TOOL;
	}
	// view类
	public static ViewTools getViewTools() {
		return VIEW_TOOL;
	}
	// Bitmap类
	public static BitmapTools getBitmapTools() {
		return BITMAP_TOOL;
	}

}
