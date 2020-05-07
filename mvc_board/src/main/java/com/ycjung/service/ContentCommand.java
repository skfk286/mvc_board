package com.ycjung.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.ycjung.dao.IDao;
import com.ycjung.dto.ContentDto;
import com.ycjung.paging.PagingVo;

public class ContentCommand implements Command {

    @Override
    public void execute(Model model, SqlSession sqlSession, PagingVo pv) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest)map.get("request");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        int bCd = Integer.parseInt(request.getParameter("bCd"));
        dao.upHit(bCd);
        ContentDto dto = dao.viewDao(bCd);
        model.addAttribute("dto", dto);
    }

}
