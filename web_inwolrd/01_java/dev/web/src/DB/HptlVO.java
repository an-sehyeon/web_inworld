package DB;

public class HptlVO {
	private String hptlNm;
	private String addr;
	private int docNum;


	public void setHptlNm(String hptlNm) { 
		this.hptlNm = hptlNm; 
	// 지금 세팅하고 있는 니 이름과 시간을 로그로 쌓는다.
	}	
	public void setAddr(String addr) { this.addr = addr; }
	public void setDocNum(int docNum) { this.docNum = docNum; }
	
	public String getHptlNm() {return hptlNm; }
	public String getAddr() {return addr; }
	public int getDocNum() {return docNum; }
}
