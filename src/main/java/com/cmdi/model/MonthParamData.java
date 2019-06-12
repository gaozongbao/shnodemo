package com.cmdi.model;

import java.util.Date;

public class MonthParamData {
	
	@Override
	public String toString() {
		return "MonthParamData [province=" + province + ", city=" + city + ", month=" + month + ", cellnum=" + cellnum
				+ ", twohitwolowcellnum=" + twohitwolowcellnum + ", lteenbnum=" + lteenbnum + ", ltecellnum="
				+ ltecellnum + ", singlecellmaxactvolteusernum=" + singlecellmaxactvolteusernum + ", voltetraval="
				+ voltetraval + ", volteradconnratio=" + volteradconnratio + ", volteraddropratio=" + volteraddropratio
				+ ", esrvcchosucratio=" + esrvcchosucratio + ", esrvcchoratio=" + esrvcchoratio + ", mrulplratio="
				+ mrulplratio + ", mruldlratio=" + mruldlratio + ", trafficvaluegt1erlcellnum="
				+ trafficvaluegt1erlcellnum + ", voltetravaleq0erlcellnum=" + voltetravaleq0erlcellnum
				+ ", lowvolteconnratiocellnum=" + lowvolteconnratiocellnum + ", highvoltedropratiocellnum="
				+ highvoltedropratiocellnum + ", higvoltemrulhplratiocellnum=" + higvoltemrulhplratiocellnum
				+ ", highvoltemrdlplratiocellnum=" + highvoltemrdlplratiocellnum + ", lowesrvcchosucratiocellnum="
				+ lowesrvcchosucratiocellnum + "]";
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getCellnum() {
		return cellnum;
	}

	public void setCellnum(int cellnum) {
		this.cellnum = cellnum;
	}

	public int getTwohitwolowcellnum() {
		return twohitwolowcellnum;
	}

	public void setTwohitwolowcellnum(int twohitwolowcellnum) {
		this.twohitwolowcellnum = twohitwolowcellnum;
	}
	
	public Double getLteenbnum() {
		return lteenbnum;
	}

	public void setLteenbnum(Double lteenbnum) {
		this.lteenbnum = lteenbnum;
	}

	public Double getLtecellnum() {
		return ltecellnum;
	}

	public void setLtecellnum(Double ltecellnum) {
		this.ltecellnum = ltecellnum;
	}

	public Double getSinglecellmaxactvolteusernum() {
		return singlecellmaxactvolteusernum;
	}

	public void setSinglecellmaxactvolteusernum(Double singlecellmaxactvolteusernum) {
		this.singlecellmaxactvolteusernum = singlecellmaxactvolteusernum;
	}

	public Double getVoltetraval() {
		return voltetraval;
	}

	public void setVoltetraval(Double voltetraval) {
		this.voltetraval = voltetraval;
	}

	public Double getVolteradconnratio() {
		return volteradconnratio;
	}

	public void setVolteradconnratio(Double volteradconnratio) {
		this.volteradconnratio = volteradconnratio;
	}

	public Double getVolteraddropratio() {
		return volteraddropratio;
	}

	public void setVolteraddropratio(Double volteraddropratio) {
		this.volteraddropratio = volteraddropratio;
	}

	public Double getEsrvcchosucratio() {
		return esrvcchosucratio;
	}

	public void setEsrvcchosucratio(Double esrvcchosucratio) {
		this.esrvcchosucratio = esrvcchosucratio;
	}

	public Double getEsrvcchoratio() {
		return esrvcchoratio;
	}

	public void setEsrvcchoratio(Double esrvcchoratio) {
		this.esrvcchoratio = esrvcchoratio;
	}

	public Double getMrulplratio() {
		return mrulplratio;
	}

	public void setMrulplratio(Double mrulplratio) {
		this.mrulplratio = mrulplratio;
	}

	public Double getMruldlratio() {
		return mruldlratio;
	}

	public void setMruldlratio(Double mruldlratio) {
		this.mruldlratio = mruldlratio;
	}

	public int getTrafficvaluegt1erlcellnum() {
		return trafficvaluegt1erlcellnum;
	}

	public void setTrafficvaluegt1erlcellnum(int trafficvaluegt1erlcellnum) {
		this.trafficvaluegt1erlcellnum = trafficvaluegt1erlcellnum;
	}

	public int getVoltetravaleq0erlcellnum() {
		return voltetravaleq0erlcellnum;
	}

	public void setVoltetravaleq0erlcellnum(int voltetravaleq0erlcellnum) {
		this.voltetravaleq0erlcellnum = voltetravaleq0erlcellnum;
	}

	public int getLowvolteconnratiocellnum() {
		return lowvolteconnratiocellnum;
	}

	public void setLowvolteconnratiocellnum(int lowvolteconnratiocellnum) {
		this.lowvolteconnratiocellnum = lowvolteconnratiocellnum;
	}

	public int getHighvoltedropratiocellnum() {
		return highvoltedropratiocellnum;
	}

	public void setHighvoltedropratiocellnum(int highvoltedropratiocellnum) {
		this.highvoltedropratiocellnum = highvoltedropratiocellnum;
	}

	public int getHigvoltemrulhplratiocellnum() {
		return higvoltemrulhplratiocellnum;
	}

	public void setHigvoltemrulhplratiocellnum(int higvoltemrulhplratiocellnum) {
		this.higvoltemrulhplratiocellnum = higvoltemrulhplratiocellnum;
	}

	public int getHighvoltemrdlplratiocellnum() {
		return highvoltemrdlplratiocellnum;
	}

	public void setHighvoltemrdlplratiocellnum(int highvoltemrdlplratiocellnum) {
		this.highvoltemrdlplratiocellnum = highvoltemrdlplratiocellnum;
	}

	public int getLowesrvcchosucratiocellnum() {
		return lowesrvcchosucratiocellnum;
	}

	public void setLowesrvcchosucratiocellnum(int lowesrvcchosucratiocellnum) {
		this.lowesrvcchosucratiocellnum = lowesrvcchosucratiocellnum;
	}

	private String province;	
	
	private String city;
	
	private String month;
	
	private int cellnum;
	
	private int twohitwolowcellnum;
	
	private Double lteenbnum;
	
	private Double ltecellnum;
	
	private Double singlecellmaxactvolteusernum;
	
	private Double voltetraval;
	
	private Double volteradconnratio;
	
	private Double volteraddropratio;
	
	private Double esrvcchosucratio;
	
	private Double esrvcchoratio;
	
	private Double mrulplratio;
	
	private Double mruldlratio;
	
	private int trafficvaluegt1erlcellnum;
	
	private int voltetravaleq0erlcellnum;
	
	private int lowvolteconnratiocellnum;
	
	private int highvoltedropratiocellnum;
	
	private int higvoltemrulhplratiocellnum;
	
	private int highvoltemrdlplratiocellnum;
	
	private int lowesrvcchosucratiocellnum;
	
}
