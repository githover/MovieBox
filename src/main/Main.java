package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kgv.dao.Movie_info_DAO;
import com.kgv.dto.Movie_info_DTO;

import movieInfo.*;
import setting.SetColor;
import setting.SetFont;
import signup_login.BuyTicket_Non;
import signup_login.Login;
import signup_login.Signup;


public class Main extends JFrame {
	
	Movie_info_DAO dao = new Movie_info_DAO();
	Movie_info_DTO dto; 
	

    String[] buttons = {"로그인", "비회원 예매"};
    
	//배경 이미지
	ImageIcon icon = new ImageIcon("./images/background_1.jpg");
	public JPanel contentPane;
	
	SetFont ft = new SetFont(); //글씨 패키지 사용
	SetColor col = new SetColor(); //색상 패키지 사용

	public Main() {
		setTitle("Mainpage"); //창 타이틀
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720); //창 크기
		setLocationRelativeTo(contentPane); // 중앙에 배치
		setUndecorated(true);

		setVisible(true);
		//배경 이미지
		 JPanel background = new JPanel() {
		       
		       public void paintComponent(Graphics g) {
		          g.drawImage(icon.getImage(),0,0,null);
		          setOpaque(false); //그림을 표시하게 설정, 투명하게 조절
		          super.paintComponent(g);
		       }
		    };

	    background.setLayout(null);

	    
	    JButton end = new JButton("EXIT");		//임시 버튼
	    end.setFont(ft.f2_18);
	    end.setBounds(1130, 620, 90, 40);
	    end.setBackground(col.color_red);
	    end.setBorderPainted(false);
	    end.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
	    	background.add(end);	    
	    
	    
	    
	    
	    
	    
	    
	    setContentPane(background);
		   
		JLabel lbTitle = new JLabel("*포스터를 클릭해서 영화정보를 확인하세요");
		lbTitle.setFont(ft.f1_22);
		lbTitle.setBounds(76, 123, 465, 56);
		background.add(lbTitle);
		
		JButton btn_m1 = new JButton();
		btn_m1.setBounds(67, 226, 230, 289);
		background.add(btn_m1);
		dto=dao.selectMovie("헤어질 결심");
		btn_m1.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
		
		btn_m1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovieInfo_01();			
				setVisible(false);
			}
		});
		
		JButton btn_m2 = new JButton();
		btn_m2.setBounds(352, 226, 221, 289);
		background.add(btn_m2);
		dto=dao.selectMovie("뒤틀린집");
		btn_m2.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
		btn_m2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovieInfo_02();			
				setVisible(false);
			}
		});
		JButton btn_m3 = new JButton();
		btn_m3.setBounds(618, 226, 230, 289);
		background.add(btn_m3);
		dto=dao.selectMovie("마녀2");
		btn_m3.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
		
		btn_m3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovieInfo_03();			
				setVisible(false);
			}
		});
		JButton btn_m4 = new JButton();
		btn_m4.setBounds(888, 226, 230, 289);
		background.add(btn_m4);
		dto=dao.selectMovie("명탐정 코난-할로윈의 신부");
		btn_m4.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
		
		btn_m4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MovieInfo_04();			
				setVisible(false);
			}
		});
		JButton btn_ticket = new JButton("예매");
		btn_ticket.setBounds(870, 25, 114, 40);
		background.add(btn_ticket);
		btn_ticket.setFont(ft.f1_16);
		btn_ticket.setForeground(Color.white);
		btn_ticket.setBackground(col.color_blue);
		btn_ticket.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		        int num = JOptionPane.showOptionDialog(null, "로그인이 필요한 서비스입니다", "알림",
		        		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.NO_OPTION, null, buttons, "로그인");
		        if(num==0)	{new Login(); setVisible(false);}
		        if(num==1)	{new BuyTicket_Non();setVisible(false);}
		        
				
			}
		});
	
		
		JButton btn_login = new JButton("로그인");
		btn_login.setBounds(1000, 25, 114, 40);
		background.add(btn_login);
		btn_login.setForeground(Color.white);
		btn_login.setFont(ft.f1_16);
		btn_login.setBackground(col.color_blue);
		
		JButton btn_signup = new JButton("회원가입");
		btn_signup.setBounds(1128, 25, 114, 40);
		background.add(btn_signup);
		btn_signup.setForeground(Color.white);
		btn_signup.setFont(ft.f1_16);
		btn_signup.setBackground(col.color_blue);
		
		//버튼 누를 시 회원가입 창으로 전환
		btn_signup.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Signup();
				setVisible(false); 
			}
		});  
		
		btn_login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Login();
				setVisible(false); 
			}
		});  
		
		
		
	}//Main()
	
	public static void main(String[] args) {
		new Main();
	}
}
