package bookmanagementStorage;
 


/*做主界面的“图书查询”功能   （函数FindBook()）
 *实现功能：
 *①
 *②
 *③
 *④
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
	
	// 拿到数据库中所有表的名字并储存到集合中
	private Vector getTableName(){
		
		tableName = new Vector();
		
		Connection conn;
		PreparedStatement preparedStatement;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//			if(!conn.isClosed())
//				System.out.println("成功打开数据库");
			
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
			System.out.println("未成功加载驱动");
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库");
			e.printStackTrace();
		}
		return tableName;
	}
	
	
	// 构造函数
	public FindBook(){}
	
	// 拿到要查询的信息并输出到主界面
	public void findInfo(JPanel panel,JTextField text){
				
/*		for(int i = 0; i < names.size(); i++)
			System.out.println(names.get(i));*/
				
		ArrayList<ArrayList<String>> datas = new ArrayList<ArrayList<String>>();	// 数据库中所有的图书信息
		ArrayList<ArrayList<String>> outputDatas = new ArrayList<ArrayList<String>>();		// 记录需要输出的信息（中间量）
		ArrayList<ArrayList<String>> bookN = new ArrayList<ArrayList<String>>();	// 所有可借书籍的信息（在记录可借书的ID号时用到，属中间量）
		ArrayList<ArrayList<String>> outputBook = new ArrayList<ArrayList<String>>();		//记录所有可借书籍的ID号
		
		ArrayList<String> lendBooks = new ArrayList<String>();	//存储所有书籍的名称
		
//		Vector headers = null;
		Connection conn;
		PreparedStatement preparedStatement = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//				if(!conn.isClosed())
//					System.out.println("成功打开数据库");
				
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
				if(result.getString("state").equals("未借")){
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
		
			// 存储所有书籍的名称（不重复），以备后用
			for(int i = 0; i < datas.size(); i++)
				if(!lendBooks.contains(datas.get(i).get(0)))
					lendBooks.add(datas.get(i).get(0));
			
	/*		for(int i = 0; i < lendBooks.size(); i++)
				System.out.println(lendBooks.get(i));*/
			
			// 按要求输出内容
			String findName = text.getText();
						
			if(findName.equals("")){
				int row = datas.size();
				int columns = datas.get(0).size();
				Object[][] demo = new Object[row][columns + 1];
				
				for(int i = 0; i < row;i++){
					if(!outputDatas.contains(datas.get(i)) && datas.get(i).get(2).equals("未借"))
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
				
//				// 输出同一本图书状态为“未借”的数量
//				for(int i = 0; i < counter.size(); i++)
//					System.out.println(counter.get(i));
				
//				//尝试输出没有重复项的图书库
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
							books[i][1] = books[i][1] + outputBook.get(i).get(j) + "、" ;
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
							demo[i][3] = counter.get(i) + "本可借";
						else
							demo[i][j + 1] = outputDatas.get(i).get(j);
					}
				
//				System.out.println(outputDatas.size());
				
				String[] head = {"可借编号","书名","作者","借阅状态"};
				
				DefaultTableModel tableModel = new DefaultTableModel(demo,head);
				JTable table = new JTable(tableModel);
				JScrollPane scroll = new JScrollPane(table);
				
				panel.add(scroll);
				panel.revalidate();
			}else{
	//	测试		System.out.println(datas.get(0).get(0));
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
					if(!outputDatas.contains(datas.get(i)) && datas.get(i).get(2).equals("未借") && datas.get(i).get(0).contains(findName))
						outputDatas.add(datas.get(i));
				}
					
				if(outputDatas.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "图书已借出","系统信息",JOptionPane.WARNING_MESSAGE);
					
				
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
								books[i][1] = books[i][1] + outputBook.get(i).get(j) + "、" ;
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
								demo[i][3] = counter.get(i) + "本可借";
							else
								demo[i][j + 1] = outputDatas.get(i).get(j);
						}
										
					String[] head = {"可借编号","书名","作者","借阅状态"};
					
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
			System.out.println("未成功加载驱动");
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("未成功打开数据库");
			e1.printStackTrace();
		}
	}
	
	
	// 拿到要查询的信息并输出到主界面
		public void numFindIndo(JPanel panel,JTextField text){
			
			ArrayList<String> array = new ArrayList<String>();
			
			Connection conn;
			PreparedStatement preparedStatement = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//					if(!conn.isClosed())
//						System.out.println("成功打开数据库");
					
				
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
				
				String[] head = {"编号","书名","作者"};
					
				DefaultTableModel tableModel = new DefaultTableModel(demo,head);
				JTable table = new JTable(tableModel);
				JScrollPane scroll = new JScrollPane(table);
					
				panel.add(scroll);
				panel.revalidate();
			
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
	
}
