/**
 * 
 */
package v2.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBHelper;

/**
 * Data Accessor模式
 * <p>
 * 数据库访问层实现被隐藏到了Data Accessor中，它将数据访问的实现机制加以封装，与数据的使用代码相分离。
 * 从外部来看，Data Accessor提供了黑盒式的数据存取接口。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class CustomerDAO {

	/**
	 * 查询客户
	 */
	public Customer getCustomer(String id) {
		String sql = "select id, amount from customers where id='" + id + "'";
		Connection connection = null;
		Statement stmt = null;
		ResultSet resultSet = null;
		try {
			connection = DBHelper.getConnection();
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
			if (resultSet.next()) {
				Customer customer = new Customer();
				customer.setId(resultSet.getString(1));
				customer.setAmount(resultSet.getDouble(2));
				return customer;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (resultSet != null) {
					try {
						resultSet.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				DBHelper.releaseConnection(connection);
			}
		}
		return null;
	}
	
	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
		String sql = "update customers set amount=" + customer.getAmount()
				+ " where id='" + customer.getId() + "'";
		Connection connection = null;
		Statement stmt = null;
		try {
			connection = DBHelper.getConnection();
			stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				DBHelper.releaseConnection(connection);
			}
		}
	}
}
