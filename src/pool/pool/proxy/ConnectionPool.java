/**
 * 
 */
package pool.proxy;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import pool.MockConnection;

/**
 * 数据库连接池示例
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class ConnectionPool {
	
	/** 池中最大连接数 */
	private static final int MAX_SIZE = 10;

	private List<ConnectionBox> pool = new ArrayList<ConnectionBox>(MAX_SIZE);
	
	/**
	 * 从池中获取一个数据库连接
	 */
	public synchronized Connection getConnection(){
		Connection connection = null;
		if (pool.size() < MAX_SIZE) {
			connection = createConnection();
			pool.add(new ConnectionBox(connection, true));
		} else {
			connection = findFreeConnection();
		}
		// 返回真正数据库连接的一个代理对象
		return new ConnectionHandler(this).proxy(connection);
	}
	
	/**
	 * 创建真正的物理连接
	 */
	private Connection createConnection(){
		// 通过DriverManager或者其他方式创建一个真正的数据库连接
		Connection conn = new MockConnection();
		return conn;
	}
	
	private Connection findFreeConnection() {
		for (ConnectionBox connBox : pool) {
			if(!connBox.isInUse()) {
				connBox.setInUse(true);
				return connBox.getConnection();
			}
		}
		throw new RuntimeException("当前没有可用连接.");
	}
	
	/**
	 * 将数据库连接放回池中
	 */
	public synchronized void releaseConnection(Connection con) {
		for (ConnectionBox connBox : pool) {
			if(con == connBox.getConnection()) {
				connBox.setInUse(false);
			}
		}
	}
	
	private static class ConnectionBox {
		
		private Connection connection;
		private boolean isInUse;// 是否在使用
		
		public ConnectionBox(Connection connection, boolean isInUse) {
			this.connection = connection;
			this.isInUse = isInUse;
		}

		public boolean isInUse() {
			return isInUse;
		}

		public void setInUse(boolean isInUse) {
			this.isInUse = isInUse;
		}

		public Connection getConnection() {
			return connection;
		}
	}
}
