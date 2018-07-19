package com.labwinner.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.labwinner.dao.SysUserDao;
import com.labwinner.domain.SysUser;
import com.labwinner.service.SysUserService;

/**
 * @Description 用户Service
 * @author liuhq
 * @version V1.0
 * @date 2017年5月18日 上午10:52:00
 * 
 * @Company 西安博文同创信息技术有限公司
 * @Copyright Copyright (c) 2017, All rights reserved.
 */
@Service
public class SysUserServiceImpl implements SysUserService {
	private static final Logger log = LoggerFactory
			.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserDao sysUserDao;

	/**
	 * {@inheritDoc}
	 */
	public List<SysUser> getAll() {
		log.debug("finding all SysUser instances");
		try {
			return sysUserDao.getAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SysUser> getAllPageable(String filter) {
		log.debug("finding all SysUser instances");
		try {
			return sysUserDao.getAllPageable(filter);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public SysUser getById(Long id) {
		log.debug("getting SysUser instance with id: " + id);
		try {

			SysUser sysUser = (SysUser) sysUserDao.getById(id);
			return sysUser;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer save(SysUser sysUser) {
		log.debug("saving SysUser instance");
		try {
			sysUserDao.save(sysUser);
			log.debug("save successful");
			return sysUser.getUserId();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(SysUser sysUser) {
		log.debug("saving SysUser instance");
		try {
			sysUserDao.update(sysUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Long id) {
		log.debug("deleting SysUser instance");
		try {
			sysUserDao.delete(id);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SysUser> getByUsername(String username) {
		log.debug("finding SysUser instance List by username");
		if (username == null || "".equals(username)) {
			username = getPrincipal();
		}
		return sysUserDao.getByUsername(username);
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SysUser> getByUsernameAndPassword(String username,
			String password) {
		log.debug("finding SysUser instance List by username and password");
		return sysUserDao.getByUsernameAndPassword(username, password);
	}

	@Override
	public List<SysUser> getContacter() {
		log.debug("finding all SysUser instances");
		try {
			return sysUserDao.getContacter();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getProList() {
		log.debug("finding all SysUser instances");
		try {
			return sysUserDao.getProList();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getUser(Integer proId, Integer roleId) {
		log.debug("finding all SysUser instances");
		try {
			return sysUserDao.getUser(proId,roleId);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getProRoleAll() {
		log.debug("finding all SysUser instances");
		try {
			return sysUserDao.getProRoleAll();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	@Override
	public void updatePassWord(SysUser sysUser) {
		log.debug("saving SysUser instance");
		try {
			sysUserDao.updatePassWord(sysUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateState(SysUser sysUser) {
		log.debug("saving SysUser instance");
		try {
			sysUserDao.updateState(sysUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getUserName() {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getUserName();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		
	
	}

	@Override
	public void saveNoImage(SysUser sysUser) {
		log.debug("saving SysUser instance");
		try {
			sysUserDao.saveNoImage(sysUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updatePersonal(SysUser sysUser) {
		log.debug("updateing SysUser instance");
		try {
			sysUserDao.updatePersonal(sysUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public void updateAppPersonal(SysUser sysUser) {
		log.debug("updateing SysUser instance");
		try {
			sysUserDao.updateAppPersonal(sysUser);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public SysUser getByExpertId(Integer expertId) {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getByExpertId(expertId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getAllPhone() {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getAllPhone();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getByNameOrPho(String username) {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getByNameOrPho(username);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer findByRoleId(Long roleId) {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.findByRoleId(roleId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public SysUser getByBasUser(Long userId, Integer agencyId) {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getByBasUser(userId,agencyId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public SysUser getExpertUser(Integer expertId) {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getExpertUser(expertId);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getTeamUsers() {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getTeamUsers();
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getByKeyword(String username) {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getByKeyword(username);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public List<SysUser> getUsers(String roleName) {
		log.debug("saving SysUser instance");
		try {
	      return sysUserDao.getUsers(roleName);
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	@Override
	public Integer getAllUser() {
		try {
		      return sysUserDao.getAllUser();
			} catch (RuntimeException re) {
				log.error("save failed", re);
				throw re;
			}
	}

	
}