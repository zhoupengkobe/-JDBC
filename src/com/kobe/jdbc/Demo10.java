package com.kobe.jdbc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.sql.Blob;
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
 * 测试BLOB
 * 二进制大对象的使用
 * @author ko
 *
 */
public class Demo10 {
	
	
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			//加载驱动类
			Class.forName("com.mysql.jdbc.Driver");
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testjdbc","root","123456");
//				ps = conn.prepareStatement("insert into t_user (username,headImg) values (?,?)");
//				ps.setString(1, "高琪");
//				ps.setBlob(2, new FileInputStream("d:/icon.jpg"));
//				ps.execute();
				
				
				
				ps = conn.prepareStatement("select * from t_user where id=?");
				ps.setObject(1, 21014);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					Blob b = rs.getBlob("headImg");
					is = b.getBinaryStream();
					os = new FileOutputStream("d:/a.jpg");
					int temp = 0;
					while ((temp=is.read())!=-1) {
						os.write(temp);
						
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
			if (is!=null) {
				try {
					is.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			//遵循：resultset--->statment--->connection这样的关闭顺序！一定要将trycatch块，分开写。
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
