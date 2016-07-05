package com.kobe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ���Ը����ݿ⽨������
 * @author ko
 *
 */
public class Demo01 {
	public static void main(String[] args) {
		Connection conn = null;
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
			long start = System.currentTimeMillis();
			//��������
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306","root","123456");
				long end = System.currentTimeMillis();
				System.out.println(conn);
				System.out.println("�������ӣ���ʱ"+(end-start)+"ms");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
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
