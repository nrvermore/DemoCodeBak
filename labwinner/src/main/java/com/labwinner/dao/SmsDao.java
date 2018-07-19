package com.labwinner.dao;


import org.apache.ibatis.annotations.Param;

import com.labwinner.domain.Sms;

/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
public interface SmsDao {

	void save(Sms sms);

	Sms findVerByName(String phone);

	Sms getSms(@Param("phone")String phone,@Param("verCode") String verCode);

   
}
