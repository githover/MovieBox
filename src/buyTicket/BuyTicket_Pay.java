package buyTicket;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.kgv.dao.Imsi_DAO;
import com.kgv.dao.M_Ticketdb_DAO;
import com.kgv.dao.Movie_info_DAO;
import com.kgv.dao.No_Imsi_DAO;
import com.kgv.dao.No_Ticketdb_DAO;
import com.kgv.dto.Imsi_DTO;
import com.kgv.dto.M_Ticketdb_DTO;
import com.kgv.dto.Movie_info_DTO;
import com.kgv.dto.No_Imsi_DTO;

import setting.SetColor;
import setting.SetFont;

public class BuyTicket_Pay{
	JFrame frame;

	private JTextField cardnum1; //카드번호를 받는 TextField
	private JTextField cardnum2;
	private JTextField cardnum3;
	private JTextField cardnum4;
	private JTextField cardPass;

	JComboBox cardmonthBox;
	JComboBox cardyearBox;

	SetColor sc = new SetColor();
	SetFont sf = new SetFont();

//	ImageIcon icon02; // 영화 DTO 생성후 포스터 연동하기
	

	private int discount_price = 0; //할인금액
	private int ad_price = 13000; //성인가격
	private int ch_price = 10000; //청소년가격
	private int total = 0;

	//// 추가##
	Imsi_DTO imsiDTO;
	No_Imsi_DTO no_imsiDTO;
	Imsi_DAO imsiDAO = new Imsi_DAO(); //jdbc연결
	No_Imsi_DAO no_imsiDAO = new No_Imsi_DAO();
	M_Ticketdb_DAO ticketDAO = new M_Ticketdb_DAO();
	No_Ticketdb_DAO no_ticketDAO = new No_Ticketdb_DAO();

	M_Ticketdb_DTO m_Ticketdb_DTO;
	M_Ticketdb_DAO m_Ticketdb_DAO = new M_Ticketdb_DAO();
	public BuyTicket_Pay() {
		initialize();
	}

