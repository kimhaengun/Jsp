package address.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import address.dao.UserDao;

public class AddFrame extends JFrame{

	MainFrame mainFrame;
	AddFrame addFrame = this;
	
	JLabel nameLabel, phoneLabel, addressLabel, groupLabel;
	JTextField nameTF, phoneTF, addressTF, groupTF;  
	JButton addButton;
	
	Container backgroundPanel; // 원래 있는 JFrame이 가지고 있는 패널
	JPanel addPanel;
	
	
	public AddFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setTitle("주소록 추가");
		setSize(300, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		initObject();
		
		initDesign();
		
		initListener();
		
		setVisible(true);
	}
	
	void initDesign() {
		backgroundPanel.setLayout(new BorderLayout());	
		addPanel.setLayout(new GridLayout(4,2));
		
		addPanel.add(nameLabel);
		addPanel.add(nameTF);
		addPanel.add(phoneLabel);
		addPanel.add(phoneTF);
		addPanel.add(addressLabel);
		addPanel.add(addressTF);
		addPanel.add(groupLabel);
		addPanel.add(groupTF);
		
		backgroundPanel.add(addPanel, BorderLayout.CENTER);
		backgroundPanel.add(addButton, BorderLayout.SOUTH);
	}
	
	void initObject() {
		backgroundPanel = getContentPane();
		addPanel = new JPanel();
		
		nameLabel = new JLabel("이름");
		phoneLabel = new JLabel("전화번호");
		addressLabel = new JLabel("주소");
		groupLabel = new JLabel("그룹");
		
		nameTF = new JTextField();
		phoneTF = new JTextField();
		addressTF = new JTextField();
		groupTF = new JTextField();
		
		addButton = new JButton("추가하기");
	}
	
	void initListener() {
		
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("추가하기 버튼 클릭됨");
				
				String name = nameTF.getText();
				String phone = phoneTF.getText();
				String address = addressTF.getText();
				String relation = groupTF.getText();
				
				System.out.println(name);
				System.out.println(phone);
				System.out.println(address);
				System.out.println(relation);
				
				UserDao.추가하기(name, phone, address, relation);
				addFrame.dispose(); // 창닫기
				mainFrame.initData();
				mainFrame.setVisible(true); // 메인프레임 보이게 하기
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				mainFrame.setVisible(true);
			}
		});
	}
}



