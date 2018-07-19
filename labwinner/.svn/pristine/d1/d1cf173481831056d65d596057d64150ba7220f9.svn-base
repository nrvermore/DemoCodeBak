package com.labwinner.controller;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.MarketAssistVoice;
import com.labwinner.domain.SysSigningAgency;
import com.labwinner.domain.SysUser;
import com.labwinner.service.MarketAssistService;
import com.labwinner.service.MarketAssistVoiceService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.FileUtil;


@RestController
public class MarketAssistVoiceController {

	private static Logger logger = LoggerFactory
			.getLogger(MarketAssistVoiceController.class);

	@Autowired
	private MarketAssistVoiceService marketAssistVoiceService;
	
	@Autowired
	private MarketAssistService marketAssistService;
	
	@Autowired
	SysUserService sysUserService;
	
	
	@Value("${marketAssistVoice.url-path}")
	String urlPath;
	
	@Value("${marketAssistVoice.upload-path}")
	String filePath;
	
	/**
	 * @Description 查询集合
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssistVoice/getAll", method = RequestMethod.GET)
	public ResultVo getAll(){
		
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUserLogin = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUserLogin.getUserId();
		List<MarketAssistVoice> marketAssistVoices = marketAssistVoiceService.getAll();
		if(marketAssistVoices!=null){
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(marketAssistVoices);
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find is null");
			resultVo.setResultData(null);
			return resultVo;
	}
	/**
	 * @Description id查询对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssistVoice/getById/{id}", method = RequestMethod.GET)
	public ResultVo getbyId(@PathVariable("id") Integer id){
		
		ResultVo resultVo = new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUserLogin = sysUserService.getByUsername(login.getPrincipal()).get(0);
		Integer userId = sysUserLogin.getUserId();
		MarketAssistVoice marketAssistVoice = marketAssistVoiceService.getbyId(id);
		if(marketAssistVoice!=null){
			marketAssistVoice.setMarketAssistVoiceName(urlPath+marketAssistVoice.getMarketAssistVoiceName());
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(marketAssistVoice);
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("find is null");
			resultVo.setResultData(null);
			return resultVo;
	}
	
	/**
	 * @Description 删除评论
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssistVoice/delete/{id}", method = RequestMethod.GET)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo =  new ResultVo();
		MarketAssistVoice marketAssistVoice = marketAssistVoiceService.getbyId(id);
		if(marketAssistVoice!=null){
			marketAssistVoiceService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		}
			resultVo.setErrCode(1);
			resultVo.setErrMsg("delete fail");
			return resultVo;
		
		}
	/**
	 * @Description 新增信息广场语音
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/marketAssistVoice/save", method = RequestMethod.POST)
	public ResultVo save(
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam(value = "marketAssistId", required = false) Integer marketAssistId) {
		
		ResultVo resultVo =  new ResultVo();
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
		SysSigningAgency agency= sysUser.getSysSigningAgency();
		FileUtil fileUtil=new FileUtil();
		MarketAssistVoice marketAssistVoice = new MarketAssistVoice();
		try {
			marketAssistVoice.setMarketAssist(marketAssistService.getbyId(marketAssistId));
			marketAssistVoice.setSysUser(sysUser);
			marketAssistVoice.setCreateDate(new Date());
			//String fileName=fileUtil.uploadVoiceFile(file, filePath);
			if(file!=null&&!"".equals(file)){
				String fileName=fileUtil.uploadVideoFile(file, filePath);
				marketAssistVoice.setMarketAssistVoiceName(fileName);
			}
			
			marketAssistVoiceService.save(marketAssistVoice);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("save success");
			return resultVo;
		} catch (Exception e) {
			resultVo.setErrCode(1);
			resultVo.setErrMsg("save fail");
			return resultVo;

		}
	}
		
	
	
//	/**
//	 * @Description 求助已解决
//	 * @author xux
//	 * @version V1.0
//	 * @date 2017年5月18日 上午11:49:52
//	 */
//	@RequestMapping(value = "/marketAssistVoice/update/{id}", method = RequestMethod.GET)
//	public ResultVo update(@PathVariable("id") Integer id) {
//		
//		ResultVo resultVo =  new ResultVo();
//		
//		MarketAssistVoice marketAssistVoice = new MarketAssistVoice();
//		marketAssistVoice.setMarketAssistId(id);
//		marketAssistVoice.setIsSolve("true");
//		marketAssistVoiceService.update(marketAssistVoice);
//		
//		resultVo.setErrCode(0);
//		resultVo.setErrMsg("update success");
//		return resultVo;
//	}
//	
	
}
