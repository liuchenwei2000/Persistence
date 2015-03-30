/**
 * 
 */
package v4.dao;

/**
 * Oracle数据库使用的CustomerDAO
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class OracleCustomerDAO implements CustomerDAO {

	/**
	 * 查询客户
	 */
	public Customer getCustomer(String id) {
		// 引入持久层框架之后，OR映射以及与数据库通信的JDBC代码都可以由其来完成
		return null;
	}
	
	/**
	 * 保存客户
	 */
	public void save(Customer customer) {
	}
}
