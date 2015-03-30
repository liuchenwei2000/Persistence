/**
 * 
 */
package util;

import java.sql.Connection;

/**
 * 数据库操作辅助类
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class DBHelper {

	/**
	 * 获取一个连接
	 */
	public static Connection getConnection() {
		// 从连接池获取Connection对象
		return null;
	}
	
	/**
	 * 释放一个连接
	 */
	public static void releaseConnection(Connection con){
		// 将Connection放回连接池
	}
}
