package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.BoardController;
import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	//로그기록 객체 생성(클래스마다 복붙해서 쓰기)
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	
	private SqlSession sql;
	
	private final String NS = "BoardMapper."; //NS = NameSpace;
	
	public BoardDAOImpl() {
		sql = DatabaseBuilder.getFactory().openSession();
		
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("insert dao check");
		int isOk= sql.insert(NS+"add", bvo);  //내가 줄거는 bvo를 줄꺼에영?  //BoardMapper.add를실행해 라는뜻 id만 바꿔서쓰자?
		if(isOk>0) { // insert update delete 시 commit이 필요
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		log.info("list check 3");
		return sql.selectList(NS+"list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("detail check 3");
		return sql.selectOne(NS+"detail", bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("edit check 3");
		//insert update delete 시 commit필요
		int isOk = sql.update(NS+"up", bvo);
		if(isOk>0) {
			sql.commit();
		}
		log.info("컨텐트>>>>환4>>>> "+bvo);
		return isOk;
	}

	@Override
	public int delete(BoardVO bvo) {
		log.info("remove check 3");
		//insert update delete 시 commit필요
		int isOk = sql.delete(NS+"del", bvo);
		if(isOk>0) {
			sql.commit();
		}
//		log.info("컨텐트>>>>환4>>>> "+bvo);
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("remove check 3");
		//insert update delete 시 commit필요
		int isOk = sql.delete(NS+"del", bno);
		if(isOk>0) {
			sql.commit();
		}
//		log.info("컨텐트>>>>환4>>>> "+bvo);
		return isOk;
	}
	
}
