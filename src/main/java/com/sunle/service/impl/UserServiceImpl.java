package com.sunle.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunle.entity.User;
import com.sunle.entity.UserAndRoleIdList;
import com.sunle.mapper.UserMapper;
import com.sunle.service.UserService;
import com.sunle.utils.WebConstants;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public String pushUserList(String code1, List<User> userList) {
		try {
			Integer userId = null;
			User user = userList.get(0);
			User existUser = this.getUserByCode1(code1);
			if (null == existUser) {
				userMapper.addUser(user);
				User users = userMapper.getUserByCode1(code1);
				if(null !=users){
					userId = users.getId();
				}
			} else {
				userId = existUser.getId();
				this.updateUser(user);
			}
			this.delteUserRoleByCode1(code1);
			List<String> roleCodeList = new LinkedList<>();
			for(User u :userList ) {
				if(StringUtils.isEmpty(u.getRoleCode())) {
					continue;
				}
				roleCodeList.add(u.getRoleCode());
			}
			if (CollectionUtils.isNotEmpty(roleCodeList)) {
				List<UserAndRoleIdList> userAndRoleIdList = this.getRoleIdListByRoleCodeList(roleCodeList);
				for (UserAndRoleIdList item : userAndRoleIdList) {
					item.setUserId(userId);
				}
				this.addUserRole(userAndRoleIdList);
			}

		} catch (Exception e) {
			
			logger.error("funding insert user error code:{}", code1);
			throw new RuntimeException("funding insert user data " + code1);
		}

		return WebConstants.success;
	}

	@Override
	public User getUserByCode1(String code1) {
		return userMapper.getUserByCode1(code1);
	}

	@Override
	public int addUser(User user) {
		userMapper.addUser(user);
		return user.getId();
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);
	}

	@Override
	public int addUserRole(List<UserAndRoleIdList> userAndRoleIdList) {
		return userMapper.addUserRole(userAndRoleIdList);
	}

	@Override
	public List<UserAndRoleIdList> getRoleIdListByRoleCodeList(List<String> UserList) {
		return userMapper.getRoleIdListByRoleCodeList(UserList);
	}

	@Override
	public void delteUserRoleByCode1(String code1) {
		userMapper.delteUserRoleByCode1(code1);
	}

}
