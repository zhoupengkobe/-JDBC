package com.kobe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试执行SQL语句以及SQL注入问题
 * @author ko
 *
 */
public class Demo2 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;		
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
				stmt = conn.createStatement();
//				String name ="赵六";
//				String sql ="INSERT into t_user (username,pwd,regTime) VALUES ('"+name+"',55555,NOW())";
//				stmt.execute(sql);
				
				//测试SQl注入
				String id = "5 or 1=1";
				String sql = "delete from t_user where id="+id;
				stmt.execute(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {	
			if (stmt!=null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

}
