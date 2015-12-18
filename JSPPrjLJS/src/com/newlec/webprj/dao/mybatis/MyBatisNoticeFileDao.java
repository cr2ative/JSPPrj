package com.newlec.webprj.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.dao.NoticeDao;
import com.newlec.webprj.dao.NoticeFileDao;
import com.newlec.webprj.vo.Member;
import com.newlec.webprj.vo.Notice;
import com.newlec.webprj.vo.NoticeFile;

public class MyBatisNoticeFileDao implements NoticeFileDao {

	//SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<NoticeFile> getNoticeFiles(String noticeCode) {
		//SqlSession session = ssf.openSession();
		NoticeFileDao dao = sqlSession.getMapper(NoticeFileDao.class); // mapper��ü ����
		
		List<NoticeFile> list = dao.getNoticeFiles(noticeCode);
		
		sqlSession.close(); // ���� ����.

		return list;
	}

	@Override
	public int insert(NoticeFile file) throws SQLException {
		//SqlSession session = ssf.openSession();
	      NoticeFileDao dao = sqlSession.getMapper(NoticeFileDao.class);
	      int result=0;
	      
	      try{
	    	  result = dao.insert(file);
	      }
	      catch(Exception e)
	      {
	         System.out.println(e.getMessage());
	         sqlSession.rollback();
	      }
	      
	      sqlSession.commit();      
	      sqlSession.close();
	      
	      return result;
	}

}

