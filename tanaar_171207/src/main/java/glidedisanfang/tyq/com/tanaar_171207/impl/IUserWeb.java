package glidedisanfang.tyq.com.tanaar_171207.impl;

import android.os.Handler;

import java.util.List;

import glidedisanfang.tyq.com.tanaar_171207.entity_warehouse.User;
import glidedisanfang.tyq.com.tanaar_171207.listener.DataListener;

public interface IUserWeb {
	

	
	/**
	 * 登陆
	 * @param phoneNum
	 * @param password
	 * @param dataListener
	 */
	public void user_login(String phoneNum, String password,
					  final DataListener<User> dataListener);
	

	/**
	 * 修改密码
	 * @param
	 * @param oldPassword
	 * @param newPassword
	 * @param
	 */
	public void user_updatepwd( String oldPassword, String newPassword, final Handler handler);



	/**
	 * 添加评论
	 * @param
	 * @param
	 * @param dataListener
	 */
	public void user_logout( final DataListener dataListener);
}
