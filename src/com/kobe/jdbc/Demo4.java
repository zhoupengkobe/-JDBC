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
 * ����ResultSet������Ļ����÷�
 * @author ko
 *
 */
public class Demo4 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
				String sql ="select id,username,pwd from t_user where id>?";//��ռλ��
				ps = conn.prepareStatement(sql);
				ps.setObject(1, 2);//��id����2�ļ�¼��ȡ����
				rs = ps.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getObject(1)+"---"+rs.getObject(2)+"---"+rs.getObject(3));
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//��ѭ��resultset--->statment--->connection�����Ĺر�˳��һ��Ҫ��trycatch�飬�ֿ�д��
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (ps!=null) {
				try {
					ps.close();
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
