package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import service.BoardService;
import service.BoardServiceImpl;



@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//로그기록 객체 생성(클래스마다 복붙해서 쓰기)
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
    private RequestDispatcher rdp; // jsp에서 받은 요청을 처리, 처리의 결과를 다른 jsp로 보내는 역할을 하는 객체   (요청에 대한응답)
	private String destPage; //목적지 주소 기록 변수(요청에 대한)
	private int isOk;	//DB의 구문 체크 값 저장
	//controller <-> service, service <-> dao
	private BoardService bsv; //아직 미구현 상태 (service팩키지 => interface로 생성)

    public BoardController() {
        bsv = new BoardServiceImpl(); // bsv를 구현할 객체 생성 (service팩키지=>class생성)
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 모든 처리가 이루어지는 부분
		//각 객체의 인코딩 설정
		request.setCharacterEncoding("utf-8"); //한글안깨지게처리
		response.setCharacterEncoding("utf-8"); // 보내는 객체의 타입을 설정
		response.setContentType("text/html; charset=UTF-8"); // 보내는 객체의 타입을 설정
		
		String uri = request.getRequestURI(); //jsp에서 오는 요청 주소
		log.info("이건 로그쩜인포임 uri >>>>> "+uri); //내 로그 찾기  /brd/register
		System.out.println("uri >>>>> "+uri);
		//register
		String path = uri.substring(uri.lastIndexOf("/")+1);
		System.out.println("실 요청 경로 >>>> "+path);
		
		switch(path) {
		case "register":
			//글쓰기 페이지로 이동해서 페이지가 나오도록 설정
			//목적지 주소 설정
			destPage = "/register.jsp";  //register.jsp로이동해 주세요
			System.out.println("이동완료~!!");
			break;
		case "insert":
			try {
				//jsp에서 데이터를 입력 후 -> 전송
				//데이터를 db에 등록 한 후 -> index.jsp로 이동
				String title = request.getParameter("title"); //모든 데이터 String
				String writer = request.getParameter("writer");
				String content = request.getParameter("content");
				log.info(">>>>> insert check 1");
				BoardVO bvo = new BoardVO(title, writer, content);
				log.info("bvo >>>>>" + bvo);
				
				isOk =bsv.register(bvo);
				log.info((isOk>0)? "OK" : "Fail");
				
				//목적지 주소 설정
//				destPage = "/insert.jsp";
				destPage = "/index.jsp";
				
				log.info("뭐 어쨋든 잘 된듯");
			} catch (Exception e) {
				log.info("insert error!!");
				e.printStackTrace();
			}
			break;
			
			
		case "list":
			try {
				log.info("list check 1");
				List<BoardVO> list = bsv.getList();
				log.info("list check 4"); 
				log.info(list.get(0).toString()); //가져온 리스트에 첫 값을 로그에 찍어봄 // BoardVO클래스에 toString 제너레잇해야함
				
				//list를 화면으로 보내기 requset객체에 실어 보내기  이름은 "list"
				request.setAttribute("list", list);
				destPage = "/list.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("list error!!");
			}
			break;
			
			
		case "detail":
			try {
				//jsp에서 보낸 bno 받기  => STring을 ㅜint로 형변환해서 받기
				int bno = Integer.parseInt( request.getParameter("bno")) ;// 리퀘스트쩜겟파라미터는 무조건 String으로 받음
				log.info("detail check 1");
				BoardVO bvo = bsv.getDetail(bno);
				log.info("detail check 4");
				log.info("bvo>>>>>>"+bvo);
				request.setAttribute("bvo", bvo);
				destPage="/detail.jsp";
			} catch (Exception e) {
				e.printStackTrace();
				log.info("detail error!!");
			}
			break;
			
		case "modify":
			try {
				//bno 받기
				int bno = Integer.parseInt(request.getParameter("bno"));
				log.info("modify check 1");
				BoardVO bvo = bsv.getDetail(bno);
				request.setAttribute("bvo", bvo);
				destPage="/modify.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
				log.info("modify error");
			}
			break;
			
			
		}//switch문 끝
		
		//목적지 데이터 경로로 전달해 주는 객체(reqeustDispatcher)
		rdp = request.getRequestDispatcher(destPage);		
		rdp.forward(request, response); // 요청에 필요한 객체를 가지고 경로로 이동
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(request, response);
	}

}
