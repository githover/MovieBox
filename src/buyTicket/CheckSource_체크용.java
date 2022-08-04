package buyTicket;
//객체 주석 풀고 실행하면 하나씩 실행됩니다.

import java.awt.EventQueue;

public class CheckSource_체크용 {
public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new BuyTicket_SelMovie();
//					new BuyTicket_Seats();
//					new BuyTicket_Pay();
//					new BuyTicket_List_N();

//					new BuyTicket_List();
//					new BuyTicket_Result();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
