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
public class MovieInfo_02 extends JFrame{
		Movie_info_DAO dao = new Movie_info_DAO();
		Movie_info_DTO dto= dao.selectMovie("뒤틀린집");
		BufferedImage img = null;
		
		private JPanel panel;
		SetFont ft = new SetFont();
		SetColor c = new SetColor();
		JButton button = new JButton("뒤로가기");

		public MovieInfo_02() {//뒤틀린집
		
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
		JLabel title = new JLabel("뒤틀린집");
		title.setFont(ft.f3_40); //폰트변경
		title.setBounds(450, 150, 500, 50);
		
		//레이블_헤어질 결심 정보 02
		JTextArea label02 = new JTextArea("\n감독 : 강동헌  / 배우 : " + 
				"서영희 ,  김보민 ,  김민재 " + "\n" + 
				"장르 : 호러, 드라마, 스릴러  \n" +
				"기본 : 15세 이상, 91분, 한국\n" + 
				"개봉 : 2022.07.13");
		label02.setFont(new Font("210 맨발의청춘Regular",Font.BOLD, 15)); //폰트변경
		label02.setBounds(450, 200, 1000, 500);
		
		//레이블_헤어질 결심 정보 03
		JTextArea label03 = new JTextArea("“이 집에 틈이 있다는 거.. 아세요?”\n" + 
				"\r\n" + 
				"피치 못할 사정으로 외딴집에 이사 오게 된 가족.\n" + 
				"엄마 ‘명혜’는 이사 온 첫 날부터 이 집이 뒤틀렸다고 전하는 이웃집 여자의 경고와\n" + 
				"창고에서 들리는 불길한 소리로 인해 밤잠을 설친다.\n" + 
				"아빠 ‘현민’은 그런 ‘명혜’를 신경쇠약으로만 여기고,\n" + 
				"둘째 딸 ‘희우’는 가족들이 보지 못하는 무언가를 마주하지만 그 사실을 숨긴다.\n" + 
				"그러던 어느 날, 알 수 없는 기운에 이끌려 잠겨 있던 창고문을 열고 만 명혜는\n" + 
				"무언가에 사로잡힌 듯 다른 사람처럼 행동하기 시작하는데…\n" + 
				"\n" + 
				"뒤틀린 틈에서 시작된 비극이 가족을 집어삼키려 한다!");
		label03.setFont(new Font("NanumSquareRoundR",Font.BOLD, 15)); //폰트변경
		label03.setBounds(450, 320, 1000, 500);
		
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
