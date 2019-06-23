package bookmanagementFrame;
 
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
 
public class UserInfoFrame extends JFrame{
 
	JButton info,lend,returnBook;
	JLabel label;
	JPanel panel,panelButton,panelInfo,panelInfo1,panelInfo2,panelInfo3;
	
	CardLayout card;
	
	// ���鰴ť�����Ϣ���ñ���
	JButton ensure,find;
	JTextField bookNum;
	JPanel jp1,jp2,jp3;
	JTextField textField;
	DefaultTableModel tableModel;
	JTable table ;
	
	public UserInfoFrame(JTextField text){
		textField = text;
		card = new CardLayout();
		
		this.setBounds(300,200,600,450);
		this.setTitle("������Ϣ");
		
		label = new JLabel(text.getText() + "�Ľ�����Ϣ",SwingConstants.CENTER);
		label.setFont(new Font("����",Font.BOLD,30));
				
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panelInfo = new JPanel();
		panelButton = new JPanel();
		panelInfo1 = new JPanel();
		panelInfo1.setLayout(new BorderLayout());
		panelInfo2 = new JPanel();
		panelInfo2.setLayout(new BorderLayout());
		panelInfo3 = new JPanel();
		panelInfo3.setLayout(new BorderLayout());
		panelInfo.setLayout(card);
		panelButton.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		info = new JButton("������Ϣ");
		lend = new JButton("����");
		returnBook = new JButton("����");
		
		panelButton.add(info);
		panelButton.add(lend);
		panelButton.add(returnBook);
		
//		panelInfo1.setBackground(Color.red);
//		panelInfo2.setBackground(Color.blue);
		
		panelInfo.add(panelInfo1,"panelInfo1");
		panelInfo.add(panelInfo2,"panelInfo2");
		panelInfo.add(panelInfo3,"panelInfo3");
		
		
		panel.add(panelButton,BorderLayout.NORTH);
		panel.add(panelInfo);
		
		this.add(label,BorderLayout.NORTH);
		this.add(panel);
		
		MyEvent();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	// ��Ϣ��ʾ
	class  NumFindInfo{
		public NumFindInfo(JPanel panel,JTextField text,String tableName){
			ArrayList<String> array = new ArrayList<String>();
			
			Connection conn;
			PreparedStatement preparedStatement = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//					if(!conn.isClosed())
//						System.out.println("�ɹ������ݿ�");
					
				String sql = null;
				if(tableName.equals("books"))
					sql = "select * from " + tableName;
				else 
					sql = "select * from " + tableName;
				preparedStatement = conn.prepareStatement(sql);
				
				ResultSet result = preparedStatement.executeQuery(); 
				while(result.next()){
					if(result.getString("id").equals(text.getText()) && tableName.equals("books")){
						array.add(result.getString("id"));
						array.add(result.getString("title"));
						array.add(result.getString("author"));
					}
//					else if(result.getString("id").equals(text.getText()) && tableName.equals("lendInfo")){
//						array.add(result.getString("id"));
//						array.add(result.getString("title"));
//						array.add(result.getString("time"));
//					}
				}
				
				Object[][] demo = new Object[1][array.size()];
					
				for(int i = 0; i < array.size(); i++)
					demo[0][i] = array.get(i);
				
//				for(int i = 0; i < array.size(); i++)
//					System.out.println(array.get(i));
				
//				if(tableName.equals("books"))
				String[] head1 = {"���","����","����"};
				String[] head2 = {"���","����","�����¼�"};
				if(tableName.equals("books"))
					tableModel = new DefaultTableModel(demo,head1);
				else
					tableModel = new DefaultTableModel(demo,head2);
 
				table = new JTable(tableModel);
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
	
	public void MyEvent(){
		// ����
		lend.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// ���鰴ť��ʾ��Ƭ��Ϣ
				ensure = new JButton("ȷ�Ͻ���");
				find = new JButton("����");
				bookNum = new JTextField(10);
				
				jp1 = new JPanel();
				jp2 = new JPanel();
				jp3 = new JPanel();
				jp1.add(bookNum);
				jp1.add(find);
				jp2.add(ensure);
				panelInfo1.add(jp1,BorderLayout.NORTH);
				panelInfo1.add(jp2,BorderLayout.SOUTH);
				// �����¼�����
				find.addActionListener(new ActionListener(){
 
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						new NumFindInfo(panelInfo1,bookNum,"books");
					}
					
				});
				
				// ȷ�������¼�����
				ensure.addActionListener(new ActionListener(){
 
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						// ��ȡϵͳʱ�䣨���ո�ʽ����-��-�ա���
						
//						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
						
						
						String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//						System.out.println(time);
						
						
						Connection conn;
						PreparedStatement preparedStatement = null;
						try {
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//								if(!conn.isClosed())
//									System.out.println("�ɹ������ݿ�");
								
							String sql = "insert into lendInfo values ('" + textField.getText() + "','" + table.getValueAt(0, 0) + "','" + table.getValueAt(0, 1) + "','" + time + "')";
							preparedStatement = conn.prepareStatement(sql);
							preparedStatement.executeUpdate();
							
							
							String sql1 = "update books set state='���' where id='" + bookNum.getText() + "'";
							preparedStatement = conn.prepareStatement(sql1);
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
					
				});
				
				panelInfo1.validate();
				card.show(panelInfo, "panelInfo1");
			}
			
		});
		
		// ������Ϣ
		info.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
				
				Connection conn;
				PreparedStatement preparedStatement = null;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//						if(!conn.isClosed())
//							System.out.println("�ɹ������ݿ�");
						
					String sql = "select * from lendInfo where lendName='" + textField.getText() + "'";
					preparedStatement = conn.prepareStatement(sql);
 
					ResultSet result = preparedStatement.executeQuery(); 
					
//					if(!result.next())
//						JOptionPane.showMessageDialog(null, "��������޼�¼");
//					else{
						while(result.next()){
							ArrayList<String> al = new ArrayList<String>();
							al.add(result.getString("id"));
							al.add(result.getString("title"));
							al.add(result.getString("time"));
							
							array.add(al);
						}
												
						int row = array.size();
						int column = array.get(0).size();
						Object[][] demo = new Object[row][column + 3];
						String[] times = new String[row];
						String[] days = new String[row];
						String[] overDays = new String[row];
						
						// ����Ӧ�����ڣ���30��Ϊ���ޣ�
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						
						for(int i = 0; i < row; i++){
							String firstTime = array.get(i).get(2);
							Date date = df.parse(firstTime);
							Calendar rightNow = Calendar.getInstance();
							rightNow.setTime(date);
							rightNow.add(Calendar.DAY_OF_YEAR,30);
							times[i] = df.format(rightNow.getTime());
						}
						
						
						// ���㻹������쵽�������ڣ�����Ϊ���㵥λ��
						SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
						
						for(int i = 0; i < row; i++){
							String firstTime = times[i];
							Date date = df1.parse(firstTime);
							long day = (date.getTime() - new Date().getTime())/(24*60*60*1000);
							days[i] = String.valueOf(day);
							if(day < 0)
								overDays[i] = "����" + (0 - day) + "��";
							else if(day >= 0)
								overDays[i] = "δ����";
						}
						
						for(int i = 0; i < row; i++)
							for(int j = 0; j <= column + 2; j++){
								if(j < column)
									demo[i][j] = array.get(i).get(j);
								else if(j == column)
									demo[i][j] = times[i];
								else if(j == column + 1)
									demo[i][j] = days[i];
								else
									demo[i][j] = overDays[i];
							}
						
						String[] head = {"���","����","����ʱ��","Ӧ������","ʣ������","״̬"};
						
						DefaultTableModel tableModelInfo = new DefaultTableModel(demo,head);
						JTable tableInfo = new JTable(tableModelInfo);
						JScrollPane s = new JScrollPane(tableInfo);
						
						panelInfo2.add(s);
						panelInfo2.revalidate();
//					}
				
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
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				card.show(panelInfo, "panelInfo2");
			}
		});
		
		// ����
		returnBook.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				// ���鰴ť��ʾ��Ƭ��Ϣ
				ensure = new JButton("ȷ�ϻ���");
//				find = new JButton("����");
				bookNum = new JTextField(10);
				
				jp1 = new JPanel();
				jp1.add(bookNum);
				jp1.add(ensure);
				panelInfo3.add(jp1,BorderLayout.NORTH);
			
				
				// ȷ�������¼�����
				ensure.addActionListener(new ActionListener(){
 
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						// ��ȡϵͳʱ�䣨���ո�ʽ����-��-�ա���
						
//						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
						
						
						String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
//						System.out.println(time);
						
						
						Connection conn;
						PreparedStatement preparedStatement = null;
						try {
							Class.forName("com.mysql.jdbc.Driver");
							conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//								if(!conn.isClosed())
//									System.out.println("�ɹ������ݿ�");
							
							String sql = "delete from lendInfo where id='" + bookNum.getText() + "'";
						
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
					
				});
				
				panelInfo3.revalidate();
				card.show(panelInfo, "panelInfo3");
			}
			
		});
	}
//	public static void main(String[] args){
//		JTextField text = new JTextField("����");
//		new UserInfoFrame(text);
//	}
}
