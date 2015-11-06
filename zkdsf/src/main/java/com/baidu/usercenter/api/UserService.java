/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.baidu.usercenter.api;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public interface UserService {
  public static final org.apache.avro.Protocol PROTOCOL = org.apache.avro.Protocol.parse("{\"protocol\":\"UserService\",\"namespace\":\"com.baidu.usercenter.api\",\"types\":[{\"type\":\"record\",\"name\":\"UserInfo\",\"fields\":[{\"name\":\"userId\",\"type\":\"long\"},{\"name\":\"username\",\"type\":\"string\"},{\"name\":\"email\",\"type\":\"string\"},{\"name\":\"mobile\",\"type\":\"long\"},{\"name\":\"depid\",\"type\":\"long\"}]},{\"type\":\"error\",\"name\":\"UsernameException\",\"fields\":[]},{\"type\":\"error\",\"name\":\"PasswordException\",\"fields\":[]},{\"type\":\"error\",\"name\":\"UsernameExistsException\",\"fields\":[]},{\"type\":\"error\",\"name\":\"OldPasswordException\",\"fields\":[]},{\"type\":\"record\",\"name\":\"Session\",\"doc\":\"**********************************会话模块*******************************************\",\"fields\":[{\"name\":\"sid\",\"type\":\"string\"},{\"name\":\"encryptKey\",\"type\":\"string\"}]}],\"messages\":{\"login\":{\"doc\":\"* 登录\\n\\t * @param username\\t\\t\\t\\tusername\\n\\t * @param password\\t\\t\\t\\tpassword\\n\\t * @return\",\"request\":[{\"name\":\"username\",\"type\":\"string\"},{\"name\":\"password\",\"type\":\"string\"}],\"response\":\"long\",\"errors\":[\"UsernameException\",\"PasswordException\"]},\"createUser\":{\"doc\":\"* 注册\\n\\t * @param username\\t\\t\\t\\tusername\\n\\t * @param password\\t\\t\\t\\tpassword\\n\\t * @param email\\t\\t\\t\\t\\temail\\n\\t * @param mobile\\t\\t\\t\\tmobile\\n\\t * @return\",\"request\":[{\"name\":\"username\",\"type\":\"string\"},{\"name\":\"password\",\"type\":\"string\"},{\"name\":\"email\",\"type\":\"string\"},{\"name\":\"mobile\",\"type\":\"long\"},{\"name\":\"depid\",\"type\":\"long\"}],\"response\":\"long\",\"errors\":[\"UsernameExistsException\"]},\"changePassword\":{\"doc\":\"* 修改密码\\n\\t * @param userId\\t\\t\\t\\t\\n\\t * @param oldpwd\\t\\t\\t\\t\\n\\t * @param newpwd\",\"request\":[{\"name\":\"userId\",\"type\":\"long\"},{\"name\":\"oldpwd\",\"type\":\"string\"},{\"name\":\"newpwd\",\"type\":\"string\"}],\"response\":\"null\",\"errors\":[\"OldPasswordException\"]},\"forgetPassword\":{\"doc\":\"* 忘记密码\\n\\t * @param username\\t\\t\\t\\t\\n\\t * @param password\\t\\t\\t\\t\\n\\t * @param checkcode\",\"request\":[{\"name\":\"username\",\"type\":\"string\"},{\"name\":\"password\",\"type\":\"string\"}],\"response\":\"null\"},\"updateUserInfo\":{\"doc\":\"* 修改用户信息\\n\\t * @param userId\\t\\t\\t\\t\\n\\t * @param email\\t\\t\\t\\t\\n\\t * @param mobile\",\"request\":[{\"name\":\"userId\",\"type\":\"long\"},{\"name\":\"email\",\"type\":\"string\"},{\"name\":\"mobile\",\"type\":\"long\"},{\"name\":\"depid\",\"type\":\"long\"}],\"response\":\"null\"},\"getUserById\":{\"doc\":\"* 读取用户信息\\n\\t * @param userId\",\"request\":[{\"name\":\"userId\",\"type\":\"long\"}],\"response\":[\"null\",\"UserInfo\"]},\"getUserByUsername\":{\"doc\":\"* 读取用户信息\\n\\t * @param username\",\"request\":[{\"name\":\"username\",\"type\":\"string\"}],\"response\":[\"null\",\"UserInfo\"]},\"getUserList\":{\"doc\":\"*读取用户列表\\n\\t*@param\",\"request\":[],\"response\":{\"type\":\"array\",\"items\":\"UserInfo\"}},\"createSession\":{\"doc\":\"* 创建会话\\n\\t * @param userId\\n\\t * @return\\tsid\",\"request\":[{\"name\":\"userId\",\"type\":\"long\"}],\"response\":\"Session\"},\"removeSession\":{\"doc\":\"* 删除会话\\n\\t * @param sid\\t\\t\\t\\t\\t\\t\\tsid\\n\\t * @return\",\"request\":[{\"name\":\"sid\",\"type\":\"string\"}],\"response\":\"null\"},\"getEncryptKey\":{\"doc\":\"* 获取会话私钥\\n\\t * @param sid\\t\\t\\t\\t\\t\\t\\tsid\\n\\t * @return\",\"request\":[{\"name\":\"sid\",\"type\":\"string\"}],\"response\":[\"string\",\"null\"]},\"getUserId\":{\"doc\":\"* 获取会话用户ID\\n\\t * @param sid\\t\\t\\t\\t\\t\\t\\tsid\\n\\t * @return\\t\\t\\t\\t\\t\\t\\t\\t不存在返回0\",\"request\":[{\"name\":\"sid\",\"type\":\"string\"}],\"response\":\"long\"}}}");
  /** * 登录
	 * @param username				username
	 * @param password				password
	 * @return */
  long login(java.lang.CharSequence username, java.lang.CharSequence password) throws org.apache.avro.AvroRemoteException, com.baidu.usercenter.api.UsernameException, com.baidu.usercenter.api.PasswordException;
  /** * 注册
	 * @param username				username
	 * @param password				password
	 * @param email					email
	 * @param mobile				mobile
	 * @return */
  long createUser(java.lang.CharSequence username, java.lang.CharSequence password, java.lang.CharSequence email, long mobile, long depid) throws org.apache.avro.AvroRemoteException, com.baidu.usercenter.api.UsernameExistsException;
  /** * 修改密码
	 * @param userId				
	 * @param oldpwd				
	 * @param newpwd */
  java.lang.Void changePassword(long userId, java.lang.CharSequence oldpwd, java.lang.CharSequence newpwd) throws org.apache.avro.AvroRemoteException, com.baidu.usercenter.api.OldPasswordException;
  /** * 忘记密码
	 * @param username				
	 * @param password				
	 * @param checkcode */
  java.lang.Void forgetPassword(java.lang.CharSequence username, java.lang.CharSequence password) throws org.apache.avro.AvroRemoteException;
  /** * 修改用户信息
	 * @param userId				
	 * @param email				
	 * @param mobile */
  java.lang.Void updateUserInfo(long userId, java.lang.CharSequence email, long mobile, long depid) throws org.apache.avro.AvroRemoteException;
  /** * 读取用户信息
	 * @param userId */
  com.baidu.usercenter.api.UserInfo getUserById(long userId) throws org.apache.avro.AvroRemoteException;
  /** * 读取用户信息
	 * @param username */
  com.baidu.usercenter.api.UserInfo getUserByUsername(java.lang.CharSequence username) throws org.apache.avro.AvroRemoteException;
  /** *读取用户列表
	*@param */
  java.util.List<com.baidu.usercenter.api.UserInfo> getUserList() throws org.apache.avro.AvroRemoteException;
  /** * 创建会话
	 * @param userId
	 * @return	sid */
  com.baidu.usercenter.api.Session createSession(long userId) throws org.apache.avro.AvroRemoteException;
  /** * 删除会话
	 * @param sid							sid
	 * @return */
  java.lang.Void removeSession(java.lang.CharSequence sid) throws org.apache.avro.AvroRemoteException;
  /** * 获取会话私钥
	 * @param sid							sid
	 * @return */
  java.lang.CharSequence getEncryptKey(java.lang.CharSequence sid) throws org.apache.avro.AvroRemoteException;
  /** * 获取会话用户ID
	 * @param sid							sid
	 * @return								不存在返回0 */
  long getUserId(java.lang.CharSequence sid) throws org.apache.avro.AvroRemoteException;

  @SuppressWarnings("all")
  public interface Callback extends UserService {
    public static final org.apache.avro.Protocol PROTOCOL = com.baidu.usercenter.api.UserService.PROTOCOL;
    /** * 登录
	 * @param username				username
	 * @param password				password
	 * @return */
    void login(java.lang.CharSequence username, java.lang.CharSequence password, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    /** * 注册
	 * @param username				username
	 * @param password				password
	 * @param email					email
	 * @param mobile				mobile
	 * @return */
    void createUser(java.lang.CharSequence username, java.lang.CharSequence password, java.lang.CharSequence email, long mobile, long depid, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
    /** * 修改密码
	 * @param userId				
	 * @param oldpwd				
	 * @param newpwd */
    void changePassword(long userId, java.lang.CharSequence oldpwd, java.lang.CharSequence newpwd, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    /** * 忘记密码
	 * @param username				
	 * @param password				
	 * @param checkcode */
    void forgetPassword(java.lang.CharSequence username, java.lang.CharSequence password, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    /** * 修改用户信息
	 * @param userId				
	 * @param email				
	 * @param mobile */
    void updateUserInfo(long userId, java.lang.CharSequence email, long mobile, long depid, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    /** * 读取用户信息
	 * @param userId */
    void getUserById(long userId, org.apache.avro.ipc.Callback<com.baidu.usercenter.api.UserInfo> callback) throws java.io.IOException;
    /** * 读取用户信息
	 * @param username */
    void getUserByUsername(java.lang.CharSequence username, org.apache.avro.ipc.Callback<com.baidu.usercenter.api.UserInfo> callback) throws java.io.IOException;
    /** *读取用户列表
	*@param */
    void getUserList(org.apache.avro.ipc.Callback<java.util.List<com.baidu.usercenter.api.UserInfo>> callback) throws java.io.IOException;
    /** * 创建会话
	 * @param userId
	 * @return	sid */
    void createSession(long userId, org.apache.avro.ipc.Callback<com.baidu.usercenter.api.Session> callback) throws java.io.IOException;
    /** * 删除会话
	 * @param sid							sid
	 * @return */
    void removeSession(java.lang.CharSequence sid, org.apache.avro.ipc.Callback<java.lang.Void> callback) throws java.io.IOException;
    /** * 获取会话私钥
	 * @param sid							sid
	 * @return */
    void getEncryptKey(java.lang.CharSequence sid, org.apache.avro.ipc.Callback<java.lang.CharSequence> callback) throws java.io.IOException;
    /** * 获取会话用户ID
	 * @param sid							sid
	 * @return								不存在返回0 */
    void getUserId(java.lang.CharSequence sid, org.apache.avro.ipc.Callback<java.lang.Long> callback) throws java.io.IOException;
  }
}