package signup_login;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.kgv.dao.Memdb_DAO;
import com.kgv.dto.Memdb_DTO;

import main.Main;
import setting.SetFont;



class Find_IdPw extends JFrame {
	
	SetFont ft = new SetFont();
	
	JTextField input_id = new JTextField();
	JTextField input_name = new JTextField();
	JTextField input_email = new JTextField();
	JTextField input_phone = new JTextField();
	JTextField input_pwd = new JTextField();
	
	// 배경이미지 생성
	ImageIcon icon = new ImageIcon("images/background_1.jpg");
	JPanel bground = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(icon.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
	JScrollPane scp = new JScrollPane(bground);
	
	
		Find_IdPw() {
			
	
			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		//setUndecorated(true); // 윈도우창 표시

		setSize(480, 720); // 프레임 사이즈
		setLocationRelativeTo(scp); // 중앙에 배치

		setLayout();
		radio();
		button();
		textBox();
		texts();

		setVisible(true);
		
		JPanel inputField = new JPanel();
		
		inputField.setOpaque(false);
	    inputField.setBounds(150,260,250,250);
	    inputField.setLayout(new GridLayout(4, 1, 0, 40));
	    bground.add(inputField);
	    
	    JTextField input_id = new JTextField();
	    input_id.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_id);
	    
	    JTextField input_name = new JTextField();
	    input_name.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_name);
	    
