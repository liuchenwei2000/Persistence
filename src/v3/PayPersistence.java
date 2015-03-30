/**
 * 
 */
package v3;

import v3.dao.Customer;
import v3.dao.CustomerDAO;
import v3.dao.CustomerDAOFactory;

/**
 * 业务层
 * <p>
 * 业务层通过接口调用底层实现，具体的DAO实现类不会出现在业务代码中。
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
		CustomerDAO dao = CustomerDAOFactory.create();
		Customer customer = dao.getCustomer(customer_id);
		if(customer.getAmount() < amount){
			throw new RuntimeException("余额已不足，无法完成支付。");
		}
		customer.setAmount(customer.getAmount() - amount);
		dao.save(customer);
	}
}
