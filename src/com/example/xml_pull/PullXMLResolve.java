package com.example.xml_pull;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

public class PullXMLResolve extends DefaultHandler {
	// ��ȡXML
	public static List<Person> readXML(InputStream inStream) {

		XmlPullParser parser = Xml.newPullParser();

		try {
			parser.setInput(inStream, "UTF-8");
			int eventType = parser.getEventType();

			Person currentPerson = null;
			List<Person> persons = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:// �ĵ���ʼ�¼�,���Խ������ݳ�ʼ������
					persons = new ArrayList<Person>();
					break;

				case XmlPullParser.START_TAG:// ��ʼԪ���¼�
					String name = parser.getName();
					if (name.equalsIgnoreCase("person")) {
						currentPerson = new Person();
						currentPerson.setId(new Integer(parser
								.getAttributeValue(null, "id")));
					} else if (currentPerson != null) {
						if (name.equalsIgnoreCase("name")) {
							currentPerson.setName(parser.nextText());// ���������TextԪ��,����������ֵ
						} else if (name.equalsIgnoreCase("age")) {
							currentPerson.setAge(new Short(parser.nextText()));
						}
					}
					break;

				case XmlPullParser.END_TAG:// ����Ԫ���¼�
					if (parser.getName().equalsIgnoreCase("person")
							&& currentPerson != null) {
						persons.add(currentPerson);
						currentPerson = null;
					}

					break;
				}

				eventType = parser.next();
			}

			inStream.close();
			return persons;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
