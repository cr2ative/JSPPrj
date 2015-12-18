package com.newlec.webprj.dao.mybatis;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newlec.webprj.dao.MemberDao;
import com.newlec.webprj.vo.Member;

public class MyBatisMemberDao implements MemberDao {

	//SqlSessionFactory ssf = NewlecSqlSessionFactoryBuilder.getSqlSessionFactory();

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<Member> getMembers() throws SQLException {

		return getMembers(1, "MID", "");
	}

	@Override
	public List<Member> getMembers(int page) throws SQLException {

		return getMembers(page, "MID", "");
	}

	@Override
	public List<Member> getMembers(int page, String field, String query) throws SQLException {

		//SqlSession session = ssf.openSession();
		MemberDao dao = sqlSession.getMapper(MemberDao.class); // mapper��ü ����
		List<Member> list = dao.getMembers(page, field, query);
		sqlSession.close(); // ���� ����.

		return list;
	}

	@Override
	public int update(Member member) throws SQLException {
		//SqlSession session = ssf.openSession();
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		int aft = dao.update(member);

		sqlSession.commit();
		sqlSession.close();
		return aft;
	}

	@Override
	public int delete(String mid) {
		//SqlSession session = ssf.openSession();
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		int aft = dao.delete(mid);

		sqlSession.commit();
		sqlSession.close();
		return aft;
	}

	@Override
	public int insert(Member member) throws SQLException {
			//SqlSession session = ssf.openSession();
			MemberDao dao = sqlSession.getMapper(MemberDao.class);
			int aft = dao.insert(member);

			sqlSession.commit();
			sqlSession.close();
			return aft;
		
	}

	@Override
	public Member getMember(String mid) throws SQLException {
		
		//SqlSession session = ssf.openSession();
		MemberDao dao = sqlSession.getMapper(MemberDao.class); // mapper��ü ����
		Member member = dao.getMember(mid);
		sqlSession.close(); // ���� ����.

		return member;
	}

}
