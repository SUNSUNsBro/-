package bookmanagementFrame;
 

/*��ͨ�û���½����
 * ʵ��
 * �� ���û��ĵ�½
 * �� ���û���ע�ᣨ��ť��ָ����
 * �� ���û�����Ϣ�޸���ʾ��ť
 * 
 * 
 * */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
 
public class UserLoginFrame extends JFrame{
	JLabel label,name,pass,identify;
	JButton login,register,modify;//���õ�½��ע�ᣬ�޸İ�ť
	JTextField adminName;
	JPasswordField password;
	JPanel panel,jp1,jp2;
	
	JPanel[] panelLeft,panelRight;
	
	JComboBox personType;
		
	public UserLoginFrame(){
		this.setBounds(400, 200, 300, 200);
		this.setTitle("��¼ϵͳ");
		this.setLayout(new BorderLayout());
		
		label = new JLabel("��¼",SwingConstants.CENTER);
		label.setFont(new Font("����",Font.BOLD,30));
		
		name = new JLabel("�� ��");
		pass = new JLabel("�� ��");
		
		adminName = new JTextField(12);
		adminName.setHorizontalAlignment(SwingConstants.CENTER);
		password = new JPasswordField(12);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setEchoChar('*');		//���û����ַ�
		
		panel = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		panel.setLayout(new BorderLayout());
		
		jp1.add(adminName);
		jp1.add(name);
		jp1.add(password);
		jp1.add(pass);
		
		panel.add(jp1);
		
		register = new JButton("ע��");
		login = new JButton("��¼");
		modify = new JButton("�޸�����");
		/*��ť��ӵ�����*/
		jp2.add(register);
		jp2.add(login);
		jp2.add(modify);
		panel.add(jp2,BorderLayout.SOUTH);
		
		this.add(label,BorderLayout.NORTH);
		this.add(panel);
		
		MyEvent();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void MyEvent(){
		// ע���¼�����
		register.addActionListener(new ActionListener(){//Ϊ��¼��ťע���¼�����
 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new RegisterFrame().show();//ע�����û�
			}
 
		});
		
		// ��¼�¼�����
		/* ����ȶ�*/
		login.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
								
				Connection conn;
				PreparedStatement preparedStatement;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstorage","root","root");
//					if(!conn.isClosed())
//						System.out.println("�ɹ������ݿ�");
					
					String sql = "select word from password where id='" + adminName.getText() + "'";
					preparedStatement = conn.prepareStatement(sql);
					
					ResultSet result = preparedStatement.executeQuery(); 
					
//					if(result.next())
//						System.out.println(result.getString("word"));
					
					String str1 = password.getText();//��ȡ����ֵ
					
					if(result.next()){
						String str2 = result.getString("word");
						if(str1.equals(str2))
							new UserInfoFrame(adminName).show();
						else{
							String info = "����������벻��ȷ��ԭ������ǣ�\n" +  "1���������룻\n" + "2��δ����С���̣�\n" + "3����Сдδ���֡�";
							JOptionPane.showMessageDialog(null, info,"ϵͳ��Ϣ",JOptionPane.INFORMATION_MESSAGE);
//							new LoginErrorFrame().show();
						}
					}else
						JOptionPane.showMessageDialog(null, "�û�������","ϵͳ��Ϣ",JOptionPane.WARNING_MESSAGE);
//						new NoExist().show();
					
				
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
				UserLoginFrame.this.dispose();
			}
 
		});
		
		// �޸�����
		modify.addActionListener(new ActionListener(){
 
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					new UserUpdate().show();//ע�����û�
				
			}
			
		});
	}
//	
	public static void main(String[] args){
		new UserLoginFrame();
	}
}
 
