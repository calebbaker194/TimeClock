package sqlengine;

import java.io.Serializable;

public class PostGresServer implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1440486958425702779L;
	private String host;
	private String nickname;
	private String db;
	private int port;
	private String username;
	private String password;
	/* (Java-doc)
	 * @param host - host or ipaddress of postgres sever
	 * @param db - name of the database
	 * @param port - port for the database
	 * @param username - the database username must have access to create tables
	 * @param password for other username
	 */
	public PostGresServer(String host,String db,int port,String username,String passwd,String nickname) 
	{
		setHost(host);
		setDb(db);
		setPort(port);
		setUsername(username);
		setPassword(passwd);
		setNickname(nickname);
	}
	public String toString()
	{
		return host+db+port+username+password;
	}
	public int hashCode()
	{
		String hc = host+port+db+username+password;
		return hc.hashCode();
	}
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
