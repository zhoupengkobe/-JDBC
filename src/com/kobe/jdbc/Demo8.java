package com.kobe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * ����ʱ�䴦��(java.sql.Date,Time,Timestamp)��ȡ��ָ��ʱ��ε�����
 * @author ko
 *
 */
public class Demo8 {
	
	/**
	 * ���ַ������������תΪlong���֣���ʽ��yyyy-MM-dd hh:mm:ss��
	 * @param args
	 */
	public static long str2Date(String dateStr){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return format.parse(dateStr).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//				ps = conn.prepareStatement("select * from t_user where regTime>? and regTime<?");
//				java.sql.Date start = new java.sql.Date(str2Date("2016-6-23 10:23:45"));
//				java.sql.Date end = new java.sql.Date(str2Date("2016-6-25 10:23:45"));
//				ps.setObject(1, start);
//				ps.setObject(2, end);
				
				ps = conn.prepareStatement("select * from t_user where lastLoginTime>? and lastLoginTime<?");
				Timestamp start = new Timestamp(str2Date("2016-6-23 10:23:45"));
				Timestamp end = new Timestamp(str2Date("2016-6-25 10:23:45"));
				ps.setObject(1, start);
				ps.setObject(2, end);
				
				rs = ps.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getInt("id")+"----"+rs.getString("username")+"----"+rs.getDate("lastLoginTime"));
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			//��ѭ��resultset--->statment--->connection�����Ĺر�˳��һ��Ҫ��trycatch�飬�ֿ�д��
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
