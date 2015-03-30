/**
 * 
 */
package v2.dao;

/**
 * Active Domain Object模式
 * <p>
 * Domain Object提供了对所面向领域内对象的封装。
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
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
