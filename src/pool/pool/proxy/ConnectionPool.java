/**
 * 
 */
package pool.proxy;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import pool.MockConnection;

/**
 * ���ݿ����ӳ�ʾ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
 */
public class ConnectionPool {
	
	/** ������������� */
	private static final int MAX_SIZE = 10;

	private List<ConnectionBox> pool = new ArrayList<ConnectionBox>(MAX_SIZE);
	
	/**
	 * �ӳ��л�ȡһ�����ݿ�����
	 */
	public synchronized Connection getConnection(){
		Connection connection = null;
		if (pool.size() < MAX_SIZE) {
			connection = createConnection();
			pool.add(new ConnectionBox(connection, true));
		} else {
			connection = findFreeConnection();
		}
		// �����������ݿ����ӵ�һ���������
		return new ConnectionHandler(this).proxy(connection);
	}
	
	/**
	 * ������������������
	 */
	private Connection createConnection(){
		// ͨ��DriverManager����������ʽ����һ�����������ݿ�����
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
		throw new RuntimeException("��ǰû�п�������.");
	}
	
	/**
	 * �����ݿ����ӷŻس���
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
		private boolean isInUse;// �Ƿ���ʹ��
		
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
