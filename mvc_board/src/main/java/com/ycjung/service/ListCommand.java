package com.ycjung.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.ycjung.dao.IDao;
import com.ycjung.paging.PagingVo;

public class ListCommand implements Command {

    @Override
    public void execute(Model model, SqlSession sqlSession, PagingVo pv) {
        
        IDao dao = sqlSession.getMapper(IDao.class);
        model.addAttribute("list", dao.listDao());
        int rowCnt = dao.selectCount();
        
        pv.setTotalCount(rowCnt); // 전체 게시글 수
        float a = (float)pv.getDisplayRow();
        float b = (float)rowCnt / a;
        
        int resultPage = (int) Math.ceil(b);
        pv.setTotalPage(resultPage); // 전체 페이지 수
        model.addAttribute("pv", pv);
    }
}
