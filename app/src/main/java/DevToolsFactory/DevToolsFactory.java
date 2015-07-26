package DevToolsFactory;

import DevTools.DataConversion;
import DevTools.DataTools;

/**
 * 工具箱工厂
 */
public class DevToolsFactory {

    //数据类
    public DataTools getDataTools(){
        return new DataConversion();
    }

}
