/**
 * 
 */
package v4.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * CustomerDAO����
 * <p>
 * ����ʵ������������ļ��м������ã�֮����DAOFactory��ȡ��
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
 */
public class CustomerDAOFactory {

	// �����������ϢҲ���Է����ⲿ��xml��
	private static Map<String,CustomerDAO> dao_map;
	
	static {
		dao_map = new HashMap<String, CustomerDAO>();
		dao_map.put("oracle", new OracleCustomerDAO());
		dao_map.put("mssql", new MSSQLCustomerDAO());
	}
	
	/**
	 * ����CustomerDAO
	 */
	public static CustomerDAO create(){
		String dbType = getDBType();
		return dao_map.get(dbType.toLowerCase());
	}

	private static String getDBType() {
		// ����ģ�ⷵ�����л���ʹ�õ����ݿ���Ϣ
		return "oracle";
	}
}
