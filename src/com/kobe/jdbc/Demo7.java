package com.kobe.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Random;

import javax.swing.JApplet;

/**
 * ����ʱ�䴦��(java.sql.Date,Time,Timestamp)
 * @author ko
 *
 */
public class Demo7 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
				for (int i = 0; i < 1000; i++) {
					
					ps1 = conn.prepareStatement("insert into t_user (username,pwd,regTime,lastLoginTime) values (?,?,?,?)");
					ps1.setObject(1, "����");
					ps1.setObject(2, "123456");
					int rand = 100000000+new Random().nextInt(1000000000);
					Date date = new Date(System.currentTimeMillis()-rand);
					Timestamp stamp = new Timestamp(System.currentTimeMillis()-rand);//�����Ҫ����ָ�����ڣ�����ʹ��Calendar,DateFormat
					ps1.setDate(3, date);
					ps1.setTimestamp(4, stamp);
					ps1.execute();
					System.out.println("����һ���û� ");
					
				}
				
				
				
				
				
			
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//��ѭ��resultset--->statment--->connection�����Ĺر�˳��һ��Ҫ��trycatch�飬�ֿ�д��
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
