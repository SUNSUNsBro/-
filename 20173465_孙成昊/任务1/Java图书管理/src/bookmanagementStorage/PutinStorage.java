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



public class PutinStorage {//������û��ı��溯��
	
	// ���û�������������password����
	public void userInfo(String userPassword1,String userPassword2,JTextField text){
		Connection conn;
		PreparedStatement preparedStatement;//��Statement��ƴд
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//			if(!conn.isClosed())
//				System.out.println("�ɹ������ݿ�");
			//���ı��л�ȡ�˻���
			String userName = text.getText();
//			System.out.println(userName);
			// ���˺Ų���
//			String sql2 = "select * from password where id='" + userName + "'"; 
//			preparedStatement = conn.prepareStatement(sql2);
//			ResultSet result = preparedStatement.executeQuery();
						
//			if(!result.wasNull()){
////				new DataRepeat().show();
//				System.out.println(result.getString("id") + "\t" + result.getString("password"));
//			}
			
			
			//
			String sql = "insert into password values('" + userName + "','" + userPassword1 + "')";//�� userName��userPassword1д��password���ݱ���
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ���������");
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ�");
			e1.printStackTrace();
		}
	}
	
	
	
	// �����ӻ������б�����ݴ��浽���ݿ�
	public void saveData(JTable table){//
		int column = table.getColumnCount();//��ȡ��������� �ĳ��� 
		int row = table.getRowCount();//��
		
//		System.out.println("rows:" + row + "            coumns:" +column);
		
		String[][] value = new String[row][column];//����һ����λ������
		
		/*���� ��ֵ�Ƿ���ȷ*/
		
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
		/*��ֵ��ȷ ���������ݿ�*/
		String sql_url = "jdbc:mysql://localhost:3306/bookstorage";	//���ݿ�·����һ�㶼������д����test�����ݿ�����
		String name = "root";		//�û���
		String password = "root";	//����
		Connection conn;
		PreparedStatement preparedStatement = null;
 
		try {
			Class.forName("com.mysql.jdbc.Driver");		//��������
			conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
//			if(!conn.isClosed())
//				System.out.println("�ɹ��������ݿ�");
			
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
			System.out.println("δ�ɹ�����������");
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ⡣");
			e1.printStackTrace();
		}
	}
	
	
	// �õ����ݿ������
	public static Vector getRows(String tableName){
 
		String sql_url = "jdbc:mysql://localhost:3306/bookstorage";	//���ݿ�·����һ�㶼������д����test�����ݿ�����
		String name = "root";		//�û���
		String password = "root";	//����
		Connection conn;
		PreparedStatement preparedStatement = null;
 
		Vector rows = null;
		Vector columnHeads = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");		//��������
			conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
//			if(!conn.isClosed())
//				System.out.println("�ɹ��������ݿ�");
			preparedStatement = conn.prepareStatement("select * from " + tableName);
			
			ResultSet result1 = preparedStatement.executeQuery();
			
//			boolean moreRecords = result1.next();
//			if(result1.wasNull())
//				JOptionPane.showMessageDialog(null, "��������޼�¼");
			
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
			System.out.println("δ�ɹ�����������");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ⡣");
			e.printStackTrace();
		}
		return rows;
	}
	
	// �õ����ݿ��ͷ
		public static Vector getHead(String tableName){
		String sql_url = "jdbc:mysql://localhost:3306/bookstorage";	//���ݿ�·��
		String name = "root";		//�û���
		String password = "root";	//����
		Connection conn;
		PreparedStatement preparedStatement = null;
 
		Vector columnHeads = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");		//��������
			conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
//			if(!conn.isClosed())
//				System.out.println("�ɹ��������ݿ�");
			preparedStatement = conn.prepareStatement("select * from " + tableName);
//			preparedStatement = conn.prepareStatement("select * from �����");
 
			ResultSet result1 = preparedStatement.executeQuery();
			
			boolean moreRecords = result1.next();
			if(!moreRecords)
				JOptionPane.showMessageDialog(null, "��������޼�¼");
			
			columnHeads = new Vector();
			ResultSetMetaData rsmd = result1.getMetaData();
			for(int i = 1; i <= rsmd.getColumnCount(); i++)
				columnHeads.addElement(rsmd.getColumnName(i));
			
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ�����������");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ⡣");
			e.printStackTrace();
		}
		return columnHeads;
	}
	
	// �õ����ݿ�����һ������
	private static Vector getNextRow(ResultSet rs,ResultSetMetaData rsmd) throws SQLException{
		Vector currentRow = new Vector();
		for(int i = 1; i <= rsmd.getColumnCount(); i++){
			currentRow.addElement(rs.getString(i));
		}
		return currentRow;
	}
	
	/*�޸ĺ���*/
	/*********************************************************************/
	public void userUpdate(String userPassword1,String userPassword2,JTextField text) {
		
		
		
		Connection conn;
		PreparedStatement preparedStatement;//��Statement��ƴд
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//			if(!conn.isClosed())
//				System.out.println("�ɹ������ݿ�");
			//���ı��л�ȡ�˻���
			String userName = text.getText();
//			System.out.println(userName);
			// ���˺Ų���
//			String sql2 = "select * from password where id='" + userName + "'"; 
//			preparedStatement = conn.prepareStatement(sql2);
//			ResultSet result = preparedStatement.executeQuery();
						
//			if(!result.wasNull()){
////				new DataRepeat().show();
//				System.out.println(result.getString("id") + "\t" + result.getString("password"));
//			}
			
			
			//
			
			/*upadte���*/
			//String sql = "update password set ('" + userName + "','" + userPassword1 + "')";//�� userName��userPassword1д��password���ݱ���
			 String sql = "update password set word=?  where id" + " =?";
			
			preparedStatement = conn.prepareStatement(sql);
			
			preparedStatement.setString(1, userPassword1);
			preparedStatement.setString(2, userName);
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			conn.close();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ���������");
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ�");
			e1.printStackTrace();
		}
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*//ʹ��PreparedStatement��mysql���ݿ���д������������ݣ���ѯ���ݺ�ɾ�����ݹ���
	public static void process1(){
		System.out.println("process1");
		String sql_url = "jdbc:mysql://localhost:3306/test";	//���ݿ�·����һ�㶼������д����test�����ݿ�����
		String name = "root";		//�û���
		String password = "123456";	//����
		Connection conn;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");		//��������
			conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
			if(!conn.isClosed())
				System.out.println("�ɹ��������ݿ�");
			//�½���
			String sql = "create table aa(id int,name text)";
			preparedStatement = conn.prepareStatement(sql);		
			preparedStatement.executeUpdate();
			
			//�ڱ����������
//			preparedStatement.executeUpdate("insert into aa values(4,'amy')");
			
			preparedStatement = conn.prepareStatement("insert into aa values(1,'����')");
			preparedStatement.executeUpdate();
			preparedStatement = conn.prepareStatement("insert into aa values(2,'����')");
			preparedStatement.executeUpdate();
			preparedStatement = conn.prepareStatement("insert into aa values(3,'����')");
			preparedStatement.executeUpdate();
			
			//��ѯ������
			System.out.println("��һ�β�ѯ�����ݣ�ɾ��ǰ��");
			preparedStatement = conn.prepareStatement("select * from aa");
			ResultSet result1 = preparedStatement.executeQuery();
			while(result1.next())
				System.out.println(result1.getInt("id")+"\t"+result1.getString("name"));
			
			//ɾ����������
			preparedStatement = conn.prepareStatement("delete from aa where id = 2");
			preparedStatement.executeUpdate();
			
			//��ѯ��������
			System.out.println("�ڶ��β�ѯ�����ݣ�ɾ����");
			preparedStatement = conn.prepareStatement("select * from aa");
			ResultSet result2 = preparedStatement.executeQuery();
			while(result2.next())
				System.out.println(result2.getInt("id")+"\t"+result2.getString("name"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ�����������");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ⡣");
			e.printStackTrace();
		}
	}
	
	//ʹ��Statement��mysql���ݿ���д������������ݣ���ѯ���ݺ�ɾ�����ݹ���
	public static void process2(){
		System.out.println("process2");
		String sql_url = "jdbc:mysql://localhost:3306/test";	//���ݿ�·����һ�㶼������д����test�����ݿ�����
		String name = "root";		//�û���
		String password = "123456";	//����
		Connection conn;
		Statement statement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");		//��������
			conn = DriverManager.getConnection(sql_url, name, password);	//�������ݿ�
			if(!conn.isClosed())
				System.out.println("�ɹ��������ݿ�");
			statement = conn.createStatement();
			//�½���
			String sql = "create table bb(id int,name text)";
			statement.executeUpdate(sql);
			
			//�ڱ����������
			statement.executeUpdate("insert into bb values(1,'����')");
			statement.executeUpdate("insert into bb values(2,'����')");
			statement.executeUpdate("insert into bb values(3,'����')");
			
			//��ѯ������
			System.out.println("��һ�β�ѯ�����ݣ�ɾ��ǰ��");
			ResultSet result1 = statement.executeQuery("select * from bb");
			while(result1.next())
				System.out.println(result1.getInt("id")+"\t"+result1.getString("name"));
			
			//ɾ����������
			statement.executeUpdate("delete from bb where id = 2");
			
			//��ѯ������
			System.out.println("�ڶ��β�ѯ�����ݣ�ɾ����");
			ResultSet result2 = statement.executeQuery("select * from bb");
			while(result2.next())
				System.out.println(result2.getInt("id")+"\t"+result2.getString("name"));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ�����������");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ⡣");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		process1();
//		process2();
	}*/
	
	/*//������
	 public static void main(String[] args){
//		table("computer");
//		Find();
		 getRows();
	}*/
}

