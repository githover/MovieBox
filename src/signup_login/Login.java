package signup_login;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.kgv.dao.Imsi_DAO;
import com.kgv.dao.Memdb_DAO;

import main.Main;
import main.Main_Admin;
import main.Main_logon;
import setting.SetColor;
import setting.SetFont;

public class Login extends JFrame{

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
   
   JPanel loginBar = new JPanel();
   JButton Signup = new JButton("회원가입");
   JButton NmemberTCon = new JButton("비회원 예매확인");
   JButton toHome = new JButton("처음으로");

   
   JLabel title = new JLabel("로그인");
   
   
   JPanel info = new JPanel();
   JLabel id = new JLabel("아이디");
   JLabel password = new JLabel("비밀번호");
   
   JPanel inputField = new JPanel();
   public JTextField input1 = new JTextField(); //public으로 변경###
   JPasswordField input2 = new JPasswordField();   

   
   JButton loginBtn = new JButton("로그인");   
   
   JButton searchIdPw = new JButton("ID/PW찾기");
   
   SetFont font = new SetFont();
   SetColor color = new SetColor();
   
   public Login() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false); // 크기 변경 불가
      setUndecorated(true); // 윈도우창 표시
      setSize(780, 600); // 프레임 사이즈
      setLocationRelativeTo(scp); // 중앙에 배치
      setLayout();
      init();
      setVisible(true);     
      
//      loginBtn.addActionListener(this); //생성자에 이벤트 등록###

      //버튼이벤트추가한거
      loginBtn.setFont(font.f3_14);
      loginBtn.setBorderPainted(false);
      loginBtn.addMouseListener(new MouseAdapter() {
    	  @Override
    	  public void mouseClicked(MouseEvent e) {
    		  textNullCheck();
    	  }
      });
   }

public void textNullCheck() {
	   if(input1.getText().equals("admin")) {
		   new Main_Admin();
		   setVisible(false);
	   }
	   else if (input1.getText().length() <= 0) {
		   JOptionPane.showMessageDialog(this, "아이디를 입력하세요!", "Message", JOptionPane.INFORMATION_MESSAGE);

	   }else if (input2.getPassword().length <= 0) {
		   JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요!", "Message", JOptionPane.INFORMATION_MESSAGE);

	   } else {
		   loginCheck();
	   }
   }
   
   public void loginCheck() { // 작성중.. 
	   Memdb_DAO dao = new Memdb_DAO();
	   Imsi_DAO imsiDAO = new Imsi_DAO();
	   
	   
	   String id = input1.getText();
	   String pwd = new String(input2.getPassword());
	   int num = dao.loginCheck(id,pwd);
	   //해당 값이 있을경우
	   if (num==1) {
		   imsiDAO.logincheck(this);
		   new Main_logon();
		   dispose();
	   } else {
		   JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 맞지 않습니다!", "Message", JOptionPane.WARNING_MESSAGE);

	   }	   
   }

   public void setLayout() {
      setContentPane(bground); // 베경 이미지 추가
      setLayout(null);
      
      loginBar.setOpaque(false);
      loginBar.setBounds(60,150,660,37);
      loginBar.setLayout(new GridLayout(1, 4, 20, 0)); //로그인 바 패널
      

      Signup.setOpaque(true);
      Signup.setBackground(color.color_blue);
      Signup.setForeground(Color.white);;
      Signup.setFont(font.f2_18);
      Signup.setHorizontalAlignment(JLabel.CENTER);
      Signup.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            new Signup();
            setVisible(false);
         }
      });
      
      NmemberTCon.setOpaque(true);
      NmemberTCon.setBackground(color.color_blue);
      NmemberTCon.setForeground(Color.white);;
      NmemberTCon.setFont(font.f2_18);
      NmemberTCon.setHorizontalAlignment(JLabel.CENTER);
      NmemberTCon.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            new CheckTick_Non();
            setVisible(false);
         }
      });
      toHome.setOpaque(true);
      toHome.setBackground(color.color_blue);
      toHome.setForeground(Color.white);;
      toHome.setFont(font.f2_18);
      toHome.setHorizontalAlignment(JLabel.CENTER);
      toHome.addActionListener(new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            new Main();
            
            setVisible(false);
         }
      });
      
      title.setBounds(60,215,300,60);
      title.setFont(font.f2_30);
      
      info.setOpaque(false);
      info.setBounds(130,300,110,100);
      info.setLayout(new GridLayout(2, 1, 0, 30)); 
      
      id.setOpaque(true);
      id.setBackground(color.color_gray);
      id.setForeground(Color.white);
      id.setFont(font.f3_16);
      id.setHorizontalAlignment(JLabel.CENTER);
      
      password.setOpaque(true);
      password.setBackground(color.color_gray);
      password.setForeground(Color.white);
      password.setFont(font.f3_16);
      password.setHorizontalAlignment(JLabel.CENTER);
      
      inputField.setOpaque(false);
      inputField.setBounds(280,300,250,230);
      inputField.setLayout(new GridLayout(4, 1, 0, 30));
      input1.setFont(font.f3_16);
      input2.setFont(font.f1_16);

      
      
      loginBtn.setBounds(550,300,110,35);
      loginBtn.setBackground(Color.lightGray);
      loginBtn.setForeground(Color.black);
      loginBtn.setFont(font.f3_14);
      
      searchIdPw.setBounds(550,365,110,35);
      searchIdPw.setBackground(Color.lightGray);
      searchIdPw.setForeground(Color.black);
      searchIdPw.setBorderPainted(false);
      searchIdPw.setFont(font.f3_14);
      searchIdPw.addActionListener(new ActionListener() {
          
          @Override
          public void actionPerformed(ActionEvent e) {
             new Find_IdPw();
             setVisible(false);
          }
       });
       
      
      
   }

   public void init() {
      add(title);

      loginBar.add(Signup);
      loginBar.add(NmemberTCon);
      loginBar.add(toHome);
      add(loginBar);
      
      info.add(id);
      info.add(password);
      add(info);
      
      inputField.add(input1);
      inputField.add(input2);
      add(inputField);
      
      add(loginBtn);
      add(searchIdPw);
      
   }
   

}