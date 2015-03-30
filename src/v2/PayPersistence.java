/**
 * 
 */
package v2;

import v2.dao.Customer;
import v2.dao.CustomerDAO;

/**
 * 业务层
 * <p>
 * 通过DAO模式对各个数据对象进行封装，从而对业务层屏蔽了数据库访问的底层实现，
 * 业务层仅包含与本领域相关的逻辑对象和算法。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class PayPersistence {

	/**
	 * 客户付款
	 */
	public void pay(String customer_id, double amount) throws Exception {
		CustomerDAO dao = new CustomerDAO();
		// 直接面向对象并操作对象，而无需和非面向对象的SQL、ResultSet打交道
		Customer customer = dao.getCustomer(customer_id);
		// 本领域相关的业务逻辑
		if(customer.getAmount() < amount){
			throw new RuntimeException("余额已不足，无法完成支付。");
		}
		customer.setAmount(customer.getAmount() - amount);
		dao.save(customer);
	}
}
