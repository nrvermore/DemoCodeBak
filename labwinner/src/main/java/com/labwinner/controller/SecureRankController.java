package com.labwinner.controller;

import java.util.List;









import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.DeviceLocation;
import com.labwinner.domain.ModifyType;
import com.labwinner.domain.SecureRank;
import com.labwinner.service.SecureRankService;


@RestController
public class SecureRankController {
	
	private static Logger logger = LoggerFactory
			.getLogger(SecureRankController.class);
	
	@Autowired 
	private SecureRankService secureRankService;
	
	/**
	 * @Description 获取所有对象列表
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	
	@RequestMapping(value = "/secureRank/list", method = RequestMethod.GET)
	public List<SecureRank> getAll() {
		List<SecureRank> list = secureRankService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}

	/**
	 * @Description 根据关键字分页获取对象
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/secureRankPageable/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	@ResponseBody
	public ResultVo getAllPageable(@PathVariable Integer page,
			@PathVariable Integer pageSize, @PathVariable String keyword) {
		ResultVo resultVo = new ResultVo();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<SecureRank> secureRanks = secureRankService.getAllPageable(keyword);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(secureRanks));
			return resultVo;
		} else {
			List<SecureRank> secureRanks = secureRankService.getAll();
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(secureRanks));
			return resultVo;
		}
		
	}
	
	/**
	 * @Description 保存对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/secureRank/add", method = RequestMethod.POST)
	public ResultVo save(@RequestBody SecureRank secureRank) {
		 ResultVo resultVo = new ResultVo();
			try {
				secureRankService.save(secureRank);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("sava successe");
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("sava fail");
				return resultVo;
			}
	}
	
	/**
	 * @Description 删除对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/secureRank/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();
		 try {
			secureRankService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("删除成功！！！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("删除失败！！！");
		}
		return resultVo;

		// SecureRank secureRank =  secureRankService.getById(id);

			//if (secureRank.getInventories().size() > 0){
				//resultVo.setErrCode(1);
				//resultVo.setErrMsg("该保密级别存在库存中，不能被删除！！！");
			//}else {
				
//				secureRankService.delete(id);
//				resultVo.setErrCode(0);
//				resultVo.setErrMsg("删除成功！！！");
		//	}
			//return resultVo;
	}
	/**
	 * @Description 更新对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/secureRank", method = RequestMethod.PUT)
	public ResultVo Update(@RequestBody SecureRank secureRank) {
		 ResultVo resultVo = new ResultVo();
			try {
				secureRankService.update(secureRank);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("update successe");
				return resultVo;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("update fail");
				return resultVo;
			}
	}
	/**
	 * @Description 查询对象方法
	 * @author liuhq
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	
	@RequestMapping(value = "/secureRank/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		 ResultVo resultVo = new ResultVo();
			try {
				SecureRank secureRank = secureRankService.getById(id);
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(secureRank);
				return resultVo;
				// }
			} catch (Exception e) {
				e.printStackTrace();
				resultVo.setErrCode(1);
				resultVo.setErrMsg("find fail");
				return resultVo;
			}
	}
}
