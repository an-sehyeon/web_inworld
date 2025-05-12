package DB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
// 스윙으로 구현
public class HptlSearch extends JFrame implements ActionListener {					// JFrame을 상속
	JTextField tf;																	// 검색을 위한 input박스
	JButton bt;																		// 버튼
	JLabel label;
	JTable table;
	JScrollPane sp;
	
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	HptlSearch(){
		tf = new JTextField();
		tf.setBounds(20, 20, 340, 30);
		
		bt = new JButton("Search");
		bt.setBounds(370, 20, 100, 30);
		bt.addActionListener(this);
		
		label = new JLabel();
		label.setBounds(20, 500, 500, 30);
		label.setText("정보 표시 영역");
		
		String header[] = {"병원 이름", "주소"};
		String contents[][] = new String[10][2];
		table = new JTable(contents, header);
		table.setRowHeight(25);
		sp = new JScrollPane(table);
		sp.setBounds(20, 100, 500, 300);
		
		// Sample Data
		table.setValueAt("Sample", 1, 1);
		
		add(tf); add(bt); add(label); add(sp);
		setSize(600, 600);
		setLayout(null);
		setVisible(true);
		
		DBConnect();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new HptlSearch();
		

	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt) {
//			Font font = new Font("맑은 고딕");
//			label.setFont(font);
			label.setText("너 지금 버튼 눌렀니?");
			for(int i = 0; i < 10; i++) {												// 검색 버튼을 누른 후 초기화 시키는 로직
				table.setValueAt("", i, 0);
				table.setValueAt("", i, 1);
			}
			
			
			
			String word = tf.getText();													// 사용자가 검색버튼을 누를 때, 입력한 값을 word에 저장
			String sql 	= 	"select hptl_nm, addr ";
				   sql	+= 	"from hptl_mast ";
				   sql	+= 	"where hptl_nm like '%" + word + "%' ";
				   sql	+= 	"order by doc_num desc ";
				   sql	+= 	"limit 10 ";
					
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				
				// for Data Test
//				if(rs.next()) {
//					HptlVO vo = new HptlVO();
//					vo.setHptlNm(rs.getString("hptl_nm"));
//					vo.setAddr(rs.getString("addr"));
//					System.out.println(vo.getHptlNm());
//				}
				
				
				int index = 0;
				while(rs.next()) {
					String hptlNm = rs.getString("hptl_nm");
					String addr = rs.getString("addr");
					table.setValueAt(hptlNm, index, 0);
					table.setValueAt(addr, index, 1);
					index++;
				}
				
				
			}
			catch(Exception ex) {ex.printStackTrace(); }
			
			
		}
	}
	
	public void DBConnect() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3399/test",
					"root",
					"3416"
					);
			if(conn != null) {															// 정상적으로 connection이 된다면?		
				label.setText("DB Connection Established");								// 문자열 출력
			}
		}
		catch(Exception e) {e.printStackTrace(); }
	}

}
