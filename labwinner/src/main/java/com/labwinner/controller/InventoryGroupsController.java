package com.labwinner.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.labwinner.common.PageEntity;
import com.labwinner.common.ResultVo;
import com.labwinner.domain.ChemicalImage;
import com.labwinner.domain.Inventories;
import com.labwinner.domain.InventoryGroups;
import com.labwinner.domain.InventoryLocation;
import com.labwinner.domain.InventoryUser;
import com.labwinner.domain.SysUser;
import com.labwinner.service.ChemicalImageService;
import com.labwinner.service.ExecuteChemicalGroupService;
import com.labwinner.service.ExecuteChemicalService;
import com.labwinner.service.InventoriesService;
import com.labwinner.service.InventoryGroupsService;
import com.labwinner.service.InventoryLocationService;
import com.labwinner.service.InventoryModifyService;
import com.labwinner.service.InventoryUserService;
import com.labwinner.service.QrInfoService;
import com.labwinner.service.ReactionDesignChemicalService;
import com.labwinner.service.SolutionDosagesService;
import com.labwinner.service.SysUserService;
import com.labwinner.util.Base64Util;
import com.labwinner.util.ExcelData;
import com.labwinner.util.ExportExcelUtils;
import com.labwinner.vo.InventoryExcelVo;
import com.labwinner.vo.InventoryGroupsVo;

@RestController
public class InventoryGroupsController {

	private static Logger logger = LoggerFactory
			.getLogger(InventoryGroupsController.class);

	@Autowired
	private InventoryGroupsService inventoryGroupsService;
	@Autowired
	private InventoriesService inventoriesService;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private ReactionDesignChemicalService reactionDesignChemicalService;
	@Autowired
	private ExecuteChemicalService executeChemicalService;

	@Autowired
	private ExecuteChemicalGroupService executeChemicalGroupService;

	@Autowired
	private InventoryUserService inventoryUserService;
	@Autowired
	private ChemicalImageService chemicalImageService;
	@Autowired
	private QrInfoService qrInfoService;
	@Autowired
	private InventoryModifyService inventoryModifyService;

	@Autowired
	private InventoryLocationService inventoryLocationService;

	@Autowired
	private SolutionDosagesService solutionDosagesService;

	@Value("${web.upload-path}")
	String filePath;
	@Value("${web.url-path}")
	private String chemicalUrlPath;
	@Value("${web.qr-path}")
	String qrPath;
	@Value("${web.qrUrl-path}")
	private String urlPath;
	@Value("${excel.upload-path}")
	private String excelPath;

	@Value("${excel.url-path}")
	private String excelUrl;

	/**
	 * @Description H5条件模糊查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */

