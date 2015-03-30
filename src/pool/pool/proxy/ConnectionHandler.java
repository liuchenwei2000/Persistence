/**
 * 
 */
package pool.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * 动态代理模式处理Connection的池化
 * 
 * @author 刘晨伟
 * 
 * 创建日期：2014年7月21日
 */
public class ConnectionHandler implements InvocationHandler {

	private ConnectionPool pool;
	private Connection realConnection;
	
	public ConnectionHandler(ConnectionPool pool) {
		this.pool = pool;
	}
	
	/**
	 * 返回参数连接对象的一个代理对象
	 */
	public Connection proxy(Connection realConnection) {
		this.realConnection = realConnection;
		return (Connection) Proxy.newProxyInstance(this.getClass()
				.getClassLoader(), new Class[] { Connection.class }, this);
	}

	/**
	 * 在这个方法里，代理对象会对close方法进行特殊处理
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object obj = null;
		if ("close".equals(method.getName())) {
			// 将数据库连接放回连接池而不是直接关闭
			pool.releaseConnection(realConnection);
		} else {
			obj = method.invoke(realConnection, args);
		}
		return obj;
	}
}