	private void initialize() {	  
		imsiDTO = imsiDAO.imsi_select(); // 임시테이블에 저장된 값을 호출 ##
		no_imsiDTO = no_imsiDAO.imsi_select(); // select로 가져온 값을 DTO로 받음


		frame = new JFrame();
		frame.setBounds(100, 100, 1280, 720);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("결제페이지");

		frame.setLocationRelativeTo(null); 
		frame.setLayout(null);
		frame.setUndecorated(true); //추가
		frame.setVisible(true);

		// 배경이미지 생성
		ImageIcon icon = new ImageIcon("images/background_1.jpg");
		JPanel back_panel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		back_panel.setLayout(null);

		frame.setContentPane(back_panel);

		///컴포넌트 추가 영역 ////
		///할인적용 패널///
		JPanel discount_panel = new JPanel();
		discount_panel.setBounds(122, 125, 675, 48);
		discount_panel.setLayout(new FlowLayout(FlowLayout.LEFT));
		discount_panel.setBackground(new Color(0,0,0,0));
		back_panel.add(discount_panel);

		//할인적용 label
		JLabel discountLabel = new JLabel("할인적용    ");
		discountLabel.setFont(sf.f2_26);
		discount_panel.add(discountLabel);

		//회원할인 라디오버튼
		JRadioButton discountBtn1 = new JRadioButton("회원할인 -1,000원     ");
		discountBtn1.setFont(sf.f2_22);
		//      discountBtn1.setContentAreaFilled(false); //투명으로 하니까 오른쪽 패널 그림이 섞이는 에러 발생해서 배경을 하얀색으로 지정함.
		discountBtn1.setBackground(Color.white);
		discountBtn1.setFocusPainted(false);
		discount_panel.add(discountBtn1);

		//비회원 라디오버튼
		JRadioButton discountBtn2 = new JRadioButton("비회원", true);
		discountBtn2.setFont(sf.f2_22);
		discountBtn2.setBackground(Color.white);
		discountBtn2.setFocusPainted(false);
		discount_panel.add(discountBtn2);

		//라디오 버튼 그룹화 -> 둘 중 하나만 선택할 수 있도록 함
		ButtonGroup discountButton = new ButtonGroup();
		discountButton.add(discountBtn1);
		discountButton.add(discountBtn2);

		///결제선택 패널///
		JPanel pay_panel1 = new JPanel();
		pay_panel1.setBounds(122, 187, 675, 48);
		pay_panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
		pay_panel1.setBackground(new Color(0,0,0,0));
		back_panel.add(pay_panel1);

		//결제수단 label
		JLabel paymentLabel = new JLabel("결제수단    ");
		paymentLabel.setFont(sf.f2_26);
		pay_panel1.add(paymentLabel);

		//카드 라디오 버튼
		JRadioButton cardcheckBtn = new JRadioButton("카드", true);
		cardcheckBtn.setFont(sf.f2_22);
		cardcheckBtn.setBackground(Color.white);
		cardcheckBtn.setFocusPainted(false);
		pay_panel1.add(cardcheckBtn);

		///카드종류 선택 패널///
		JPanel pay_panel2 = new JPanel();
		pay_panel2.setBounds(122, 245, 429, 48);
		pay_panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		pay_panel2.setBackground(new Color(0,0,0,0));
		back_panel.add(pay_panel2);

		//카드종류 label
		JLabel cardkindLabel = new JLabel("카드종류    ");
		cardkindLabel.setFont(sf.f2_26);
		pay_panel2.add(cardkindLabel);

		//카드 종류를 담은 배열
		String card[] = {"카드선택","하나카드","신한카드","우리카드","현대카드","농협카드"};
		//콤보박스에 card를 넣어서 만듦
		JComboBox cardchoiceBox = new JComboBox(card);
		cardchoiceBox.setFont(sf.f2_14);
		cardchoiceBox.setBackground(Color.white);
		cardchoiceBox.setPreferredSize(new Dimension(150,30));
		pay_panel2.add(cardchoiceBox);

		///카드번호 입력 패널///
		JPanel pay_panel3 = new JPanel();
		pay_panel3.setBounds(122, 300, 700, 48);
		pay_panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		pay_panel3.setBackground(new Color(0,0,0,0));
		back_panel.add(pay_panel3);

		//카드번호 label
		JLabel cardnumLabel = new JLabel("카드번호    ");
		cardnumLabel.setFont(sf.f2_26);
		pay_panel3.add(cardnumLabel);

		cardnum1 = new JTextField(4);
		cardnum1.setPreferredSize(new Dimension(20,30));
		cardnum1.setFont(sf.f2_16);
		pay_panel3.add(cardnum1);
		//key이벤트 
		cardnum1.addKeyListener(new KeyAdapter() {
			//숫자가아니면 입력이 되지 않음
			@Override
			public void keyTyped(KeyEvent e) {    		  
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}    	
				//4자리 이상 입력하지 못하도록 함
				if(cardnum1.getText().length()>=4) {
					e.consume();
				}
			}
			//숫자4개 입력되면 다음 field로 넘어가도록 설정
			@Override
			public void keyPressed(KeyEvent e) {
				if(cardnum1.getText().length()==3) {
					cardnum2.requestFocus(true);
				}
			}
		});

		JLabel dash1 = new JLabel("-");
		dash1.setFont(sf.f2_26);
		pay_panel3.add(dash1);