	    JTextField input_email = new JTextField();
	    input_email.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_email);
	    
	    JTextField input_email1 = new JTextField();
	    input_email1.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_email1);
	    
	    add(inputField);
	}
		
		
	public void texts() {
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 250, 110, 250);
		panel.setLayout(null);
		bground.add(panel);
		
		JPanel title_p = new JPanel();
		title_p.setOpaque(false);
		title_p.setBounds(0, 100, 400, 60);
		title_p.setLayout(null);
		bground.add(title_p);
		
		JLabel title = new JLabel("아이디/패스워드 찾기");
		title.setBounds(20,10,300,60);
	    title.setFont(ft.f2_30);
	    title_p.add(title);
		
		JLabel id = new JLabel("아이디");
		id.setFont(ft.f1_16);
		id.setBounds(40, 0, 100, 50);
		panel.add(id);
		
		JLabel name = new JLabel("이름");
		name.setFont(ft.f1_16);
		name.setBounds(40, 70, 100, 50);
		panel.add(name);
		
		JLabel email = new JLabel("이메일");
		email.setFont(ft.f1_16);
		email.setBounds(40, 140, 100, 50);
		panel.add(email);
		
		JLabel phone = new JLabel("전화번호");
		phone.setFont(ft.f1_16);
		phone.setBounds(40, 210, 100, 50);
		panel.add(phone);
	}
	
	public void radio() {
																			//아이디 찾기를 위한 찾기버튼
		JPanel Btn2 = new JPanel();
		Btn2.setLayout(new GridLayout(1, 2, 20, 0));
		Btn2.setOpaque(false);
		Btn2.setBounds(85,600,300,37);
		Btn2.setVisible(false);
		bground.add(Btn2);
		
		JButton findBtn2 = new JButton("찾기");
		JButton backBtn2 = new JButton("뒤로가기");
		
		findBtn2.setFont(ft.f1_18);
		findBtn2.setBackground(Color.black); // 배경색상 변경
		findBtn2.setForeground(Color.white); // 글씨색상 변경
		findBtn2.setBorderPainted(false); // 테두리제거
		findBtn2.setFocusPainted(false); // 선택테두리 제거
		findBtn2.setBounds(100,200,500,80);
		findBtn2.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          if(e.getSource() == findBtn2) {
	        	  find_id();
	          }
	         }
	      });
		Btn2.add(findBtn2);
		
		
		backBtn2.setFont(ft.f1_18);
		backBtn2.setBackground(Color.black); // 배경색상 변경
		backBtn2.setForeground(Color.white); // 글씨색상 변경
		backBtn2.setBorderPainted(false); // 테두리제거
		backBtn2.setFocusPainted(false); // 선택테두리 제거
		backBtn2.setBounds(0,100,120,50);
		backBtn2.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            new Login();
	            setVisible(false);
	         }
	      });
		Btn2.add(backBtn2);
		add(Btn2);
		
		
		ButtonGroup group = new ButtonGroup();
		
		JPanel panel01 = new JPanel();
		panel01.setBackground(new Color(255, 255,255));
		//panel01.setOpaque(true);
		panel01.setBounds(0, 250, 450, 60);
		panel01.setLayout(null);
		
		input_pwd.setBounds(150, 11, 250, 32); 
		panel01.add(input_pwd);
		
		JLabel pwd = new JLabel("비밀번호");
		pwd.setFont(ft.f1_16);
		pwd.setBounds(40, 0, 100, 50);
		panel01.add(pwd);
		bground.add(panel01);
		
		JPanel raBar = new JPanel();
	    raBar.setOpaque(false);
		raBar.setBounds(30,180,400,37);
	    raBar.setLayout(new GridLayout(1, 2, 5, 0)); //로그인 바 패널
	    bground.add(raBar);
		
		JRadioButton radio01 = new JRadioButton("비밀번호 찾기");
		radio01.setSelected(true);//디폴트로 선택되어있음.
		radio01.setFont(ft.f3_16);
		radio01.setBackground(new Color(255, 255,255));
		radio01.setHorizontalAlignment(JLabel.CENTER);
		radio01.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio01) {
					Btn2.setVisible(false);
					panel01.setVisible(false);
				}
			}
		});
		
		raBar.add(radio01);
		group.add(radio01);
		
		JRadioButton radio02 = new JRadioButton("아이디 찾기");
		radio02.setFont(ft.f3_16);
		radio02.setBackground(new Color(255, 255,255));
		radio02.setHorizontalAlignment(JLabel.CENTER);
		
		panel01.setVisible(false);
		radio02.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio02) {
					panel01.setVisible(true);
					Btn2.setVisible(true);
				}
			}
		});

		raBar.add(radio02);
		group.add(radio02);
	}
	
	public void textBox() {
		JPanel inputField = new JPanel();
		
		inputField.setOpaque(false);
	    inputField.setBounds(150,260,250,250);
	    inputField.setLayout(new GridLayout(4, 1, 0, 40));
	    bground.add(inputField);
	    
	    
	    input_id.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_id);
	    
	    
	    input_name.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_name);
	    
	    
	    input_email.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_email);
	    
	    
	    input_phone.setHorizontalAlignment(JLabel.LEFT);
	    inputField.add(input_phone);
	    
	    add(inputField);
	}
	
	public void button() {
		
		JPanel Btn = new JPanel();
		Btn.setLayout(new GridLayout(1, 2, 20, 0));
		Btn.setOpaque(false);
		Btn.setBounds(85,600,300,37);
		//Btn.setBackground(new Color(255, 255,255));
		bground.add(Btn);
		
		JButton backBtn = new JButton("뒤로가기"); 
		JButton findBtn = new JButton("찾기");
		
		findBtn.setFont(ft.f1_18);
		findBtn.setBackground(Color.black); // 배경색상 변경
		findBtn.setForeground(Color.white); // 글씨색상 변경
		findBtn.setBorderPainted(false); // 테두리제거
		findBtn.setFocusPainted(false); // 선택테두리 제거
		findBtn.setBounds(0,100,120,50);
		findBtn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	          if(e.getSource() == findBtn) {
	        	  find_pw();
	          }
	         }
	      });
		Btn.add(findBtn);
		add(Btn);
		
		backBtn.setFont(ft.f1_18);
		backBtn.setBackground(Color.black); // 배경색상 변경
		backBtn.setForeground(Color.white); // 글씨색상 변경
		backBtn.setBorderPainted(false); // 테두리제거
		backBtn.setFocusPainted(false); // 선택테두리 제거
		backBtn.setBounds(0,100,120,50);
		backBtn.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent e) {
	            new Login();
	            setVisible(false);
	         }
	      });
		Btn.add(backBtn);
		add(Btn);
	}
	
	public void find_pw() {
		Memdb_DAO dao = new Memdb_DAO();
		
		String id = input_id.getText();	
		String name = input_name.getText();
		String email = input_email.getText();
		String phone = input_phone.getText();
		
		if(id.equals("")) {
			JOptionPane.showMessageDialog(this, "아이디를 입력해주세요!","아이디 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요!","이름 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else if(email.equals("")) {
			JOptionPane.showMessageDialog(this, "이메일을 입력해주세요!","이메일 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else if(phone.equals("")) {
			JOptionPane.showMessageDialog(this,"전화번호를 입력해주세요!","연락처 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else {
			 String answer = dao.pwFinder(id, name, email, phone);
			 if(answer != null) {
				 JOptionPane.showMessageDialog(this,"비밀번호는 "+answer+" 입니다.","확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				 setVisible(false);
				 new Login();
			 }else {
				 JOptionPane.showMessageDialog(this,"비밀번호를 찾을수 없습니다.","확인 메시지",JOptionPane.INFORMATION_MESSAGE);
			 }
		}
	}
	
	public void find_id() {
		Memdb_DAO dao = new Memdb_DAO();
		
		String pwd = input_pwd.getText();
		String name = input_name.getText();
		String email = input_email.getText();
		String phone = input_phone.getText();
		
		if(pwd.equals("")) {
			JOptionPane.showMessageDialog(this, "패스워드를 입력해주세요!","이름 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else if(name.equals("")) {
			JOptionPane.showMessageDialog(this, "이름을 입력해주세요!","이름 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else if(email.equals("")) {
			JOptionPane.showMessageDialog(this, "이메일을 입력해주세요!","이메일 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else if(phone.equals("")) {
			JOptionPane.showMessageDialog(this,"전화번호를 입력해주세요!","연락처 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}else {
			 String answer = dao.idFinder(pwd ,name, email, phone);
			 if(answer != null) {
				 JOptionPane.showMessageDialog(this,"아이디는 "+answer+" 입니다.","확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				 setVisible(false);
				 new Login();
			 }else {
				 JOptionPane.showMessageDialog(this,"아이디를 찾을수 없습니다.","확인 메시지",JOptionPane.INFORMATION_MESSAGE);
			 }
		}
	}
	
	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);
		
	}
}