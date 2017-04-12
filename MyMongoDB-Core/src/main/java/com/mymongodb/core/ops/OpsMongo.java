package com.mymongodb.core.ops;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

public class OpsMongo {
	
	public static final int ASC = 1;

	public static final int DESC = -1;

	private static MongoClient mongoClient;
	
	
	static {
		
		ResourceBundle rb = ResourceBundle.getBundle("config");
		String hostandport = rb.getString("mongos.host");

		List<ServerAddress> sdList = new ArrayList<ServerAddress>();

		for (String item : hostandport.split(",")) {
			String host = item.split(":")[0].trim();
			int port = Integer.valueOf(item.split(":")[1].trim());
			sdList.add(new ServerAddress(host, port));
		}
		MongoClientOptions.Builder options = new MongoClientOptions.Builder();
		options.connectionsPerHost(300);// 连接池设置为300个连接,默认为100
		options.connectTimeout(15000);// 连接超时，推荐>3000毫秒
		options.maxWaitTime(5000); //
		options.socketTimeout(0);// 套接字超时时间，0无限制
		options.threadsAllowedToBlockForConnectionMultiplier(5000);// 线程队列数，如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。
		options.build();
		mongoClient = new MongoClient(sdList, options.build());

	}
	
	
	public static void main(String[] args) {
		
		System.out.println(getDB("wishful_admin"));
		
	}
	
	
	/**
	 * 获取DB实例 - 指定DB
	 *
	 * @param dbName
	 * @return
	 */
	public static MongoDatabase getDB(String dbName) {
			return mongoClient.getDatabase(dbName);
	}
	
	public static void testConn(){
		
		
		
		
	}
	
	
	
}
