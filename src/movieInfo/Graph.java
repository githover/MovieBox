package movieInfo;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kgv.dao.M_Ticketdb_DAO;
import com.kgv.dao.Movie_info_DAO;
import com.kgv.dao.No_Ticketdb_DAO;
import com.kgv.dto.Movie_info_DTO;

public class Graph extends JFrame{
	Movie_info_DAO moviedao = new Movie_info_DAO();
	M_Ticketdb_DAO mtdao = new M_Ticketdb_DAO();
	No_Ticketdb_DAO ntdao = new No_Ticketdb_DAO();
	List<Movie_info_DTO> moviedto = moviedao.allMovie();

	int movie1, movie2, movie3, movie4;
	JPanel drawingPanel = new JPanel();
	
	public Graph() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x버튼 활성
		setTitle("예매현황");
		setLocation(500, 200);
		setSize(550,300); //프레임 사이즈
		setVisible(true);
		setLocationRelativeTo(null); // 중앙에 배치
		
//		add(drawingPanel,BorderLayout.CENTER);
		
		//예매
//		movie1 = mtdao.moviecount(moviedto.get(0).toString())+ntdao.moviecount(moviedto.get(0).toString());
//		movie2 = mtdao.moviecount(moviedto.get(1).toString())+ntdao.moviecount(moviedto.get(1).toString());
//		movie3 = mtdao.moviecount(moviedto.get(2).toString())+ntdao.moviecount(moviedto.get(2).toString());
//		movie4 = mtdao.moviecount(moviedto.get(3).toString())+ntdao.moviecount(moviedto.get(3).toString());
		
		movie1 = 38; movie2=58; movie3=82; movie4=27;
		setScores(movie1, movie2, movie3, movie4);
		repaint();
		
	}


	public void paint(Graphics g) {
		g.clearRect(0, 0, getWidth(), getHeight());
		g.drawLine(50, 250, 500, 250);

		for(int cnt=1; cnt<11; cnt++) {
			g.drawString(cnt*10 + "", 25, 255-20*cnt);
			g.drawLine(50, 250-20*cnt, 500, 250-20*cnt);
		}
		
//		List<Movie_info_DTO> moviedto = moviedao.allMovie();
//		String s = moviedto.get(0).toString();
//		System.out.println(s);
		g.drawLine(50, 20, 50, 250);
		g.drawString(moviedto.get(0).toString(), 100, 270);
		g.drawString(moviedto.get(1).toString(), 200, 270);
		g.drawString(moviedto.get(2).toString(), 300, 270);
		g.drawString(moviedto.get(3).toString(), 400, 270);
		g.setColor(Color.red);
		
		if(movie1>0) 
			g.fillRect(110, 250-movie1*2, 10, movie1*2);
		if(movie2>0)
			g.fillRect(210, 250-movie2*2, 10, movie2*2);
		if(movie3>0)
			g.fillRect(310, 250-movie3*2, 10, movie3*2);
		if(movie4>0)
			g.fillRect(410, 250-movie4*2, 10, movie4*2);
	}
	
	void setScores(int movie1, int movie2, int movie3, int movie4) {
		this.movie1 = movie1;
		this.movie2 = movie2;
		this.movie3 = movie3;
		this.movie4 = movie4;
	}

}