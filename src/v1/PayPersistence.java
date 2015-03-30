/**
 * 
 */
package v1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBHelper;

/**
 * ��ͳ��ʽʵ�� �ͻ�����ҵ��
 * <p>
 * ҵ���߼������ݷ����߼�ȫ����һ��������ɡ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
 */
public class PayPersistence {

	/**
	 * �ͻ��������
	 */
	public void pay(String customer_id, double amount) throws Exception {
		Connection connection = DBHelper.getConnection();
		Statement stmt = connection.createStatement();
		// ���������ݷ����߼�
		String sql = "select amount from customers where id='" + customer_id + "'";
		ResultSet resultSet = stmt.executeQuery(sql);
		if(resultSet.next()) {
			// ������ҵ���߼�
			double amount_left = resultSet.getDouble(1);
			if(amount_left < amount){
				throw new RuntimeException("����Ѳ��㣬�޷����֧����");
			}
			// ���������ݷ����߼�
			sql = "update customers set amount=amount-" + amount + " where id='" + customer_id + "'";
			Statement stmt2 = connection.createStatement();
			stmt2.executeUpdate(sql);
			stmt2.close();
		}
		resultSet.close();
		stmt.close();
		connection.close();
	}
}
