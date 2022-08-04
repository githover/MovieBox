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
public class MovieInfo_03 extends JFrame{
		Movie_info_DAO dao = new Movie_info_DAO();
		Movie_info_DTO dto= dao.selectMovie("마녀2");
		BufferedImage img = null;
		
		private JPanel panel;
		SetFont ft = new SetFont();
		SetColor c = new SetColor();
		JButton button = new JButton("뒤로가기");

		public MovieInfo_03() {//마녀2
		
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
		JLabel title = new JLabel("마녀2");
		title.setFont(ft.f3_40); //폰트변경
		title.setBounds(450, 150, 500, 50);
		
		//레이블_헤어질 결심 정보 02
		JTextArea label02 = new JTextArea("\n감독 : 박훈정  / 배우 : " + 
				"신시아,  박은빈,  서은수, 진구, 성유빈, 조민수 " + "\n" + 
				"장르 : 액션  \n" +
				"기본 : 15세 이상, 137분, 한국\n" + 
				"개봉 : 2022.06.15");
		label02.setFont(new Font("210 맨발의청춘Regular",Font.BOLD, 15)); //폰트변경
		label02.setBounds(450, 200, 1000, 500);
		
		//레이블_헤어질 결심 정보 03
		JTextArea label03 = new JTextArea("’자윤’이 사라진 뒤,\n 정체불명의 집단의 무차별 습격으로 마녀 프로젝트가 진행되고 있는 ‘아크’가 초토화된다.\n"
				+ "그곳에서 홀로 살아남은 ‘소녀’는 생애 처음 세상 밖으로 발을 내딛고\n"
				+ " 우연히 만난 ‘경희’의 도움으로 농장에서 지내며 따뜻한 일상에 적응해간다.\n\n"
				+ "한편, ‘소녀’가 망실되자 행방을 쫓는 책임자 ‘장’과 마녀 프로젝트의 창시자 ‘백총괄’의 지령을 받고 \n"
				+ "제거에 나선 본사 요원 ‘조현’, "
				+ "‘경희’의 농장 소유권을 노리는 조직의 보스 ‘용두’와 \n"
				+ "상해에서 온 의문의 4인방까지 각기 다른 목적을 지닌 세력이 하나 둘 모여들기 시작하면서 \n\n"
				+ "‘소녀’ 안에 숨겨진 본성이 깨어나는데… 모든 것의 시작, 더욱 거대하고 강력해진 마녀가 온다.\n\n<영화 마녀2>");
		label03.setFont(new Font("NanumSquareRoundR",Font.BOLD, 15)); //폰트변경
		label03.setBounds(450, 340, 1000, 500);
		
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
