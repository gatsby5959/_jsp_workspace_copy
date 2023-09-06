package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import domain.BoardVO;

public class BoardServiceImpl implements BoardService {
   
   private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
   private BoardDAO bdao; // dao=> interface로 생성
   
   public BoardServiceImpl() {
      bdao= new BoardDAOImpl(); // dao => class로 생성
   }

   @Override
   public int register(BoardVO bvo) {
      log.info("service register check 2");
      return bdao.insert(bvo);
   }

@Override
public List<BoardVO> getList() {
	log.info("list check 2");
	return bdao.selectList();
}

@Override
public BoardVO getDetail(int bno) {
	log.info("detail check 2");
	return bdao.selectOne(bno);
}

@Override
public int modify(BoardVO bvo) {
	log.info("edit check 2");
	return bdao.update(bvo);
}

@Override
public int remove(BoardVO bvo) {
	log.info("edit check 2");
	return bdao.delete(bvo);
}

@Override
public int remove(int bno) {
	log.info("edit check 2");
	return bdao.delete(bno);
}


   
   
}