import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import com.kgv.dao.Movie_info_DAO;
import com.kgv.dto.Movie_info_DTO;

import movieInfo.MovieInfo_01;
import movieInfo.MovieInfo_02;
import movieInfo.MovieInfo_03;
import movieInfo.MovieInfo_04;
import setting.SetColor;
import setting.SetFont;
import signup_login.BuyTicket_Non;
import signup_login.Login;
import signup_login.Signup;

import java.util.*;

class Ex10 extends JFrame{
    Thread snow;
    SnowPanel sp;
    
    Movie_info_DAO dao = new Movie_info_DAO();
	Movie_info_DTO dto; 
	

    String[] buttons = {"로그인", "비회원 예매"};
    
	//배경 이미지
	ImageIcon icon = new ImageIcon("./images/background_1.jpg");
	public JPanel contentPane;
	

	SetFont ft = new SetFont(); //글씨 패키지 사용
	SetColor col = new SetColor(); //색상 패키지 사용
    Ex10(){
        this.setTitle("눈 내리는 풍경");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sp=new SnowPanel();
        sp.setBackground(Color.black);

        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.add(sp);
        this.setSize(1280,720);

        this.setVisible(true);
        snow=new Thread(sp);
        snow.start();	    	

    }
    
    class SnowPanel extends JPanel implements Runnable{
        Vector<Point> v =new Vector<Point>();//눈덩이 50개의 위치를 저장
        SnowPanel(){
            this.setLayout(null);
            for(int i=0; i<30; i++){
                int x=(int)(Math.random()*1280);
                int y=(int)(Math.random()*720);
                v.add(new Point(x,y));
            }
            
            JButton end = new JButton("EXIT");		//임시 버튼
    	    end.setFont(ft.f2_18);
    	    end.setBounds(1130, 620, 90, 40);
    	    end.setBackground(col.color_red);
    	    end.setBorderPainted(false);
    	    end.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				
    				System.exit(0);
    			}
    		});
    	    add(end);

    	    JLabel lbTitle = new JLabel("*포스터를 클릭해서 영화정보를 확인하세요");
    		lbTitle.setFont(ft.f1_22);
    		lbTitle.setBounds(76, 123, 465, 56);
    		add(lbTitle);
    		
    		JButton btn_m1 = new JButton();
    		btn_m1.setBounds(67, 226, 230, 289);
    		add(btn_m1);
    		dto=dao.selectMovie("헤어질 결심");
    		btn_m1.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
    		
    		btn_m1.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				new MovieInfo_01();			
    				setVisible(false);
    			}
    		});
    		
    		JButton btn_m2 = new JButton();
    		btn_m2.setBounds(352, 226, 221, 289);
    		add(btn_m2);
    		dto=dao.selectMovie("뒤틀린집");
    		btn_m2.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
    		btn_m2.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				new MovieInfo_02();			
    				setVisible(false);
    			}
    		});
    		JButton btn_m3 = new JButton();
    		btn_m3.setBounds(618, 226, 230, 289);
    		add(btn_m3);
    		dto=dao.selectMovie("마녀2");
    		btn_m3.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
    		
    		btn_m3.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				new MovieInfo_03();			
    				setVisible(false);
    			}
    		});
    		JButton btn_m4 = new JButton();
    		btn_m4.setBounds(888, 226, 230, 289);
    		add(btn_m4);
    		dto=dao.selectMovie("명탐정 코난-할로윈의 신부");
    		btn_m4.setIcon(new ImageIcon("./images/"+dto.getB_Poster()));
    		
    		btn_m4.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				new MovieInfo_04();			
    				setVisible(false);
    			}
    		});
    		JButton btn_ticket = new JButton("예매");
    		btn_ticket.setBounds(870, 25, 114, 40);
    		add(btn_ticket);
    		btn_ticket.setFont(ft.f1_16);
    		btn_ticket.setForeground(Color.white);
    		btn_ticket.setBackground(col.color_blue);
    		btn_ticket.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent e) {
    		        int num = JOptionPane.showOptionDialog(null, "로그인이 필요한 서비스입니다", "알림",
    		        		JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.NO_OPTION, null, buttons, "로그인");
    		        if(num==0)	{new Login(); setVisible(false);}
    		        if(num==1)	{new BuyTicket_Non();setVisible(false);}
    		        
    				
    			}
    		});
    	
    		
    		JButton btn_login = new JButton("로그인");
    		btn_login.setBounds(1000, 25, 114, 40);
    		add(btn_login);
    		btn_login.setForeground(Color.white);
    		btn_login.setFont(ft.f1_16);
    		btn_login.setBackground(col.color_blue);
    		
    		JButton btn_signup = new JButton("회원가입");
    		btn_signup.setBounds(1128, 25, 114, 40);
    		add(btn_signup);
    		btn_signup.setForeground(Color.white);
    		btn_signup.setFont(ft.f1_16);
    		btn_signup.setBackground(col.color_blue);
    		
    		//버튼 누를 시 회원가입 창으로 전환
    		btn_signup.addActionListener(new ActionListener(){

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				new Signup();
    				setVisible(false); 
    			}
    		});  
    		
    		btn_login.addActionListener(new ActionListener(){

    			@Override
    			public void actionPerformed(ActionEvent e) {
    				new Login();
    				setVisible(false); 
    			}
    		});  
    	    
    	    
    	    
        }
        
        
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            ImageIcon icon=new ImageIcon("images/background_1.jpg");
            ImageIcon icon2=new ImageIcon("images/popcorn.png");

            Image img=icon.getImage();
            Image img2=icon2.getImage();

            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);//배경
            g.setColor(Color.lightGray);
            for(int i=0; i<v.size(); i++){//10x10 눈덩이를 그린다.
                Point p=v.get(i);
//                g.fillOval((int)p.getX(), (int)p.getY(), 10, 10);
                g.drawImage(img2, (int)p.getX(), (int)p.getY(), 20, 20, this);//배경
            }
        }
        
        
        
        
        public void changeSnowPoaition(){
            for(int i=0; i<v.size(); i++){
                Point p=v.get(i);
                p.x+=(int)(Math.random()*20);
                p.y+=(int)(Math.random()*5);
                //눈덩이가 프레임 밖으로 나가게 되면 나간 좌표를 0으로 초기화
                if(p.x>1280)
                    p.x=0;
                if(p.y>720)
                    p.y=0;
                v.set(i, p);
            }
        }
        
        public void run(){
            while(true){
                try{
                    Thread.sleep(50);
                }
                catch(Exception e){
                    return;
                }
                changeSnowPoaition();
                repaint();//계속 업데이트
            }
        }
    }
}
public class JavaApplication36 {
    public static void main(String[] args) {
        new Ex10();
    }
}
