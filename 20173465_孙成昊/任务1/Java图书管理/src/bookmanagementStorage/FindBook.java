package bookmanagementStorage;
 


/*��������ġ�ͼ���ѯ������   ������FindBook()��
 *ʵ�ֹ��ܣ�
 *��
 *��
 *��
 *��
 * 
 * 
 * 
 * 
 * 
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
 
public class FindBook {
	
	Vector tableName;
	
	// �õ����ݿ������б�����ֲ����浽������
	private Vector getTableName(){
		
		tableName = new Vector();
		
		Connection conn;
		PreparedStatement preparedStatement;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//			if(!conn.isClosed())
//				System.out.println("�ɹ������ݿ�");
			
			String sql = "show tables";
			preparedStatement = conn.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			ResultSetMetaData metaData = result.getMetaData();
						
			while(result.next()){
				for(int i = 1; i <= metaData.getColumnCount(); i++){
					tableName.addElement(result.getString(i));
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ���������");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("δ�ɹ������ݿ�");
			e.printStackTrace();
		}
		return tableName;
	}
	
	
	// ���캯��
	public FindBook(){}
	
	// �õ�Ҫ��ѯ����Ϣ�������������
	public void findInfo(JPanel panel,JTextField text){
				
/*		for(int i = 0; i < names.size(); i++)
			System.out.println(names.get(i));*/
				
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();	// ���ݿ������е�ͼ����Ϣ
		ArrayList<ArrayList<String>> outputDatas = new ArrayList<ArrayList<String>>();		// ��¼��Ҫ�������Ϣ���м�����
		ArrayList<ArrayList<String>> bookN = new ArrayList<ArrayList<String>>();	// ���пɽ��鼮����Ϣ���ڼ�¼�ɽ����ID��ʱ�õ������м�����
		ArrayList<ArrayList<String>> outputBook = new ArrayList<ArrayList<String>>();		//��¼���пɽ��鼮��ID��
		
		ArrayList<String> lendBooks = new ArrayList<String>();	//�洢�����鼮������
		
//		Vector headers = null;
		Connection conn;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//				if(!conn.isClosed())
//					System.out.println("�ɹ������ݿ�");
				
			String sql = "select * from books";
			preparedStatement = conn.prepareStatement(sql);
			
			ResultSet result = preparedStatement.executeQuery(); 
				
			while(result.next()){
				ArrayList<String> al = new ArrayList<String>();
				ArrayList<String> al2 = new ArrayList<String>();
				al.add(result.getString("title"));
				al.add(result.getString("author"));
				al.add(result.getString("state"));
//					
				if(result.getString("state").equals("δ��")){
					al2.add(result.getString("id"));
					al2.add(result.getString("title"));
					al2.add(result.getString("state"));
					
					bookN.add(al2);
				}
				datas.add(al);
			
//					System.out.println(result);
//						System.out.println(result.getString("title") + "\t" + result.getString("author") + "\t" +result.getString("press"));
			}
