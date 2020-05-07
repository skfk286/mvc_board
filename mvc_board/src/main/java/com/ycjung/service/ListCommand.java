package com.ycjung.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.ycjung.dao.IDao;
import com.ycjung.paging.PagingVo;

public class ListCommand implements Command {

    @Override
    public void execute(Model model, SqlSession sqlSession, PagingVo pv) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest)map.get("request");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        model.addAttribute("list", dao.listDao());
        
        model.addAttribute("pv", pv);
    }
}
