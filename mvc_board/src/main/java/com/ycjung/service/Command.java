package com.ycjung.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.ycjung.paging.PagingVo;

public interface Command {
    public void execute(Model model, SqlSession sqlSession, PagingVo pv);
}
