package signup_login;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.kgv.dao.No_Imsi_DAO;
import com.kgv.dao.No_Memdb_DAO;
import com.kgv.dto.No_Memdb_DTO;

import buyTicket.BuyTicket_SelMovie;
import main.Main;
import setting.SetColor;
import setting.SetFont;

public class BuyTicket_Non extends JFrame implements ActionListener{
	//DAO 생성자 ###
	No_Imsi_DAO no_imsiDAO = new No_Imsi_DAO();
	No_Memdb_DTO no_dto = new No_Memdb_DTO();
	No_Memdb_DAO no_dao = new No_Memdb_DAO();
	
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
	
	JButton toHome = new JButton("처음으로");
	
	JLabel title = new JLabel("비회원 예매");
	
	
	JPanel info = new JPanel();
	JLabel birth = new JLabel("생년월일(8자리)");
	JLabel phone = new JLabel("휴대폰 번호");
	JLabel pwd = new JLabel("비밀번호(6자리)");
	JLabel pwdChk = new JLabel("비밀번호확인");

	JPanel inputField = new JPanel();
	public JTextField input1 = new JTextField(""); //생년월일(8자리)
	public JTextField input2 = new JTextField("");	//휴대폰번호
	public JPasswordField input3 = new JPasswordField(""); //비밀번호 (6자리)
	public JPasswordField input4 = new JPasswordField(""); //비밀번호 확인

	JButton nBtn = new JButton("예매"); //예매 클릭시 데이터 넘어가게 구현
	
	
	SetFont font = new SetFont();
	SetColor color = new SetColor();
	
	public BuyTicket_Non() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		setUndecorated(true); // 윈도우창 표시
		setSize(550, 600); // 프레임 사이즈
		setLocationRelativeTo(scp); // 중앙에 배치
		setLayout();
		init();
		setVisible(true);
		
		nBtn.addActionListener(this); //예매버튼에 이벤트 등록###
	}

	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);
		
		
		
		toHome.setOpaque(true);
		toHome.setBounds(320,480,150,40);
		toHome.setBackground(color.color_blue);
		toHome.setForeground(Color.white);;
		toHome.setFont(font.f2_18);
		toHome.setHorizontalAlignment(JLabel.CENTER);
		toHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Main();	
				setVisible(false);
			}
		});
		title.setBounds(60,115,300,60);
		title.setFont(font.f2_30);
		
		info.setOpaque(false);
		info.setBounds(100,200,180,210);
		info.setLayout(new GridLayout(4, 1, 0, 30)); 
		
		birth.setFont(font.f3_16);
		phone.setFont(font.f3_16);
		pwd.setFont(font.f3_16);
		pwdChk.setFont(font.f3_16);

		inputField.setOpaque(false);
		inputField.setBounds(250,200,220,210);
		inputField.setLayout(new GridLayout(4, 1, 0, 30));
		
		
		nBtn.setBounds(120,480,150,40);
		nBtn.setBackground(color.color_gray);
		nBtn.setForeground(Color.WHITE);
		nBtn.setFont(font.f3_18);
	}

	public void init() {
		add(title);

		add(toHome);
		
		info.add(birth);
		info.add(phone);
		info.add(pwd);
		info.add(pwdChk);

		add(info);
		
		inputField.add(input1);
		inputField.add(input2);
		inputField.add(input3);
		inputField.add(input4);

		add(inputField);
		
		add(nBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = e.getActionCommand();
			if(btn.equals("예매")){
				String birth = input1.getText(); //테이블에서 String으로 바꾸기
				String hp = input2.getText();
				String pwd = new String(input3.getPassword());
				String pwdchk = new String(input4.getPassword());
				//유효성 검사
				if(birth.equals("")) {
					JOptionPane.showMessageDialog(this, "생년월일을 입력해주세요!","생년월일 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(birth.length()!=8) {
					JOptionPane.showMessageDialog(this, "생년월일은 8자리로 입력해주세요!","생년월일 확인 메시지",JOptionPane.INFORMATION_MESSAGE);

				}
				else if(hp.equals("")) {
					JOptionPane.showMessageDialog(this, "휴대전화번호를 입력새주세요!","휴대전화 확인 메시지",JOptionPane.INFORMATION_MESSAGE);

				}
				else if(hp_Check(input2.getText())!=1) { //hp_Check 메소드 들어감
					JOptionPane.showMessageDialog(this, "- 를 넣어 입력해 주세요!","휴대전화 확인 메시지",JOptionPane.INFORMATION_MESSAGE);

				}else if(pwd.equals("")) {
					JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요!","비밀번호 확인 메시지",JOptionPane.INFORMATION_MESSAGE);

				}
				else if(pwd.length()!=6){
					JOptionPane.showMessageDialog(this, "비밀번호는 6자리로 입력해주세요!","비밀번호 체크 메시지",JOptionPane.INFORMATION_MESSAGE);

				}else if(!pwd.equals(pwdchk)) {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다!","비밀번호 체크 메시지",JOptionPane.INFORMATION_MESSAGE);

				}else if(pwd_Check(pwd)==1) {//pwd_Check 메소드 들어감
					JOptionPane.showMessageDialog(this, "비밀번호는 영어+숫자 조합으로 입력해주세요!","비밀번호 체크 메시지",JOptionPane.INFORMATION_MESSAGE);
				}else {
			
					int result = no_dao.No_Signup(hp, birth, pwd);
					if(result == 1) { //비회원 등록 성공
						JOptionPane.showMessageDialog(this, "비회원가입에 성공했습니다!!","비회원가입 완료 메시지",JOptionPane.INFORMATION_MESSAGE);
						
					}else {
						JOptionPane.showMessageDialog(this, "비회원가입에 실패했습니다!!","비회원가입 실패 메시지",JOptionPane.INFORMATION_MESSAGE);
					}
					no_imsiDAO.logincheck(this);
					dispose();
					new BuyTicket_SelMovie();
					
					// 로그인 유효성 검사 이벤트가 등록되면, 
					// 유효성 검사 이후에 실행되도록 imsiDAO.logincheck(this); 만 추가하면 됨.
					//System.out.println("버튼이벤트 실행 됨");
					//new BuyTicket_SelMovie(); //예매로 넘어가게
				}	
			}
		}
		
	}//action Performed();
	
	public int hp_Check(String hp) {
		int re = 0;
		if(input2.getText().matches("\\d{3}-\\d{4}-\\d{4}")) {
			re = 1;
		}
		return re;
	}
	 
	public int pwd_Check(String pwd) {//pw유효성 검사
		int re = 0;
		char ch;
		int code;
		for(int i = 0; i<pwd.length();i++) {
			ch = pwd.charAt(i);
			code = (int)ch;
			if(code>=0 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				re = 1;
			}
		}
		return re;
	}
	

}