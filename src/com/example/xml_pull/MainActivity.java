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
//		��ȡ����Դ����ԭʼ��Դ����������XML�ļ���Ϊ��Դ��������
		inStream = getResources().openRawResource(R.raw.preson);
//		ʵ����PullXMLResolve
		pullXMLResolve = new PullXMLResolve();
//		����ȥһ�������õ�һ�������List
		list = pullXMLResolve.readXML(inStream);
		TextView textView = (TextView) findViewById(R.id.TextView);
//		��ȡ����ĳ���
		String[] strings = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {

			strings[i] = get(i);
		}
		textView.setText(strings[0] + "\n" + strings[1]);
	}

	public String get(int i) {
		Person person = list.get(i);
//		��ȡid��name��age
		String id = person.getId().toString();
		String name = person.getName().toString();
		String age = person.getAge().toString();
		String string = id + "  " + name + "  " + age;
		return string;
	}
}
