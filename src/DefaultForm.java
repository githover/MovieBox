import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

class DefaultForm extends JFrame {

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


	
	
		DefaultForm() {
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 크기 변경 불가
//		setUndecorated(true); // 윈도우창 표시

		
	
		
		
		setSize(1280, 720); // 프레임 사이즈
		setLocationRelativeTo(scp); // 중앙에 배치

		setLayout();
		init();

		setVisible(true);

	}

	public void setLayout() {
		setContentPane(bground); // 베경 이미지 추가
		setLayout(null);
		
		
		

	}

	public void init() {
		
	}

	public static void main(String[] args) {
		new DefaultForm();

	}

}