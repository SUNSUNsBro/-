package bookmanagementFrame;
/*管理员登陆界面*/ 
/*
 * ①实现登陆按钮的界面跳转
 * ②密码错误提示
 * ③管理员不再利用后台，设置密码为123456
 * 
 * */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
 

public class LoginFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8247892237265367402L;//表示不同版间的兼容性，后面的码自动生成
	JLabel label,name,pass;//不可编辑文本
	JButton login;//登陆按钮
	JTextField adminName;//账号文本框，
	JPasswordField password;//密码文本框
	JPanel panel,jp1,jp2;//设计三个版面，分别存放
	
	
	/*登陆版面*/
	public LoginFrame(){
		this.setBounds(400, 200, 300, 200);//边框
		this.setTitle("图书馆管理系统登录");//标题
		this.setLayout(new BorderLayout());//设置为边框布局
		
		label = new JLabel("登录",SwingConstants.CENTER);
		label.setFont(new Font("楷体",Font.BOLD,30));
		
		name = new JLabel("账 号");//不可编辑文本框  做提醒 输入账号，密码
		pass = new JLabel("密 码");
		
		/*从文本框中获取文本*/
		adminName = new JTextField(12);//为文本 adminName申请 空间
		//adminName.setText("admin");//将文本框的显示内容设置为admin
		adminName.setHorizontalAlignment(SwingConstants.CENTER);//居中
		password = new JPasswordField(12);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setEchoChar('*');		//设置回显字符
		
		
		
		/*为三个版面申请新的空间*/
		panel = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		panel.setLayout(new BorderLayout());//设置为边框布局
		
		jp1.add(adminName);//将账号文本框添加到jp1版面上
		jp1.add(name);
		jp1.add(password);
		jp1.add(pass);
		
		panel.add(jp1);
		
		login = new JButton("登录");
		jp2.add(login);
		panel.add(jp2,BorderLayout.SOUTH);
		
		this.add(label,BorderLayout.NORTH);
		this.add(panel);
		
		MyEvent();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void MyEvent(){
		login.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String word = "123456";	// 正确密码
				String str = new String(password.getPassword());
				
				if(str.equals(word))
					new LendAdminFrame().show();
//					new TableFrame().show();
//					new RuKuFrame();
				else{
					String str1 = "你输入的密码不正确，原因可能是：\n" +  "1、忘记密码；\n" + "2、未开启小键盘；\n" + "3、大小写未区分。";
					JOptionPane.showMessageDialog(null, str1);
//					new LoginErrorFrame().show();
					
				}
				LoginFrame.this.dispose();
			}
 
		});
	}
	
	public static void main(String[] args){
		new LoginFrame();
	}
}
