package signup_login;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.kgv.dao.Memdb_DAO;
import com.kgv.dto.Memdb_DTO;
import main.Main;
import setting.SetColor;
import setting.SetFont;


public class Signup extends JFrame implements ActionListener {
	
	Memdb_DAO dao = new Memdb_DAO();
	Memdb_DTO dto ;
	
	JScrollPane scrollPane;
	
	//배경 이미지
	ImageIcon icon = new ImageIcon("images/background_1.jpg");							
	BufferedImage img = null; 
	
	SetFont ft = new SetFont();//글씨체
	SetColor col = new SetColor();//색상
	
	public JPanel contentPane;
	public JTextField text_name,text_id,text_email,text_hp;
	public JPasswordField text_pw,text_pwchk;
	
	public Signup() {
		setTitle("회원가입");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setUndecorated(true);
		setVisible(true);
		setBounds(100, 100, 700, 720); //창 크기 조절
		setLocationRelativeTo(null); // 중앙에 배치
		
		//배경 이미지
	    JPanel background = new JPanel() {
	       @Override
	       public void paintComponent(Graphics g) {
	          g.drawImage(icon.getImage(),0,0,null);
	          setOpaque(false); //그림을 표시하게 설정, 투명하게 조절
	          super.paintComponent(g);
	       }
	    };
	    background.setLayout(null);
	    scrollPane = new JScrollPane(background);
	    setContentPane(scrollPane);
	    
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255,255));
		panel.setBounds(90, 111, 506, 514); 
		background.add(panel);
		panel.setLayout(null);
		
		JLabel lb_title = new JLabel("KGV 회원가입");
		lb_title.setForeground(col.color_red);
		lb_title.setFont(ft.f1_22);
		lb_title.setBounds(185, 18, 160, 44);
		panel.add(lb_title);
		
		JLabel lb_name = new JLabel("이름");
		lb_name.setFont(ft.f1_16);
		lb_name.setBounds(83, 77, 43, 18);
		panel.add(lb_name);
		
	
		JLabel lb_id = new JLabel("아이디");
		lb_id.setFont(ft.f1_16);
		lb_id.setBounds(72, 151, 54, 18);
		panel.add(lb_id);
		
		JLabel lb_pw = new JLabel("비밀번호");
		lb_pw.setFont(ft.f1_16);
		lb_pw.setBounds(52, 210, 74, 18);
		panel.add(lb_pw);
		
		JLabel lb_pwchk = new JLabel("비밀번호 확인");
		lb_pwchk.setFont(ft.f1_16);
		lb_pwchk.setBounds(24, 268, 114, 18);
		panel.add(lb_pwchk);
		
		JLabel lb_email = new JLabel("이메일");
		lb_email.setFont(ft.f1_16);
		lb_email.setBounds(78, 327, 60, 18);
		panel.add(lb_email);
		
		JLabel lb_hp = new JLabel("휴대전화");
		lb_hp.setFont(ft.f1_16);
		lb_hp.setBounds(64, 394, 74, 18);
		panel.add(lb_hp);
		
		//이름
		text_name = new JTextField();
		text_name.setBounds(140, 69, 235, 39);
		panel.add(text_name);
		text_name.setColumns(10);
		

		//아이디
		text_id = new JTextField();
		text_id.setBounds(140, 143, 235, 39);
		panel.add(text_id);
		text_id.setColumns(10);
		
			
		//패스워드
		text_pw = new JPasswordField();
		text_pw.setBounds(140, 202, 235, 39);
		panel.add(text_pw);
		text_pw.setColumns(10);
		

		
		//pw 확인
		text_pwchk = new JPasswordField();
		text_pwchk.setBounds(140, 260, 235, 39);
		panel.add(text_pwchk);
		text_pwchk.setColumns(10);
		
	
		
		//이메일
		text_email = new JTextField();
		text_email.setBounds(140, 319, 235, 39);
		panel.add(text_email);
		text_email.setColumns(10);
		
		
		//핸드폰
		text_hp = new JTextField();
		text_hp.setBounds(140, 386, 235, 39);
		panel.add(text_hp);
		text_hp.setColumns(10);
		


		
		JButton btn_idchk = new JButton("확인");
		btn_idchk.setBackground(col.color_blue);
		btn_idchk.setForeground(new Color(255, 255, 255));
		btn_idchk.setFont(ft.f3_14);
		btn_idchk.setBounds(389, 143, 89, 27);
		panel.add(btn_idchk);
		btn_idchk.addActionListener(this); //추가한것
		
		JButton btn_chk = new JButton("인증");
		btn_chk.setBackground(col.color_blue);
		btn_chk.setFont(ft.f3_14);
		btn_chk.setBounds(392, 392, 86, 27);
		btn_chk.setForeground(new Color(255, 255, 255));
		panel.add(btn_chk);
		btn_chk.addActionListener(this); //인증 버튼 클릭시 핸드폰 유효성 검사

			
		
		JButton btn_signup = new JButton("가입하기");
		btn_signup.setForeground(new Color(255, 255, 255));
		btn_signup.setBackground(col.color_gray);
		btn_signup.setFont(ft.f1_18);
		btn_signup.setBounds(261, 452, 127, 39);
		panel.add(btn_signup);
		btn_signup.addActionListener(this);//추가한거
		
		JButton btn_main = new JButton("처음으로");
		btn_main.setForeground(new Color(255, 255, 255));
		btn_main.setFont(ft.f1_18);
		btn_main.setBackground(col.color_gray);
		btn_main.setBounds(120, 452, 127, 39);
		panel.add(btn_main);
		
		
        //main 버튼 누르면 main으로 돌아가기
		btn_main.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false); //창 안보이게 하기
			}
		});
		
		//배경 이미지 삽입
		JScrollPane layeredPane = new JScrollPane();
		layeredPane.setSize(1000,720);
		layeredPane.setLayout(null);
		

	}//Signup()
	


	
	//유효성 검사 시작
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = e.getActionCommand();
			if(btn.equals("가입하기")) { //작성 후 가입하기 버튼을 누르면 유효성 검사를 시작하게 
				String name = text_name.getText();
				String id = text_id.getText();
				String pwd = new String(text_pw.getPassword());
				String pwdchk = new String(text_pwchk.getPassword());
				
				if(name.equals("")){
					JOptionPane.showMessageDialog(this, "이름을 입력해주세요!","이름 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(id.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요!","아이디 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(pwd.equals("")) {
					JOptionPane.showMessageDialog(this, "비밀번호를 입력해주세요!","비밀번호 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}else if(pwd.length()<6|| pwd.length()>20) {
					JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상 20이하로 입력해주세요!","비밀번호 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(pwdchk.equals("")) {
					JOptionPane.showMessageDialog(this, "재확인 비밀번호를 입력해주세요!","비밀번호 체크 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(!pwd.equals(pwdchk)) {
					JOptionPane.showMessageDialog(this,"비밀번호가 일치하지 않습니다","비밀번호 체크 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(pwd_Check(pwd)==1) { //pwd_Check 메소드 불러옴
					JOptionPane.showMessageDialog(this, "영어 숫자 조합으로 입력해주세요","비밀번호 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(text_email.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "이메일을 입력해주세요","이메일 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(email_Check(text_email.getText())==1) {
					JOptionPane.showMessageDialog(this, "이메일 형식을 지켜주세요!","이메일 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					dto = new Memdb_DTO(text_id.getText(),text_name.getText(),text_pw.getText(),
					text_email.getText(),text_hp.getText());
					
					int result = dao.Signup(dto);
					if(result == 1) { //회원등록 성공
						JOptionPane.showMessageDialog(this, "회원가입에 성공했습니다!!","회원가입 완료 메시지",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(this, "회원가입에 실패했습니다!!","회원가입 실패 메시지",JOptionPane.INFORMATION_MESSAGE);
					}
					dispose();
					new Main(); //회원가입 후 로그인창으로 넘어가게
				}
			}else if(btn.equals("확인")) { //아이디 중복 확인 버튼 클릭 시
				String idChk = text_id.getText(); 
				if(idChk.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력해주세요","아이디 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(idChk.length() < 6 || idChk.length() > 10) {
					JOptionPane.showMessageDialog(this, "아이디는 6자 이상 15자리 이하로 해주세요","아이디 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else if(id_Check(idChk) == 1) { //id_Check 메서드 불러옴
					JOptionPane.showMessageDialog(this, "아이디는 특수문자를 제외하고 입력해주세요","아이디 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int result = dao.id_Check(text_id.getText());
					
					if(result==0) {
						JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다","아이디 중복확인 메시지",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(this, "중복된 아이디 입니다","아이디 중복확인 메시지",JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}else if(btn.equals("인증")) { //핸드폰 인증 버튼
				String hpChk = text_hp.getText();
				if(hpChk.equals("")) {
					JOptionPane.showMessageDialog(this, "핸드폰 번호를 입력해주세요","핸드폰 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}else if(hp_Check(hpChk) != 1) {
					JOptionPane.showMessageDialog(this, "-를 넣어 입력해주세요!","핸드폰 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}else if(hp_Check(hpChk)==1) {
					JOptionPane.showMessageDialog(this, "인증이 성공되었습니다!","핸드폰 확인 메시지",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}//action Performed();
	
	public int id_Check(String id) { //아이디 특수문자 체크 메서드
		int re = 0;
		char ch;
		int code;
		for(int i = 0; i< id.length();i++) {
			ch = id.charAt(i);
			code = (int)ch;
			if(code>=0 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127){
				re = 1;
			}
		}
		return re;
	}
	
	public int pwd_Check(String pw) { //pw 유효성 검사 메서드
		int re = 0;
		char ch;
		int code;
		for(int i = 0; i< pw.length();i++) {
			ch = pw.charAt(i);
			code = (int)ch;
			if(code>=0 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127){
				re = 1;
			}
		}
		return re;
	}
	
	public int email_Check(String email) { //이메일 유효성 검사 메서드 '@','.' 이 들어가는가
		int re = 0;
		if (text_email.getText().matches("\\d{3}-\\d{4}-\\d{4}")){
			re = 1;
		}
		return re;
	}
	
	public int hp_Check(String hp) { //휴대전화 유효성 검사 메서드
		int re = 0;
		if(text_hp.getText().matches("\\d{3}-\\d{4}-\\d{4}")) {
			re = 1;
		}
		return re;
	}
	

}//Signup
