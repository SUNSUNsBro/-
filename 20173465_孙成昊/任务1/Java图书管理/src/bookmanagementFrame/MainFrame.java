package bookmanagementFrame;
 

/*�����棬ͼ���ѯ
 * 
 * ʵ�� 
 * ��ͼ���ѯ
 * ���û�ע����ʾ
 * �۹���Ա��½��ʾ
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
	JPanel jpanel,jp1,jp2,jp3;//�ĸ�����
	JTextField text;
	JButton search,admin,stu;//�ֱ�Ϊ����ѯ������Ա���û���½ ��ť
	
	//JButton lend;
	DefaultTableModel tableModel;
	
	public MainFrame(){
		this.setLayout(new BorderLayout());
		this.setBounds(400, 200, 600, 450);
		this.setTitle("��ӭ����ͼ�����ϵͳ");
	
		//����������Ĳ���
		label1 = new JLabel("ͼ���ѯ",SwingConstants.CENTER);
		label1.setFont(new Font("����",Font.BOLD,40));		//��������ʹ�С
		
		//�����м�Ĳ���
		label2 = new JLabel("ͼ��:");//JLabel���� ���û�������ʾ
		
		text = new JTextField(15);//���ò�ѯ��ť
		search = new JButton("��ѯ");//
		
		jpanel = new JPanel();
		jpanel.setLayout(new BorderLayout());
		jp1 = new JPanel();//������Ҫ���֣�ͼ���ѯ���ܴ���
		jp2 = new JPanel();		//����������Ĳ��֣�����ʾ��ѯ���ݵĵط���
//		jp2.setBackground(Color.BLUE);
		
		
		/*��������ť��Ϊ�û���½ָ��*/
		stu = new JButton("�û���¼");
		admin = new JButton("����Ա");
		
//		//����
//		lend = new JButton("ȷ�Ͻ���");
		
		/*�� 
		 * �� ����ͼ����
		 * �� ��ȡͼ�����ı���
		 * �� ��ѯ�ı�
		 * ��ӵ���һ������
		 * */
		jp1.add(label2);
		jp1.add(text);
		jp1.add(search);
//		jp1.add(lend);
				
		jp3 = new JPanel();
		jp3.setLayout(new FlowLayout(FlowLayout.RIGHT));//���Ҿ���
		/*�� ������½��ʾ��ת��ť��ӵ�����������*/
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
		// ��ѯ��ť�¼�
		search.addActionListener(new ActionListener(){//Ϊ��ѯ��ťע���¼�����
 
			@Override
			public void actionPerformed(ActionEvent arg0) {//��Ӧ����
				// TODO Auto-generated method stub
				new FindBook().findInfo(jp2,text);//����FindBook.java �е�FindBook()����
			}
			
		});
		
		// ����Ա��ť�¼�
		admin.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new LoginFrame().show();//����Ա��½����
			}
			
		});
		//�û���ť�¼�
		stu.addActionListener(new ActionListener(){
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				new UserLoginFrame().show();//��ͨ�û���¼����
			}
			
		});
	}
	
	public static void main(String[] args){//������������  ��ҳ�洰�尴ť
		new MainFrame();
	}
}
