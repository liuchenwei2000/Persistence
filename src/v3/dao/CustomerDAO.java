/**
 * 
 */
package v3.dao;

/**
 * CustomerDAO�ӿ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
 */
public interface CustomerDAO {

	/**
	 * ��ѯ�ͻ�
	 */
	public Customer getCustomer(String id);
	
	/**
	 * ����ͻ�
	 */
	public void save(Customer customer);
}
