/**
 * 
 */
package pool.decorator;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * ���ݿ����ӿͻ�����
 * <p>
 * ��ȡ���ݿ����Ӻ󣬵�����ֻ��Ҫ����JDBC Connection�ı�׼�÷����е��ü��ɣ��Ӷ�ʵ�������ݿ����ӳص�͸������
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
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
