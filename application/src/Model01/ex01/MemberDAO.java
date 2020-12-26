package Model01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
			String dbURL="jdbc:mysql://localhost:3306/Project1?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";                             
            String dbID="root";
            String dbPassword="8465123z";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(dbURL,dbID,dbPassword);
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
		System.out.println(_Word);
		try {
		String query="insert into WORD2";
				query+=" values(?,?,?,";
				query+="DATE_FORMAT(sysdate()+9/24,'%Y%m%d-%h%i'),";
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
			String query = "select * from WORD2 where id=? AND "
					+ "save_date <= DATE_FORMAT(sysdate()+9/24,'%Y%m%d-%h%i')";
			System.out.println("prepareStatement:"+query);
			System.out.println(id);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String _id=	rs.getString("id");
				String _FRONT= rs.getString("Word");
				String _BACK=rs.getString("Mean");
				String _DATE=rs.getString("save_date");
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
			String query = "select * from WORD2 where id=? AND Word=?";
			System.out.println("prepareStatement:"+query);
			System.out.println(id);
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			pstmt.setString(2,search_Word);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String _id=	rs.getString("id");
				String _FRONT= rs.getString("Word");
				String _BACK=rs.getString("Mean");
				String _DATE=rs.getString("save_date");
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
			String query = "select * from WORD2 where id=?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			while(rs.next())
			{
				String _id=	rs.getString("id");
				String _FRONT= rs.getString("Word");
				String _BACK=rs.getString("Mean");
				String _DATE=rs.getString("save_date");
				int _count = rs.getInt("count");
				WordVo wordvo = new WordVo();
				wordvo.setUserId(_id);
				wordvo.setwORD(_FRONT);
				wordvo.setMean(_BACK);
				wordvo.setSaveDate(_DATE);
				wordvo.setCount(_count);
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
			String query = "UPDATE WORD2 SET word = ?, mean=? WHERE id=? AND word=? OR mean=?";
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
			String query = "DELETE FROM WORD2 where id=? and word=?";
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
			
		String query="UPDATE WORD2 set save_date=DATE_FORMAT(sysdate()+10/(24*60)+9/24,'%Y%m%d-%h%i'),count=0 "; //10분 추가.
				query+="where id=? and ";
				query+="word=? ";
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
			
		String query="UPDATE WORD2 set count=count+1, save_date=case("
				+ "when count=0 then DATE_FORMAT(sysdate()+1+9/24,'%Y%m%d-%h%i')"
				+ "when count=1 then DATE_FORMAT(sysdate()+3+9/24,'%Y%m%d-%h%i')"
				+ "when count=2 then sysdate()+7"
				+ "when count=3 then sysdate()+30"
				+ "when count=4 then sysdate()+365)";
				query+="where id=? and ";
				query+="word=? ";
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