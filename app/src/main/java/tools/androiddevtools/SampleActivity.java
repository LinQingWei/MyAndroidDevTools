package tools.androiddevtools;

import java.util.Arrays;

import DevToolsFactory.DevToolsFactory;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SampleActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {

	private TextView tvtest;
	private RadioGroup radioGroup;
	DevToolsFactory devTools;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		initialize();
		devTools = new DevToolsFactory();
        radioGroup.setOnCheckedChangeListener(this);

	}

	private void initialize() {
		tvtest = (TextView) findViewById(R.id.tv_test);
		radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.bytearray2intarray :
				String byteStr = "0x00,0x10,0x0A,0xA0,0x99,0xFF";
				byte[] bytesArray2intArray = {0x00, 0x10, 0x0A, (byte) 0xA0, (byte) 0x99, (byte) 0xFF};
				tvtest.setText("byte in string =    " + byteStr + '\n' + "in =    "
						+ Arrays.toString(bytesArray2intArray) + '\n' + "out =    "
						+ Arrays.toString(devTools.getDataTools().byteArray2IntArray(bytesArray2intArray)));
				break;
			case R.id.ascii2byte :
				byte byte1 =(byte)((char)'E') ;
				byte byte2 =(byte)((char)'F') ;
				tvtest.setText("ASCII in char =    " + "E," + "F" + '\n' + "ASCII in number =    " + byte1 + ',' + byte2
						+ '\n' + "out =   " + String.format("0x%02x",devTools.getDataTools().uniteBytes(byte1, byte2)));
		}
	}
}
