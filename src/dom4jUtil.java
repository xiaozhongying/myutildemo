package common.util;

import java.util.Collections;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.xpath.DefaultXPath;

public class dom4jUtil
{
	/**
	 * 读取soap的返回值
	 * @throws DocumentException
	 */
	public static String getXMLString(String xml)
	{
		String msg = "";
		try
		{
			Document document = DocumentHelper.parseText(xml);
			DefaultXPath xpath = new DefaultXPath("//return");
			System.out.println("xpath:" + xpath);
			xpath.setNamespaceURIs(Collections.singletonMap("ns2", "http://schemas.xmlsoap.org/soap/envelope/"));
			Element ele = (Element) xpath.selectSingleNode(document);
			msg = ele.getText();
		}
		catch(DocumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return msg;
	}
}
