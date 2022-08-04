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

import com.kgv.dao.Imsi_DAO;
import com.kgv.dao.Movie_info_DAO;
import com.kgv.dao.No_Imsi_DAO;
import com.kgv.dto.Movie_info_DTO;

import movieInfo.*;
import setting.SetColor;
import setting.SetFont;


public class Main_Admin extends JFrame {
	
	Movie_info_DAO dao = new Movie_info_DAO();
	Movie_info_DTO dto; 
	

    String[] buttons = {"로그인", "비회원 예매"};
    
	//배경 이미지
	ImageIcon icon = new ImageIcon("./images/background_1.jpg");
	public JPanel contentPane;
	
	SetFont ft = new SetFont(); //글씨 패키지 사용
	SetColor col = new SetColor(); //색상 패키지 사용

	public Main_Admin() {
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
	    setContentPane(background);
		JButton btn_login = new JButton("로그아웃");
		btn_login.setBounds(700, 280, 200, 200);
		background.add(btn_login);
		btn_login.setForeground(Color.white);
		btn_login.setFont(ft.f1_16);
		btn_login.setBackground(col.color_blue);
		
		JButton btn_signup = new JButton("관리자페이지");
		btn_signup.setBounds(350, 280, 200, 200);
		background.add(btn_signup);
		btn_signup.setForeground(Color.white);
		btn_signup.setFont(ft.f1_16);
		btn_signup.setBackground(col.color_blue);
		
		//버튼 누를 시 예매확인 리스트 창으로 전환
		btn_signup.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new Admin();
				setVisible(false); 
			}
		});  
		
		btn_login.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "메인페이지로 이동");
				Imsi_DAO idao = new Imsi_DAO();
				idao.imsi_delete();
				No_Imsi_DAO nidao = new No_Imsi_DAO();
				nidao.imsi_delete();
				new Main();
				setVisible(false);
			}
		});  
		
		
		
	}//Main()
	
}
