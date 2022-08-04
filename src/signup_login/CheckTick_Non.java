package signup_login;
import buyTicket.BuyTicket_List_N;

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

import com.kgv.dao.Memdb_DAO;

import main.Main;
import setting.SetColor;
import setting.SetFont;
public class CheckTick_Non extends JFrame {

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

	
	JPanel loginBar = new JPanel();
	JButton login = new JButton("회원 로그인");
	JButton toHome = new JButton("처음으로");
	
	JLabel title = new JLabel("비회원 예매확인");
	
	
	JPanel info = new JPanel();
	JLabel birth = new JLabel("생년월일(8자리)");
	JLabel phone = new JLabel("휴대폰 번호");
	JLabel pwd = new JLabel("비밀번호(6자리)");
	
	JPanel inputField = new JPanel();
	JTextField input1 = new JTextField();	//생년월일
	JTextField input2 = new JTextField();	//폰번호
	JPasswordField input3 = new JPasswordField();	//비번

	
	JButton chkBtn = new JButton("예매 확인");
	
	SetFont font = new SetFont();
	SetColor color = new SetColor();
	
	CheckTick_Non() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		setUndecorated(true); // 윈도우창 표시
		setSize(600, 600); // 프레임 사이즈
		setLocationRelativeTo(scp); // 중앙에 배치
		setLayout();
		init();
		setVisible(true);
	}

	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);
		
		loginBar.setOpaque(false);
		loginBar.setBounds(110,150,400,37);
		loginBar.setLayout(new GridLayout(1, 2, 20, 0)); //로그인 바 패널
		
		login.setOpaque(true);
		login.setBackground(color.color_blue);
		login.setForeground(Color.white);;
		login.setFont(font.f2_18);
		login.setHorizontalAlignment(JLabel.CENTER);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				setVisible(false);
			}
		});

		
		
		toHome.setOpaque(true);
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
		
		title.setBounds(60,215,300,60);
		title.setFont(font.f2_30);
		
		info.setOpaque(false);
		info.setBounds(100,300,180,210);
		info.setLayout(new GridLayout(4, 1, 0, 30)); 
		
		birth.setFont(font.f3_16);
		phone.setFont(font.f3_16);
		pwd.setFont(font.f3_16);
		
		inputField.setOpaque(false);
		inputField.setBounds(280,300,220,210);
		inputField.setLayout(new GridLayout(4, 1, 0, 30));
		
		
		chkBtn.setBounds(230,520,150,40);
		chkBtn.setBackground(color.color_gray);
		chkBtn.setForeground(Color.WHITE);
		chkBtn.setFont(font.f3_14);
		chkBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				checkInfo();
//				setVisible(false);
			}
		});

	}

	public void checkInfo(){						//비회원 조회
		Memdb_DAO dao = new Memdb_DAO();
		
		String birth = input1.getText();			//input1 => 생일, input2 =>핸드폰 번호, input3 => 비밀번호
		String phone = input2.getText();
		String pwd = new String(input3.getPassword());
		
		int answer = dao.noMemCheck(birth, phone, pwd);
		
		if(answer ==1) {
			new BuyTicket_List_N();
			setVisible(false);
		}else {
			JOptionPane.showMessageDialog(this, "회원정보를 찾을수 없습니다!\n -정보를 다시 확인해주세요-","확인 메시지",JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void init() {
		add(title);

		loginBar.add(login);
		loginBar.add(toHome);
		add(loginBar);
		
		info.add(birth);
		info.add(phone);
		info.add(pwd);
		add(info);
		
		inputField.add(input1);
		inputField.add(input2);
		inputField.add(input3);
		add(inputField);
		
		add(chkBtn);
	}
	public static void main(String[] args) {
		new CheckTick_Non();
	}
	

}