package buyTicket;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.kgv.dao.Imsi_DAO;
import com.kgv.dao.M_Ticketdb_DAO;
import com.kgv.dao.Movie_info_DAO;
import com.kgv.dao.No_Imsi_DAO;
import com.kgv.dao.No_Ticketdb_DAO;
import com.kgv.dto.Imsi_DTO;
import com.kgv.dto.M_Ticketdb_DTO;
import com.kgv.dto.Movie_info_DTO;
import com.kgv.dto.No_Imsi_DTO;
import com.kgv.dto.No_Ticketdb_DTO;

import main.Main;
import main.Main_logon;
import setting.SetColor;
import setting.SetFont;
class BuyTicket_Result extends JFrame {

	Imsi_DTO imsi_DTO;
	Imsi_DAO imsi_DAO = new Imsi_DAO(); // jdbc 연결
	No_Imsi_DTO no_imsi_DTO;
	No_Imsi_DAO no_imsi_DAO = new No_Imsi_DAO();
	M_Ticketdb_DAO mdao = new M_Ticketdb_DAO();
	M_Ticketdb_DTO mdto;
	No_Ticketdb_DAO ndao = new No_Ticketdb_DAO();
	No_Ticketdb_DTO ndto;
	
	Movie_info_DAO movieDAO= new Movie_info_DAO();
	Movie_info_DTO movieDTO;
	
	int setPrice;
	String price;
	
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
	//영화 포스터 이미지
	ImageIcon icon2; 
	JLabel poster = new JLabel();

	
	//상단 타이틀
	JLabel title = new JLabel("예매가 완료되었습니다.");

