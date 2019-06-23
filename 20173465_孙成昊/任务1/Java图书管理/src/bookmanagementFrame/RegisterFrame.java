package bookmanagementFrame;
 
/*���û�ע��ģ��
 * 
 * ʵ��
 * ����������ıȽ�
 * �ڽ�ע��ɹ����˺ŵ������ݿ�
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
	 * ע�����
	 * 
	 * 
	 * 
	 * 
	 */
	public RegisterFrame(){
		/*λ��������*/
		this.setBounds(400,200,300,200);
		this.setTitle("ע��");
		this.setLayout(new BorderLayout());
		
		title = new JLabel("ע��",SwingConstants.CENTER);//����
		title.setFont(new Font("����",Font.BOLD,30));//����
		
		panel = new JPanel();
		
		text = new JTextField(15);//
		text.setHorizontalAlignment(SwingConstants.CENTER);
		password1 = new JPasswordField(15);//��������
		password1.setEchoChar('*');
		password1.setHorizontalAlignment(SwingConstants.CENTER);
		password2 = new JPasswordField(15);//ȷ������
		password2.setEchoChar('*');
		password2.setHorizontalAlignment(SwingConstants.CENTER);
		
		user = new JLabel("��         ��");
		pass1 = new JLabel("��         ��");
		pass2 = new JLabel("ȷ������");
		
		button = new JButton("ע��");
		
		
		/*�� �û��������룬ȷ������ �ı�����ӵ� panel1����*/
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
	
	
	/*ע�ᰴť��Ϊע�ᰴť����¼�����*/
	public void MyEvent(){
		button.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				/*��ȡ������ı����ݣ����бȽϣ��������������ͬ���������ݿ⣬�½�һ����Ϣ*/
				String userPassword1 = password1.getText();
//				System.out.println(userPassword1);
				String userPassword2 = password2.getText();
				
				
				/*����������ͬ�������ݵ������ݿ�*/
				if(userPassword1.equals(userPassword2)){
				// TODO Auto-generated method stub
					
					//����������� �û��� ��Ϊ��������PutinStorage����
					new bookmanagementStorage.PutinStorage().userInfo(userPassword1,userPassword2,text);
					//�˺�ע��ɹ��󣬵�����½����
					new UserLoginFrame().show();
				}else
					
					/*
					 * 
					 * �������벻��ͬ*/
					JOptionPane.showMessageDialog(null, "�������벻һ�£��������������룡����","ϵͳ��Ϣ",JOptionPane.WARNING_MESSAGE);
//					new RegistError().show();
			}
			
		});
	}
	
//	public static void main(String[] args){
//		new RegisterFrame();
//	}
}
