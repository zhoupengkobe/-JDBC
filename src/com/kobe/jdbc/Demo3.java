package com.kobe.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;

/**
 * 测试Statement接口的用法，测试PreparedStatement的基本用法
 * @author ko
 *
 */
public class Demo3 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
				String sql ="insert into t_user (username,pwd,regTime) values (?,?,?)";//？占位符
				ps = conn.prepareStatement(sql);
				ps.setObject(1, "高琪");
				ps.setObject(2, "12345");
				ps.setObject(3, new Date(System.currentTimeMillis()));
				
				
				System.out.println("插入一行记录");
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {	
			if (ps!=null) {
				try {
					ps.close();
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
