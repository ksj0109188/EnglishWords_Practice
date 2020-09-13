package Model01.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doHandle(request,response);
	}

	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String command = request.getParameter("command");
		MemberDAO dao = new MemberDAO();
	
		if(command!=null&&command.equals("login")){
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pw");
			Membervo vo = new Membervo();
			vo.setId(_id);
			vo.setPwd(_pwd);
			int check =(int)dao.Login(vo);
			if(check==1) {out.println("<script>alert('로그인');location.href='ChoiceContent.jsp?S_ID="+_id+"'</script>");}
			else if(check==2) {out.println("<script>alert('아이디가 틀렸습니다.'); history.go(-1);</script>");}
			else if(check==0) {out.println("<script>alert('비밀번호가 틀렸습니다.'); history.go(-1);</script>");}
		}
		else if(command!=null&&command.equals("add_member")){
			String _id = request.getParameter("rgister_id");
			String _pwd = request.getParameter("register_pwd");
			System.out.println(_id);
			Membervo vo = new Membervo();
			vo.setId(_id);
			vo.setPwd(_pwd);
			dao.addMember(vo);
			out.println("<script>alert('가입완료.');window.close();</script>");
		}
		else if(command!=null&&command.equals("saveWord")){
			int ErrorCheck =1;
			String _word = request.getParameter("word");
			String _mean = request.getParameter("mean");
			String _S_ID = request.getParameter("S_ID");
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			vo.setwORD(_word);
			vo.setMean(_mean);
			out.println(_S_ID); //세션 아이디 테스트
			//중복저장 체크
			ErrorCheck=dao.SaveWord(vo);
			if(ErrorCheck==1)out.println("<script>location.href='SaveWord.jsp';</script>");
			else if(ErrorCheck==0)out.println("<script>alert('중복단어');location.href='SaveWord.jsp';</script>"); 
		}
		else if(command!=null&&command.equals("study")){
			String _S_ID = request.getParameter("id");
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			List wordList=dao.ListWord(vo);
			request.setAttribute("wordList",wordList);
			RequestDispatcher dispatch = request.getRequestDispatcher("Study.jsp");
			dispatch.forward(request,response);
		}
		else if(command!=null&&command.equals("find")){
			String _S_ID = request.getParameter("S_ID");
			String _S_Word = request.getParameter("search_Word");
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			vo.setwORD(_S_Word);
			List wordList=dao.Search_ListWord(vo);
			request.setAttribute("wordList",wordList);
			RequestDispatcher dispatch = request.getRequestDispatcher("wordModify.jsp");
			dispatch.forward(request,response);
		}
	
		else if(command!=null&&command.equals("modify")){
			System.out.println("Modify_check");
			String _S_ID = request.getParameter("id");
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			List wordList=dao.ListWord_modify(vo);
			request.setAttribute("wordList",wordList);
			RequestDispatcher dispatch = request.getRequestDispatcher("wordModify.jsp");
			dispatch.forward(request,response);
		}
		else if(command!=null&&command.equals("Modify_Card")){
			System.out.println("Modify_Card_command");
			String _S_ID = request.getParameter("S_ID");
			String _S_Word = request.getParameter("first_word");	//초기
			String _S_Word_Modify = request.getParameter("word");	//수정
			String _S_Mean = request.getParameter("first_mean");	//초기
			String _S_Mean_Modify = request.getParameter("mean");	//수정
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			vo.setwORD(_S_Word);
			vo.setMean(_S_Mean);
			vo.setM_wORD(_S_Word_Modify);
			vo.setM_Mean(_S_Mean_Modify);
			dao.ListWord_modify_card(vo);
			List wordList=dao.ListWord_modify(vo);
			out.println("<script>opener.location.reload();\n" + 
					"window.close();</script>");
//			request.setAttribute("wordList",wordList);
//			RequestDispatcher dispatch = request.getRequestDispatcher("wordModify.jsp");
//			dispatch.forward(request,response);
		}
		else if(command!=null&&command.equals("delete_Card")){ 
			String _S_ID = request.getParameter("S_ID");
			String _S_WORD = request.getParameter("word");
			System.out.println(_S_WORD);
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			vo.setwORD(_S_WORD);
			dao.delete_Word(vo);
			out.println("<script>opener.location.reload();\n" + 
					"window.close();</script>");
		}
		//studypage의 command값 처리
		else if(command!=null&&command.equals("again")){ 
			String _S_ID = request.getParameter("id");
			String _S_card_front=request.getParameter("card_front");
			String _S_card_back=request.getParameter("card_back");
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			vo.setwORD(_S_card_front);
			vo.setMean(_S_card_back);
			dao.again(vo);
			 out.println("<script>history.go(-1);</script>");
		}
		else if(command!=null&&command.equals("currect")){ 
			String _S_ID = request.getParameter("id");
			String _S_card_front=request.getParameter("card_front");
			String _S_card_back=request.getParameter("card_back");
			WordVo vo = new WordVo();
			vo.setUserId(_S_ID);
			vo.setwORD(_S_card_front);
			vo.setMean(_S_card_back);
			dao.currect(vo);
			 out.println("<script>history.go(-1);</script>");
		}
	}
}
