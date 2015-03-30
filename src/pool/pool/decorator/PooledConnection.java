/**
 * 
 */
package pool.decorator;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * �ɷ������ӳص�Connection
 * <p>
 * ����װ����ʵConnection���󣬵��ر����ӵ�ʱ�򣬲�����������close�����ǷŻس��С�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
 */
public class PooledConnection extends ConnectionDecorator {

	private ConnectionPool pool;

	public PooledConnection(ConnectionPool pool, Connection realConnection) {
		super(realConnection);
		this.pool = pool;
	}

	@Override
	public void close() throws SQLException {
		// �����ӷŻس��У�������ֱ�ӹر����ӡ�
		this.pool.releaseConnection(realConnection);
	}
}
