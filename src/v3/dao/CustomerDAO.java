/**
 * 
 */
package v3.dao;

/**
 * CustomerDAO接口
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public interface CustomerDAO {

	/**
	 * 查询客户
	 */
	public Customer getCustomer(String id);
	
	/**
	 * 保存客户
	 */
	public void save(Customer customer);
}
