package bookmanagementFrame;
 
/*新用户注册模块
 * 
 * 实现
 * ①两次密码的比较
 * ②将注册成功的账号导入数据库
 * 

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
 
import bookmanagementStorage.PutinStorage;
 
public class RegisterFrame extends JFrame{
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 8444493691825829364L;

	JPanel panel;
	
	JTextField text;
	JPasswordField password1,password2;
	
	JLabel title,user,pass1,pass2;
	
	JButton button;
	/**
	 * 注册界面
	 * 
	 * 
	 * 
	 * 
	 */
	public RegisterFrame(){
		/*位置与名称*/
		this.setBounds(400,200,300,200);
		this.setTitle("注册");
		this.setLayout(new BorderLayout());
		
		title = new JLabel("注册",SwingConstants.CENTER);//居中
		title.setFont(new Font("楷体",Font.BOLD,30));//字体
		
		panel = new JPanel();
		
		text = new JTextField(15);//
		text.setHorizontalAlignment(SwingConstants.CENTER);
		password1 = new JPasswordField(15);//设置密码
		password1.setEchoChar('*');
		password1.setHorizontalAlignment(SwingConstants.CENTER);
		password2 = new JPasswordField(15);//确认密码
		password2.setEchoChar('*');
		password2.setHorizontalAlignment(SwingConstants.CENTER);
		
		user = new JLabel("用         户");
		pass1 = new JLabel("密         码");
		pass2 = new JLabel("确认密码");
		
		button = new JButton("注册");
		
		
		/*将 用户名，密码，确认密码 文本框添加到 panel1版面*/
		panel.add(text);
		panel.add(user);
		panel.add(password1);
		panel.add(pass1);
		panel.add(password2);
		panel.add(pass2);
		
		
		/**/
		this.add(title,BorderLayout.NORTH);
		this.add(panel);
		this.add(button,BorderLayout.SOUTH);
		
		MyEvent();
		
//		this.setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	/*注册按钮，为注册按钮添加事件侦听*/
	public void MyEvent(){
		button.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				/*获取输入的文本内容，进行比较，如果两次密码相同，导入数据库，新建一条信息*/
				String userPassword1 = password1.getText();
//				System.out.println(userPassword1);
				String userPassword2 = password2.getText();
				
				
				/*两次密码相同，将数据导入数据库*/
				if(userPassword1.equals(userPassword2)){
				// TODO Auto-generated method stub
					
					//将两次密码和 用户名 作为参数传入PutinStorage函数
					new bookmanagementStorage.PutinStorage().userInfo(userPassword1,userPassword2,text);
					//账号注册成功后，弹出登陆界面
					new UserLoginFrame().show();
				}else
					
					/*
					 * 
					 * 弹出密码不相同*/
					JOptionPane.showMessageDialog(null, "两次密码不一致，请重新输入密码！！！","系统信息",JOptionPane.WARNING_MESSAGE);
//					new RegistError().show();
			}
			
		});
	}
	
//	public static void main(String[] args){
//		new RegisterFrame();
//	}
}
