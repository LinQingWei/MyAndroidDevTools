package DevToolsFactory;

import DevTools.DataConversion;
import DevTools.DataTools;

/**
 * �����乤��
 */
public class DevToolsFactory {

    //������
    public DataTools getDataTools(){
        return new DataConversion();
    }

}
