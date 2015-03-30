/**
 * 
 */
package v2;

import v2.dao.Customer;
import v2.dao.CustomerDAO;

/**
 * ҵ���
 * <p>
 * ͨ��DAOģʽ�Ը������ݶ�����з�װ���Ӷ���ҵ������������ݿ���ʵĵײ�ʵ�֣�
 * ҵ���������뱾������ص��߼�������㷨��
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
		CustomerDAO dao = new CustomerDAO();
		// ֱ��������󲢲������󣬶�����ͷ���������SQL��ResultSet�򽻵�
		Customer customer = dao.getCustomer(customer_id);
		// ��������ص�ҵ���߼�
		if(customer.getAmount() < amount){
			throw new RuntimeException("����Ѳ��㣬�޷����֧����");
		}
		customer.setAmount(customer.getAmount() - amount);
		dao.save(customer);
	}
}
