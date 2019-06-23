package bookmanagementFrame;
 

/*主界面，图书查询
 * 
 * 实现 
 * ①图书查询
 * ②用户注册提示
 * ③管理员登陆提示
 *  
 *  
 *  
 *  */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import bookmanagementStorage.FindBook;
 
public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4623892370894286164L;
	JLabel label1,label2,info;	
	JPanel jpanel,jp1,jp2,jp3;//四个版面
	JTextField text;
	JButton search,admin,stu;//分别为，查询，管理员，用户登陆 按钮
	
	//JButton lend;
	DefaultTableModel tableModel;
	
	public MainFrame(){
		this.setLayout(new BorderLayout());
		this.setBounds(400, 200, 600, 450);
		this.setTitle("欢迎进入图书管理系统");
	
		//窗体最上面的部分
		label1 = new JLabel("图书查询",SwingConstants.CENTER);
		label1.setFont(new Font("楷体",Font.BOLD,40));		//设置字体和大小
		
		//窗体中间的部分
		label2 = new JLabel("图书:");//JLabel对象 多用户输入提示
		
		text = new JTextField(15);//设置查询按钮
		search = new JButton("查询");//
		
		jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		jp1 = new JPanel();//界面主要部分，图书查询功能窗体
		jp2 = new JPanel();		//窗体最下面的部分（及显示查询内容的地方）
//		jp2.setBackground(Color.BLUE);
		
		
		/*做两个按钮作为用户登陆指引*/
		stu = new JButton("用户登录");
		admin = new JButton("管理员");
		
//		//测试
//		lend = new JButton("确认借阅");
		
		/*将 
		 * ① 输入图书名
		 * ② 获取图书名文本框
		 * ③ 查询文本
		 * 添加到第一个版面
		 * */
		jp1.add(label2);
		jp1.add(text);
		jp1.add(search);
//		jp1.add(lend);
				
		jp3 = new JPanel();
		jp3.setLayout(new FlowLayout(FlowLayout.RIGHT));//向右居中
		/*将 两个登陆提示跳转按钮添加到第三个版面*/
		jp3.add(stu);
		jp3.add(admin);
		
		
		jp2.setLayout(new BorderLayout());
		jp2.add(jp3,BorderLayout.SOUTH);
		
		
		
//		jp2.add(info,BorderLayout.SOUTH);
		
		jpanel.add(jp1,BorderLayout.NORTH);
		jpanel.add(jp2);
		
		
		
		this.add(label1,BorderLayout.NORTH);
		this.add(jpanel);
		
		MyEvent();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
 
	public void MyEvent(){
		// 查询按钮事件
		search.addActionListener(new ActionListener(){//为查询按钮注册事件侦听
 
			@Override
			public void actionPerformed(ActionEvent arg0) {//响应函数
				// TODO Auto-generated method stub
				new FindBook().findInfo(jp2,text);//调用FindBook.java 中的FindBook()函数
			}
			
		});
		
		// 管理员按钮事件
		admin.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new LoginFrame().show();//管理员登陆界面
			}
			
		});
		//用户按钮事件
		stu.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new UserLoginFrame().show();//普通用户登录界面
			}
			
		});
	}
	
	public static void main(String[] args){//主函数，调用  主页面窗体按钮
		new MainFrame();
	}
}
