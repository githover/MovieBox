package buyTicket;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;

import setting.SetColor;
import setting.SetFont;

import com.kgv.dao.Imsi_DAO;
import com.kgv.dao.Movie_info_DAO;
import com.kgv.dao.No_Imsi_DAO;
import com.kgv.dto.Imsi_DTO;
import com.kgv.dto.Movie_info_DTO;
import com.kgv.dto.No_Imsi_DTO;

import main.Main;

public class BuyTicket_Seats extends JFrame {
	
	Imsi_DAO imsi_DAO = new Imsi_DAO(); // jdbc 연결
	Imsi_DTO imsi_DTO;

	No_Imsi_DAO no_imsi_DAO = new No_Imsi_DAO();
	No_Imsi_DTO no_imsi_DTO;

	Movie_info_DAO movieDAO= new Movie_info_DAO();
	Movie_info_DTO movieDTO;
	
	int setPrice, ad_num, ch_num, ticket_num, tmp, tmp_ad, tmp_ch;
	String price, selSeats = "";
	ImageIcon icon;
	JScrollPane scp;
	JButton reset, bBtn, nBtn;
	JPanel bground, sel, ticket, info8, seatIndex, seat;
	Choice combo1, combo2;
	ImageIcon icon2;
	JLabel adult, child, mPoster, info1, info2, info4, info5, info6, info7, info9,numMsg;
	JLabel[] sel_seat = new JLabel[6];
	ArrayList<JLabel> sIndex = new ArrayList<>();
	String[] ch = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" }; // 좌석 인덱스 12개
	String[] msg = { "취소", "확인" };
	ArrayList<JButton> seats;
	String[] arr = new String[120]; // 좌석명 배열 저장
	EtchedBorder eborder;
	SetFont f;
	SetColor c;

