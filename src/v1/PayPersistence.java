/**
 * 
 */
package v1;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import util.DBHelper;

/**
 * 传统方式实现 客户付款业务
 * <p>
 * 业务逻辑和数据访问逻辑全部在一个类中完成。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class PayPersistence {

	/**
	 * 客户付款操作
	 */
	public void pay(String customer_id, double amount) throws Exception {
		Connection connection = DBHelper.getConnection();
		Statement stmt = connection.createStatement();
		// 这里是数据访问逻辑
		String sql = "select amount from customers where id='" + customer_id + "'";
		ResultSet resultSet = stmt.executeQuery(sql);
		if(resultSet.next()) {
			// 这里是业务逻辑
			double amount_left = resultSet.getDouble(1);
			if(amount_left < amount){
				throw new RuntimeException("余额已不足，无法完成支付。");
			}
			// 这里是数据访问逻辑
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