	@RequestMapping(value = "/inventoryGroupsApp/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				List<InventoryGroups> list = inventoryGroupsService
						.getAppByKeyword(keyword, userId);

				if (list != null) {
					for (InventoryGroups inventoryGroups : list) {
						getImages(inventoryGroups);
					}
					int total = list.size();
					double c = (((double) total) / pageSize);
					int d = (int) Math.ceil(c);
					PageEntity pageEntity = new PageEntity();
					pageEntity.setPageNum(page + 1);
					pageEntity.setPageSize(pageSize);
					pageEntity.setPages(d);
					pageEntity.setTotal(total);
					int num = list.size() > page * pageSize ? page * pageSize
							: list.size();
					if (page <= d) {
						pageEntity.setList(list.subList(0, num));
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(pageEntity);
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<InventoryGroups> list = inventoryGroupsService
						.getAppListByKeyword(keyword);
				if (list != null) {
					for (InventoryGroups inventoryGroups : list) {
						getImages(inventoryGroups);
					}
					int total = list.size();
					double c = (((double) total) / pageSize);
					int d = (int) Math.ceil(c);
					PageEntity pageEntity = new PageEntity();
					pageEntity.setPageNum(page + 1);
					pageEntity.setPageSize(pageSize);
					pageEntity.setPages(d);
					pageEntity.setTotal(total);
					int num = list.size() > page * pageSize ? page * pageSize
							: list.size();
					if (page <= d) {
						pageEntity.setList(list.subList(0, num));
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(pageEntity);
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				List<InventoryGroups> list = inventoryGroupsService
						.getUserInventorys(userId);
				if (list != null) {
					for (InventoryGroups inventoryGroups : list) {
						getImages(inventoryGroups);
					}
					int total = list.size();
					double c = (((double) total) / pageSize);
					int d = (int) Math.ceil(c);
					PageEntity pageEntity = new PageEntity();
					pageEntity.setPageNum(page + 1);
					pageEntity.setPageSize(pageSize);
					pageEntity.setPages(d);
					pageEntity.setTotal(total);
					int num = list.size() > page * pageSize ? page * pageSize
							: list.size();
					if (page <= d) {
						pageEntity.setList(list.subList(0, num));
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(pageEntity);
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;

			} else {
				List<InventoryGroups> list = inventoryGroupsService
						.getAllInventorys();
				if (list != null) {
					for (InventoryGroups inventoryGroups : list) {
						getImages(inventoryGroups);
					}
					int total = list.size();
					double c = (((double) total) / pageSize);
					int d = (int) Math.ceil(c);
					PageEntity pageEntity = new PageEntity();
					pageEntity.setPageNum(page + 1);
					pageEntity.setPageSize(pageSize);
					pageEntity.setPages(d);
					pageEntity.setTotal(total);
					int num = list.size() > page * pageSize ? page * pageSize
							: list.size();
					if (page <= d) {
						pageEntity.setList(list.subList(0, num));
					}
					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(pageEntity);
					return resultVo;
				}
				resultVo.setErrCode(2);
				resultVo.setErrMsg("find is null");
				return resultVo;
			}
		}
	}

	/**
	 * @Description 实验设计添加库存查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups/reactionDesign/{page}/{pageSize}/{keyword}", method = RequestMethod.GET)
	public ResultVo getGroupsByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword) {
		ResultVo resultVo = new ResultVo();

		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}
		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				List<InventoryGroups> list = inventoryGroupsService
						.getAppByKeyword(keyword, userId);

				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;

			} else {
				List<InventoryGroups> list = inventoryGroupsService
						.getAppListByKeyword(keyword);

				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				List list = inventoryGroupsService.getUserInventorys(userId);

				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;

			} else {
				List<InventoryGroups> list = inventoryGroupsService
						.getAllInventorys();

				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;
			}
		}

	}

	/**
	 * @Description Web条件模糊查询
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups/{page}/{pageSize}/{keyword}/{type}", method = RequestMethod.GET)
	public ResultVo getByKeyword(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("keyword") String keyword,
			@PathVariable("type") Integer type) {
		ResultVo resultVo = new ResultVo();

//		Set<InventoryGroups> sets = new HashSet<InventoryGroups>();
//		Set<Integer> idSets = new HashSet<Integer>();
		List<InventoryGroups> sets = new ArrayList<InventoryGroups>();
		List<Integer> idSets = new ArrayList<Integer>();
		
		List<InventoryGroups> list = new ArrayList<InventoryGroups>();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		if (page != null && pageSize != null) {
			PageHelper.startPage(page, pageSize);
		}

		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				if (type == 1 || type == 2) {
					// 搜索化学品名称和供应商名称
					list = inventoryGroupsService.getByKeyword(keyword, userId,
							type, null);

					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;

				} else {
					Integer locId = Integer.valueOf(keyword);
					List<Integer> ids = getTree(locId);
					for (Integer locaId : ids) {
						// 搜索库存位置
						List<InventoryGroups> locList = inventoryGroupsService
								.getByKeyword(null, userId, type, locaId);
						if (locList != null && locList.size() > 0) {

							for (InventoryGroups inventoryGroups : locList) {
								if (!idSets.contains(inventoryGroups
										.getGroupId())) {
									idSets.add(inventoryGroups.getGroupId());
									sets.add(inventoryGroups);
								}
							}
						}
					}

//					list = new ArrayList<InventoryGroups>(sets);
					list = sets;

				}

				int total = list.size();
				double c = (((double) total) / 10);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setPages(d);
				pageEntity.setTotal(total);
				int num = list.size() > page * pageSize ? page * pageSize
						: list.size();
				if (page <= d) {
					pageEntity
							.setList(list.subList((page - 1) * pageSize, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;

			} else {
				if (type == 1 || type == 2) {
					list = inventoryGroupsService.getListByKeyword(keyword,
							type, null);

					resultVo.setErrCode(0);
					resultVo.setErrMsg("find success");
					resultVo.setResultData(new PageInfo(list));
					return resultVo;

				} else {
					Integer locId = Integer.valueOf(keyword);
					List<Integer> ids = getTree(locId);
					for (Integer locaId : ids) {
						List<InventoryGroups> locList = inventoryGroupsService
								.getListByKeyword(null, type, locaId);
						if (locList != null && locList.size() > 0) {
							for (InventoryGroups inventoryGroups : locList) {
								if (!idSets.contains(inventoryGroups
										.getGroupId())) {
									idSets.add(inventoryGroups.getGroupId());
									sets.add(inventoryGroups);
								}
							}
						}
					}
//					list = new ArrayList<InventoryGroups>(sets);
					list = sets;
				}

				int total = list.size();
				double c = (((double) total) / 10);
				int d = (int) Math.ceil(c);
				PageEntity pageEntity = new PageEntity();
				pageEntity.setPageNum(page);
				pageEntity.setPageSize(pageSize);
				pageEntity.setPages(d);
				pageEntity.setTotal(total);
				int num = list.size() > page * pageSize ? page * pageSize
						: list.size();
				if (page <= d) {
					pageEntity
							.setList(list.subList((page - 1) * pageSize, num));
				}
				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(pageEntity);
				return resultVo;
			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = inventoryGroupsService.getUserInventorys(userId);

				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;

			} else {
				list = inventoryGroupsService.getAllInventorys();

				resultVo.setErrCode(0);
				resultVo.setErrMsg("find success");
				resultVo.setResultData(new PageInfo(list));
				return resultVo;
			}
		}
	}

	/**
	 * @Description 库存分组详情
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups/{id}", method = RequestMethod.GET)
	public ResultVo getById(@PathVariable("id") Integer id) {
		ResultVo resultVo = new ResultVo();
		InventoryGroups inventoryGroups = inventoryGroupsService.getById(id);
		if (inventoryGroups != null) {
			List<ChemicalImage> chemicalImages = new ArrayList<ChemicalImage>(
					inventoryGroups.getChemicalImages());
			if (chemicalImages != null && chemicalImages.size() > 0) {
				for (ChemicalImage chemicalImage : chemicalImages) {
					if (chemicalImage.getDissolvantDescribe() != null) {
						chemicalImage.setDissolvantDescribe(chemicalUrlPath
								+ chemicalImage.getDissolvantDescribe());
					}
				}
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(inventoryGroups);
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("find is null");
		return resultVo;
	}

	/**
	 * @Description barcode查找库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups/getByBarcode/{page}/{pageSize}/{barcode}", method = RequestMethod.GET)
	public ResultVo getByBarcode(@PathVariable("page") Integer page,
			@PathVariable("pageSize") Integer pageSize,
			@PathVariable("barcode") String barcode) {
		ResultVo resultVo = new ResultVo();

		Set<InventoryGroups> sets = new HashSet<InventoryGroups>();
		Set<Integer> idSets = new HashSet<Integer>();
		List<InventoryGroups> list = new ArrayList<InventoryGroups>();

		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();
		InventoryLocation location = inventoryLocationService
				.getByBarcode(barcode);
		Integer locId = location.getCid();
		InventoryLocation inventoryLocation = inventoryLocationService
				.getTree(locId);
		if (inventoryLocation.getPid() != 0
				|| inventoryLocation.getPid() != null) {
			String pName = getAllPname(location.getCid());
			inventoryLocation.setParentName(pName);
		}

		if (!roleName.equals("ROLE_TEAM")) {
			List<Integer> ids = getTree(locId);
			for (Integer locaId : ids) {
				// 搜索库存位置
				List<InventoryGroups> locList = inventoryGroupsService
						.getByKeyword(null, userId, 3, locaId);
				if (locList != null && locList.size() > 0) {

					for (InventoryGroups inventoryGroups : locList) {
						if (!idSets.contains(inventoryGroups.getGroupId())) {
							idSets.add(inventoryGroups.getGroupId());
							sets.add(inventoryGroups);
						}
					}
				}
			}
			list = new ArrayList<InventoryGroups>(sets);
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page + 1);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setData(inventoryLocation);
			pageEntity.setTotal(total);
			int num = list.size() > page * pageSize ? page * pageSize : list
					.size();
			if (page <= d) {
				pageEntity.setList(list.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;

		} else {
			List<Integer> ids = getTree(locId);
			for (Integer locaId : ids) {
				List<InventoryGroups> locList = inventoryGroupsService
						.getListByKeyword(null, 3, locaId);
				if (locList != null && locList.size() > 0) {
					for (InventoryGroups inventoryGroups : locList) {
						if (!idSets.contains(inventoryGroups.getGroupId())) {
							idSets.add(inventoryGroups.getGroupId());
							sets.add(inventoryGroups);
						}
					}
				}
			}
			list = new ArrayList<InventoryGroups>(sets);
			int total = list.size();
			double c = (((double) total) / pageSize);
			int d = (int) Math.ceil(c);
			PageEntity pageEntity = new PageEntity();
			pageEntity.setPageNum(page + 1);
			pageEntity.setPageSize(pageSize);
			pageEntity.setPages(d);
			pageEntity.setTotal(total);
			pageEntity.setData(inventoryLocation);
			int num = list.size() > page * pageSize ? page * pageSize : list
					.size();
			if (page <= d) {
				pageEntity.setList(list.subList(0, num));
			}
			resultVo.setErrCode(0);
			resultVo.setErrMsg("find success");
			resultVo.setResultData(pageEntity);
			return resultVo;
		}

	}

	/**
	 * @Description 编辑库存分组
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups", method = RequestMethod.PUT)
	public ResultVo update(@RequestBody InventoryGroupsVo inventoryGroupsVo) {
		ResultVo resultVo = new ResultVo();

		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();

		InventoryGroups inventoryGroups = inventoryGroupsVo
				.getInventoryGroups();

		// 要删除的url
		List<String> urls = inventoryGroupsVo.getUrls();
		// 要保存的图片
		List<String> imgs = inventoryGroupsVo.getImgs();
		List<Integer> inventoryUsers = inventoryGroupsVo.getInventoryList();

		if (inventoryUsers != null && inventoryUsers.size() > 0) {
			if (!inventoryUsers.contains(userId)) {
				inventoryUsers.add(userId);
			}
		}

		inventoryGroupsService.update(inventoryGroups);

		// 删除库存权限成员
		inventoryUserService.delete(inventoryGroups.getGroupId());

		// 保存权限表
		saveInventoryGroups(inventoryGroups, inventoryUsers);

		// 保存图片
		saveImage(inventoryGroups, imgs);
		// 删除要删的url图片
		if (null != urls && urls.size() > 0) {
			for (String url : urls) {
				new File(filePath + url.substring(url.lastIndexOf("/") + 1))
						.delete();
				chemicalImageService.deleteByUrl(
						url.substring(url.lastIndexOf("/") + 1),
						inventoryGroups.getGroupId());
			}
		}
		resultVo.setErrCode(0);
		resultVo.setErrMsg("save successe");

		return resultVo;

	}

	/**
	 * @Description 删除库存接口
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups/delete/{id}", method = RequestMethod.DELETE)
	public ResultVo deleteInventoryGroup(@PathVariable("id") Integer id) {

		ResultVo resultVo = new ResultVo();
		List<Integer> inventoryIds = new ArrayList<Integer>();
		InventoryGroups inventoryGroups = inventoryGroupsService.getById(id);
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();

		// 组id获取库存分组
		List<Inventories> inventoryList = inventoriesService.getByGroupId(id,
				null);
		// 实验设计用到库存id
		List<Integer> inventorys = reactionDesignChemicalService
				.getInventorys();
		// 执行用到化学品
		List<Integer> idList = executeChemicalService.getInventorys();

		List<Integer> solutionInventoryList = solutionDosagesService
				.getAllInventories();

		if (inventoryGroups != null && inventoryList != null
				&& inventoryList.size() > 0) {

			for (Inventories inventory : inventoryList) {
				Integer inventoryId = inventory.getInventoryId();
				Integer cid = inventory.getSysUser().getUserId();
				if ((cid - userId != 0) && !roleName.equals("ROLE_TEAM")) {
					resultVo.setErrCode(1);
					resultVo.setErrMsg("您无权删除库存");
					return resultVo;
				}
				if (solutionInventoryList.contains(inventoryId)) {
					resultVo.setErrCode(6);
					resultVo.setErrMsg("溶液用到，您无权删除库存");
					return resultVo;
				}
				inventoryIds.add(inventoryId);
			}

			if (inventorys.contains(id) || idList.contains(id)) {
				resultVo.setErrCode(3);
				resultVo.setErrMsg("实验用到，您无权删除库存");
				return resultVo;
			}

			// 删除化学品图片表
			chemicalImageService.deleteById(id);
			// 库存权限表
			inventoryUserService.delete(id);
			// 库存历史变更表
			inventoryModifyService.batchRemove(inventoryIds);
			// 二维码信息表
			qrInfoService.batchRemove(inventoryIds);

			// 库存基本信息表
			inventoriesService.deleteByGroupId(id);

			inventoryGroupsService.delete(id);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("delete success");
			return resultVo;
		}
		resultVo.setErrCode(2);
		resultVo.setErrMsg("无此分组库存");
		return resultVo;

	}

	/**
	 * @Description 保存权限用户
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveInventoryGroups(InventoryGroups inventoryGroups,
			List<Integer> sysUsers) {

		// 保存系统权限用户信息
		if (inventoryGroups.getSecureRank().getSecureRankId() == 2) {
			InventoryUser inventoryUser = new InventoryUser();
			SysUser sysUser = new SysUser();
			if (sysUsers != null) {
				for (Integer userId : sysUsers) {
					inventoryUser.setInventoryGroups(inventoryGroups);
					sysUser.setUserId(userId);
					inventoryUser.setSysUser(sysUser);
					inventoryUserService.save(inventoryUser);
				}
			}
		}
	}

	public String getAllPname(Integer id) {
		try {
			String ss = "";
			loop: for (int i = 0; i < 10; i++) {
				InventoryLocation inventoryLocation = inventoryLocationService
						.getById(id);
				if (inventoryLocation.getPid() != null
						&& inventoryLocation.getPid() != 0) {
					if (i == 0) {
						ss = "";
					} else if (i == 1) {
						ss = inventoryLocation.getLabel();
					} else {
						ss = inventoryLocation.getLabel() + ">" + ss;
					}
					id = inventoryLocation.getPid();
				} else {
					if (!"".equals(ss)) {
						ss = inventoryLocation.getLabel() + ">" + ss;
					} else {
						ss = inventoryLocation.getLabel();
					}
					break loop;
				}
			}
			return ss;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	/**
	 * @Description 保存图片
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	private void saveImage(InventoryGroups inventoryGroups, List<String> imgStrs) {

		// 保存上传图片
		if (imgStrs != null && imgStrs.size() > 0) {
			// base64保存图片
			Base64Util base64Util = new Base64Util();
			ChemicalImage image = new ChemicalImage();
			for (String imgStr : imgStrs) {
				imgStr = imgStr.substring(imgStr.indexOf(",") + 1);
				image.setInventoryGroups(inventoryGroups);
				image.setDissolvantDescribe(base64Util.GenerateImage(imgStr,
						filePath));
				chemicalImageService.save(image);
			}
		}
	}

	/**
	 * 递归算法解析成树形结构
	 *
	 * @param cid
	 * @return
	 * @author jiqinlin
	 */
	@RequestMapping(value = "/inventoryLoc/{cid}", method = RequestMethod.GET)
	public List<Integer> getTree(@PathVariable("cid") Integer cid) {
		// public List<Integer> getTree(Integer cid) {

		List<Integer> locIdList = new ArrayList<Integer>();

		// 根据cid获取节点对象(SELECT * FROM tb_tree t WHERE t.cid=?)
		InventoryLocation node = inventoryLocationService.getLocation(cid);
		if (node != null) {
			locIdList.add(cid);
			// 查询cid下的所有子节点(SELECT * FROM tb_tree t WHERE t.pid=?)
			List<InventoryLocation> childTreeNodes = inventoryLocationService
					.getLocations(cid);
			if (childTreeNodes != null && childTreeNodes.size() > 0) {
				// 遍历子节点
				if (childTreeNodes != null && childTreeNodes.size() > 0) {
					for (InventoryLocation child : childTreeNodes) {
						locIdList.addAll(getTree(child.getCid()));
					}
				}
			}

		}
		return locIdList;

	}

	public void getImages(InventoryGroups inventoryGroups) {
		List<ChemicalImage> images = chemicalImageService
				.getByGroupId(inventoryGroups.getGroupId());
		if (images != null && images.size() > 0) {
			for (ChemicalImage chemicalImage : images) {
				if (chemicalImage.getDissolvantDescribe() != null) {
					chemicalImage.setDissolvantDescribe(chemicalUrlPath
							+ chemicalImage.getDissolvantDescribe());
				}
			}
			inventoryGroups.setChemicalImages(images);
		}
	}

	/**
	 * @Description 打印全部库存
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups/printExcel/{keyword}/{type}", method = RequestMethod.GET)
	public ResultVo printExcel(@PathVariable("keyword") String keyword,
			@PathVariable("type") Integer type) {
		ResultVo resultVo = new ResultVo();

//		Set<InventoryGroups> sets = new HashSet<InventoryGroups>();
//		Set<Integer> idSets = new HashSet<Integer>();
		
		List<InventoryGroups> sets = new ArrayList<InventoryGroups>();
		List<Integer> idSets = new ArrayList<Integer>();
		
		List<InventoryGroups> list = new ArrayList<InventoryGroups>();
		List<Inventories> inventoryList = new ArrayList<Inventories>();
		List<Integer> groupIds = new ArrayList<Integer>();
		// 获取当前用户
		LoginController login = new LoginController();
		SysUser sysUser = sysUserService.getByUsername(login.getPrincipal())
				.get(0);
		Integer userId = sysUser.getUserId();
		String roleName = sysUser.getSysRole().getRoleName();

		if (keyword != null && keyword != "" && !"undefined".equals(keyword)) {
			if (!roleName.equals("ROLE_TEAM")) {
				if (type == 1 || type == 2) {
					// 搜索化学品名称和供应商名称
					list = inventoryGroupsService.getByKeyword(keyword, userId,
							type, null);

				} else {
					Integer locId = Integer.valueOf(keyword);
					List<Integer> ids = getTree(locId);
					for (Integer locaId : ids) {
						// 搜索库存位置
						List<InventoryGroups> locList = inventoryGroupsService
								.getByKeyword(null, userId, type, locaId);
						if (locList != null && locList.size() > 0) {

							for (InventoryGroups inventoryGroups : locList) {
								if (!idSets.contains(inventoryGroups
										.getGroupId())) {
									idSets.add(inventoryGroups.getGroupId());
									sets.add(inventoryGroups);
								}
							}
						}
					}

					list = sets;

				}

			} else {
				if (type == 1 || type == 2) {
					list = inventoryGroupsService.getListByKeyword(keyword,
							type, null);

				} else {
					Integer locId = Integer.valueOf(keyword);
					List<Integer> ids = getTree(locId);
					for (Integer locaId : ids) {
						List<InventoryGroups> locList = inventoryGroupsService
								.getListByKeyword(null, type, locaId);
						if (locList != null && locList.size() > 0) {
							for (InventoryGroups inventoryGroups : locList) {
								if (!idSets.contains(inventoryGroups
										.getGroupId())) {
									idSets.add(inventoryGroups.getGroupId());
									sets.add(inventoryGroups);
								}
							}
						}
					}
					list =sets;
				}

			}
		} else {
			if (!roleName.equals("ROLE_TEAM")) {
				list = inventoryGroupsService.getUserInventorys(userId);

			} else {
				list = inventoryGroupsService.getAllInventorys();

			}
		}

		for (InventoryGroups inventroyGroup : list) {
			groupIds.add(inventroyGroup.getGroupId());
		}

		if (groupIds == null || groupIds.size() == 0) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("inventory is null");
			return resultVo;
		}

		if (type == 1 || type == 2) {
			inventoryList = inventoriesService.getByGroupIds(groupIds);
		} else {
			if (keyword != null && keyword != ""
					&& !"undefined".equals(keyword)) {
				for (Integer groupId : groupIds) {
					Integer locId = Integer.valueOf(keyword);
					List<Integer> ids = getTree(locId);
					for (Integer locaId : ids) {
						List<Inventories> inventorys = inventoriesService
								.getByGroupId(groupId, locaId);
						if (inventorys != null && inventorys.size() > 0) {
							inventoryList.addAll(inventorys);
						}
					}
				}
			} else {
				inventoryList = inventoriesService.getByGroupIds(groupIds);
			}

		}

		if (inventoryList == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("inventory is null");
			return resultVo;
		}

		String fileName = "库存数据.xlsx";
		String path = excelPath + fileName;
		ExcelData data = new ExcelData();
		data.setName("库存数据");
		List<String> titles = new ArrayList();
		titles.add("库存名称");
		titles.add("可用量");
		titles.add("批号");
		titles.add("储存位置");
		titles.add("负责人");
		titles.add("供应商");
		data.setTitles(titles);

		List<List<Object>> rows = new ArrayList();

		for (Inventories inventories : inventoryList) {
			List<Object> row = new ArrayList();

			InventoryLocation location = inventories.getInventoryLocation();
			if (location != null && location.getPid() != 0) {
				String pName = getAllPname(location.getCid());
				location.setParentName(pName);
				location.setLabel(pName + ">" + location.getLabel());
			}

			row.add(inventories.getInventoryName());
			row.add(inventories.getActAvaWei()
					+ inventories.getMeasurement().getMeasureUnit());
			row.add(inventories.getInventoryGroups().getBatchNumber());
			row.add(inventories.getInventoryLocation().getLabel());
			row.add(inventories.getSysUser().getRealname());
			row.add(inventories.getInventoryGroups().getSupplier()
					.getSuprName());
			rows.add(row);
		}

		data.setRows(rows);

		// 生成本地
		try {
			ExportExcelUtils.exportExcel(path, data);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("生成excel成功");
			resultVo.setResultData(excelUrl + fileName);
			resultVo.setResultData1(path);
			return resultVo;
		} catch (Exception e) {

			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("生成excel失败");
			return resultVo;
		}
	}

	/**
	 * @Description 库存分组主键获取对象
	 * @author xux
	 * @version V1.0
	 * @date 2017年5月18日 上午11:49:52
	 */
	@RequestMapping(value = "/inventoryGroups/getByGroupIdList", method = RequestMethod.POST)
	public ResultVo getByGroupIds(@RequestBody InventoryExcelVo inventoryExcelVo) {
		List<Inventories> inventoryList = new ArrayList<Inventories>();
		ResultVo resultVo = new ResultVo();

		List<Integer> ids = inventoryExcelVo.getIds();
		Integer cid = inventoryExcelVo.getCid();
		if (cid == null) {
			inventoryList = inventoriesService.getByGroupIds(ids);
		} else {
			for (Integer groupId : ids) {
				List<Integer> localList = getTree(cid);
				for (Integer locaId : localList) {
					List<Inventories> inventorys = inventoriesService
							.getByGroupId(groupId, locaId);
					if (inventorys != null && inventorys.size() > 0) {
						inventoryList.addAll(inventorys);
					}
				}
			}
		}

		if (inventoryList == null) {
			resultVo.setErrCode(2);
			resultVo.setErrMsg("inventory is null");
			return resultVo;
		}

		String fileName = "库存数据.xlsx";
		String path = excelPath + fileName;
		ExcelData data = new ExcelData();
		data.setName("库存数据");
		List<String> titles = new ArrayList();
		titles.add("库存名称");
		titles.add("可用量");
		titles.add("批号");
		titles.add("储存位置");
		titles.add("负责人");
		titles.add("供应商");
		data.setTitles(titles);

		List<List<Object>> rows = new ArrayList();

		for (Inventories inventories : inventoryList) {
			List<Object> row = new ArrayList();

			InventoryLocation location = inventories.getInventoryLocation();
			if (location != null && location.getPid() != 0) {
				String pName = getAllPname(location.getCid());
				location.setParentName(pName);
				location.setLabel(pName + ">" + location.getLabel());
			}

			row.add(inventories.getInventoryName());
			row.add(inventories.getActAvaWei()
					+ inventories.getMeasurement().getMeasureUnit());
			row.add(inventories.getInventoryGroups().getBatchNumber());
			row.add(inventories.getInventoryLocation().getLabel());
			row.add(inventories.getSysUser().getRealname());
			row.add(inventories.getInventoryGroups().getSupplier()
					.getSuprName());
			rows.add(row);
		}

		data.setRows(rows);

		// 生成本地
		try {
			ExportExcelUtils.exportExcel(path, data);
			resultVo.setErrCode(0);
			resultVo.setErrMsg("生成excel成功");
			resultVo.setResultData(excelUrl + fileName);
			resultVo.setResultData1(path);
			return resultVo;
		} catch (Exception e) {

			e.printStackTrace();
			resultVo.setErrCode(1);
			resultVo.setErrMsg("生成excel失败");
			return resultVo;
		}

	}

}
