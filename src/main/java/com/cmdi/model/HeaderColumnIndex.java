package com.cmdi.model;

/**
 * @author 高宗宝
 */
public class HeaderColumnIndex {
	public int cgiIndex = -1;

	@Override
	public String toString() {
		return "HeaderColumnIndex [cgiIndex=" + cgiIndex + ", enodebnameIndex=" + enodebnameIndex + ", cellnameIndex="
				+ cellnameIndex + ", tacIndex=" + tacIndex + ", localcellidIndex=" + localcellidIndex + ", enbidIndex="
				+ enbidIndex + ", longitudeIndex=" + longitudeIndex + ", latitudeIndex=" + latitudeIndex
				+ ", coveragetypeIndex=" + coveragetypeIndex + ", AzimuthIndex=" + AzimuthIndex
				+ ", antennaheightIndex=" + antennaheightIndex + ", totaldowntiltangleIndex=" + totaldowntiltangleIndex
				+ ", carrierffrequencynumIndex=" + carrierffrequencynumIndex + ", workfrequencybandIndex="
				+ workfrequencybandIndex + ", pciIndex=" + pciIndex + ", MaxTransmitPowerIndex=" + MaxTransmitPowerIndex
				+ ", CoverSceneIndex=" + CoverSceneIndex + ", ProvinceNameIndex=" + ProvinceNameIndex
				+ ", CityNameIndex=" + CityNameIndex + ", DistrictandcountyIndex=" + DistrictandcountyIndex
				+ ", VendorIndex=" + VendorIndex + ", ElectronicTiltAngleIndex=" + ElectronicTiltAngleIndex
				+ ", MechanicalTiltAngleIndex=" + MechanicalTiltAngleIndex + ", IsCoreAreaIndex=" + IsCoreAreaIndex
				+ ", statusIndex=" + statusIndex + "]";
	}

	public int enodebnameIndex = -1;
	public int cellnameIndex = -1;
	public int tacIndex = -1;
	public int localcellidIndex = -1;
	public int enbidIndex = -1;
	public int longitudeIndex = -1;
	public int latitudeIndex = -1;
	public int coveragetypeIndex = -1;
	public int AzimuthIndex = -1;
	public int antennaheightIndex = -1;
	public int totaldowntiltangleIndex = -1;
	public int carrierffrequencynumIndex = -1;
	public int workfrequencybandIndex = -1;
	public int pciIndex = -1;
	public int MaxTransmitPowerIndex = -1;
	public int CoverSceneIndex = -1;
	public int ProvinceNameIndex = -1;
	public int CityNameIndex = -1;
	public int DistrictandcountyIndex = -1;
	public int VendorIndex = -1;
	public int ElectronicTiltAngleIndex = -1;
	public int MechanicalTiltAngleIndex = -1;
	public int IsCoreAreaIndex = -1;
	public int statusIndex = -1;

	public void getIndexFromHeader(String[] headers) {
		for (int i = 0; i < headers.length; i++) {
			switch (headers[i].trim()) {
			case "小区ECGI":
				cgiIndex = i;
				break;
			case "所属E_NODEB":
				enodebnameIndex = i;
				break;
			case "小区中文名":
				cellnameIndex = i;
				break;
			case "跟踪区码":
				tacIndex = i;
				break;
			case "本地小区标识":
				localcellidIndex = i;
				break;
			case "enbid":
				enbidIndex = i;
				break;
			case "经度":
				longitudeIndex = i;
				break;
			case "纬度":
				latitudeIndex = i;
				break;
			case "覆盖类型":
				coveragetypeIndex = i;
				break;
			case "方位角":
				AzimuthIndex = i;
				break;
			case "天线挂高（米）":
				antennaheightIndex = i;
				break;
			case "总下倾角"://no
				totaldowntiltangleIndex = i;
				break;
			case "中心载频的信道号":
				carrierffrequencynumIndex = i;
				break;
			case "工作频段":
				workfrequencybandIndex = i;
				break;
			case "物理小区识别码"://确定命名
				pciIndex = i;
				break;
			case "PCI":
				pciIndex = i;
				break;
			case "最大发射功率":
				MaxTransmitPowerIndex = i;
				break;
			case "集团场景"://确定列
				CoverSceneIndex = i;
				break;
			case "省份":
				ProvinceNameIndex = i;
				break;
			case "地市":
				CityNameIndex = i;
				break;
			case "区县":
				DistrictandcountyIndex = i;
				break;
			case "主设备厂家名称":
				VendorIndex = i;
				break;
			case "电子下倾角":
				ElectronicTiltAngleIndex = i;
				break;
			case "机械下倾角":
				MechanicalTiltAngleIndex = i;
				break;
			case "是否核心区域":
				IsCoreAreaIndex = i;
				break;
			case "设备维护状态":
				statusIndex = i;
			default:
				break;
			}
		}
	}
}
