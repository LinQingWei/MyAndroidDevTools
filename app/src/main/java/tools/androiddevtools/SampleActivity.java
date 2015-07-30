package tools.androiddevtools;

import java.util.Arrays;

import DevTools.DataConversion;
import DevToolsFactory.DevToolsFactory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SampleActivity extends ActionBarActivity implements RadioGroup.OnCheckedChangeListener {

	private TextView tvtest;
	private RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sample);
		initialize();
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
				tvtest.setText("byte in string = " + byteStr + '\n' + "in = " + Arrays.toString(bytesArray2intArray)
						+ '\n' + "out = "
						+ Arrays.toString(DevToolsFactory.getDataTools().byteArray2IntArray(bytesArray2intArray)));
				break;
			case R.id.ascii2byte :
				byte byte1 = (byte) ((char) 'E');
				byte byte2 = (byte) ((char) 'F');
				tvtest.setText("ASCII in char = " + "E," + "F" + '\n' + "ASCII in number = " + byte1 + ',' + byte2
						+ '\n' + "out = "
						+ String.format("0x%02x", DevToolsFactory.getDataTools().uniteBytes(byte1, byte2)));
				break;
			case R.id.str2bytearray :
				String str = "ABCDEFG";
				String strResult = "";
				for (int i = 0; i < str.length(); i++) {
					strResult += String.format("0x%02x", DevToolsFactory.getDataTools().HexString2Bytes(str)[i]) + ',';
				}
				tvtest.setText("in = " + str + '\n' + "out = " + strResult.toUpperCase());
				break;
			case R.id.bytearray2strex :
				byte[] bytes = {0x00, 0X01, 0X02, 0X03};
				tvtest.setText("in = " + "{0x00,0x01,0x02,0x03}" + '\n' + "out = "
						+ DevToolsFactory.getDataTools().byte2StrEx(bytes, DataConversion.TYPE_IN_0X_WITH_SHORTLINE));
				break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent();
		switch (item.getItemId()) {
			case R.id.fileactivity :
				intent.setClass(this, FileSampleActivity.class);
				startActivity(intent);
				break;
			case R.id.main_toview :
				intent.setClass(this, ViewSampleActivity.class);
				startActivity(intent);
				break;
		}

		return super.onOptionsItemSelected(item);
	}

}
