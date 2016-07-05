package com.kobe.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Clob;
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
 * ����CLOB
 * �ı�������ʹ��
 * ���������ַ������ļ����ݲ������ݿ��е�CLOB�ֶΣ���CLOB �ֶ�ֵȡ�����Ĳ�����
 * @author ko
 *
 */
public class Demo9 {
	
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reader r = null;
		try {
			//����������
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//				ps = conn.prepareStatement("insert into t_user (username,myInfo) values (?,?)");
//				ps.setString(1, "����");
//				ps.setClob(2, new FileReader(new File("d:/a.txt")));//���ı��ļ�����ֱ�����뵽���ݿ���
//				//�������е��ַ������뵽���ݿ��CLOB��
//				ps.setClob(2, new BufferedReader(new InputStreamReader(new ByteArrayInputStream("zhoupeng de a".getBytes()))));
				
				ps = conn.prepareStatement("select * from t_user where id=?");
				ps.setObject(1, 21012);
				rs = ps.executeQuery();
				while (rs.next()) {
					Clob c = rs.getClob("myInfo");
					r = c.getCharacterStream();
					int temp = 0;
					while ((temp=r.read())!=-1) {
						System.out.print((char)temp);
						
					}
					
				}
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if (r!=null) {
				try {
					r.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
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
