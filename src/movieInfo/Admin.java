package movieInfo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.kgv.dao.Movie_info_DAO;
import com.kgv.dto.Movie_info_DTO;

import main.Main_Admin;
import setting.SetColor;
import setting.SetFont;


public class Admin extends JFrame {
	Movie_info_DAO dao = new Movie_info_DAO();

	JFileChooser fileComponent = new JFileChooser();
	JFrame window = new JFrame();

	SetFont f = new SetFont();
	SetColor c = new SetColor(); 

	// 배경이미지 생성
	ImageIcon icon = new ImageIcon("./images/background_1.jpg");
	JPanel bground = new JPanel() {
		public void paintComponent(Graphics g) {
			g.drawImage(icon.getImage(), 0, 0, null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};

	JScrollPane scp = new JScrollPane(bground);

	//영화 등록
	public JPanel contentPane;
	public JTextField movie_title, poster1, poster2;
	public JTextArea movie_info1, movie_info2;
	public JLabel lb_title, lb_poster1, lb_poster2, lb_info1, lb_info2;
	public JButton btnOpen1, btnOpen2, send, delete, confirm, backBtn;


	//중앙 예매 내역 화면
	String[] mInfo = {"영화이름","포스터1","포스터2","정보1","정보2"};
	DefaultTableModel tableModel = new DefaultTableModel(mInfo,0);
	public void setUpTable() {

		List<Movie_info_DTO> listdto = dao.allMovie();

		Object contents[] = new Object[5];

		for(int i=0; i< listdto.size(); i++) {
//			Movie_info_DTO d = listdto.get(i);
			contents[0] = listdto.get(i).getTitle();
			contents[1] = listdto.get(i).getB_Poster();
			contents[2] = listdto.get(i).getS_poster();
			contents[3] = listdto.get(i).getInfo1();
			contents[4] = listdto.get(i).getInfo2();

			tableModel.addRow(contents);
//			System.out.println(d);
		}

	}
	JTable tLists = new JTable(tableModel);
	JScrollPane scp2 = new JScrollPane(tLists);



	public Admin() {			
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		//			setUndecorated(true); // 윈도우창 표시

		setBounds(100, 100, 1280, 720); //창 크기 조절
		setLocationRelativeTo(scp); // 중앙에 배치
		
		setLayout();
		init();

		setUpTable();
		setUndecorated(true);

		JPanel panel = new JPanel();
		panel.setBackground(c.color_light1);
		panel.setBounds(70, 120, 506, 450); 
		add(panel);
		panel.setLayout(null);

		lb_title = new JLabel("영 화 제 목");
		lb_title.setFont(f.f2_22);
		lb_title.setBounds(10, 45, 100, 30);
		panel.add(lb_title);

		//		lb_poster1, lb_poster2, lb_info1, lb_info2

		lb_poster1 = new JLabel("포  스  터 1");
		lb_poster1.setFont(f.f2_22);
		lb_poster1.setBounds(10, 105, 150, 30);
		panel.add(lb_poster1);

		lb_poster2 = new JLabel("포  스  터 2");
		lb_poster2.setFont(f.f2_22);
		lb_poster2.setBounds(10, 165, 150, 30);
		panel.add(lb_poster2);

		lb_info1 = new JLabel("영화 정보 1");
		lb_info1.setFont(f.f2_22);
		lb_info1.setBounds(10, 225, 150, 30);
		panel.add(lb_info1);

		lb_info2 = new JLabel("영화 정보 2");
		lb_info2.setFont(f.f2_22);
		lb_info2.setBounds(10, 300, 150, 30);
		panel.add(lb_info2);

		movie_title = new JTextField();
		movie_title.setBounds(150, 40, 235, 39);
		panel.add(movie_title);
		movie_title.setColumns(10);

		poster1 = new JTextField();
		poster1.setBounds(150, 100, 235, 39);
		panel.add(poster1);
		poster1.setColumns(10);

		btnOpen1 = new JButton("첨부");
		btnOpen1.setFont(f.f1_14);
		btnOpen1.setBounds(405, 102, 65, 34);
		btnOpen1.setBackground(c.color_red);
		btnOpen1.setBorderPainted(false);
		panel.add(btnOpen1);
		btnOpen1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileComponent.showOpenDialog(window) == JFileChooser.APPROVE_OPTION){
					File f = new File(fileComponent.getSelectedFile().toString());
//					poster1.setText(fileComponent.getSelectedFile().toString());
					poster1.setText(f.getName());
				}
			}
		});

		poster2 = new JTextField();
		poster2.setBounds(150, 160, 235, 39);
		panel.add(poster2);
		poster2.setColumns(10);

		btnOpen2 = new JButton("첨부");
		btnOpen2.setFont(f.f1_14);
		btnOpen2.setBackground(c.color_red);
		btnOpen2.setBorderPainted(false);
		btnOpen2.setBounds(405, 162, 65, 34);
		panel.add(btnOpen2);
		btnOpen2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileComponent.showOpenDialog(window) == JFileChooser.APPROVE_OPTION){
					File f = new File(fileComponent.getSelectedFile().toString());
//					poster2.setText(fileComponent.getSelectedFile().toString());
					poster2.setText(f.getName());
				}
			}
		});



		movie_info1 = new JTextArea("영화정보1");
		movie_info1.setBounds(150, 220, 235, 70);
		movie_info1.setLineWrap(true);
		panel.add(movie_info1);

		movie_info2 = new JTextArea("영화정보2");
		movie_info2.setBounds(150, 300, 235, 140);
		movie_info2.setLineWrap(true);
		panel.add(movie_info2);
		
		send = new JButton("등록");
		send.setFont(f.f1_18);
		send.setBackground(c.color_red);
		send.setBorderPainted(false);
		send.setFocusPainted(false);
		send.setBounds(70, 585, 80, 34);
		add(send);
		send.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				Movie_info_DTO dto = new Movie_info_DTO();
				dto.setTitle(movie_title.getText());
				dto.setB_Poster(poster1.getText());
				dto.setS_poster(poster2.getText());
				dto.setInfo1(movie_info1.getText());
				dto.setInfo2(movie_info2.getText());
				Movie_info_DAO dao = new Movie_info_DAO();
				int res = dao.movieInsert(dto);
				
				if(res ==1) {
					System.out.println("insert 성공");

					new Admin();
					setVisible(false);
				}
			}
		});
				
		delete = new JButton("삭제");
		delete.setFont(f.f1_18);
		delete.setBackground(c.color_red);
		delete.setBorderPainted(false);
		delete.setFocusPainted(false);
		delete.setBounds(495, 585, 80, 34);
		add(delete);
		delete.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tLists.getSelectedRow();
				int col = tLists.getSelectedColumn();
				Object value =  tLists.getValueAt(row, col);
				
				System.out.println(value);
				
				Movie_info_DAO dao = new Movie_info_DAO();
				int res = dao.movieDelete(value);
				
				if(res ==1) {
					System.out.println("delete 성공");
					new Admin();
					setVisible(false);
				}else {
					System.out.println("삭제할 수 없습니다. "); // 해당 영화 예매내역이 존재하면 삭제할 수 없음 (무결성 제약조건)
					// 삭제할수없다는 창 으로 안내
				}
			}
		});
		
		confirm = new JButton("예매현황");
		confirm.setFont(f.f1_18);
		confirm.setBackground(c.color_red);
		confirm.setBorderPainted(false);
		confirm.setFocusPainted(false);
		confirm.setBounds(600, 585, 120, 34);
		add(confirm);
		confirm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Graph();
			}
		});
		
		
		
		backBtn = new JButton("뒤로");
		backBtn.setFont(f.f1_18);
		backBtn.setBackground(c.color_red);
		backBtn.setBorderPainted(false);
		backBtn.setFocusPainted(false);
		backBtn.setBounds(1150, 585, 90, 34);
		add(backBtn);
		backBtn.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new Main_Admin();
				setVisible(false);
			}
		});
		

		
		setVisible(true);

	}//Admin()

	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);			
		scp2.setLocation(600,120);	//테이블
		scp2.setSize(640,450);
		tLists.setRowHeight(40);
		tLists.setFont(f.f3_14);

	}

	public void init() {
		add(scp2);

	}
	
}