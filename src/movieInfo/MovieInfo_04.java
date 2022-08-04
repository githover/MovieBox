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
public class MovieInfo_04 extends JFrame{
		Movie_info_DAO dao = new Movie_info_DAO();
		Movie_info_DTO dto = dao.selectMovie("명탐정 코난-할로윈의 신부");
		BufferedImage img = null;
		
		private JPanel panel;
		SetFont ft = new SetFont();
		SetColor c= new SetColor();
		JButton button = new JButton("뒤로가기");

		public MovieInfo_04() {
		
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
		JLabel title = new JLabel("코난");
		title.setFont(ft.f3_40); //폰트변경
		title.setBounds(450, 150, 500, 50);
		
		//레이블_헤어질 결심 정보 02
		JTextArea label02 = new JTextArea("\n감독 : 미츠나카 스스무 " +
				"장르 : 애니메이션  \n" +
				"기본 : 12세 이상, 110분, 일본\n" + 
				"개봉 : 2022.07.13");
		label02.setFont(new Font("210 맨발의청춘Regular",Font.BOLD, 15)); //폰트변경
		label02.setBounds(450, 200, 1000, 500);
		
		//레이블_헤어질 결심 정보 03
		JTextArea label03 = new JTextArea("트리플 페이스 아무로 토오루, 그의 목에 폭탄이 채워졌다?!\n" + 
				" \n" + 
				"극악무도한 폭파범 ‘플라먀’에 의해 교묘한 함정에 빠진 아무로 토오루!\n" + 
				"모든 것이 베일에 가려진 ‘플라먀’,\n" + 
				"유일한 단서는 아무로 토오루가 그의 경찰 동기들과 마지막으로 함께 했던 하루.\n" + 
				"이와 동시에 결혼식의 신부가 된 경시청의 사토 형사는 불길한 예감을 감출 수 없는데…\n" + 
				" \n" + 
				"도시 전체를 인질로 삼은 ‘플라먀’,\n" + 
				"그리고 이를 막으려 하는 아무로 토오루와 명탐정 코난의 숨 막히는 공조!\n" + 
				"\n" + 
				"다시 시작된 카운트다운, 할로윈의 밤이 광란으로 빛난다!");
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
