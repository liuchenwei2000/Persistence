/**
 * 
 */
package pool.decorator;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接客户端类
 * <p>
 * 获取数据库连接后，调用者只需要按照JDBC Connection的标准用法进行调用即可，从而实现了数据库连接池的透明化。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionPool pool = new ConnectionPool();
		Connection con = null;
		for (int i = 0; i < 10; i++) {
			con = pool.getConnection();
		}
		try {
			pool.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			con.close();
			pool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
