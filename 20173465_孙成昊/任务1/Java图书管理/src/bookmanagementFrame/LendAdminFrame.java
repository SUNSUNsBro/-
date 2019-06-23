package bookmanagementFrame;
 

/**
 * ����Ա���ܽ���
 * ��ͼ�������Ϣģ��
 * �ڽ�����Ϣ����ģ��
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
	/*��ƹ���Ա����*/
	public LendAdminFrame(){
		this.setTitle("����Ա");
		this.setBounds(400,300,200,200);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		storage = new JButton("ͼ��������");
		lendInfo = new JButton("������Ϣ����");
		
		this.add(storage);
		this.add(lendInfo);
		
		MyEvent();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/*ͨ��Ϊ������ťע���¼�����������ͼ�����ͽ�����������ת*/
	public void MyEvent(){
		// ͼ��������
		storage.addActionListener(new ActionListener(){//Ϊͼ��������ťע���¼�����
 
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent arg0) {//��Ӧ����
				// TODO Auto-generated method stub
				new TableFrame(storage.getText()).show();
			}
			
		});
		
		// �û�������Ϣ����
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
