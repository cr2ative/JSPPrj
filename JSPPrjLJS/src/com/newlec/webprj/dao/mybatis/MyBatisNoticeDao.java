package com.newlec.webprj.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.lf5.util.ResourceUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.dao.NoticeDao;
import com.newlec.webprj.vo.Member;
import com.newlec.webprj.vo.Notice;

public class MyBatisNoticeDao implements NoticeDao {

	//SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Notice> getNotices(int page, String field, String query) {
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class); // mapper按眉 积己
		List<Notice> list = dao.getNotices(page, field, query);

		//sqlSession.close(); // 技记 辆丰.

		return list;
	}
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public Notice getNotice(String code) {
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class); // mapper按眉 积己
		Notice notice = dao.getNotice(code);

		//sqlSession.close(); // 技记 辆丰.

		return notice;
	}

	@Override
	public int insert(Notice n) throws SQLException {
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		int af = 0;
		af = dao.insert(n);
		/*try {
			result = dao.insert(n);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			sqlSession.rollback();
		}

		sqlSession.commit();
		sqlSession.close();*/

		return af;
	}

	@Override
	public String getLastCode() {
		//SqlSession session = ssf.openSession();
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		String code = dao.getLastCode();
		
		//sqlSession.close();
		
		return code;
	}
	@Override
	public Notice getPrevNotice(String code) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		
		return dao.getPrevNotice(code);
	}
	@Override
	public Notice getNextNotice(String code) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		
		return dao.getNextNotice(code);
	}
	@Override
	public int delete(String code) {
		NoticeDao dao = sqlSession.getMapper(NoticeDao.class);
		
		return dao.delete(code);
	}

}
