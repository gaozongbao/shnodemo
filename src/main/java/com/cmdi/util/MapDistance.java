package com.cmdi.util;

/** 
 * @ClassName: MapDistance 
 * @Description: TODO
 * @author: 高宗宝
 * @date: 2019年6月13日
 * @version: 1.0
 */
public class MapDistance { 
        
	private static  double EARTH_RADIUS = 6378.137*1000;//地球半径
	private static double rad(double d)
	{
	  return d * Math.PI / 180.0;
	}
      
    
	/** 
	* @Title: GetDistance 
	* @Description: TODO 
	* @param lat1
	* @param lng1
	* @param lat2
	* @param lng2 
	* @return double
	* @author 高宗宝
	* @date 2019年6月13日上午10:51:47
	*/ 
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2)
	{
	  double radLat1 = rad(lat1);
	  double radLat2 = rad(lat2);
	  double a = radLat1 - radLat2;
	  double b = rad(lng1) - rad(lng2);

	  double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
	   Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
	  s = s * EARTH_RADIUS;
	//  s = Math.round(s * 10000) / 10000;
	  return s;
	}
      
    public static void main(String[] args) {
        //测试经纬度：117.11811  36.68484
        //测试经纬度2：117.00999000000002  36.66123
        //System.out.println(getDistance("117.11811","36.68484","117.00999000000002","36.66123"));
          
        //System.out.println(getAround("117.11811", "36.68484", "13000"));
        //117.01028712333508(Double), 117.22593287666493(Double),
        //36.44829619896034(Double), 36.92138380103966(Double)
    	System.out.println(GetDistance(29.490295,106.486654,29.615467, 106.581515));
          
    }
      
}
