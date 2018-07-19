package com.labwinner.controller;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalParameter;
import com.labwinner.domain.MolecularImage;
import com.labwinner.domain.RepresentationMap;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ChemicalParameterService;
import com.labwinner.service.MolecularImageService;
import com.labwinner.service.RepresentationMapService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.StringUtil;
import com.labwinner.vo.UserChemicalVo;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@RestController
public class ChemicalParameterController {

	private static Logger logger = LoggerFactory
			.getLogger(ChemicalParameterController.class);

	@Autowired
	private ChemicalParameterService chemicalParameterService;

	@Autowired
	private MolecularImageService molecularImageService;
	@Autowired
	private RepresentationMapService representationMapService;
	@Autowired
	private SysUserService sysUserService;

	@Value("${chemicalImage.url-path}")
	private String urlPath;

	@Value("${chemicalImage.upload-path}")
	String filePath;

	/**
	 * @Description 获取所有对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/chemicalParameter", method = RequestMethod.GET)
	public List<ChemicalParameter> getAll() {
		List<ChemicalParameter> list = chemicalParameterService.getAll();
		if (list == null) {
			String message = "没有对象";
			logger.warn(message);
		}
		return list;
	}
	
	/**
	 * @Description 获取所有对象列表
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/chemicalParameter/isLock/{id}/{isLock}", method = RequestMethod.GET)
	public ResultVo lockChemical(@PathVariable("id")Integer id,@PathVariable("isLock")String isLock) {
		ResultVo resultVo = new ResultVo();
		if(isLock.equals("undefined")){
			isLock = null;
		}
		chemicalParameterService.lockChemical(id, isLock);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("update success");
		return resultVo;
	}

	@RequestMapping(value = "/chemicalParameter/findByKeyword/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();

		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			List<ChemicalParameter> list = chemicalParameterService
					.getByKeyword(keyword);
			if (list==null) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find result is null");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(list));
			return resultVo;
		} else {
			List<ChemicalParameter> list = chemicalParameterService.getAll();
			if (list==null) {
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find result is null");
				return resultVo;
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(new PageInfo(list));
			return resultVo;
		}
	}

	/**
	 * @Description 根据主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/chemicalParameter/{id}", method = RequestMethod.GET)
	public ChemicalParameter getById(@PathVariable("id") Integer id) {
		ChemicalParameter chemicalParameter = chemicalParameterService
				.getById(id);
		if (chemicalParameter == null) {
			String message = "对象不存在(id:" + id + ")";
			logger.warn(message);
		}

		List<MolecularImage> molecularImages = new ArrayList<MolecularImage>(
				chemicalParameter.getMolecularImages());

		if (null != molecularImages && molecularImages.size() > 0) {
			for (MolecularImage molecularImage : molecularImages) {
				molecularImage.setImageName(urlPath
						+ molecularImage.getImageName());
			}
		}
		List<RepresentationMap> representationMaps = new ArrayList<RepresentationMap>(
				chemicalParameter.getRepresentationMaps());
		if (null != representationMaps && representationMaps.size() > 0) {
			for (RepresentationMap representationMap : representationMaps) {
				representationMap.setRepName(urlPath
						+ representationMap.getRepName());
			}
		}
		return chemicalParameter;
	}

	/**
	 * @Description 根据主键获取对象 去处标签
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:50:28
	 */
	@RequestMapping(value = "/chemicalParameter/getAll", method = RequestMethod.GET)
	public ResultVo getByChParId() {

		ResultVo resultVo = new ResultVo();
		List<ChemicalParameter> chemicalParameters = chemicalParameterService
				.getAll();
		Connection connection = null;

		try {
			connection = getConn();
			connection.setAutoCommit(false);
			PreparedStatement statement = (PreparedStatement) connection
					.prepareStatement("INSERT INTO representation_map (rep_name,ch_par_id) VALUES ( ?,? ) ");

			Integer num = 1;
			for (int k = 0; k < chemicalParameters.size(); k++) {
				StringUtil stringUtil = new StringUtil();
				ChemicalParameter chemicalParameter = chemicalParameters.get(k);

				if (null != chemicalParameter.getChemicalIamge()) {
					String[] a = stringUtil.getImgs(chemicalParameter
							.getChemicalIamge());
					if (a != null && a.length > 0) {
						for (int i = 0; i < a.length; i++) {
							statement.setInt(2, chemicalParameter.getChParId());
							statement.setString(1, a[i]);
							statement.addBatch();
							num++;
							if (num != 0 && num % 500 == 0) {
								statement.executeBatch();
								connection.commit();
								num = 0;
							}
						}
						statement.executeBatch();
						connection.commit();
					}
				}
				if (null != chemicalParameter.getRepresentationMap()) {
					num++;

				}

			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("insert success");
			resultVo.setResultData(num);
			return resultVo;
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @Description 保存对象方法
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@RequestMapping(value = "/chemicalParameter", method = RequestMethod.POST)
	public ResultVo save(@RequestBody UserChemicalVo userChemicalVo) {
		ResultVo resultVo = new ResultVo();
		ChemicalParameter chemicalParameter = userChemicalVo.getChemicalParameter();
		List<String> molecularImgs = userChemicalVo.getMolecularImgs();
		List<String> representationMapImgs = userChemicalVo.getRepresentationMapImgs();
			// TODO 判断更新，增加
			if (chemicalParameter != null) {
				
				Integer names = chemicalParameterService.getNameCount(chemicalParameter.getChineseName());
				Integer cases = chemicalParameterService.getCasCount(chemicalParameter.getCas());
				if(names>0){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("中文名称重复");
					return resultVo;
				}
				if(cases>0){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("cas重复");
					return resultVo;
				}
				
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				Integer userId = sysUser.getUserId();
				chemicalParameter.setCreater(userId+"");
				chemicalParameter.setCreateDate(new Date());
				chemicalParameter.setFlag(1);
				chemicalParameterService.save(chemicalParameter);
				
				// 保存上传图片
				if (molecularImgs != null && molecularImgs.size() > 0) {
					// base64保存图片
					Base64Util base64Util = new Base64Util();
					MolecularImage molecularImage = new MolecularImage();
					for (String imgStr : molecularImgs) {
						imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
						molecularImage.setChemicalParameter(chemicalParameter);
						molecularImage.setImageName(base64Util.GenerateImage(imgStr,
								filePath));
						molecularImageService.save(molecularImage);
					}
				}
				
				// 保存上传图片
				if (representationMapImgs != null && representationMapImgs.size() > 0) {
					// base64保存图片
					Base64Util base64Util = new Base64Util();
					RepresentationMap representationMap = new RepresentationMap();
					for (String imgStr : representationMapImgs) {
						imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
						representationMap.setChemicalParameters(chemicalParameter);
						representationMap.setRepName(base64Util.GenerateImage(imgStr,
								filePath));
						representationMapService.save(representationMap);
					}
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("save fail");
			return resultVo;
	}
	
	/**
	 * @Description 保存/更新对象方法
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:51:21
	 */
	@RequestMapping(value = "/chemicalParameter", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody UserChemicalVo userChemicalVo) {
		ResultVo resultVo = new ResultVo();
		ChemicalParameter chemicalParameter = userChemicalVo.getChemicalParameter();
		List<String> molecularImgs = userChemicalVo.getMolecularImgs();
		List<String> representationMapImgs = userChemicalVo.getRepresentationMapImgs();
		List<String> molecularImgUrls = userChemicalVo.getMolecularImgUrls();
		List<String> representationMapImgUrls = userChemicalVo.getRepresentationMapImgUrls();
			// TODO 判断更新，增加
			if (chemicalParameter != null) {
				
				Integer cases = chemicalParameterService.getCasCount(chemicalParameter.getCas());
				if(cases>0){
					resultVo.setErrCode(1);
					resultVo.setErrMsg("cas重复");
					return resultVo;
				}
				
				LoginController login = new LoginController();
				SysUser sysUser = sysUserService.getByUsername(login.getPrincipal()).get(0);
				Integer userId = sysUser.getUserId();
				chemicalParameter.setModifier(userId+"");
				chemicalParameter.setModifyDate(new Date());
				chemicalParameterService.update(chemicalParameter);
				
				// 删除要删的url图片
				if (representationMapImgUrls!=null && representationMapImgUrls.size() > 0) {
					for (String url : representationMapImgUrls) {
						new File(filePath
								+ url.substring(url.lastIndexOf("/") + 1))
								.delete();
						representationMapService.deleteByUrl(
								url.substring(url.lastIndexOf("/") + 1));
					}
				}
				
				// 删除要删的url图片
				if (molecularImgUrls!=null && molecularImgUrls.size() > 0) {
					for (String url : molecularImgUrls) {
						new File(filePath
								+ url.substring(url.lastIndexOf("/") + 1))
								.delete();
						molecularImageService.deleteByUrl(
								url.substring(url.lastIndexOf("/") + 1));
					}
				}
				// 保存上传图片
				if (molecularImgs != null && molecularImgs.size() > 0) {
					// base64保存图片
					Base64Util base64Util = new Base64Util();
					MolecularImage molecularImage = new MolecularImage();
					for (String imgStr : molecularImgs) {
						imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
						molecularImage.setChemicalParameter(chemicalParameter);
						molecularImage.setImageName(base64Util.GenerateImage(imgStr,
								filePath));
						molecularImageService.save(molecularImage);
					}
				}
				
				// 保存上传图片
				if (representationMapImgs != null && representationMapImgs.size() > 0) {
					// base64保存图片
					Base64Util base64Util = new Base64Util();
					RepresentationMap representationMap = new RepresentationMap();
					for (String imgStr : representationMapImgs) {
						imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
						representationMap.setChemicalParameters(chemicalParameter);
						representationMap.setRepName(base64Util.GenerateImage(imgStr,
								filePath));
						representationMapService.save(representationMap);
					}
				}
				
				resultVo.setErrCode(0);
				resultVo.setErrMsg("save success");
				return resultVo;
			}
			resultVo.setErrCode(2);
			resultVo.setErrMsg("save fail");
			return resultVo;
	}

	/**
	 * @Description 删除对象方法
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:52:47
	 */
	@RequestMapping(value = "/chemicalParameter/{id}", method = RequestMethod.DELETE)
	public ResultVo delete(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		chemicalParameterService.delete(id);
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save success");
		return resultVo;
	}

	public static Connection getConn() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://192.168.1.107:3306/labwinner_dev"; // DMEO为数据库名
		String user = "root";
		String password = "123456";
		try {
			Class.forName(driver);
			Connection conn = (Connection) DriverManager.getConnection(url,
					user, password);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
