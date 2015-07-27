package DevToolsFactory;

import DevTools.DataConversion;
import DevTools.DataTools;
import DevTools.FileTool;
import DevTools.FileTools;

/**
 * 工具箱工厂
 */
public class DevToolsFactory {

	private static final DataConversion DATA_CONVERSION = new DataConversion();
	private static final FileTool FILE_TOOL = new FileTool();
	// 数据类
    public DataTools getDataTools(){
		return DATA_CONVERSION;
    }
	// 文件类
	public FileTools getFileTools() {
		return FILE_TOOL;
	}

}