		cardnum2 = new JTextField(4);
		cardnum2.setPreferredSize(new Dimension(40,30));
		cardnum2.setFont(sf.f2_16);
		pay_panel3.add(cardnum2);
		//key이벤트
		cardnum2.addKeyListener(new KeyAdapter() {
			//숫자가아니면 입력이 되지 않음
			@Override
			public void keyTyped(KeyEvent e) {    		  
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}   
				//4자리 이상 입력하지 못하도록 함
				if(cardnum2.getText().length()>=4) {
					e.consume();
				}
			}
			//숫자4개 입력되면 다음 field로 넘어가도록 설정
			@Override
			public void keyPressed(KeyEvent e) {
				if(cardnum2.getText().length()==3) {
					cardnum3.requestFocus(true);
				}
			}
		});

		JLabel dash2 = new JLabel("-");
		dash2.setFont(sf.f2_26);
		pay_panel3.add(dash2);

		cardnum3 = new JTextField(4);
		cardnum3.setPreferredSize(new Dimension(40,30));
		cardnum3.setFont(sf.f2_16);
		pay_panel3.add(cardnum3);
		//key이벤트
		cardnum3.addKeyListener(new KeyAdapter() {
			//숫자가아니면 입력이 되지 않음
			@Override
			public void keyTyped(KeyEvent e) {    		  
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}    	
				//4자리 이상 입력하지 못하도록 함
				if(cardnum3.getText().length()>=4) {
					e.consume();
				}
			}
			//숫자4개 입력되면 다음 field로 넘어가도록 설정
			@Override
			public void keyPressed(KeyEvent e) {
				if(cardnum3.getText().length()==3) {
					cardnum4.requestFocus(true);
				}
			}
		});

		JLabel dash3 = new JLabel("-");
		dash3.setFont(sf.f2_26);
		pay_panel3.add(dash3);

		cardnum4 = new JTextField(4);
		cardnum4.setPreferredSize(new Dimension(40,30));
		cardnum4.setFont(sf.f2_16);
		pay_panel3.add(cardnum4);
		//key이벤트
		cardnum4.addKeyListener(new KeyAdapter() {
			//숫자가아니면 입력이 되지 않음
			@Override
			public void keyTyped(KeyEvent e) {    		  
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				} 
				//4자리 이상 입력하지 못하도록 함
				if(cardnum4.getText().length()>=4) {
					e.consume();
				}
			}
			//숫자4개 입력되면 다음 field로 넘어가도록 설정
			@Override
			public void keyPressed(KeyEvent e) {
				if(cardnum4.getText().length()==3) {
					cardPass.requestFocus();
				}
			}
		});

		///유효기간 입력 패널///
		JPanel pay_panel4 = new JPanel();
		pay_panel4.setBounds(122, 350, 700, 48);
		pay_panel4.setLayout(new FlowLayout(FlowLayout.LEFT));
		pay_panel4.setBackground(new Color(0,0,0,0));
		back_panel.add(pay_panel4);

		//유효기간 label
		JLabel cardvalLabel = new JLabel("유효기간    ");
		cardvalLabel.setFont(sf.f2_26);
		pay_panel4.add(cardvalLabel);

		//월을 담은 배열
		String month[] = {"01","02","03","04","05","06","07","08","09","10","11","12"};
		//콤보박스에 month를 넣어서 만듦
		cardmonthBox = new JComboBox(month);
		cardmonthBox.setBackground(Color.white);
		cardmonthBox.setFont(sf.f2_16);
		cardmonthBox.setPreferredSize(new Dimension(80,30));
		pay_panel4.add(cardmonthBox);

		JLabel slashLabel = new JLabel("/");
		slashLabel.setFont(sf.f2_26);
		pay_panel4.add(slashLabel);

		//년도를 담은 배열
		String year[] = {"2022","2023","2024","2025","2026","2027","2028","2029","2030"};
		//콤보박스에 year를 넣어서 만듦
		cardyearBox = new JComboBox(year);
		cardyearBox.setBackground(Color.white);
		cardyearBox.setFont(sf.f2_16);
		cardyearBox.setPreferredSize(new Dimension(80,30));
		pay_panel4.add(cardyearBox);

		JLabel add_explanation = new JLabel("  (카드에 있는 유효번호를 입력해주세요.)");
		add_explanation.setFont(sf.f2_16);
		pay_panel4.add(add_explanation);



		///비밀번호 입력 패널///
		JPanel pay_panel5 = new JPanel();
		pay_panel5.setBounds(122, 400, 700, 48);
		pay_panel5.setLayout(new FlowLayout(FlowLayout.LEFT));
		pay_panel5.setBackground(new Color(0,0,0,0));
		back_panel.add(pay_panel5);

		//카드 비밀번호 Label
		JLabel cardpassLabel = new JLabel("비밀번호    ");
		cardpassLabel.setFont(sf.f2_26);
		pay_panel5.add(cardpassLabel);

		//카드 비번 앞 2자리 입력 
		cardPass = new JTextField();      
		cardPass.setFont(sf.f2_16);
		cardPass.setPreferredSize(new Dimension(30,30));
		pay_panel5.add(cardPass);
		//카드비번 이벤트 
		cardPass.addKeyListener(new KeyAdapter() { 
			@Override
			public void keyTyped(KeyEvent e) {
				//숫자가아니면 입력이 되지 않음
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();
				}
				//2자리 이상 입력하지 못하도록 함
				if(cardPass.getText().length()>=2) {
					e.consume();
				}
			}
		});


		JLabel passLabel = new JLabel("**");
		passLabel.setFont(sf.f2_22);
		pay_panel5.add(passLabel);

		///결제금액 패널///
		JPanel pay_panel6 = new JPanel();
		pay_panel6.setBounds(229, 486, 146, 68);
		pay_panel6.setLayout(new GridLayout(2, 1));
		back_panel.add(pay_panel6);

		JLabel sumpayLabel = new JLabel("결제금액");
		sumpayLabel.setFont(sf.f2_22);
		sumpayLabel.setHorizontalAlignment(JLabel.CENTER);
		pay_panel6.add(sumpayLabel);

		JLabel sumPayment = new JLabel(total+"원");
		sumPayment.setFont(sf.f2_22);
		sumPayment.setHorizontalAlignment(JLabel.CENTER);
		pay_panel6.add(sumPayment);

		///할인금액 패널///
		JPanel pay_panel7 = new JPanel();
		pay_panel7.setBounds(442, 486, 146, 68);
		pay_panel7.setLayout(new GridLayout(2, 1));
		back_panel.add(pay_panel7);

		JLabel sumdiscountLabel = new JLabel("할인금액");
		sumdiscountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sumdiscountLabel.setFont(sf.f2_22);
		pay_panel7.add(sumdiscountLabel);

		JLabel sumDiscount = new JLabel();
		sumDiscount.setHorizontalAlignment(SwingConstants.CENTER);
		sumDiscount.setFont(sf.f2_22);
		pay_panel7.add(sumDiscount);      

		///총결제금액 패널///
		JPanel pay_panel8 = new JPanel();
		pay_panel8.setBounds(661, 486, 146, 68);
		pay_panel8.setLayout(new GridLayout(2, 1));
		back_panel.add(pay_panel8);

		JLabel totalpayLabel = new JLabel("총결제금액");
		totalpayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		totalpayLabel.setFont(sf.f2_22);
		pay_panel8.add(totalpayLabel);

		JLabel totalPayment = new JLabel();
		totalPayment.setHorizontalAlignment(SwingConstants.CENTER);
		totalPayment.setFont(sf.f2_22);
		pay_panel8.add(totalPayment);

		JLabel minusLabel = new JLabel("-");
		minusLabel.setFont(sf.f2_22);
		minusLabel.setBounds(402, 500, 25, 38);
		back_panel.add(minusLabel);

		JLabel equalLabel = new JLabel("=");
		equalLabel.setFont(sf.f2_22);
		equalLabel.setBounds(619, 500, 25, 38);
		back_panel.add(equalLabel);

		//이전으로 가는 버튼
		JButton preButton = new JButton("< PRE");
		preButton.setFont(sf.f1_18);
		preButton.setBackground(Color.black); //배경색상 변경
		preButton.setForeground(Color.white); //글씨색상 변경
		preButton.setBorderPainted(false); //테두리제거
		preButton.setFocusPainted(false); //선택테두리 제거
		preButton.setBounds(419, 617, 125, 34);
		back_panel.add(preButton);

		//결제하기 버튼
		JButton payButton = new JButton("예매하기 >");
		payButton.setFont(sf.f1_18);
		payButton.setBackground(Color.RED);
		payButton.setForeground(Color.white);
		payButton.setBorderPainted(false);
		payButton.setFocusPainted(false);
		payButton.setBounds(618, 618, 125, 34);
		back_panel.add(payButton);

		///예매내역 상태 확인 패널/// --> 아직 변수명 정리 안되어있음
		JPanel panel05 = new JPanel();
		panel05.setBounds(900, 110, 300, 450);
		back_panel.add(panel05);
		panel05.setLayout(null);

		//예매 정보
		JLabel label05 = new JLabel("예매정보");
		label05.setBounds(45, 18, 210, 30);
		panel05.add(label05);
		label05.setFont(sf.f2_26);
		label05.setHorizontalAlignment(JLabel.CENTER);
		
		///여기서부터 포스터 연동
		Movie_info_DAO movieDAO = new Movie_info_DAO();
		Movie_info_DTO movieDTO = new Movie_info_DTO();
		if(imsiDTO.getM_id()!=null) {
			movieDTO = movieDAO.selectMovie(imsiDTO.getTitle());
		}else if(no_imsiDTO.getNo_phone()!=null) {
			movieDTO = movieDAO.selectMovie(no_imsiDTO.getTitle());
		}
		String poster = movieDTO.getS_poster();
		ImageIcon icon02 = new ImageIcon("./images/"+poster); // 영화 DTO 생성후 포스터 연동하기
		
		//사진
		JPanel panel06 = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(icon02.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel06.setBounds(90, 55, 121, 173);
		panel05.add(panel06);
		
		System.out.println(icon02);
		///여기까지 포스터연동

		JLabel label06 = new JLabel(); //영화제목
		label06.setFont(sf.f2_16);
		label06.setBounds(27, 238, 241, 19);
		label06.setHorizontalAlignment(JLabel.CENTER);
		panel05.add(label06);

		JLabel label07 = new JLabel(); // 극장
		label07.setFont(sf.f2_14);
		label07.setBounds(70, 264, 161, 19);
		label07.setHorizontalAlignment(JLabel.CENTER);
		panel05.add(label07);

		JLabel label08 = new JLabel(); //날짜
		label08.setFont(sf.f2_14);
		label08.setBounds(70, 291, 161, 19);
		label08.setHorizontalAlignment(JLabel.CENTER);
		panel05.add(label08);

		JLabel label09 = new JLabel(); //시간
		label09.setFont(sf.f2_14);
		label09.setBounds(70, 320, 161, 19);
		label09.setHorizontalAlignment(JLabel.CENTER);
		panel05.add(label09);

		JLabel label = new JLabel(); //좌석
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(sf.f2_14);
		label.setBounds(27, 349, 246, 19);
		panel05.add(label);

		JLabel label_1 = new JLabel();
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(sf.f2_22);
		label_1.setBounds(12, 418, 276, 30);
		panel05.add(label_1);

		DecimalFormat df = new DecimalFormat("###,###");

		if(imsiDTO.getM_id()!=null) { //회원일때
			discountBtn1.setSelected(true);
			label06.setText(imsiDTO.getTitle());
			label07.setText(imsiDTO.getTheater());
			label08.setText(imsiDTO.getTicket_date());
			label09.setText(imsiDTO.getTicket_time());
			label.setText(imsiDTO.getSeat());
			discount_price=1000;
			total = imsiDTO.getAd_num()*ad_price+imsiDTO.getCh_num()*ch_price;
			sumPayment.setText(df.format(total)+"원");
			sumDiscount.setText(df.format(discount_price*(imsiDTO.getAd_num()+imsiDTO.getCh_num()))+"원");
			totalPayment.setText(df.format(total-(discount_price*(imsiDTO.getAd_num()+imsiDTO.getCh_num())))+"원");
			label_1.setText("총 결제 금액 : "+df.format(total-(discount_price*(imsiDTO.getAd_num()+imsiDTO.getCh_num())))+"원");			
		} else if(no_imsiDTO.getNo_phone()!=null){ //비회원일때
			discountBtn2.setSelected(true);
			label06.setText(no_imsiDTO.getTitle());
			label07.setText(no_imsiDTO.getTheater());
			label08.setText(no_imsiDTO.getTicket_date());
			label09.setText(no_imsiDTO.getTicket_time());
			label.setText(no_imsiDTO.getSeat());
			discount_price=0;
			total = no_imsiDTO.getAd_num()*ad_price+no_imsiDTO.getCh_num()*ch_price;
			sumPayment.setText(df.format(total)+"원");
			sumDiscount.setText(df.format(discount_price)+"원");
			totalPayment.setText(df.format(total-discount_price)+"원");
			label_1.setText("총 결제 금액 : "+df.format(total-discount_price)+"원");
		} else {
			System.out.println("양쪽 테이블이 모두 살아 있거나, 비어있습니다."); 
		}
		

		preButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new BuyTicket_Seats();
				frame.setVisible(false); //창 안보이게 하기
			}
		});

		payButton.addActionListener(new ActionListener(){ //결제완료 버튼 이벤트

			@Override
			public void actionPerformed(ActionEvent e) {
				int res=0;

				if(cardchoiceBox.getSelectedItem().toString().equals("카드선택")) {
					JOptionPane.showMessageDialog(null, "카드를 선택해주세요", "카드선택 알림창", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(cardnum1.getText().length()!=4 ||cardnum2.getText().length()!=4 ||cardnum3.getText().length()!=4 || cardnum4.getText().length()!=4) {
					JOptionPane.showMessageDialog(null, "카드번호를 전부 입력해주세요", "카드번호 알림창", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(cardPass.getText().length()!=2) {
					JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요", "비밀번호 알림창", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(today()==false) {
					JOptionPane.showMessageDialog(null, "카드 유효기간이 지났습니다", "유효기간 알림창", JOptionPane.INFORMATION_MESSAGE);
				}
				else { // 유효성 검증을 모두 통과하면 insert 과정을 실행할 수 있도록 함.
					if(imsiDTO.getM_id()!=null) {//회원일때
						res = ticketDAO.insertdb(imsiDTO);

					} else if(no_imsiDTO.getNo_phone()!=null){ //비회원일때
						res = no_ticketDAO.insertdb(no_imsiDTO);
					}

					if(res==1) {//insert 성공해야 넘어가도록 함. 
						new BuyTicket_Result();
						frame.setVisible(false); //창 안보이게 하기
					}else {
						System.out.println("결제실패");
					}
				}    		  
			}

		});      
		

	}//생성자

	public boolean today(){ //카드 유효기간 검증 메서드(미완성)
		//현재 날짜와 비교하여 유효기간을 검증함. 통과하면 true값을 반환
		Boolean res = false;

		int month = Integer.parseInt(cardmonthBox.getSelectedItem().toString());
		int year = Integer.parseInt(cardyearBox.getSelectedItem().toString());

		Date now = new Date();

		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("MM");

		int nowyear = Integer.parseInt(sdf1.format(now));
		int nowmonth = Integer.parseInt(sdf2.format(now));

		if(nowyear>year || (nowyear==year && nowmonth>month)) {
			res=false;
		}else {
			res=true;
		}

		return res;

	}

}