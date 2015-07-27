package DevTools;

/**
 * Created by razerdp on 2015/7/26. 数据类接口
 */
public interface DataTools {
    public int[] byteArray2IntArray(byte[] byteArray);
    public byte uniteBytes(byte src0, byte src1);
    public byte[] HexString2Bytes(String src);
	public String byte2StrEx(byte[] srcBytes, int type);
}
