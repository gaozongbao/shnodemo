package com.cmdi.thread;

import com.cmdi.model.mro.*;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.util.*;

/**
 * @author: 高宗宝
 * @date: 2019/5/22
 * @version: 1.0
 * @description: someword
 */
public class XmlParse {
	private static final Logger logger = Logger.getLogger(XmlParse.class);

	public static ArrayList<ScMeasureMasterCell> parseByDom4j(InputStream inputStream, String encoding)
			throws DocumentException {
		SAXReader saxReader = new SAXReader();
		saxReader.setEncoding(encoding);
		Document document = saxReader.read(inputStream);
		Element rootElement = document.getRootElement();
		Element eNBElement = rootElement.element("eNB");
		if (eNBElement == null) {
			logger.error("error xml");
			return null;
		}
		ArrayList<ScMeasureMasterCell> arrayList = new ArrayList<ScMeasureMasterCell>();
		Integer enbId = MroUtil.toInt(eNBElement.attributeValue("id"));
		Iterator measurementIterator = eNBElement.elementIterator("measurement");
		while (measurementIterator.hasNext()) {
			Element measurement = (Element) measurementIterator.next();
			String smr = measurement.element("smr").getStringValue();
			if (smr == null)
				continue;
			if (!smr.contains("RSRP"))
				continue;
			HashMap<String, Integer> smrIndex = smrToIndex(smr);
			// 处理measurement中smr包括RSRP的数据
			Iterator objectIterator = measurement.elementIterator("object");
			while (objectIterator.hasNext()) {
				ScMeasureMasterCell scMeasureMasterCell = new ScMeasureMasterCell();
				Element object = (Element) objectIterator.next();
				scMeasureMasterCell.setEnbId(enbId);
				Integer eci = MroUtil.toInt(object.attributeValue("id"));
				if(eci < 0)
					continue;
				int index = eci % 256 % 3;
				if(index == 0)
					index = 3;
				scMeasureMasterCell.setIndex(index);
				scMeasureMasterCell.setEci(eci);
				scMeasureMasterCell.setCgi("460-00-" + enbId + "-" + (eci - enbId * 256));
				scMeasureMasterCell.setMmeUeS1apId(MroUtil.toInt(object.attributeValue("MmeUeS1apId")));
				// 取每个obj中的第一条v
				Element v = object.element("v");
				vToScNcmeasure(v, smrIndex, scMeasureMasterCell);
				if(scMeasureMasterCell.getLteScEarfcn() == 37900 || scMeasureMasterCell.getLteScEarfcn() == 38400) {
					arrayList.add(scMeasureMasterCell);
				}
			}
			smrIndex.clear();
		}
		try {
			if (inputStream != null)
				inputStream.close();
		} catch (IOException e) {
			logger.error(e);
		}
		return arrayList;
	}

	protected static HashMap<String, Integer> smrToIndex(String smr) {
		String[] vs = smr.trim().split("\\s+", -1);
		HashMap<String, Integer> map = new HashMap<>();
		map.put("NIL", -1);
		for (int i = 0; i < vs.length; i++) {
			map.put(vs[i].trim(), i);
		}
		return map;
	}

	protected static void vToScNcmeasure(Element v, HashMap<String, Integer> index, ScMeasureMasterCell scMeasureMasterCell) {
		String[] vs = v.getStringValue().trim().split("\\s+", -1);
		
		//减140就是dbm
		scMeasureMasterCell.setLteScRSRP(MroUtil.toInt(vs[index.get("MR.LteScRSRP")]) - 140);

		scMeasureMasterCell.setLteScTadv(MroUtil.toInt(vs[index.get("MR.LteScTadv")]));

		scMeasureMasterCell.setLteScAOA(MroUtil.toInt(vs[index.get("MR.LteScAOA")]));

		scMeasureMasterCell.setLteScEarfcn(MroUtil.toInt(vs[index.get("MR.LteScEarfcn")]));
		scMeasureMasterCell.setLteScPci(MroUtil.toInt(vs[index.get("MR.LteScPci")]));
		
	}

	protected static TwoMeasure vToScQcimeasure(String v, HashMap<String, Integer> index) {
		String[] vs = v.trim().split("\\s+", -1);
		TwoMeasure twoMeasure = new TwoMeasure();
		twoMeasure.setLteScPlrULQci1(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci1")]));
		twoMeasure.setLteScPlrULQci2(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci2")]));
		twoMeasure.setLteScPlrULQci3(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci3")]));
		twoMeasure.setLteScPlrULQci4(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci4")]));
		twoMeasure.setLteScPlrULQci5(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci5")]));
		twoMeasure.setLteScPlrULQci6(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci6")]));
		twoMeasure.setLteScPlrULQci7(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci7")]));
		twoMeasure.setLteScPlrULQci8(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci8")]));
		twoMeasure.setLteScPlrULQci9(MroUtil.toInt(vs[index.get("MR.LteScPlrULQci9")]));

		twoMeasure.setLteScPlrDLQci1(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci1")]));
		twoMeasure.setLteScPlrDLQci2(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci2")]));
		twoMeasure.setLteScPlrDLQci3(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci3")]));
		twoMeasure.setLteScPlrDLQci4(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci4")]));
		twoMeasure.setLteScPlrDLQci5(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci5")]));
		twoMeasure.setLteScPlrDLQci6(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci6")]));
		twoMeasure.setLteScPlrDLQci7(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci7")]));
		twoMeasure.setLteScPlrDLQci8(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci8")]));
		twoMeasure.setLteScPlrDLQci9(MroUtil.toInt(vs[index.get("MR.LteScPlrDLQci9")]));

		return twoMeasure;
	}

}
