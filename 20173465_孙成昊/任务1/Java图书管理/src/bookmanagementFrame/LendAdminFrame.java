package bookmanagementFrame;
 

/**
 * 管理员功能界面
 * ①图书入库信息模块
 * ②借阅信息管理模块
 * 
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class LendAdminFrame extends JFrame{
	
	private static final long serialVersionUID = -5142627980868200075L;
	JPanel panel;
	JButton storage,lendInfo;
	/*设计管理员版面*/
	public LendAdminFrame(){
		this.setTitle("管理员");
		this.setBounds(400,300,200,200);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		storage = new JButton("图书入库管理");
		lendInfo = new JButton("借阅信息管理");
		
		this.add(storage);
		this.add(lendInfo);
		
		MyEvent();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*通过为两个按钮注册事件侦听，到达图书馆里和借出情况界面跳转*/
	public void MyEvent(){
		// 图书入库管理
		storage.addActionListener(new ActionListener(){//为图书入库管理按钮注册事件侦听
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {//响应函数
				// TODO Auto-generated method stub
				new TableFrame(storage.getText()).show();
			}
			
		});
		
		// 用户借阅信息管理
		lendInfo.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new TableFrame(lendInfo.getText()).show();
			}
			
		});
	}
	
	public static void main(String[] args){
		new LendAdminFrame();
	}
}
