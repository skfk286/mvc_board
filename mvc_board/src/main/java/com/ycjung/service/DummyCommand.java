package com.ycjung.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.ycjung.dao.IDao;
import com.ycjung.paging.PagingVo;

public class DummyCommand implements Command{
    
    @Override
    public void execute(Model model, SqlSession sqlSession, PagingVo pv) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest)map.get("request");
        
        IDao dao = sqlSession.getMapper(IDao.class);

        for (int i = 0; i < 101; i++) {
            dao.dummyDataInsert("임시제목" + i, "홍길동" + i, "임시줄거리" + i);
        }
    }
}
