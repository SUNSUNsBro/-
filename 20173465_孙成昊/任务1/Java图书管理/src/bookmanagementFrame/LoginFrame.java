package bookmanagementFrame;
/*����Ա��½����*/ 
/*
 * ��ʵ�ֵ�½��ť�Ľ�����ת
 * �����������ʾ
 * �۹���Ա�������ú�̨����������Ϊ123456
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
	private static final long serialVersionUID = -8247892237265367402L;//��ʾ��ͬ���ļ����ԣ���������Զ�����
	JLabel label,name,pass;//���ɱ༭�ı�
	JButton login;//��½��ť
	JTextField adminName;//�˺��ı���
	JPasswordField password;//�����ı���
	JPanel panel,jp1,jp2;//����������棬�ֱ���
	
	
	/*��½����*/
	public LoginFrame(){
		this.setBounds(400, 200, 300, 200);//�߿�
		this.setTitle("ͼ��ݹ���ϵͳ��¼");//����
		this.setLayout(new BorderLayout());//����Ϊ�߿򲼾�
		
		label = new JLabel("��¼",SwingConstants.CENTER);
		label.setFont(new Font("����",Font.BOLD,30));
		
		name = new JLabel("�� ��");//���ɱ༭�ı���  ������ �����˺ţ�����
		pass = new JLabel("�� ��");
		
		/*���ı����л�ȡ�ı�*/
		adminName = new JTextField(12);//Ϊ�ı� adminName���� �ռ�
		//adminName.setText("admin");//���ı������ʾ��������Ϊadmin
		adminName.setHorizontalAlignment(SwingConstants.CENTER);//����
		password = new JPasswordField(12);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setEchoChar('*');		//���û����ַ�
		
		
		
		/*Ϊ�������������µĿռ�*/
		panel = new JPanel();
		jp1 = new JPanel();
		jp2 = new JPanel();
		panel.setLayout(new BorderLayout());//����Ϊ�߿򲼾�
		
		jp1.add(adminName);//���˺��ı�����ӵ�jp1������
		jp1.add(name);
		jp1.add(password);
		jp1.add(pass);
		
		panel.add(jp1);
		
		login = new JButton("��¼");
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
				String word = "123456";	// ��ȷ����
				String str = new String(password.getPassword());
				
				if(str.equals(word))
					new LendAdminFrame().show();
//					new TableFrame().show();
//					new RuKuFrame();
				else{
					String str1 = "����������벻��ȷ��ԭ������ǣ�\n" +  "1���������룻\n" + "2��δ����С���̣�\n" + "3����Сдδ���֡�";
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