	//중앙 티켓 정보 패널
	JPanel tinfo = new JPanel();
		JLabel info1 = new JLabel("예매번호 : ");	
		JLabel info1_1 = new JLabel();
		JLabel info2 = new JLabel("영        화 : ");
		JLabel info2_1 = new JLabel();
		JLabel info3 = new JLabel("일        시 : ");
		JLabel info3_1 = new JLabel();
		JLabel info3_2 = new JLabel();
		JLabel info4 = new JLabel("좌        석 : ");
		JLabel info4_1 = new JLabel();
		JLabel info4_2 = new JLabel();
		JLabel info5 = new JLabel("결제금액 : ");
		JLabel info5_1 = new JLabel();
		JLabel info6 = new JLabel("결제수단 : ");
		JLabel info6_1 = new JLabel("카드");

		
	//하단 버튼
	JButton toHome = new JButton("처음으로");
	SetFont f = new SetFont();
	SetColor c = new SetColor();
	BuyTicket_Result() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		setUndecorated(true); // 윈도우창 표시
		setSize(1280, 720); // 프레임 사이즈
		setLocationRelativeTo(scp); // 중앙에 배치
		set();
		setLayout();
		init();
		setVisible(true);
	}

	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);
		
		poster.setOpaque(true);
		poster.setIcon(icon2);
		poster.setBounds(250,200,230,289);
		
		title.setBounds(50,90,400,100);
		title.setFont(f.f3_30);
		tinfo.setLayout(null);					//티켓 정보 패널
		tinfo.setBounds(600, 180, 350, 320);	
		
		info1.setOpaque(true);
		info1.setLocation(20,20);
		info1.setSize(80,20);
		info1.setFont(f.f3_16);	
		info1_1.setOpaque(true);
		info1_1.setLocation(105,20);
		info1_1.setSize(100,20);
		info1_1.setFont(f.f3_16);
		
		info2.setOpaque(true);
		info2.setLocation(20,70);
		info2.setSize(80,20);
		info2.setFont(f.f3_16);
		
		info2_1.setOpaque(true);
		info2_1.setLocation(105,70);
		info2_1.setSize(200,20);
		info2_1.setFont(f.f3_16);
		
		info3.setOpaque(true);
		info3.setLocation(20,120);
		info3.setSize(80,20);
		info3.setFont(f.f3_16);
		
		info3_1.setOpaque(true);
		info3_1.setLocation(105,120);
		info3_1.setSize(120,20);
		info3_1.setFont(f.f3_16);
		
		info3_2.setOpaque(true);
		info3_2.setLocation(102,140);
		info3_2.setSize(120,20);
		info3_2.setFont(f.f3_16);
		
		info4.setOpaque(true);
		info4.setLocation(20,170);
		info4.setSize(80,20);
		info4.setFont(f.f3_16);
		
		info4_1.setOpaque(true);
		info4_1.setLocation(105,170);
		info4_1.setSize(200,20);
		info4_1.setFont(f.f3_16);
		info4_2.setOpaque(true);
		info4_2.setLocation(105,190);
		info4_2.setSize(200,20);
		info4_2.setFont(f.f3_16);
		
		info5.setOpaque(true);
		info5.setLocation(20,220);
		info5.setSize(80,20);
		info5.setFont(f.f3_16);
		info5_1.setLocation(105,220);
		info5_1.setSize(180,20);
		info5_1.setFont(f.f3_16);
		
		
		
		info6.setOpaque(true);
		info6.setLocation(20,270);
		info6.setSize(80,20);
		info6.setFont(f.f3_16);
		info6_1.setLocation(105,270);
		info6_1.setSize(90,20);
		info6_1.setFont(f.f3_16);
		
		toHome.setFont(f.f1_18);
		toHome.setBackground(Color.RED);
		toHome.setForeground(Color.white);
		toHome.setBorderPainted(false);
		toHome.setFocusPainted(false);
		toHome.setBounds(520, 618, 125, 34);
	}

	public void init() {
		//상단 타이틀 추가
		add(title);
		//영화포스터 추가
		add(poster);
		//중앙 티켓 내역
		tinfo.add(info1);
		tinfo.add(info1_1);
		tinfo.add(info2);
		tinfo.add(info2_1);
		tinfo.add(info3);
		tinfo.add(info3_1);
		tinfo.add(info3_2);
		tinfo.add(info4);
		tinfo.add(info4_1);
		tinfo.add(info4_2);
		tinfo.add(info5);
		tinfo.add(info5_1);
		tinfo.add(info6);
		tinfo.add(info6_1);
		add(tinfo);
				
		//하단 버튼 추가
		add(toHome);
	}
	
	public void set() {
		imsi_DTO = imsi_DAO.imsi_select(); // 임시 테이블에 저장된 값 불러오기
		no_imsi_DTO = no_imsi_DAO.imsi_select();

		if(imsi_DTO.getM_id()!=null) {
			/*
			 * m_ticketdb_DAO 사용하여서 m_ticketdb에 저장
			 */
			
			info1_1.setText(imsi_DTO.getTicket_num());
			info2_1.setText(imsi_DTO.getTitle());
			info3_1.setText(imsi_DTO.getTicket_date());
			info3_2.setText(imsi_DTO.getTicket_time());
			info4_1.setText(imsi_DTO.getSeat());
			info4_2.setText("(성인 : "+imsi_DTO.getAd_num()+"명, 청소년 : "+imsi_DTO.getCh_num()+"명)");
			movieDTO=movieDAO.selectMovie(imsi_DTO.getTitle());

			DecimalFormat df = new DecimalFormat("###,###");		//영화 금액 연동
			setPrice = (imsi_DTO.getAd_num() * 12000 + imsi_DTO.getCh_num() * 9000);
			price = df.format(setPrice) + "원 (회원할인)";
			info5_1.setText(price);
		}
		else if(no_imsi_DTO.getNo_phone()!=null) {
			
			
			info1_1.setText(no_imsi_DTO.getTicket_num());
			info2_1.setText(no_imsi_DTO.getTitle());
			info3_1.setText(no_imsi_DTO.getTicket_date());
			info3_2.setText(no_imsi_DTO.getTicket_time());
			info4_1.setText(no_imsi_DTO.getSeat());
			info4_2.setText("(성인 : "+no_imsi_DTO.getAd_num()+",명 청소년 : "+no_imsi_DTO.getCh_num()+"명)");

			movieDTO=movieDAO.selectMovie(no_imsi_DTO.getTitle());

			
			DecimalFormat df = new DecimalFormat("###,###");		//영화 금액 연동
			setPrice = no_imsi_DTO.getAd_num() * 13000 + no_imsi_DTO.getCh_num() * 10000;
			price = df.format(setPrice) + "원";
			info5_1.setText(price);
		}
		String str = movieDTO.getB_Poster();
		icon2 = new ImageIcon("images/"+str);
		System.out.println(str);
		toHome.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(imsi_DTO.getM_id()!=null) {
					imsi_DAO.reset(imsi_DTO.getM_id());
					new Main_logon();

				}
				else if(no_imsi_DTO.getNo_phone()!=null) {
					no_imsi_DAO.reset(no_imsi_DTO.getNo_phone());	
					new Main();

				}
				
				setVisible(false); //창 안보이게 하기
			}
		});
	}
	
	

}