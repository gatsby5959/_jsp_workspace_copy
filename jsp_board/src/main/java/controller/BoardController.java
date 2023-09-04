package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.BoardService;
import service.BoardServiceImpl;



@WebServlet("/brd")
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
