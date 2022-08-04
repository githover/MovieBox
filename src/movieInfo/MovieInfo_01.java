package movieInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import com.kgv.dao.Movie_info_DAO;
import com.kgv.dto.Movie_info_DTO;

import main.Main;
import setting.SetColor;
import setting.SetFont;
public class MovieInfo_01 extends JFrame{
		Movie_info_DAO dao = new Movie_info_DAO();
		Movie_info_DTO dto= dao.selectMovie("헤어질 결심");
		BufferedImage img = null;
		
		private JPanel panel;
		SetFont ft = new SetFont();
		SetColor c = new SetColor();
		JButton button = new JButton("뒤로가기");

		public MovieInfo_01() {
		
			setTitle("영화정보");
			setBounds(100,100,1280,720);
			setLocationRelativeTo(null); // 중앙에 배치
			setLayout(null);//
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setUndecorated(true); // 윈도우창 표시

		//배경화면
		ImageIcon back = new ImageIcon("./Images/background_1.jpg");
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(back.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		}; panel.setBounds(0, 0, 1280, 720);
		
		//버튼
		
		button.setBackground(c.color_blue);
		button.setForeground(Color.white);
		button.setFont(ft.f1_18);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setBounds(580, 645, 125, 34);
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Main();
				setVisible(false);
			}
		});
		
		//박스
		JPanel rec01 = new JPanel() {
			public void paint(Graphics g) {
				g.drawRect(0, 0, 710, 100);
			}
		};
		rec01.setBounds(440,210,1000,1000);
		
		JPanel rec02 = new JPanel() {
			public void paint(Graphics g) {
			g.drawRect(0, 0, 710, 300);	
			}
		};
		rec02.setBounds(440,320,1000,1000);
		
		//영화 포스터 헤어질 결심 포스터
		ImageIcon icon = new ImageIcon("./Images/"+dto.getB_Poster());
		JLabel img = new JLabel(icon);
		img.setBounds(-20, 140, 500, 500);
	
		//레이블_헤어질 결심 01
		JLabel title = new JLabel("헤어질 결심");
		title.setFont(ft.f3_40); //폰트변경
		title.setBounds(450, 150, 500, 50);
		
		//레이블_헤어질 결심 정보 02
		JTextArea label02 = new JTextArea("\n감독 : 박찬욱  /  프로듀서 : 백지선 ,  고대석  /  배우 : " + 
				"탕웨이 ,  박해일 ,  이정현 ,  박용우 ,  고경표 , 김신영" + "\r\n" + 
				"장르 : 로맨스, 멜로, 드라마  \n" +
				"기본 : 15세 이상, 138분, 한국\n" + 
				"개봉 : 2022.06.29");
		label02.setFont(new Font("210 맨발의청춘Regular",Font.BOLD, 15)); //폰트변경
		label02.setBounds(450, 200, 1000, 500);
		
		//레이블_헤어질 결심 정보 03
		JTextArea label03 = new JTextArea("산 정상에서 추락한 한 남자의 변사 사건.\n담당 형사 '해준'(박해일)은\n사망자의 아내 '서래'(탕웨이)와 마주하게 된다.\n"
				+ "\"산에 가서 안 오면 걱정했어요, 마침내 죽을까 봐.\"\n\n남편의 죽음 앞에서 특별한 동요를 보이지 않는 '서래'\n경찰은 보통의 유가족과는 다른 '서래'를 용의선상에 올린다.\n'해준'은 사건 당일의 알리바이 탐문과 신문,\r\n" + 
				"잠복수사를 통해 '서래'를 알아가면서\r\n" + 
				"그녀에 대한 관심이 점점 커져가는 것을 느낀다."
				+ "\n\n진심을 숨기는 용의자\n용의자에게 의심과 관심을 동시에 느끼는 형사\n그들의 <헤어질 결심>");
		label03.setFont(new Font("NanumSquareRoundR",Font.BOLD, 15)); //폰트변경
		label03.setBounds(450, 320, 1000, 200);
		
		//프레임에 추가
		add(button);
		add(rec01);
		add(rec02);
		add(img);
		add(title);
		add(label03);
		add(label02);
		add(panel);
		
		//프레임 설정
		setResizable(false);//사이즈 고정
		setVisible(true);
	}
		
}
