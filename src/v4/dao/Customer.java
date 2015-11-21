/**
 * 
 */
package v4.dao;

/**
 * Domain Object示例
 * <p>
 * 引入持久层之后，这个类可以由工具自动生成。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月23日
 */
public class Customer {

	private String id;
	private double amount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