	BuyTicket_Seats() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		setUndecorated(true); // 윈도우창 표시
		setSize(1280, 720); // 프레임 사이즈
		setLocationRelativeTo(scp); // 중앙에 배치
		set();
		setLayout();
		init();
		eventHandling();
		setVisible(true);

	}

	public void set() {
		imsi_DTO = imsi_DAO.imsi_select(); // 임시 테이블에 저장된 값 불러오기
		no_imsi_DTO = no_imsi_DAO.imsi_select();

		icon = new ImageIcon("images/background_1.jpg");
		bground = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		eborder = new EtchedBorder(EtchedBorder.LOWERED);// 테두리 폼
		scp = new JScrollPane(bground);
		// 초기화 버튼
		reset = new JButton("초기화");
		// 인원선택 패널
		sel = new JPanel();
		adult = new JLabel("  성인");
		child = new JLabel("청소년");
		numMsg = new JLabel("(최대 6명까지 선택가능)");
		combo1 = new Choice();
		combo2 = new Choice();
		for(int i=0; i<6;i++) {
			if(i==0) {
				combo1.add("select");
				combo2.add("select");
			}else {
			combo1.add(""+i);
			combo2.add(""+i);}
		}
		ticket = new JPanel(); // 우측 예매확인 패널
		mPoster = new JLabel();
		if (imsi_DTO.getM_id() != null) {
			info1 = new JLabel(imsi_DTO.getTitle());
			info2 = new JLabel(imsi_DTO.getTheater());
			info4 = new JLabel(imsi_DTO.getTicket_date());
			info5 = new JLabel(imsi_DTO.getTicket_time());
			movieDTO=movieDAO.selectMovie(imsi_DTO.getTitle());
		} else if (no_imsi_DTO.getNo_phone() != null) {
			info1 = new JLabel(no_imsi_DTO.getTitle());
			info2 = new JLabel(no_imsi_DTO.getTheater());
			info4 = new JLabel(no_imsi_DTO.getTicket_date());
			info5 = new JLabel(no_imsi_DTO.getTicket_time());
			movieDTO=movieDAO.selectMovie(no_imsi_DTO.getTitle());

		}
		String poster = movieDTO.getS_poster();
		icon2 = new ImageIcon("images/"+poster);

		info6 = new JLabel("<선택좌석>");
		info7 = new JLabel("최종가격");
		info8 = new JPanel();

		info9 = new JLabel();
		setPrice(); // 총 가격 설정

		seatIndex = new JPanel();// 좌측 좌석 index 패널
		// 중앙 좌석 배치도 패널
		seat = new JPanel();
		seats = new ArrayList<JButton>();
		String[] arr = new String[120]; // 좌석명 배열 저장
		// 하단 버튼
		bBtn = new JButton("< PRE");
		nBtn = new JButton("결제하기 >");
		f = new SetFont();
		c = new SetColor();

	}

	public void eventHandling() {
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pageReset();
			}
		});

		combo1.addItemListener(new ItemListener() { // 성인 매수 설정

			@Override
			public void itemStateChanged(ItemEvent e) {
				ticket_num += ch_num;

				Choice cb = (Choice) e.getSource();
				String str = (String) cb.getSelectedItem();
				try {
					ad_num = Integer.parseInt(str);
					ticket_num = ad_num + ch_num;
					tmp = ad_num + ch_num;

					tmp_ad = ad_num;

//					if (ticket_num > 6 || sel_seat[0].getText() != "X" || sel_seat[1].getText() != "X"
//							|| sel_seat[2].getText() != "X" || sel_seat[3].getText() != "X"
//							|| sel_seat[4].getText() != "X" || sel_seat[5].getText() != "X") {
//						pageReset();
//					}
					
					if (ticket_num > 6) {
						JOptionPane.showMessageDialog(null, "관람인원이 너무 많습니다");
						pageReset();


					}
							
					else if(sel_seat[0].getText() != "X" || sel_seat[1].getText() != "X"
							|| sel_seat[2].getText() != "X" || sel_seat[3].getText() != "X"
							|| sel_seat[4].getText() != "X" || sel_seat[5].getText() != "X") {
						JOptionPane.showMessageDialog(null, "초기화");

						pageReset();
					}
					
					
					
					
					System.out.println("성인 클릭후 좌석 개수 :" + tmp);

				} catch (Exception e1) {
					ad_num = 0;
				}
			}
		});
		combo2.addItemListener(new ItemListener() { // 청소년 매수 설정
			@Override
			public void itemStateChanged(ItemEvent e) {
				ticket_num = ad_num;

				Choice cb = (Choice) e.getSource();
				String str = (String) cb.getSelectedItem();

				try {

					ch_num = Integer.parseInt(str);

					ticket_num = ad_num + ch_num;
					tmp = ad_num + ch_num;

					tmp_ch = ch_num;
//
//					if (ticket_num > 6 || sel_seat[0].getText() != "X" || sel_seat[1].getText() != "X"
//							|| sel_seat[2].getText() != "X" || sel_seat[3].getText() != "X"
//							|| sel_seat[4].getText() != "X" || sel_seat[5].getText() != "X") {
//						pageReset();
//					}
					
					if (ticket_num > 6) {
						JOptionPane.showMessageDialog(null, "관람인원이 너무 많습니다");
						pageReset();
					}
							
					else if(sel_seat[0].getText() != "X" || sel_seat[1].getText() != "X"
							|| sel_seat[2].getText() != "X" || sel_seat[3].getText() != "X"
							|| sel_seat[4].getText() != "X" || sel_seat[5].getText() != "X") {
						pageReset();
					}

					System.out.println("청소년 클릭후 좌석 개수 :" + tmp);
				} catch (Exception e1) {
					ch_num = 0;
				}
			}
		});
		bBtn.addActionListener(new ActionListener() { // 이전 페이지로 이동

			@Override
			public void actionPerformed(ActionEvent e) {
				new BuyTicket_SelMovie();
				setVisible(false);
			}
		});
		nBtn.addActionListener(new ActionListener() { // 결제 페이지로 이동

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(selSeats);
				System.out.println(ad_num);
				System.out.println(ch_num);
				/**
				 * if 원하는 정보를 다 받았으면 db에 insert 후 다음페이지로 이동
				 **/
				if (selSeats == "" || tmp_ad!=0 || tmp_ch!=0) {
					JOptionPane.showMessageDialog(null, "인원 수에 맞는 좌석을 선택하세요");
					pageReset();
				}
				else {
				String str=ticketNumber();
				
				if (imsi_DTO.getM_id() != null) {
					imsi_DAO.updateTicket(ad_num, ch_num, selSeats,str);
					new BuyTicket_Pay();
					setVisible(false);
				} else if (no_imsi_DTO.getNo_phone() != null) {
					no_imsi_DAO.updateTicket(ad_num, ch_num, selSeats,str);
					new BuyTicket_Pay();
					setVisible(false);
				}
			}
			}
		});
		for (int i = 0; i < arr.length; i++) { // 좌석 선택 이벤트 생성
			seats.get(i).addActionListener(listener3);
		}

	}

	ActionListener listener3 = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < arr.length; i++) {
				seats.get(i).addActionListener(e2 -> {
				});
				if (e.getSource() == seats.get(i)) {

					for (int j = 0; j < tmp; j++) {
						if (sel_seat[j].getText() == "X" && seats.get(i).getBackground() == c.color_yellow) {
							seats.get(i).setBackground(c.color_red);
							sel_seat[j].setText(arr[i]);

							selSeats += sel_seat[j].getText() + " ";

							if (tmp_ad > 0) {
								tmp_ad--;
							} else {
								tmp_ch--;
							}

							setPrice();
//							System.out.println(sel_seat[j].getText() + " 좌석 선택완료");
							ticket_num--;
//							System.out.println("성인 개수:" + ad_num + "청소년 개수 :" + ch_num);
//							System.out.println("현재 좌석 : " + selSeats);
//							System.out.println(" 선택후 ticket_num : " + ticket_num);
//							System.out.println("=======================");
							break;
						} else if (sel_seat[j].getText() == arr[i]) {
							seats.get(i).setBackground(c.color_yellow);
							selSeats = selSeats.replaceAll(sel_seat[j].getText() + " ", "");

							if (tmp_ch < ch_num) {
								tmp_ch++;
							} else {
								tmp_ad++;
							}

							setPrice();

//							System.out.println(sel_seat[j].getText() + " 좌석 취소완료");
//							System.out.println("현재 좌석 : " + selSeats);
							ticket_num++;
//							System.out.println("성인 개수:" + ad_num + "청소년 개수 :" + ch_num);
//							System.out.println(" 취소후ticket_num : " + ticket_num);
//							System.out.println("=======================");

							sel_seat[j].setText("X");

							break;
						}
					}
				}

			}
		}
	};

	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);

		reset.setBounds(1100, 50, 75, 30); // 초기화 버튼
		reset.setBackground(c.color_blue);
		reset.setFont(f.f3_14);

		sel.setLayout(null);
		sel.setBounds(90, 110, 700, 60); // 상단 인원 선택 패널 사이즈
		sel.setBackground(c.color_light2);

		adult.setOpaque(true); // 성인 인원선택
		adult.setLocation(50, 10);
		adult.setFont(f.f3_16);
		adult.setBorder(eborder);
		adult.setSize(50, 40);
		combo1.setFont(f.f3_18);
		combo1.setLocation(140, 15);
		combo1.setSize(95, 200);
		combo1.setBackground(c.color_light1);

		child.setOpaque(true); // 청소년 인원 선택
		child.setLocation(300, 10);
		child.setFont(f.f3_16);
		child.setBorder(eborder);
		child.setSize(60, 40);

		combo2.setFont(f.f3_18);
		combo2.setLocation(400, 15);
		combo2.setSize(95, 200);
		combo2.setBackground(c.color_light1);

		numMsg.setOpaque(true);
		numMsg.setLocation(525, 10);
		numMsg.setFont(f.f2_16);
		numMsg.setSize(200, 40);

		seatIndex.setBounds(90, 200, 50, 360); // 좌측 index
		seatIndex.setLayout(new GridLayout(12, 1));

		for (int i = 0; i < ch.length; i++) { // 반복문으로 라벨을 seatIndex 패널에 붙임
			sIndex.add(new JLabel("    " + ch[i]));
			sIndex.get(i).setBorder(eborder);
			sIndex.get(i).setFont(f.f3_14);
			seatIndex.add(sIndex.get(i));

		}

		seat.setBounds(190, 200, 570, 360); // 좌석표
		seat.setLayout(new GridLayout(12, 12));

		int a = 0; // 좌석표 번호 매기기
		for (int i = 0; i < arr.length; i += 10) {
			for (int j = 0; j < 10; j++) {
				arr[i + j] = ch[a];
			}
			a++;

		}

		for (int i = 0; i < arr.length; i++) { // 반복문으로 좌석버튼을 패널에 붙임
			if ((i + 1) % 10 == 0)
				arr[i] += "" + 10;
			else
				arr[i] += "" + (i + 1) % 10;

			seats.add(new JButton(arr[i]));
			seats.get(i).setBackground(c.color_yellow);
			seats.get(i).setBorder(eborder);
			seats.get(i).setFont(f.f3_14);
			seat.add(seats.get(i));
		}

		ticket.setLayout(null);
		ticket.setBackground(c.color_light2); // 우측 티켓 패널
		ticket.setBounds(900, 110, 300, 450); // 우측 티켓 패널 사이즈

		mPoster.setOpaque(true); // 영화 포스터
		mPoster.setIcon(icon2);
		mPoster.setBounds(155, 15, 121, 173);
		
		info1.setOpaque(true);
		info1.setLocation(20, 10); // 영화제목
		info1.setSize(120, 50);
		info1.setFont(f.f2_22);
		info1.setBackground(c.color_light2);

		info2.setLocation(35, 70); // 지역
		info2.setSize(100, 50);
		info2.setFont(f.f3_16);
		info4.setLocation(20, 100); // 날짜
		info4.setSize(200, 50);
		info4.setFont(f.f3_16);
		info5.setLocation(15, 130); // 시간
		info5.setSize(120, 50);
		info5.setFont(f.f3_16);
		info6.setLocation(175, 190); // <선택좌석>
		info6.setSize(120, 30);
		info6.setFont(f.f3_16);

		info7.setLocation(30, 400); // 최종가격
		info7.setSize(70, 30);
		info7.setFont(f.f3_18);

		info8.setOpaque(false);
		info8.setLocation(150, 230); // 선택 좌석 패널
		info8.setSize(130, 160);
		info8.setLayout(new GridLayout(3, 2, 10, 10));

		for (int i = 0; i < sel_seat.length; i++) {// 선택좌석 라벨 추가
			sel_seat[i] = new JLabel("X");
			sel_seat[i].setHorizontalAlignment(JLabel.CENTER);
			sel_seat[i].setOpaque(true);
			sel_seat[i].setBorder(eborder);
			sel_seat[i].setFont(f.f3_22);
			sel_seat[i].setBackground(c.color_blue);

		}

		info9.setLocation(220, 400); // 영화금액
		info9.setSize(120, 30);
		info9.setFont(f.f3_16);

		bBtn.setFont(f.f1_18);
		bBtn.setBackground(Color.black); // 배경색상 변경
		bBtn.setForeground(Color.white); // 글씨색상 변경
		bBtn.setBorderPainted(false); // 테두리제거
		bBtn.setFocusPainted(false); // 선택테두리 제거
		bBtn.setBounds(419, 617, 125, 34);

		nBtn.setFont(f.f1_18);
		nBtn.setBackground(Color.RED);
		nBtn.setForeground(Color.white);
		nBtn.setBorderPainted(false);
		nBtn.setFocusPainted(false);
		nBtn.setBounds(618, 618, 125, 34);

	}

	public void init() {
		// 상단 인원 선택 패널
		sel.add(adult);
		sel.add(combo1);
		sel.add(child);
		sel.add(combo2);
		sel.add(numMsg);
		add(sel);
		// 초기화 버튼
		add(reset);
		// 좌측 좌석 인덱스
		add(seatIndex);
		// 중앙 좌석표
		add(seat);
		// 우측 티켓 확인
		ticket.add(mPoster);
		ticket.add(info1);
		ticket.add(info2);
		ticket.add(info4);
		ticket.add(info5);
		ticket.add(info6);
		ticket.add(info7);

		info8.add(sel_seat[0]);
		info8.add(sel_seat[1]);
		info8.add(sel_seat[2]);
		info8.add(sel_seat[3]);
		info8.add(sel_seat[4]);
		info8.add(sel_seat[5]);
		ticket.add(info8);
		ticket.add(info9);
		add(ticket);
		// 하단 버튼
		add(bBtn);
		add(nBtn);

		combo1.addItemListener(null);

		JButton end = new JButton("Θ"); // 임시 버튼
		end.setFont(f.f3_22);
		end.setBounds(1150, 620, 60, 40);
		end.setBackground(c.color_red);
		end.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
//		add(end);
	}

	public void setPrice() {
		DecimalFormat df = new DecimalFormat("###,###");
		// 성인 14,000원 청소년 10,000원 영화금액
//		setPrice = ad_num * 13000 + ch_num * 10000;
		setPrice = (ad_num - tmp_ad) * 13000 + (ch_num - tmp_ch) * 10000;
		price = df.format(setPrice) + "원";
		info9.setText(price);
	}

	public void pageReset() {
		for (int i = 0; i < sel_seat.length; i++)
			sel_seat[i].setText("X");

		for (int i = 0; i < arr.length; i++)
			seats.get(i).setBackground(c.color_yellow);

		ad_num = 0;
		tmp_ad = 0;
		ch_num = 0;
		tmp_ch = 0;
		selSeats = "";
		setPrice();
		combo1.select(0);
		combo2.select(0);
		tmp = 0;
		ticket_num = 0;
//		JOptionPane.showMessageDialog(null, "초기화 완료");
	}
	
	public String ticketNumber() {
		int res;		//2022000000~202299999 에서 난수 출력
		res=(int)(Math.random()*99999+1)+202200000;
		String str=""+res;
		return str;
	}
	
	
}