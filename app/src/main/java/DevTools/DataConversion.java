package DevTools;

/**
 * 数据转换类
 */
public class DataConversion implements DataTools {
	/**
	 * byte[]转int[]，这个用于将类似于0x10解析为int的10而非int的16
	 * 
	 * @param byteArray
	 *            sample:0X00,0X0A,0XA0,0XFF
	 * @return int[] sample:0,10,100,165
	 */
	public int[] byteArray2IntArray(byte[] byteArray) {
		int[] temp = new int[byteArray.length];
		for (int i = 0; i < byteArray.length; i++) {
			Byte aByte = byteArray[i];
			Byte high = (byte) ((aByte & 0xf0) >> 4);
			int n = Math.abs(high.intValue()) * 10;
			Byte low = (byte) (aByte & 0x0f);
			n += low.intValue();
			temp[i] = n;
		}
		return temp;
	}

	// 以下两个方法是将string转为byte

	/**
	 * 将两个ASCII字符合成一个字节； 如："EF"--> 0xEF
	 *
	 * @param src0
	 *            byte sample:E
	 * @param src1
	 *            byte sample:F
	 * @return byte sample:0XEF
	 */
	public byte uniteBytes(byte src0, byte src1) {
		byte _b0 = Byte.decode("0x" + new String(new byte[]{src0})).byteValue();
		_b0 = (byte) (_b0 << 4);
		byte _b1 = Byte.decode("0x" + new String(new byte[]{src1})).byteValue();
		byte ret = (byte) (_b0 ^ _b1);
		return ret;
	}

	/**
	 * 将指定字符串src，以每两个字符分割转换为16进制形式 如："2B44EFD9" --> byte[]{0x2B, 0x44, 0xEF,
	 * 0xD9}
	 *
	 * @param src
	 *            String sample:2B44EFD9
	 * @return
     *            byte[] sample:byte[]{0x2B, 0x44, 0xEF, 0xD9}
	 */
	public byte[] HexString2Bytes(String src) {
		byte[] ret = new byte[src.length()];
		byte[] tmp = src.getBytes();
		for (int i = 0; i < src.length() / 2; i++) {
			ret[i] = uniteBytes(tmp[i * 2], tmp[i * 2 + 1]);
		}
		return ret;
	}

}
