/**
 * 
 */
package v3;

import v3.dao.Customer;
import v3.dao.CustomerDAO;
import v3.dao.CustomerDAOFactory;

/**
 * ҵ���
 * <p>
 * ҵ���ͨ���ӿڵ��õײ�ʵ�֣������DAOʵ���಻�������ҵ������С�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
 */
public class PayPersistence {

	/**
	 * �ͻ�����
	 */
	public void pay(String customer_id, double amount) throws Exception {
		CustomerDAO dao = CustomerDAOFactory.create();
		Customer customer = dao.getCustomer(customer_id);
		if(customer.getAmount() < amount){
			throw new RuntimeException("����Ѳ��㣬�޷����֧����");
		}
		customer.setAmount(customer.getAmount() - amount);
		dao.save(customer);
	}
}
