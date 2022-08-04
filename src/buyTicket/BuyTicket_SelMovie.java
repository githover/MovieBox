package buyTicket;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.kgv.dao.Imsi_DAO;
import com.kgv.dao.Movie_info_DAO;
import com.kgv.dao.No_Imsi_DAO;
import com.kgv.dto.Imsi_DTO;
import com.kgv.dto.Movie_info_DTO;
import com.kgv.dto.No_Imsi_DTO;

import main.Main;
import main.Main_logon;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import setting.SetColor;
import setting.SetFont;

public class BuyTicket_SelMovie extends JFrame {
	Imsi_DAO imsi_DAO = new Imsi_DAO();
	Imsi_DTO imsi_DTO = imsi_DAO.imsi_select();

	No_Imsi_DAO no_imsi_DAO = new No_Imsi_DAO();
	No_Imsi_DTO no_imsi_DTO = no_imsi_DAO.imsi_select();

//	String poster;
//	String s;
//	String nnn;
	
	String title;
	String[] movie = new String[100];


	String[] theater1= {"강남","건대입구","구로","대학로","명동","목동","상봉","송파","신촌"};
	String[] theater2= {"광주","고양","광교","구리","기흥","김포","동탄","동백"};
	String[] theater3= {"계양","부평","연수역","학익","주안역","청라"};
	String[] theater4= {"강릉","원주","원통","인제","춘천"};
	String[] theater5= {"논산","당진","서산","제천","천안"};
	String[] theater6= {"수성","연경","월성","한일","현대"};
	String[] theater7= {"대연","동래","서면","센텀시티"};
	String[] theater8= {"거제","경산","고성","구미","김해","마산","안동","창원"};
	String[] theater9= {"광양","군산","나주","목포","순천"};

	JButton buttonNext,buttonPrev;
	JPanel main_panel;
	SetFont f = new SetFont();
	SetColor c = new SetColor();

	//int index=0;
	
	
	public BuyTicket_SelMovie() {

		setSize(1280,720);
		setTitle("KGV 영화예매");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setUndecorated(true);						//윈도우 창 표시
		getContentPane().setLayout(null);
		setLocationRelativeTo(null); 				//중앙에 배치
		setVisible(true);
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);





		//메인 패널 및 배경 이미지
		ImageIcon icon01 = new ImageIcon("./images/background_1.jpg");
		JPanel main_panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				g.drawImage(icon01.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
				g.drawLine(5, 0, 5, 35);
			}
		};
		main_panel.setLayout(null);


		//영화 패널
		JPanel panel01 = new JPanel();
		panel01.setBounds(71, 191, 168, 369);
		main_panel.add(panel01);
		panel01.setLayout(new BorderLayout(0, 0));


		//극장 패널
		JPanel panel02 = new JPanel();
		panel02.setBounds(311, 191, 108, 369);
		panel02.setBackground(c.color_light2);
		main_panel.add(panel02);


		JPanel panel03_1 = new JPanel();
		panel03_1.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_1);
		panel03_1.setLayout(new BorderLayout(0, 0));

		JPanel panel03_2 = new JPanel();
		panel03_2.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_2);
		panel03_2.setLayout(new BorderLayout(0, 0));

		JPanel panel03_3 = new JPanel();
		panel03_3.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_3);
		panel03_3.setLayout(new BorderLayout(0, 0));

		JPanel panel03_4 = new JPanel();
		panel03_4.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_4);
		panel03_4.setLayout(new BorderLayout(0, 0));

		JPanel panel03_5 = new JPanel();
		panel03_5.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_5);
		panel03_5.setLayout(new BorderLayout(0, 0));

		JPanel panel03_6 = new JPanel();
		panel03_6.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_6);
		panel03_6.setLayout(new BorderLayout(0, 0));

		JPanel panel03_7 = new JPanel();
		panel03_7.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_7);
		panel03_7.setLayout(new BorderLayout(0, 0));

		JPanel panel03_8 = new JPanel();
		panel03_8.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_8);
		panel03_8.setLayout(new BorderLayout(0, 0));

		JPanel panel03_9 = new JPanel();
		panel03_9.setBounds(422, 191, 108, 369);
		main_panel.add(panel03_9);
		panel03_9.setLayout(new BorderLayout(0, 0));

		//달력 패널
		JPanel panel04 = new JPanel();
		panel04.setBounds(602, 194, 210, 35);
		main_panel.add(panel04);
		panel04.add(datePicker);

		//시간선택 패널
		JPanel panel05 = new JPanel();
		panel05.setBackground(c.color_light2);
		panel05.setBounds(602, 284, 210, 276);
		main_panel.add(panel05);
		panel05.setLayout(null);
		GridBagLayout gbl_panel05 = new GridBagLayout();
		gbl_panel05.columnWidths = new int[]{162, 0};
		gbl_panel05.rowHeights = new int[]{47, 42, 42, 42, 42, 42, 0, 0};
		gbl_panel05.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel05.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel05.setLayout(gbl_panel05);

		//예매정보 패널
		JPanel panel06 = new JPanel();
		panel06.setBackground(c.color_light2);
		panel06.setBounds(900, 110, 300, 450);
		main_panel.add(panel06);
		panel06.setLayout(null);
		
		//예매정보 패널의 영화제목 레이블에 넣을 값 DB에서 불러오기
		Movie_info_DTO dto=new Movie_info_DTO();
		
		Movie_info_DAO movieDAO = new Movie_info_DAO();
		List<Movie_info_DTO> listdto = movieDAO.allMovie();

		for(int i=0; i< listdto.size(); i++) {
			movie[i] = listdto.get(i).getTitle();
		}


		//영화 사진 패널+이미지 삽입
