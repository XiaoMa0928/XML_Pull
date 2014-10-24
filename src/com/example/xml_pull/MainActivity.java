package com.example.xml_pull;

import java.io.InputStream;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {
	List<Person> list;
	PullXMLResolve pullXMLResolve;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		InputStream inStream = null;
//		获取流资源，打开原始资源。。。。将XML文件作为资源给输入流
		inStream = getResources().openRawResource(R.raw.preson);
//		实例化PullXMLResolve
		pullXMLResolve = new PullXMLResolve();
//		传进去一个流，得到一个链表给List
		list = pullXMLResolve.readXML(inStream);
		TextView textView = (TextView) findViewById(R.id.TextView);
//		获取链表的长度
		String[] strings = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {

			strings[i] = get(i);
		}
		textView.setText(strings[0] + "\n" + strings[1]);
	}

	public String get(int i) {
		Person person = list.get(i);
//		获取id，name，age
		String id = person.getId().toString();
		String name = person.getName().toString();
		String age = person.getAge().toString();
		String string = id + "  " + name + "  " + age;
		return string;
	}
}
