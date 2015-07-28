package DevTools;

/**
 * Created by razerdp on 2015/7/26. 数据类接口
 */
public interface DataTools {
	/**
	 * byte[]转int[]，这个用于将类似于0x10解析为int的10而非int的16
	 *
	 * @param byteArray
	 *            sample:0x00,0x0A,0xA0,0xFF
	 * @return int[] sample:0,10,100,165
	 */
	public int[] byteArray2IntArray(byte[] byteArray);
	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 *
	 * @param src0
	 *            byte sample:E
	 * @param src1
	 *            byte sample:F
	 * @return byte sample:0XEF
	 */
	public byte uniteBytes(byte src0, byte src1);
	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 *
	 * @param src
	 *            String sample:2B44EFD9
	 * @return byte[] sample:byte[]{0x2B, 0x44, 0xEF, 0xD9}
	 */
	public byte[] HexString2Bytes(String src);
	/**
	 * 将byte[]数组转换为特殊格式的String
	 *
	 * @param srcBytes
	 *            sample:{0x00,0x02,0x10}
	 * @param type
	 *            TYPE_IN_0X/TYPE_IN_COLON/TYPE_IN_SHORTLINE
	 * @return sample:type=TYPE_IN_COLON return:00-02-10
	 */
	public String byte2StrEx(byte[] srcBytes, int type);
}
