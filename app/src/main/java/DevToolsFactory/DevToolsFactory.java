package DevToolsFactory;

import DevTools.DataConversion;
import DevTools.DataTools;

/**
 * �����乤��
 */
public class DevToolsFactory {

    private static final DataConversion dataConversion=new DataConversion();
    //������
    public DataTools getDataTools(){
        return dataConversion;
    }

}
