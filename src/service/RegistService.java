package service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import util.DataBaseUtils;
import bean.User;

	//用户登陆服务类

public class RegistService {
	
	public boolean setUser(String username, String password,
						   String sex, String address, String telephone) {
		String id = UUID.randomUUID() + "";
		String createTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		DataBaseUtils.update("INSERT INTO t_user(id,username,password,male,create_time,is_delete,address,telephone) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)",id,username,password,sex,createTime,1,address,telephone);
		return true;
	}

}
