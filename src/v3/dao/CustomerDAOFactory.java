/**
 * 
 */
package v3.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * CustomerDAO工厂
 * <p>
 * 具体实现类可在配置文件中加以配置，之后由DAOFactory读取。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class CustomerDAOFactory {

	// 这里的配置信息也可以放在外部的xml中
	private static Map<String,CustomerDAO> dao_map;
	
	static {
		dao_map = new HashMap<String, CustomerDAO>();
		dao_map.put("oracle", new OracleCustomerDAO());
		dao_map.put("mssql", new MSSQLCustomerDAO());
	}
	
	/**
	 * 创建CustomerDAO
	 */
	public static CustomerDAO create(){
		String dbType = getDBType();
		return dao_map.get(dbType.toLowerCase());
	}

	private static String getDBType() {
		// 这里模拟返回运行环境使用的数据库信息
		return "oracle";
	}
}