//				headers = new Vector();
//				ResultSetMetaData rsmd = result.getMetaData();
//				for(int k = 1; k <= rsmd.getColumnCount(); k++)
//					headers.addElement(rsmd.getColumnName(k));
			
			
//					String[] head = new String[datas.get(0).size()];
	/*		System.out.println(datas.get(0).size());
			for(int i = 0; i < headers.size(); i++){
				System.out.println(headers.get(i).toString());
//				head[i] = headers.get(i).toString();
			}	*/
		
			// �洢�����鼮�����ƣ����ظ������Ա�����
			for(int i = 0; i < datas.size(); i++)
				if(!lendBooks.contains(datas.get(i).get(0)))
					lendBooks.add(datas.get(i).get(0));
			
	/*		for(int i = 0; i < lendBooks.size(); i++)
				System.out.println(lendBooks.get(i));*/
			
			// ��Ҫ���������
			String findName = text.getText();
						
			if(findName.equals("")){
				int row = datas.size();
				int columns = datas.get(0).size();
				Object[][] demo = new Object[row][columns + 1];
				
				for(int i = 0; i < row;i++){
					if(!outputDatas.contains(datas.get(i)) && datas.get(i).get(2).equals("δ��"))
						outputDatas.add(datas.get(i));
				}
				
				Vector counter = new Vector();
//				System.out.println("size========"+outputDatas.size());
				for(int i = 0; i < outputDatas.size(); i++){
					int count = 0;
					for(int j = 0; j < datas.size(); j++){
						if(datas.get(j).equals(outputDatas.get(i)))
							count ++;
					}
						counter.add(count);
				}
//				System.out.println("counter========"+counter.size());
				
//				// ���ͬһ��ͼ��״̬Ϊ��δ�衱������
//				for(int i = 0; i < counter.size(); i++)
//					System.out.println(counter.get(i));
				
//				//�������û���ظ����ͼ���
//				for(int i = 0; i < outputDatas.size(); i++){
//					for(int j = 0; j < outputDatas.get(0).size(); j++)
//						System.out.print(outputDatas.get(i).get(j));
//					System.out.println();
//				}
//				System.out.println(counter.size() + "===========" + outputDatas.size());
				
				
				for(int i = 0; i < outputDatas.size(); i++){
					ArrayList<String> mid = new ArrayList<String>();
					mid.add(outputDatas.get(i).get(0));
					for(int j = 0; j < bookN.size(); j++){
						if(bookN.get(j).get(1).equals(outputDatas.get(i).get(0))){
							mid.add(bookN.get(j).get(0));
						}
					}
					outputBook.add(mid);
				}
				
				/*for(int i = 0; i < outputBook.size(); i++){
				*	for(int j = 0; j < outputBook.get(i).size(); j++)
				*		System.out.print(outputBook.get(i).get(j) + "\t");
				*	System.out.println();
			    *}
				*/
				
				String[][] books = new String[outputBook.size()][2];
				
				for(int i = 0; i < outputBook.size(); i++){
					books[i][0] = outputBook.get(i).get(0);
					books[i][1] = "";
					for(int j = 1; j < outputBook.get(i).size(); j++){
						if(j < outputBook.get(i).size() - 1)
							books[i][1] = books[i][1] + outputBook.get(i).get(j) + "��" ;
						else if(j == outputBook.get(i).size() - 1)
							books[i][1] = books[i][1] + outputBook.get(i).get(j);
					}
				}
					
			/*	for(int i = 0; i < books.length; i++){
					for(int j = 0; j < 2; j++)
						System.out.print(books[i][j] + "\t");
					System.out.println();
				}*/
				
				
				for(int i = 0; i < outputDatas.size(); i++)
					for(int j = 0; j < outputDatas.get(0).size(); j++){
						demo[i][0] = books[i][1];
						
						if(j == 2)
							demo[i][3] = counter.get(i) + "���ɽ�";
						else
							demo[i][j + 1] = outputDatas.get(i).get(j);
					}
				
//				System.out.println(outputDatas.size());
				
				String[] head = {"�ɽ���","����","����","����״̬"};
				
				DefaultTableModel tableModel = new DefaultTableModel(demo,head);
				JTable table = new JTable(tableModel);
				JScrollPane scroll = new JScrollPane(table);
				
				panel.add(scroll);
				panel.revalidate();
			}else{
	//	����		System.out.println(datas.get(0).get(0));
//				for(int i = 0; i < datas.size(); i++){
//					for(int j = 0; j < datas.get(i).size(); j++){
//						if(datas.get(i).get(j).contains(findName)){
//							outputDatas.add(datas.get(i));
//	//						System.out.print(datas.get(i));
				
//						}
//						
//	//					System.out.print(datas.get(i).get(j) + "\t");
//					}
//	//				System.out.println();
//				}
				
				for(int i = 0; i < datas.size();i++){
					if(!outputDatas.contains(datas.get(i)) && datas.get(i).get(2).equals("δ��") && datas.get(i).get(0).contains(findName))
						outputDatas.add(datas.get(i));
				}
					
				if(outputDatas.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "ͼ���ѽ��","ϵͳ��Ϣ",JOptionPane.WARNING_MESSAGE);
					
				
				}
				else{
					
					int row = outputDatas.size();
					int columns = outputDatas.get(0).size();
					Object[][] demo = new Object[row][columns + 1];
					
				/*	for(int i = 0; i < outputDatas.size(); i++){
						for(int j = 0; j < outputDatas.get(0).size(); j++){
							System.out.print(outputDatas.get(i).get(j) + "\t");
						}
						System.out.println();
					}*/
					
					Vector counter = new Vector();
//					System.out.println("size========"+outputDatas.size());
					for(int i = 0; i < outputDatas.size(); i++){
						int count = 0;
						for(int j = 0; j < datas.size(); j++){
							if(datas.get(j).equals(outputDatas.get(i)))
								count ++;
						}
							counter.add(count);
					}
					
//					for(int i = 0; i < counter.size(); i++)
//						System.out.println(counter.get(i));
					
					
					for(int i = 0; i < outputDatas.size(); i++){
						ArrayList<String> mid = new ArrayList<String>();
						mid.add(outputDatas.get(i).get(0));
						for(int j = 0; j < bookN.size(); j++){
							if(bookN.get(j).get(1).equals(outputDatas.get(i).get(0))){
								mid.add(bookN.get(j).get(0));
							}
						}
						outputBook.add(mid);
					}
					
					
					String[][] books = new String[outputBook.size()][2];
					
					for(int i = 0; i < outputBook.size(); i++){
						books[i][0] = outputBook.get(i).get(0);
						books[i][1] = "";
						for(int j = 1; j < outputBook.get(i).size(); j++){
							if(j < outputBook.get(i).size() - 1)
								books[i][1] = books[i][1] + outputBook.get(i).get(j) + "��" ;
							else if(j == outputBook.get(i).size() - 1)
								books[i][1] = books[i][1] + outputBook.get(i).get(j);
						}
					}
					
		/*			for(int i = 0; i < books.length; i++){
						for(int j = 0; j < 2; j++)
							System.out.print(books[i][j] + "\t");
						System.out.println();
					}*/
					
					
					
					for(int i = 0; i < outputDatas.size(); i++)
						for(int j = 0; j < outputDatas.get(0).size(); j++){
							demo[i][0] = books[i][1];
							
							if(j == 2)
								demo[i][3] = counter.get(i) + "���ɽ�";
							else
								demo[i][j + 1] = outputDatas.get(i).get(j);
						}
										
					String[] head = {"�ɽ���","����","����","����״̬"};
					
					DefaultTableModel tableModel = new DefaultTableModel(demo,head);
					JTable table = new JTable(tableModel);
					JScrollPane scroll = new JScrollPane(table);
					
					panel.add(scroll);
					panel.revalidate();
				}
			}
//			for(int i = 0; i < outputDatas.size(); i++)
//				System.out.println(outputDatas.get(i));}
			
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
	
	
	// �õ�Ҫ��ѯ����Ϣ�������������
		public void numFindIndo(JPanel panel,JTextField text){
			
			ArrayList<String> array = new ArrayList<String>();
			
			Connection conn;
			PreparedStatement preparedStatement = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//					if(!conn.isClosed())
//						System.out.println("�ɹ������ݿ�");
					
				
				String sql = "select * from books";
				preparedStatement = conn.prepareStatement(sql);
				
				ResultSet result = preparedStatement.executeQuery(); 
				while(result.next()){
					if(result.getString("id").equals(text.getText())){
						array.add(result.getString("id"));
						array.add(result.getString("title"));
						array.add(result.getString("author"));
					}
				}
				
				Object[][] demo = new Object[1][array.size()];
					
				for(int i = 0; i < array.size(); i++)
					demo[0][i] = array.get(i);
				
				String[] head = {"���","����","����"};
					
				DefaultTableModel tableModel = new DefaultTableModel(demo,head);
				JTable table = new JTable(tableModel);
				JScrollPane scroll = new JScrollPane(table);
					
				panel.add(scroll);
				panel.revalidate();
			
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
	
}
