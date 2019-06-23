package bookmanagementStorage;
 
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
 

/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */



public class PutinStorage {//添加新用户的保存函数
	
	// 将用户名和密码存放在password表中
	public void userInfo(String userPassword1,String userPassword2,JTextField text){
		Connection conn;
		PreparedStatement preparedStatement;//简化Statement的拼写
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//			if(!conn.isClosed())
//				System.out.println("成功打开数据库");
			//从文本中获取账户名
			String userName = text.getText();
//			System.out.println(userName);
			// 按账号查找
//			String sql2 = "select * from password where id='" + userName + "'"; 
//			preparedStatement = conn.prepareStatement(sql2);
//			ResultSet result = preparedStatement.executeQuery();
						
//			if(!result.wasNull()){
////				new DataRepeat().show();
//				System.out.println(result.getString("id") + "\t" + result.getString("password"));
//			}
			
			
			//
			String sql = "insert into password values('" + userName + "','" + userPassword1 + "')";//将 userName和userPassword1写入password数据表中
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("未成功加载驱动");
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库");
			e1.printStackTrace();
		}
	}
	
	
	
	// 将可视化界面中表的数据储存到数据库
	public void saveData(JTable table){//
		int column = table.getColumnCount();//获取增添的数据 的长度 
		int row = table.getRowCount();//行
		
//		System.out.println("rows:" + row + "            coumns:" +column);
		
		String[][] value = new String[row][column];//定义一个二位串数组
		
		/*测试 赋值是否正确*/
		
		/*String[] name = new String[column];
		String[][] value = new String[row][column];
		
		for(int i = 0; i < column; i++){
			name[i] = table.getColumnName(i);
		}*/
		
		/*for(int i = 0; i < column; i++){
			System.out.println(name[i]);
		}*/
		
//		System.out.println(table.getColumnCount());
		
		for(int i = 0; i < row; i++){
			for(int j = 0; j < column; j++){
//				System.out.println(table.getValueAt(i, j).toString());
				value[i][j] = table.getValueAt(i, j).toString();
//				System.out.println(value[i][j]);
			}
		}
		
//		for(int i = 0; i < row; i++){
//			for(int j = 0; j < column; j++)
//				System.out.println(value[i][j]);
//		}
 
		
		// TODO Auto-generated method stub
		/*赋值正确 ，导入数据库*/
		String sql_url = "jdbc:mysql://localhost:3306/bookstorage";	//数据库路径（一般都是这样写），test是数据库名称
		String name = "root";		//用户名
		String password = "root";	//密码
		Connection conn;
		PreparedStatement preparedStatement = null;
 
		try {
			Class.forName("com.mysql.jdbc.Driver");		//连接驱动
			conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
			
			//preparedStatement = conn.prepareStatement("delete from books where true");
			//preparedStatement.executeUpdate();
			
			for(int i = 0; i < row; i++){
//				System.out.println("==========================");
				String sql = "insert into books values('" + value[i][0] + "','" + value[i][1] + "','"+ value[i][2] + "','"+ value[i][3] + "','"+ value[i][4] + "','"+ value[i][5] + "','"+ value[i][6] + "','"+ value[i][7] + "')";
//				System.out.println(sql);
				preparedStatement = conn.prepareStatement(sql);
				/* String sql = "insert into books values(?,?,?,?,?,?)";
				
				preparedStatement = conn.prepareStatement(sql);

				preparedStatement.setString(1, value[i][0]);
				preparedStatement.setString(2, value[i][1]);
				preparedStatement.setString(3, value[i][2]);
				preparedStatement.setString(4, value[i][3]);
				preparedStatement.setString(5, value[i][4]);
				preparedStatement.setString(6, value[i][5]);
				preparedStatement.setString(7, value[i][6]);
				preparedStatement.setString(8, value[i][7]);*/
				
				
				preparedStatement.executeUpdate();
			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("未成功加载驱动。");
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库。");
			e1.printStackTrace();
		}
	}
	
	
	// 得到数据库表数据
	public static Vector getRows(String tableName){
 
		String sql_url = "jdbc:mysql://localhost:3306/bookstorage";	//数据库路径（一般都是这样写），test是数据库名称
		String name = "root";		//用户名
		String password = "root";	//密码
		Connection conn;
		PreparedStatement preparedStatement = null;
 
		Vector rows = null;
		Vector columnHeads = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");		//连接驱动
			conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
			preparedStatement = conn.prepareStatement("select * from " + tableName);
			
			ResultSet result1 = preparedStatement.executeQuery();
			
//			boolean moreRecords = result1.next();
//			if(result1.wasNull())
//				JOptionPane.showMessageDialog(null, "结果集中无记录");
			
			rows = new Vector();
			
//			while(result1.next())
//				System.out.println(result1.getInt("id")+"\t"+result1.getString("name"));
			
			ResultSetMetaData rsmd = result1.getMetaData();
					
			while(result1.next()){
				rows.addElement(getNextRow(result1,rsmd));
			}
			
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功加载驱动。");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库。");
			e.printStackTrace();
		}
		return rows;
	}
	
	// 得到数据库表头
		public static Vector getHead(String tableName){
		String sql_url = "jdbc:mysql://localhost:3306/bookstorage";	//数据库路径
		String name = "root";		//用户名
		String password = "root";	//密码
		Connection conn;
		PreparedStatement preparedStatement = null;
 
		Vector columnHeads = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");		//连接驱动
			conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
//			if(!conn.isClosed())
//				System.out.println("成功连接数据库");
			preparedStatement = conn.prepareStatement("select * from " + tableName);
//			preparedStatement = conn.prepareStatement("select * from 计算机");
 
			ResultSet result1 = preparedStatement.executeQuery();
			
			boolean moreRecords = result1.next();
			if(!moreRecords)
				JOptionPane.showMessageDialog(null, "结果集中无记录");
			
			columnHeads = new Vector();
			ResultSetMetaData rsmd = result1.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++)
				columnHeads.addElement(rsmd.getColumnName(i));
			
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功加载驱动。");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库。");
			e.printStackTrace();
		}
		return columnHeads;
	}
	
	// 得到数据库中下一行数据
	private static Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
		Vector currentRow = new Vector();
		for(int i = 1; i <= rsmd.getColumnCount(); i++){
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}
	
	/*修改函数*/
	/*********************************************************************/
	public void userUpdate(String userPassword1,String userPassword2,JTextField text) {
		
		
		
		Connection conn;
		PreparedStatement preparedStatement;//简化Statement的拼写
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//			if(!conn.isClosed())
//				System.out.println("成功打开数据库");
			//从文本中获取账户名
			String userName = text.getText();
//			System.out.println(userName);
			// 按账号查找
//			String sql2 = "select * from password where id='" + userName + "'"; 
//			preparedStatement = conn.prepareStatement(sql2);
//			ResultSet result = preparedStatement.executeQuery();
						
//			if(!result.wasNull()){
////				new DataRepeat().show();
//				System.out.println(result.getString("id") + "\t" + result.getString("password"));
//			}
			
			
			//
			
			/*upadte语句*/
			//String sql = "update password set ('" + userName + "','" + userPassword1 + "')";//将 userName和userPassword1写入password数据表中
			 String sql = "update password set word=?  where id" + " =?";
			
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, userPassword1);
			preparedStatement.setString(2, userName);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("未成功加载驱动");
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库");
			e1.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*//使用PreparedStatement对mysql数据库进行创建表，增加数据，查询数据和删除数据过程
	public static void process1(){
		System.out.println("process1");
		String sql_url = "jdbc:mysql://localhost:3306/test";	//数据库路径（一般都是这样写），test是数据库名称
		String name = "root";		//用户名
		String password = "123456";	//密码
		Connection conn;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");		//连接驱动
			conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
			if(!conn.isClosed())
				System.out.println("成功连接数据库");
			//新建表
			String sql = "create table aa(id int,name text)";
			preparedStatement = conn.prepareStatement(sql);		
			preparedStatement.executeUpdate();
			
			//在表中添加内容
//			preparedStatement.executeUpdate("insert into aa values(4,'amy')");
			
			preparedStatement = conn.prepareStatement("insert into aa values(1,'张三')");
			preparedStatement.executeUpdate();
			preparedStatement = conn.prepareStatement("insert into aa values(2,'李四')");
			preparedStatement.executeUpdate();
			preparedStatement = conn.prepareStatement("insert into aa values(3,'王五')");
			preparedStatement.executeUpdate();
			
			//查询表内容
			System.out.println("第一次查询表内容（删除前）");
			preparedStatement = conn.prepareStatement("select * from aa");
			ResultSet result1 = preparedStatement.executeQuery();
			while(result1.next())
				System.out.println(result1.getInt("id")+"\t"+result1.getString("name"));
			
			//删除表中数据
			preparedStatement = conn.prepareStatement("delete from aa where id = 2");
			preparedStatement.executeUpdate();
			
			//查询表中内容
			System.out.println("第二次查询表内容（删除后）");
			preparedStatement = conn.prepareStatement("select * from aa");
			ResultSet result2 = preparedStatement.executeQuery();
			while(result2.next())
				System.out.println(result2.getInt("id")+"\t"+result2.getString("name"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功加载驱动。");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库。");
			e.printStackTrace();
		}
	}
	
	//使用Statement对mysql数据库进行创建表，增加数据，查询数据和删除数据过程
	public static void process2(){
		System.out.println("process2");
		String sql_url = "jdbc:mysql://localhost:3306/test";	//数据库路径（一般都是这样写），test是数据库名称
		String name = "root";		//用户名
		String password = "123456";	//密码
		Connection conn;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");		//连接驱动
			conn = DriverManager.getConnection(sql_url, name, password);	//连接数据库
			if(!conn.isClosed())
				System.out.println("成功连接数据库");
			statement = conn.createStatement();
			//新建表
			String sql = "create table bb(id int,name text)";
			statement.executeUpdate(sql);
			
			//在表中添加内容
			statement.executeUpdate("insert into bb values(1,'张三')");
			statement.executeUpdate("insert into bb values(2,'李四')");
			statement.executeUpdate("insert into bb values(3,'王五')");
			
			//查询表内容
			System.out.println("第一次查询表内容（删除前）");
			ResultSet result1 = statement.executeQuery("select * from bb");
			while(result1.next())
				System.out.println(result1.getInt("id")+"\t"+result1.getString("name"));
			
			//删除表中数据
			statement.executeUpdate("delete from bb where id = 2");
			
			//查询表内容
			System.out.println("第二次查询表内容（删除后）");
			ResultSet result2 = statement.executeQuery("select * from bb");
			while(result2.next())
				System.out.println(result2.getInt("id")+"\t"+result2.getString("name"));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功加载驱动。");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库。");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		process1();
//		process2();
	}*/
	
	/*//主函数
	 public static void main(String[] args){
//		table("computer");
//		Find();
		 getRows();
	}*/
}

