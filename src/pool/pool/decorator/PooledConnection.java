/**
 * 
 */
package pool.decorator;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 可放入连接池的Connection
 * <p>
 * 用来装饰真实Connection对象，当关闭连接的时候，并不是真正的close，而是放回池中。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class PooledConnection extends ConnectionDecorator {

	private ConnectionPool pool;

	public PooledConnection(ConnectionPool pool, Connection realConnection) {
		super(realConnection);
		this.pool = pool;
	}

	@Override
	public void close() throws SQLException {
		// 将连接放回池中，而不是直接关闭连接。
		this.pool.releaseConnection(realConnection);
	}
}
