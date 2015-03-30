/**
 * 
 */
package pool.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * ��̬����ģʽ����Connection�ĳػ�
 * 
 * @author ����ΰ
 * 
 * �������ڣ�2014��7��21��
 */
public class ConnectionHandler implements InvocationHandler {

	private ConnectionPool pool;
	private Connection realConnection;
	
	public ConnectionHandler(ConnectionPool pool) {
		this.pool = pool;
	}
	
	/**
	 * ���ز������Ӷ����һ���������
	 */
	public Connection proxy(Connection realConnection) {
		this.realConnection = realConnection;
		return (Connection) Proxy.newProxyInstance(this.getClass()
				.getClassLoader(), new Class[] { Connection.class }, this);
	}

	/**
	 * �������������������close�����������⴦��
	 * 
	 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Object obj = null;
		if ("close".equals(method.getName())) {
			// �����ݿ����ӷŻ����ӳض�����ֱ�ӹر�
			pool.releaseConnection(realConnection);
		} else {
			obj = method.invoke(realConnection, args);
		}
		return obj;
	}
}