package com.kobe.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JApplet;

/**
 * 测试事务的基本概念和用法
 * @author ko
 *
 */
public class Demo6 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
				conn.setAutoCommit(false);//设置手动提交事务
				ps1 = conn.prepareStatement("insert into t_user (username,pwd) values (?,?)");
				ps1.setObject(1, "高琪");
				ps1.setObject(2, "123456");
				ps1.execute();
				System.out.println("插入一个用户 ");
				
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				ps2 = conn.prepareStatement("insert into t_user (username,pwd) values (?,?)");
				ps2.setObject(1, "马士兵");
				ps2.setObject(2, "123456");
				ps2.execute();
				System.out.println("插入第二个用户 ");
				
				
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//遵循：resultset--->statment--->connection这样的关闭顺序！一定要将trycatch块，分开写。
			if (ps2!=null) {
				try {
					ps2.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (ps1!=null) {
				try {
					ps1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
