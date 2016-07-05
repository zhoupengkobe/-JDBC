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
 * ����������Ļ����÷�
 * @author ko
 *
 */
public class Demo5 {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
				
				conn.setAutoCommit(false);//��Ϊ�ֶ��ύ
				long start = System.currentTimeMillis();
				stmt = conn.createStatement();
				
				for (int i = 0; i < 20000; i++) {
					stmt.addBatch("insert into t_user (username,pwd,regTime) values ('gao"+i+"',6666,now())");
				}
				stmt.executeBatch();
				conn.commit();//�ύ����
				long end = System.currentTimeMillis();
				System.out.println("����20000�����ݣ���ʱ�����룩"+(end-start));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (stmt!=null) {
				try {
					stmt.close();
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
