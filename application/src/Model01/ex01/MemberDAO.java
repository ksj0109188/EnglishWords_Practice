package Model01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	private ResultSet rs= null;
	public MemberDAO()
	{
		try {
			Context ctx=new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			dataFactory = (DataSource)envContext.lookup("jdbc/oracle");
		}
		catch(Exception e) {
			e.printStackTrace();	
		}
	}
	
	public void addMember(Membervo memberVO) {
		// TODO Auto-generated method stub
		String id = memberVO.getId();
		String pwd = memberVO.getPwd();
		try {
			Connection con = dataFactory.getConnection();
			String query = "insert into WORD_USER1";
			query +="(ID,PWD) ";
			query +="values(?,?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			pstmt.executeUpdate();
			pstmt.close();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
			
		}
	}
	public int Login(Membervo memberVO) {
		// TODO Auto-generated method stub
		String dbPW="";
		int x = -1;
		try {
			String id = memberVO.getId();
			String pwd = memberVO.getPwd();
			Connection con = dataFactory.getConnection();
			String query = "select PWD FROM WORD_USER1 where ID=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				dbPW = rs.getString("PWD");
				if(dbPW.equals(pwd))x=1; //아이디가 존재하면
				else x=0; //아이디가 없을경우
			}
			else { //아이디가 없을 경우
			x=2;
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		return x;
	}

	public int SaveWord(WordVo vo) {
		// TODO Auto-generated method stub
		int CheckBit = 1;
		String _ID = vo.getUserId();
		String _Word = vo.getwORD();
		String _mean = vo.getMean();
		try {
		Connection con = dataFactory.getConnection();
		String query="insert into WORD2";
				query+=" values(?,?,?,";
				query+="to_char(sysdate+9/24,'yyyy-mm-dd hh24:mi:ss'),";
				query+="0)";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1,_ID);
		pstmt.setString(2,_Word);
		pstmt.setString(3,_mean);
		pstmt.executeUpdate();
		con.close();
		pstmt.close();
		return CheckBit;
		}
		catch(Exception e) {
			e.printStackTrace();
			return CheckBit=0;
		}
	}

	public List<WordVo> ListWord(WordVo vo) {
		// TODO listword
		List<WordVo> membersList = new ArrayList<WordVo>();
		try {
			String id=vo.getUserId();
			Connection con = dataFactory.getConnection();
			String query = "select * from WORD2 where rownum=1 AND USERID=? AND "
					+ "WORDDATE <=to_char(sysdate+9/24,'yyyy-mm-dd hh24:mi:ss')";
			System.out.println("prepareStatement:"+query);
			System.out.println(id);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String _id=	rs.getString("USERID");
				String _FRONT= rs.getString("FRONTCARD");
				String _BACK=rs.getString("BACKCARD");
				String _DATE=rs.getString("WORDDATE");
				WordVo wordvo = new WordVo();
				wordvo.setUserId(_id);
				wordvo.setwORD(_FRONT);
				wordvo.setMean(_BACK);
				wordvo.setSaveDate(_DATE);
				membersList.add(wordvo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		return membersList;
	} //listWord() end.
	public List Search_ListWord(WordVo vo) {
		// TODO Auto-generated method stub
		List<WordVo> membersList = new ArrayList<WordVo>();
		try {
			String id=vo.getUserId();
			String search_Word = vo.getwORD();
			Connection con = dataFactory.getConnection();
			String query = "select * from WORD2 where USERID=? AND FRONTCARD=?";
			System.out.println("prepareStatement:"+query);
			System.out.println(id);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,search_Word);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String _id=	rs.getString("USERID");
				String _FRONT= rs.getString("FRONTCARD");
				String _BACK=rs.getString("BACKCARD");
				String _DATE=rs.getString("WORDDATE");
				WordVo wordvo = new WordVo();
				wordvo.setUserId(_id);
				wordvo.setwORD(_FRONT);
				wordvo.setMean(_BACK);
				wordvo.setSaveDate(_DATE);
				membersList.add(wordvo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		return membersList;
	}
	public List ListWord_modify(WordVo vo) {
		// TODO Auto-generated method stub
		List<WordVo> membersList = new ArrayList<WordVo>();
		try {
			String id=vo.getUserId();
			Connection con = dataFactory.getConnection();
			String query = "select * from WORD2 where UserId=?";
			System.out.println("prepareStatement:"+query);
			System.out.println(id);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String _id=	rs.getString("USERID");
				String _FRONT= rs.getString("FRONTCARD");
				String _BACK=rs.getString("BACKCARD");
				String _DATE=rs.getString("WORDDATE");
				WordVo wordvo = new WordVo();
				wordvo.setUserId(_id);
				wordvo.setwORD(_FRONT);
				wordvo.setMean(_BACK);
				wordvo.setSaveDate(_DATE);
				membersList.add(wordvo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			}
		return membersList;	
	}
	public void ListWord_modify_card(WordVo vo) {
		// TODO Auto-generated method stub
		String id=vo.getUserId();
		String Word=vo.getwORD();
		String Mean=vo.getMean();
		String M_Word=vo.getM_wORD();
		String M_Mean=vo.getM_Mean();
		try {
			Connection con = dataFactory.getConnection();
			String query = "UPDATE WORD2 SET FRONTCARD = ?, BACKCARD=? WHERE userid=? AND frontcard=? OR backcard=?";
			System.out.println("prepareStatement:"+query);
			System.out.println(id);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,M_Word);
			pstmt.setString(2,M_Mean);
			pstmt.setString(3,id);
			pstmt.setString(4,Word);
			pstmt.setString(5,Mean);
			pstmt.executeQuery();
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			}
	}
	public void delete_Word(WordVo vo) {
		// TODO Auto-generated method stub
		String id=vo.getUserId();
		String Word=vo.getwORD();
		try {
			Connection con = dataFactory.getConnection();
			String query = "DELETE FROM WORD2 where USERID=? and frontcard=?";
			System.out.println("prepareStatement:"+query);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,Word);
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
			}
	}

	public void again(WordVo vo) {
		// TODO Auto-generated method stub
		String _ID = vo.getUserId();
		String _Word = vo.getwORD();
		String _mean = vo.getMean();
		try {
		Connection con = dataFactory.getConnection();
		String query="UPDATE WORD2 set WORDDATE=to_char(sysdate+10/(24*60)+9/24,'yyyy-mm-dd hh24:mi:ss'),CCOUNT=0 "; //10분 추가.
				query+="where userid=? and ";
				query+="frontcard=? ";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1,_ID);
		pstmt.setString(2,_Word);
		pstmt.executeUpdate();
		con.commit();
		con.close();
		pstmt.close();
		}
		catch(Exception e) {
		}
	}

	public void currect(WordVo vo) {
		// TODO Auto-generated method stub
		String _ID = vo.getUserId();
		String _Word = vo.getwORD();
		String _mean = vo.getMean();
		try {
		Connection con = dataFactory.getConnection();
		String query="UPDATE WORD2 set CCOUNT=CCOUNT+1, WORDDATE=decode(ccount,0,to_char(sysdate+1+9/24,'yyyy-mm-dd hh24:mi:ss')"
				+ ",1,to_char(sysdate+3+9/24,'yyyy-mm-dd hh24:mi:ss')"
				+ ",2,sysdate+7"
				+ ",3,sysdate+30"
				+ ",4,sysdate+365)";
				query+="where userid=? and ";
				query+="frontcard=? ";
		pstmt = con.prepareStatement(query);
		pstmt.setString(1,_ID);
		pstmt.setString(2,_Word);
		pstmt.executeUpdate();
		con.commit();
		con.close();
		pstmt.close();
		}
		catch(Exception e) {
		}
	}	
} //MemberDao end. 