//		ImageIcon icon02 = new ImageIcon("./images/s_뒤틀린집.jpg");
//		Movie_info_DTO movieDTO = new Movie_info_DTO();
		
//		if(imsi_DTO.getM_id()!=null) {
//	         movieDTO = movieDAO.selectMovie(s);
//	      }else if(no_imsi_DTO.getNo_phone()!=null) {
//	         movieDTO = movieDAO.selectMovie(s);
//	      }
//		  String poster = movieDTO.getS_poster();
//	      ImageIcon icon02 = new ImageIcon("./images/"+poster); 
		
//		JPanel panel07 = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				g.drawImage(icon02.getImage(),0,0,null);
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
//		panel07.setBounds(90, 60, 121, 173);
//		panel06.add(panel07);

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
		getContentPane().add(main_panel);


		//화살표 이미지 삽입
		ImageIcon icon03 = new ImageIcon("./images/circ_arrow.jpg");
		JPanel panel08 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(icon03.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel08.setBackground(c.color_light2);
		panel08.setBounds(255, 350, 42, 42);
		main_panel.add(panel08);
		panel08.setLayout(null);

		JPanel panel09 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(icon03.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel09.setBackground(c.color_light2);
		panel09.setBounds(545, 350, 42, 42);
		main_panel.add(panel09);
		panel09.setLayout(null);

		JPanel panel10 = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				g.drawImage(icon03.getImage(),0,0,null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		panel10.setBackground(c.color_light2);
		panel10.setBounds(835, 350, 42, 42);
		main_panel.add(panel10);
		panel10.setLayout(null);

		
//		//예매정보 - 영화 포스터 패널
//		ImageIcon icon02 = new ImageIcon("./images/s_뒤틀린집.jpg");
//		JPanel panel07 = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				g.drawImage(icon02.getImage(),0,0,null);
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
//		panel07.setBounds(90, 40, 121, 173);
////		panel06.add(panel07);
//		
//		ImageIcon icon04 = new ImageIcon("./images/s_마녀2.jpg");
//		JPanel panel11 = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				g.drawImage(icon04.getImage(),0,0,null);
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
//		panel11.setBounds(90, 40, 121, 173);
////		panel06.add(panel11);
//		
//		ImageIcon icon05 = new ImageIcon("./images/s_코난.jpg");
//		JPanel panel12 = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				g.drawImage(icon05.getImage(),0,0,null);
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
//		panel12.setBounds(90, 40, 121, 173);
////		panel06.add(panel12);
//		
//		ImageIcon icon06 = new ImageIcon("./images/s_헤어질 결심.jpg");
//		JPanel panel13 = new JPanel() {
//			@Override
//			public void paintComponent(Graphics g) {
//				g.drawImage(icon06.getImage(),0,0,null);
//				setOpaque(false);
//				super.paintComponent(g);
//			}
//		};
//		panel13.setBounds(90, 40, 121, 173);
////		panel06.add(panel13);
		
		
		//영화,극장,날짜,시간,예매정보 라벨
		JLabel label01 = new JLabel("영화");
		label01.setFont(f.f1_30);
		label01.setBounds(76, 122, 144, 35);
		label01.setHorizontalAlignment(JLabel.CENTER);
		main_panel.add(label01);

		JLabel label02 = new JLabel("극장");
		label02.setFont(f.f1_30);
		label02.setBounds(311, 122, 220, 35);
		label02.setHorizontalAlignment(JLabel.CENTER);
		main_panel.add(label02);

		JLabel label03 = new JLabel("날짜");
		label03.setFont(f.f1_30);
		label03.setBounds(602, 122, 210, 35);
		label03.setHorizontalAlignment(JLabel.CENTER);
		main_panel.add(label03);

		JLabel label04 = new JLabel("시간");
		label04.setFont(f.f1_30);
		label04.setBounds(602, 239, 210, 35);
		label04.setHorizontalAlignment(JLabel.CENTER);
		main_panel.add(label04);

		JLabel label05 = new JLabel("예매정보");
		label05.setFont(f.f1_30);
		label05.setBounds(45, 10, 210, 30);
		label05.setHorizontalAlignment(JLabel.CENTER);
		panel06.add(label05);


		//예매 정보 라벨(영화 제목, 극장, 날짜, 시간 모두 한패널 안에)
		JLabel label06 = new JLabel();
		label06.setFont(f.f3_22);
		label06.setBounds(0, 234, 300, 40);
		label06.setHorizontalAlignment(JLabel.CENTER);
		panel06.add(label06);

		JLabel label07 = new JLabel();
		label07.setFont(f.f3_18);
		label07.setBounds(70, 278, 161, 40);
		label07.setHorizontalAlignment(JLabel.CENTER);
		panel06.add(label07);

		JLabel label08 = new JLabel();
		label08.setFont(f.f3_18);
		label08.setBounds(70, 314, 161, 40);
		label08.setHorizontalAlignment(JLabel.CENTER);
		panel06.add(label08);

		JLabel label09 = new JLabel();
		label09.setFont(f.f3_18);
		label09.setBounds(70, 348, 161, 40);
		label09.setHorizontalAlignment(JLabel.CENTER);
		panel06.add(label09);




		//영화 리스트 + 리스트 클릭 시 이벤트 처리(예매정보 패널의 영화제목 레이블에 제목을 넣어줄수 있도록)
		JList list01 = new JList(movie);
		list01.setBackground(c.color_light2);
		list01.setFont(f.f3_18);
		list01.setBounds(76, 191, 155, 369);

//		panel07.setVisible(false);
//		panel11.setVisible(false);
//		panel12.setVisible(false);
//		panel13.setVisible(false);
//		
		
		
		
		list01.addListSelectionListener(new ListSelectionListener() {
			Movie_info_DTO movieDTO = new Movie_info_DTO();
			public void valueChanged(ListSelectionEvent e) {
				
				if (!e.getValueIsAdjusting()) {                     
					label06.setText((String) list01.getSelectedValue()); //라벨의 텍스트를 변경(업데이트)한다.
					
//					if((String) list01.getSelectedValue()==movie[0]) {
//						panel07.setVisible(true);
//						panel11.setVisible(false);
//						panel12.setVisible(false);
//						panel13.setVisible(false);
//					}else if ((String) list01.getSelectedValue()==movie[1]) {
//						panel07.setVisible(false);
//						panel11.setVisible(true);
//						panel12.setVisible(false);
//						panel13.setVisible(false);
//					}else if ((String) list01.getSelectedValue()==movie[2]) {
//						panel07.setVisible(false);
//						panel11.setVisible(false);
//						panel12.setVisible(true);
//						panel13.setVisible(false);
//					}else if ((String) list01.getSelectedValue()==movie[3]) {
//						panel07.setVisible(false);
//						panel11.setVisible(false);
//						panel12.setVisible(false);
//						panel13.setVisible(true);
//					}else {
//						panel07.setVisible(false);
//						panel11.setVisible(false);
//						panel12.setVisible(false);
//						panel13.setVisible(false);
//					}
//					
//					if(imsi_DTO.getM_id()!=null) {
//						imsi_DAO.updatemovie(label06.getText(),imsi_DTO.getM_id());
//						imsi_DTO = imsi_DAO.imsi_select();
//					}else if(no_imsi_DTO.getNo_phone()!=null) {
//						no_imsi_DAO.updatemovie(label06.getText(),no_imsi_DTO.getNo_phone());
//						no_imsi_DTO = no_imsi_DAO.imsi_select();
//					}		
					
//					if(imsi_DTO.getM_id()!=null) {
//						movieDTO = movieDAO.selectMovie(imsi_DTO.getTitle());
//					}else if(no_imsi_DTO.getNo_phone()!=null) {
//						movieDTO = movieDAO.selectMovie(no_imsi_DTO.getTitle());
//					}
				
					
					//여기부터 포스터 연동
					if(imsi_DTO.getM_id()!=null) {
						movieDTO = movieDAO.selectMovie((String) list01.getSelectedValue());
					}else if(no_imsi_DTO.getNo_phone()!=null) {
						movieDTO = movieDAO.selectMovie((String) list01.getSelectedValue());
					}
					String poster = movieDTO.getS_poster();	
					ImageIcon icon02 = new ImageIcon("./images/"+poster);				
					JLabel panel07 = new JLabel();
					panel07.setOpaque(true); // 영화 포스터
					panel07.setIcon(icon02);
					panel06.add(panel07);
					panel07.setBounds(90, 60, 121, 173);
				}

			}           
		});
		panel01.add(list01);


//		String[] poster = new String[10];
//		for (int i=0;i<movie.length; i++) {
//			poster[i]=movie[i];
//		}
		
		


		//극장 리스트
		JList list02_1 = new JList(theater1);
		panel03_1.add(list02_1, BorderLayout.CENTER);
		list02_1.setBackground(c.color_light2);
		list02_1.setFont(f.f3_18);
		list02_1.setBounds(422, 191, 108, 369);

		JList list02_2 = new JList(theater2);
		panel03_2.add(list02_2, BorderLayout.CENTER);
		list02_2.setBackground(c.color_light2);
		list02_2.setFont(f.f3_18);
		list02_2.setBounds(422, 191, 108, 369);

		JList list02_3 = new JList(theater3);
		panel03_3.add(list02_3, BorderLayout.CENTER);
		list02_3.setBackground(c.color_light2);
		list02_3.setFont(f.f3_18);
		list02_3.setBounds(422, 191, 108, 369);

		JList list02_4 = new JList(theater4);
		panel03_4.add(list02_4, BorderLayout.CENTER);
		list02_4.setBackground(c.color_light2);
		list02_4.setFont(f.f3_18);
		list02_4.setBounds(422, 191, 108, 369);

		JList list02_5 = new JList(theater5);
		panel03_5.add(list02_5, BorderLayout.CENTER);
		list02_5.setBackground(c.color_light2);
		list02_5.setFont(f.f3_18);
		list02_5.setBounds(422, 191, 108, 369);

		JList list02_6 = new JList(theater6);
		panel03_6.add(list02_6, BorderLayout.CENTER);
		list02_6.setBackground(c.color_light2);
		list02_6.setFont(f.f3_18);
		list02_6.setBounds(422, 191, 108, 369);

		JList list02_7 = new JList(theater7);
		panel03_7.add(list02_7, BorderLayout.CENTER);
		list02_7.setBackground(c.color_light2);
		list02_7.setFont(f.f3_18);
		list02_7.setBounds(422, 191, 108, 369);

		JList list02_8 = new JList(theater8);
		panel03_8.add(list02_8, BorderLayout.CENTER);
		list02_8.setBackground(c.color_light2);
		list02_8.setFont(f.f3_18);
		list02_8.setBounds(422, 191, 108, 369);

		JList list02_9 = new JList(theater9);
		panel03_9.add(list02_9, BorderLayout.CENTER);
		list02_9.setBackground(c.color_light2);
		list02_9.setFont(f.f3_18);
		list02_9.setBounds(422, 191, 108, 369);

		panel02.setLayout(new GridLayout(14,1));



		//극장 버튼
		JButton button01 = new JButton("서울");
		JButton button02 = new JButton("경기");
		JButton button03 = new JButton("인천");
		JButton button04 = new JButton("강원");
		JButton button05 = new JButton("충청");
		JButton button06 = new JButton("대구");
		JButton button07 = new JButton("부산");
		JButton button08 = new JButton("경상");
		JButton button09 = new JButton("전라");

		button01.setFont(f.f3_18);
		button02.setFont(f.f3_18);
		button03.setFont(f.f3_18);
		button04.setFont(f.f3_18);
		button05.setFont(f.f3_18);
		button06.setFont(f.f3_18);
		button07.setFont(f.f3_18);
		button08.setFont(f.f3_18);
		button09.setFont(f.f3_18);

		button01.setBackground(c.color_light2);
		button02.setBackground(c.color_light2);
		button03.setBackground(c.color_light2);
		button04.setBackground(c.color_light2);
		button05.setBackground(c.color_light2);
		button06.setBackground(c.color_light2);
		button07.setBackground(c.color_light2);
		button08.setBackground(c.color_light2);
		button09.setBackground(c.color_light2);

		button01.setBorderPainted(false);
		button02.setBorderPainted(false);
		button03.setBorderPainted(false);
		button04.setBorderPainted(false);
		button05.setBorderPainted(false);
		button06.setBorderPainted(false);
		button07.setBorderPainted(false);
		button08.setBorderPainted(false);
		button09.setBorderPainted(false);

		panel02.add(button01);
		panel02.add(button02);
		panel02.add(button03);
		panel02.add(button04);
		panel02.add(button05);
		panel02.add(button06);
		panel02.add(button07);
		panel02.add(button08);
		panel02.add(button09);


		//시간 선택 버튼
		ButtonGroup group = new ButtonGroup();

		JRadioButton radio01 = new JRadioButton(" 10:50");
		radio01.setFont(f.f3_18);
		radio01.setBounds(0, 0, 162, 42);
		radio01.setBackground(c.color_light2);
		GridBagConstraints gbc_radio01 = new GridBagConstraints();
		gbc_radio01.fill = GridBagConstraints.BOTH;
		gbc_radio01.insets = new Insets(0, 0, 5, 0);
		gbc_radio01.gridx = 0;
		gbc_radio01.gridy = 0;
		panel05.add(radio01, gbc_radio01);
		group.add(radio01); 

		JRadioButton radio02 = new JRadioButton(" 12:30");
		radio02.setFont(f.f3_18);
		radio02.setBounds(8, 47, 162, 42);
		radio02.setBackground(c.color_light2);
		GridBagConstraints gbc_radio02 = new GridBagConstraints();
		gbc_radio02.fill = GridBagConstraints.BOTH;
		gbc_radio02.insets = new Insets(0, 0, 5, 0);
		gbc_radio02.gridx = 0;
		gbc_radio02.gridy = 1;
		panel05.add(radio02, gbc_radio02);
		group.add(radio02); 

		JRadioButton radio03 = new JRadioButton(" 14:20");
		radio03.setFont(f.f3_18);
		radio03.setBounds(8, 107, 162, 42);
		radio03.setBackground(c.color_light2);

		GridBagConstraints gbc_radio03 = new GridBagConstraints();
		gbc_radio03.fill = GridBagConstraints.BOTH;
		gbc_radio03.insets = new Insets(0, 0, 5, 0);
		gbc_radio03.gridx = 0;
		gbc_radio03.gridy = 2;
		panel05.add(radio03, gbc_radio03);
		group.add(radio03); 

		JRadioButton radio04 = new JRadioButton(" 16:40");
		radio04.setFont(f.f3_18);
		radio04.setBounds(8, 167, 162, 42);
		radio04.setBackground(c.color_light2);

		GridBagConstraints gbc_radio04 = new GridBagConstraints();
		gbc_radio04.fill = GridBagConstraints.BOTH;
		gbc_radio04.insets = new Insets(0, 0, 5, 0);
		gbc_radio04.gridx = 0;
		gbc_radio04.gridy = 3;
		panel05.add(radio04, gbc_radio04);
		group.add(radio04);

		JRadioButton radio05 = new JRadioButton(" 18:30");
		radio05.setFont(f.f3_18);
		radio05.setBounds(8, 227, 162, 42);
		radio05.setBackground(c.color_light2);
		GridBagConstraints gbc_radio05 = new GridBagConstraints();
		gbc_radio05.fill = GridBagConstraints.BOTH;
		gbc_radio05.insets = new Insets(0, 0, 5, 0);
		gbc_radio05.gridx = 0;
		gbc_radio05.gridy = 4;
		panel05.add(radio05, gbc_radio05);
		group.add(radio05); 

		JRadioButton radio06 = new JRadioButton(" 20:20");
		radio06.setFont(f.f3_18);
		radio06.setBounds(8, 288, 162, 42);
		radio06.setBackground(c.color_light2);
		GridBagConstraints gbc_radio06 = new GridBagConstraints();
		gbc_radio06.fill = GridBagConstraints.BOTH;
		gbc_radio06.insets = new Insets(0, 0, 5, 0);
		gbc_radio06.gridx = 0;
		gbc_radio06.gridy = 5;
		panel05.add(radio06, gbc_radio06);
		group.add(radio06);



		//페이지 이동 버튼
		JButton buttonNext = new JButton("좌석선택 >");
		buttonNext.setFont(f.f1_18);
		buttonNext.setBackground(Color.RED);
		buttonNext.setForeground(Color.white);
		buttonNext.setBorderPainted(false);
		buttonNext.setFocusPainted(false);
		buttonNext.setBounds(618, 618, 125, 34);

		main_panel.add(buttonNext);

		JButton buttonPrev = new JButton("< PRE");
		buttonPrev.setFont(f.f1_18);
		buttonPrev.setBackground(Color.black); //배경색상 변경
		buttonPrev.setForeground(Color.white); //글씨색상 변경
		buttonPrev.setBorderPainted(false); //테두리제거
		buttonPrev.setFocusPainted(false); //선택테두리 제거
		buttonPrev.setBounds(419, 617, 125, 34);
		main_panel.add(buttonPrev);	





		//		
		//지역 버튼 이벤트 클릭 시 색깔 변하게
		button01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button01) {
					label07.setText("");
					button01.setBackground(new Color(0xFFADD8E6));
					button02.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button02) {
					label07.setText("");
					button02.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button03.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button03) {
					label07.setText("");
					button03.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button02.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button04.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button04) {
					label07.setText("");
					button04.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button02.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button05.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button05) {
					label07.setText("");
					button05.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button02.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button06.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button06) {
					label07.setText("");
					button06.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button02.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button07.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button07) {
					label07.setText("");
					button07.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button02.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button08.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button08) {
					label07.setText("");
					button08.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button02.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button09.setBackground(c.color_light2);
				}
			}
		});
		button09.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == button09) {
					label07.setText("");
					button09.setBackground(new Color(0xFFADD8E6));
					button01.setBackground(c.color_light2);
					button02.setBackground(c.color_light2);
					button03.setBackground(c.color_light2);
					button04.setBackground(c.color_light2);
					button05.setBackground(c.color_light2);
					button06.setBackground(c.color_light2);
					button07.setBackground(c.color_light2);
					button08.setBackground(c.color_light2);
				}
			}
		});



		//지역 버튼 이벤트 처리-지역버튼 클릭 시 지역에 해당하는 극장만 보이게
		panel03_2.setVisible(false);

		button01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_1.setVisible(true);
				panel03_2.setVisible(false);
				panel03_3.setVisible(false);
				panel03_4.setVisible(false);
				panel03_5.setVisible(false);
				panel03_6.setVisible(false);
				panel03_7.setVisible(false);
				panel03_8.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_2.setVisible(true);
				panel03_1.setVisible(false);
				panel03_3.setVisible(false);
				panel03_4.setVisible(false);
				panel03_5.setVisible(false);
				panel03_6.setVisible(false);
				panel03_7.setVisible(false);
				panel03_8.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_3.setVisible(true);
				panel03_1.setVisible(false);
				panel03_2.setVisible(false);
				panel03_4.setVisible(false);
				panel03_5.setVisible(false);
				panel03_6.setVisible(false);
				panel03_7.setVisible(false);
				panel03_8.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_4.setVisible(true);
				panel03_1.setVisible(false);
				panel03_2.setVisible(false);
				panel03_3.setVisible(false);
				panel03_5.setVisible(false);
				panel03_6.setVisible(false);
				panel03_7.setVisible(false);
				panel03_8.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_5.setVisible(true);
				panel03_1.setVisible(false);
				panel03_2.setVisible(false);
				panel03_3.setVisible(false);
				panel03_4.setVisible(false);
				panel03_6.setVisible(false);
				panel03_7.setVisible(false);
				panel03_8.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_6.setVisible(true);
				panel03_1.setVisible(false);
				panel03_2.setVisible(false);
				panel03_3.setVisible(false);
				panel03_4.setVisible(false);
				panel03_5.setVisible(false);
				panel03_7.setVisible(false);
				panel03_8.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button07.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_7.setVisible(true);
				panel03_1.setVisible(false);
				panel03_2.setVisible(false);
				panel03_3.setVisible(false);
				panel03_4.setVisible(false);
				panel03_5.setVisible(false);
				panel03_6.setVisible(false);
				panel03_8.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button08.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_8.setVisible(true);
				panel03_1.setVisible(false);
				panel03_2.setVisible(false);
				panel03_3.setVisible(false);
				panel03_4.setVisible(false);
				panel03_5.setVisible(false);
				panel03_6.setVisible(false);
				panel03_7.setVisible(false);
				panel03_9.setVisible(false);
			}
		});
		button09.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel03_9.setVisible(true);
				panel03_1.setVisible(false);
				panel03_2.setVisible(false);
				panel03_3.setVisible(false);
				panel03_4.setVisible(false);
				panel03_5.setVisible(false);
				panel03_6.setVisible(false);
				panel03_7.setVisible(false);
				panel03_8.setVisible(false);
			}
		});

		datePanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == datePanel) {
					label08.setText(model.getYear() + "-" + (model.getMonth() + 1)
							+ "-" + model.getDay());
				}
			}
		});




		//극장 리스트 이벤트 처리(리스트 항목 클릭 시 그 값을 가져와 예매정보 패널의 극장 리스트에 반환)
		list02_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_1=list02_1.getSelectedValuesList();
					for(String value:ls_1) {
						label07.setText("서울 "+value);
						//System.out.println(value);
					}
				}
			}
		});
		list02_2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_2=list02_2.getSelectedValuesList();
					for(String value:ls_2) {
						label07.setText("경기 "+value);
					}
				}
			}
		});
		list02_3.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_3=list02_3.getSelectedValuesList();
					for(String value:ls_3) {
						label07.setText("인천 "+value);
					}
				}
			}
		});
		list02_4.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_4=list02_4.getSelectedValuesList();
					for(String value:ls_4) {
						label07.setText("강원 "+value);
					}
				}
			}
		});
		list02_5.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_5=list02_5.getSelectedValuesList();
					for(String value:ls_5) {
						label07.setText("충청 "+value);
					}
				}
			}
		});
		list02_6.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_6=list02_6.getSelectedValuesList();
					for(String value:ls_6) {
						label07.setText("대구 "+value);
					}
				}
			}
		});
		list02_7.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_7=list02_7.getSelectedValuesList();
					for(String value:ls_7) {
						label07.setText("부산 "+value);
					}
				}
			}
		});
		list02_8.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_8=list02_8.getSelectedValuesList();
					for(String value:ls_8) {
						label07.setText("경상 "+value);
					}
				}
			}
		});
		list02_9.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {	
					List<String> ls_9=list02_9.getSelectedValuesList();
					for(String value:ls_9) {
						label07.setText("전라 "+value);
					}
				}
			}
		});


		//시간 라디오버튼 이벤트 처리
		radio01.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio01) {
					label09.setText(" 10:50 ~ 12:20");
				}
			}
		});
		radio02.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio02) {
					label09.setText(" 12:30 ~ 14:00");
				}
			}
		});
		radio03.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio03) {
					label09.setText(" 14:20 ~ 15:50");
				}
			}
		});
		radio04.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio04) {
					label09.setText(" 16:40 ~ 18:10");
				}
			}
		});
		radio05.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio05) {
					label09.setText(" 18:30 ~ 20:00");
				}
			}
		});
		radio06.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == radio06) {
					label09.setText(" 20:20 ~ 21:50");
				}
			}
		});

		//		@Override
		//		public void deselect(int index) {
		//			  list.removeSelectionInterval(index, index);
		//			}

		//앞 뒤 창으로 넘어가는 이벤트

		buttonPrev.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "메인페이지로 이동합니다");

				if(imsi_DTO.getM_id()!= null) {
					new Main_logon();
				}
				else if(no_imsi_DTO.getNo_phone()!=null){
					no_imsi_DAO.imsi_delete();
					new Main();
				}
				setVisible(false); //창 안보이게 하기
			}
		});


		buttonNext.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				int res = 0;
				System.out.println(imsi_DTO.getM_id());

				if(label06.getText().length()==0) {
					JOptionPane.showMessageDialog(null, "영화를 선택하세요","영화 선택 알림창",JOptionPane.INFORMATION_MESSAGE);
				}else if(label07.getText().length()==0 ) {
					JOptionPane.showMessageDialog(null, "극장을 선택하세요","극장 선택 알림창",JOptionPane.INFORMATION_MESSAGE);
				}else if(label08.getText().length()==0 ) {
					JOptionPane.showMessageDialog(null, "날짜를 선택하세요","날짜 선택 알림창",JOptionPane.INFORMATION_MESSAGE);
				}else if(label09.getText().length()==0 ) {
					JOptionPane.showMessageDialog(null, "시간을 선택하세요","시간 선택 알림창",JOptionPane.INFORMATION_MESSAGE);
				}else {
					if(imsi_DTO.getM_id() != null) { //회원
						imsi_DTO.setTitle(label06.getText()); 
						imsi_DTO.setTheater(label07.getText());
						imsi_DTO.setTicket_date(label08.getText());
						imsi_DTO.setTicket_time(label09.getText());
						res = imsi_DAO.updatemovie(imsi_DTO);
						//System.out.println(res);
					} else if(no_imsi_DTO.getNo_phone() != null) { //비회원
						no_imsi_DTO.setTitle(label06.getText());
						no_imsi_DTO.setTheater(label07.getText());
						no_imsi_DTO.setTicket_date(label08.getText());
						no_imsi_DTO.setTicket_time(label09.getText());
						res=no_imsi_DAO.updatemovie(no_imsi_DTO);
						//System.out.println(res);
					}
				}



				if(res ==1) { //update 가 성공했다면 아래 코드를 실행
					new BuyTicket_Seats();
					setVisible(false); //창 안보이게 하기
				}else { //실패
					//					JOptionPane.showMessageDialog(null, "예매 정보를 선택하세요","알림",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});

	}



//	public String moviename(String s) {
////		System.out.println(s);
//		Movie_info_DTO movieDTO = new Movie_info_DTO();
//		Movie_info_DAO movieDAO = new Movie_info_DAO();
//		
//		if(imsi_DTO.getM_id()!=null) {
//			movieDTO = movieDAO.selectMovie(s);		
//			poster = movieDTO.getS_poster();
//		}else if(no_imsi_DTO.getNo_phone()!=null) {
//			movieDTO = movieDAO.selectMovie(s);
//			poster = movieDTO.getS_poster();
//		}
////		System.out.println(poster);
//		
//		return poster;
//	}
	
}
