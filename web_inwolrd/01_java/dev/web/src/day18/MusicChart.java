package day18;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


// 버튼 클릭 시 Bugs Music 웹사이트의 현재 차트를 가져와 상위 10곡 출력하는 프로그램
public class MusicChart extends JFrame implements ActionListener {			// MusicChart클래스는 JFrame을 상속받아 GUI창으로 사용, 
																			// ActionListener 인터페이스를 구현하여 버튼 클릭 이벤트 처리
	JLabel[] labels;														// 노래 순위를 표시할 JLabel 배열 
	JButton bt;																// 데이터를 가져오는 버튼
	JLabel log;																// 버튼 클릭 횟수를 표시할 라벨
	int count = 0;															// 버튼 클릭 횟수를 저장하는 변수 초기화
	
	MusicChart(){															// 생성자 : 초기화 작업 수행
		
		// 버튼 생성 및 설정
		bt = new JButton("GET!!!");											// JButton 객체를 생성해 "GET!!!" 버튼 생성
		bt.setBounds(20, 20, 340, 30);  									// 버튼 위치(x, y)와 너비, 높이(width, height) 설정
		add(bt);															// 프레임에 버튼 추가			
		bt.addActionListener(this);											// 버튼에 액션 리스너 설정
		
		// 상위 10곡 노래를 표시할 라벨 배열 초기화
		labels = new JLabel[10];											// JLabel 배열을 생성해 상위 10개의 곡 순위를 표시할 공간을 미리 만듬									
		for(int i=0; i<10; i++) {									
			labels[i] = new JLabel("Ranking " + (i+1));						// 각 라벨에 "Ranking 1", "Ranking 2",... "Ranking 10"으로 초기 텍스트 설정
			labels[i].setBounds(20, 80 + (40*i), 340, 30);					// 라벨의 위치와 크기 설정
																			// x 좌표 20, y 좌표는 80 + (40 * i)로 설정하여, 라벨들이 수직으로 아래로 배치되도록 함
																		    // y 값 80에서 시작하고, 각 라벨은 40 픽셀씩 아래로 내려감 (40 * i로 계산)
																		    // 라벨의 너비 340, 높이 30으로 설정
			add(labels[i]);													// 설정한 라벨을 프레임에 추가하여 화면에 표시되도록 함
		}
		
		
		// 로그 라벨 초기화 및 설정
		log = new JLabel("Log Region");										// "Log Region" 텍스트의 라벨을 log변수에 저장								
		log.setBounds(20, 480, 340, 30);									// log 라벨의 위치와 크기 설정
		add(log);															// log 라벨을 프레임에 추가
		
		// 프레임 설정
		setSize(400, 600);													// 프레임 크기를 너비 400, 높이 600으로 설정
		setLayout(null);													// Layout을 null로 설정하여 직접 위치를 배치
		setVisible(true);													// 프레임을 화면에 보이도록 설정
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MusicChart mc = new MusicChart();									// MusicChart객체 생성하여 mc변수에 저장, 생성자 호출하여 프레임 표시
	}
	
	// ActionEvent가 발생했을 때 호출되는 메서드
	public void actionPerformed(ActionEvent e) {
		
		// 버튼에서 이벤트가 발생했는지 확인
		if(e.getSource() == bt) {											// 버튼을 클릭했다면?
			System.out.println("Button Clicked!!");							// 콘솔에 "Button Clicked!!" 출력
			count++;														// 클릭 횟수를 1 증가시킴
			log.setText("Button Clicked!! Count : " + count);				// log 라벨에 클릭 횟수를 업데이트하여 표시
		}
		
		// Bugs Music 차트를 가져올 URL
		String URL = "http://music.bugs.co.kr/chart";
		
		
		try {
			Document doc = Jsoup.connect(URL).get();						// Jsoup 라이브러리를 사용하여 URL에서 HTML 문서를 가져옴
			System.out.println(doc);										// 가져온 HTML 문서를 출력
			
			Elements elements = doc.select("p.title");						// p.title = p태그 중 class가 title인 것
																			// CSS Selector 표준 문법 준수
			
			int rank = 1;													// 순위를 나타낼 변수 초기화
			
			// 가져온 노래 제목 요소들에 대해 반복문 실행
			for(Element element : elements) {
				String text = "Ranking " + rank + " : " + element.text();	// 순위 + 제목 텍스트 생성
				System.out.println(text);									// 생성한 텍스트 출력
				labels[rank-1].setText(text);								// 인덱스는 0번째 부터이기때문에 -1을 한 후 생성한 텍스트를 JLabel에 설정하여 화면에 표시
				if(rank == 10) break;										// 10위까지만 표시하고 반복문 종료
				rank++;
			}
			
			// 아티스트 이름을 위해 rank변수 초기화
			rank = 1;
			Elements artists = doc.select("p.artist");						// p 태그 중에서 class가 "artist"인 요소 선택(아티스트 이름에 해당)
			
			// 가져온 아티스트 요소들에 대해 반복문 실행
			for(Element artist : artists) {												
				String text = labels[rank-1].getText();						// 이미 설정된 순위와 제목 텍스트를 가져옴
				text += "[" + artist.text() + "]";							// 기존 텍스트에 아티스트 이름 추가
				labels[rank-1].setText(text);								// 업데이트된 텍스트를 JLabel에 설정			
				if(rank == 10) break;										// 10위까지만 표시하고 반복문 종료
				rank++;
			}
		}

		catch(Exception ex) {												// 예외처리
			ex.printStackTrace();											// 예외 발생 시 오류 메시지 출력 코드

		}
	}

}
