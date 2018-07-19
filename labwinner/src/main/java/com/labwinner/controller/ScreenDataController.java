package com.labwinner.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labwinner.common.ResultVo;
import com.labwinner.domain.Approvel;
import com.labwinner.domain.CalendarAssist;
import com.labwinner.domain.Calendars;
import com.labwinner.domain.DeviceAppointment;
import com.labwinner.domain.DeviceCollect;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.Reaction;
import com.labwinner.domain.SignIn;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ApprovelService;
import com.labwinner.service.CalendarAssistService;
import com.labwinner.service.CalendarsService;
import com.labwinner.service.DeviceAppointmentService;
import com.labwinner.service.DeviceCollectService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.JournalArticleService;
import com.labwinner.service.ProjectBasicInfoService;
import com.labwinner.service.ReactionService;
import com.labwinner.service.SignInService;
import com.labwinner.service.SysUserService;

@RestController
public class ScreenDataController {
	
	private static Logger logger = LoggerFactory
			.getLogger(ScreenDataController.class);
	
	
	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private SignInService signInService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private CalendarsService calendarsService;
	@Autowired
	private CalendarAssistService calendarAssistService;
	@Autowired
	private ReactionService reactionService;
	@Autowired
	private ApprovelService approvelService;
	@Autowired
	private ProjectBasicInfoService projectBasicInfoService;
	@Autowired
	private DeviceAppointmentService deviceAppointmentService;
	@Autowired
	private DeviceCollectService deviceCollectService;
	@Autowired
	private JournalArticleService journalArticleService;
	@Value("${sysUserPhone.url-path}")
	private String userImageUrl;

	
	@RequestMapping(value = "/screenInventoryData", method = RequestMethod.GET)
	public ResultVo getScreenInventoryData() {
		ResultVo resultVo = new ResultVo();
		//库存数据
		List<Inventories> list = inventoriesService.getScreenInventorys();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	@RequestMapping(value = "/screenSignInData", method = RequestMethod.GET)
	public ResultVo getScreenSingInData() {
		ResultVo resultVo = new ResultVo();
		//库存数据
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd H:m:s");
		String nowDate = dateFormat.format(date);
		List<SignIn> teamList = signInService.getAllByDay(nowDate);
		for (SignIn signIn : teamList) {
			SysUser sysUser = signIn.getSysUser();
			if (sysUser.getUserImage() != null && !"".equals(sysUser.getUserImage())) {
				sysUser.setUserImage(userImageUrl + sysUser.getUserImage());
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(teamList);
		return resultVo;
	}
	
	@RequestMapping(value = "/screenReactionData", method = RequestMethod.GET)
	public ResultVo getScreenReactionData() {
		ResultVo resultVo = new ResultVo();
		List<Reaction> reactionList = reactionService.getScreenAll();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(reactionList);
		return resultVo;
	}
	
	@RequestMapping(value = "/screenOrderData", method = RequestMethod.GET)
	public ResultVo getScreenOrderData() {
		ResultVo resultVo = new ResultVo();
		List<Approvel> orderList = approvelService.getScreenOrder();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(orderList);
		return resultVo;
	}
	
	
	@RequestMapping(value = "/screenCalendarData", method = RequestMethod.GET)
	public ResultVo getScreenCalendarData() {
		ResultVo resultVo = new ResultVo();
		//未完成任務
				List<Calendars> calendars=calendarsService.getScreenCalendars();
				for(Calendars calendar:calendars){
					List<CalendarAssist> calendarAssists=calendarAssistService.getByCalendarId(calendar.getCalendarId());
					calendar.setCalendarAssists(calendarAssists);
					if(calendarAssists!=null&&calendarAssists.size()>0){
						int count=0;
						for(CalendarAssist calendarAssist:calendarAssists){
							if(calendarAssist.getAssistStatus().getAssistStatusId()==2){
								count++;
							}
						}
						calendar.setFlag(count);
					}else{
						calendar.setFlag(0);
					}
				}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(calendars);
		return resultVo;
	}
	
	@RequestMapping(value = "/screenAppontmentData", method = RequestMethod.GET)
	public ResultVo getScreenAppontmentData() {
		ResultVo resultVo = new ResultVo();
		//未完成任務
		 List<DeviceAppointment> list = deviceAppointmentService.getAllUse();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	@RequestMapping(value = "/screenDeviceData", method = RequestMethod.GET)
	public ResultVo getScreenDeviceData() {
		ResultVo resultVo = new ResultVo();
		//未完成任務
		 List<DeviceCollect> list = deviceCollectService.getAllScreen();
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(list);
		return resultVo;
	}
	
	
	@RequestMapping(value = "/screenCount", method = RequestMethod.GET)
	public ResultVo getScreenCount() {
		ResultVo resultVo = new ResultVo();
		Map<String,Integer> map = new HashMap<String,Integer>();
		//项目数量
		Integer proCount = projectBasicInfoService.getProNameAll().size();
		
		//实验数量
		Integer reactionCount = reactionService.getAll().size();
		
		//库存数量
		Integer inventoryCount = inventoriesService.getAllInventorys().size();
		
		//预约
		Integer appointmentCount =deviceAppointmentService.getAllAppointment();
		//采购
		Integer orderCount = approvelService.getAllOrders();
		//知识
		int knowNum=0;
		List<Map<String, Object>> listKnow=new ArrayList<Map<String,Object>>();
		listKnow=journalArticleService.getKnowledge("ROLE_TEAM",0);
		List<Map<String, Object>> listKnowledge=new ArrayList<Map<String,Object>>();
		int count=0;
		int count1=0;
		int count2=0;
		if(listKnow!=null&&listKnow.size()>0){
		for(int i=0;i<listKnow.size();i++){
				if("1".equals(String.valueOf(listKnow.get(i).get("classify")))){
					count++;
					if(count<3){
						listKnowledge.add(listKnow.get(i));
					}
				}
				if("2".equals(String.valueOf(listKnow.get(i).get("classify")))){
					count1++;
					if(count1<3){
						listKnowledge.add(listKnow.get(i));
					}
				}
				if("4".equals(String.valueOf(listKnow.get(i).get("classify")))){
					count2++;
					if(count2<2){
						listKnowledge.add(listKnow.get(i));
					}
				}
			}
		if(listKnowledge.size()<5){
			if(listKnow.size()>=5){
				listKnowledge=listKnow.subList(0, 5);
			}else{
				listKnowledge=listKnow;
			}
		}
			knowNum=listKnow.size();
		}
		
		map.put("proCount", proCount);
		map.put("reactionCount", reactionCount);
		map.put("inventoryCount", inventoryCount);
		map.put("appointmentCount", appointmentCount);
		map.put("orderCount", orderCount);
		map.put("knowNum", knowNum);
		
		resultVo.setErrCode(0);
		resultVo.setErrMsg("find success");
		resultVo.setResultData(map);
		return resultVo;
		
	}
	
	

}
