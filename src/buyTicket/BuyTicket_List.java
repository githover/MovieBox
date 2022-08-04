package buyTicket;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.kgv.dao.Imsi_DAO;
import com.kgv.dao.M_Ticketdb_DAO;
import com.kgv.dto.Imsi_DTO;
import com.kgv.dto.M_Ticketdb_DTO;

import main.Main;
import main.Main_logon;
import setting.SetColor;
import setting.SetFont;

public class BuyTicket_List extends JFrame {

	M_Ticketdb_DAO dao = new M_Ticketdb_DAO();//
	Imsi_DAO imsidao = new Imsi_DAO();
	Imsi_DTO dto = imsidao.imsi_select();
	

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
	
	JLabel title = new JLabel("예매내역"); 	//타이틀
	
	
	//중앙 예매 내역 화면
	String[] mInfo = {"예매번호","영화이름","극장명","날짜","시간","좌석","티켓수"};
	DefaultTableModel tableModel = new DefaultTableModel(mInfo,0);

	public void setUpTable() {

		List<M_Ticketdb_DTO> listdto = dao.showList(dto.getM_id());

		Object contents[] = new Object[7];

		for(int i=0; i< listdto.size(); i++) {
			M_Ticketdb_DTO d = listdto.get(i);
			contents[0] = listdto.get(i).getTicket_num();
			contents[1] = listdto.get(i).getTitle();
			contents[2] = listdto.get(i).getTheater();
			contents[3] = listdto.get(i).getTicket_date();
			contents[4] = listdto.get(i).getTicket_time();
			contents[5] = listdto.get(i).getSeat();
			contents[6] = listdto.get(i).getAd_num()+listdto.get(i).getCh_num();

			tableModel.addRow(contents);
		}

	}


	JTable tLists = new JTable(tableModel);
	JScrollPane scp2 = new JScrollPane(tLists);

	JButton bBtn2 = new JButton("취소하기");//하단 버튼
	JButton nBtn = new JButton("처음으로");//하단 버튼

	SetFont f = new SetFont();
	SetColor c = new SetColor(); 
			
		public BuyTicket_List() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
		setSize(1280, 720); // 프레임 사이즈
		setUndecorated(true); // 윈도우창 표시

		setLocationRelativeTo(scp); // 중앙에 배치
		setTitle("예매내역");
		setLayout();

		init();

		setUpTable();
		
		setVisible(true);

		
//		bBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new BuyTicket_Result();
//				setVisible(false);
//			}
//		});
		
		nBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new Main_logon();
				setVisible(false);
			}
		});
		
		bBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tLists.getSelectedRow();
				int col = tLists.getSelectedColumn();
				Object value =  tLists.getValueAt(row, col);
				System.out.println(value);
				dao.cancle(value);
				new BuyTicket_List();
				setVisible(false);
			}
		});
	}

	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);

		
		
		title.setBounds(75,120,500,50); //타이틀
		title.setFont(f.f3_30);

		
		
		scp2.setLocation(100,180);	//테이블
		scp2.setSize(1070,350);
		tLists.setRowHeight(40);
		tLists.setFont(f.f3_16);

		
		
		bBtn2.setFont(f.f1_18);
		bBtn2.setBackground(Color.black); //배경색상 변경
		bBtn2.setForeground(Color.white); //글씨색상 변경
		bBtn2.setBorderPainted(false); //테두리제거
		bBtn2.setFocusPainted(false); //선택테두리 제거
		bBtn2.setBounds(419, 617, 125, 34);

		nBtn.setFont(f.f1_18);
		nBtn.setBackground(Color.RED);
		nBtn.setForeground(Color.white);
		nBtn.setBorderPainted(false);
		nBtn.setFocusPainted(false);
		nBtn.setBounds(618, 618, 125, 34);
			
		
	}

	public void init() {
		add(title);
		add(bBtn2);
		add(nBtn);
		add(scp2);
		
	}

}