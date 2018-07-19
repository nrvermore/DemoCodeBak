package com.labwinner.util;

import java.util.Calendar;
import java.util.UUID;

public class OrderNumberUtil {
	 public static String getOrderIdByUUId() {
		// String machineId = "SJ";//最大支持1-9个集群机器部署
         int hashCodeV = UUID.randomUUID().toString().hashCode();
         if(hashCodeV < 0) {//有可能是负数
             hashCodeV = - hashCodeV;
         }
         // 0 代表前面补充0     
         // 4 代表长度为4     
         // d 代表参数为正数型
         Calendar now = Calendar.getInstance();  
        int today=(now.get(Calendar.MONTH) + 1);
        String dayString="";
        if(today<10){
     	   dayString="0"+today;
        }else{
     	   dayString=String.valueOf(today);
        }
         return now.get(Calendar.YEAR)+dayString+now.get(Calendar.DAY_OF_MONTH)+String.format("%08d", hashCodeV);
     }
}
