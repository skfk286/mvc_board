package com.ycjung.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ycjung.dao.IDao;
import com.ycjung.dto.ContentDto;
import com.ycjung.paging.PagingVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PagingVo pv;
	
    @RequestMapping(value = "/list")
    public String list(Model model) {
        //System.out.println( org.springframework.core.SpringVersion.getVersion() ); 
        System.out.println("list()");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        model.addAttribute("list", dao.listDao());
        
        model.addAttribute("pv", pv);
        return "list";
    }
    
    @RequestMapping(value = "/listSub")
    public String listSub(Model model) {
        System.out.println("listSub()");
        
        return "redirect:list";
    }
    
    @RequestMapping("/write_view")
    public String write_view(Model model) {
        System.out.println("write_view()");
        
        return "write_view";
    }
    
    @RequestMapping("/write")
    public String write(HttpServletRequest request, Model model) {
        System.out.println("write()");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        dao.writeDao(request.getParameter("bTitle"), request.getParameter("bAuthor"),
                request.getParameter("bContent"));
        
        model.addAttribute("request",request);
        
        return "redirect:list";
    }
    
    @RequestMapping("/content_view")
    public String content_view(HttpServletRequest request, Model model) {
        System.out.println("content_view()");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        int bCd = Integer.parseInt(request.getParameter("bCd"));
        dao.upHit(bCd);
        ContentDto dto = dao.viewDao(bCd);
        model.addAttribute("dto", dto);
        model.addAttribute("request",request);
        return "content_view";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    public String modify(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        System.out.println("modify()");
        request.setCharacterEncoding("utf-8");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        int bCd = Integer.parseInt(request.getParameter("bCd"));
        dao.modify(request.getParameter("bAuthor"), request.getParameter("bTitle"), request.getParameter("bContent"), new Timestamp(System.currentTimeMillis()), bCd);
        
        model.addAttribute("request",request);
        
        return "redirect:list";
    }
    
/*    @RequestMapping("/reply_view")
    public String reply_view(HttpServletRequest request, Model model) {
        System.out.println("reply_view()");
        
        model.addAttribute("request",request);
        
        return "reply_view";
    }*/
    
/*    @RequestMapping("/reply")
    public String reply(HttpServletRequest request, Model model) {
        System.out.println("reply()");
        
        model.addAttribute("request",request);
        
        return "redirect:list";
    }*/
     
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request, Model model) {
        System.out.println("delete()");
        IDao dao = sqlSession.getMapper(IDao.class);
        dao.deleteDao(request.getParameter("bCd"));
        model.addAttribute("request",request);
        
        return "redirect:list";
    }
    
    @RequestMapping("/deleteAll")
    public String deleteAll(HttpServletRequest request, Model model) {
        System.out.println("allDelete()");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        dao.deleteAll();
        model.addAttribute("request",request);
        
        return "redirect:list";
    }
    
    @RequestMapping("/dummy")
    public String dummy(HttpServletRequest request, Model model) {
        System.out.println("dummy()");
        
        IDao dao = sqlSession.getMapper(IDao.class);
        
        for (int i = 0; i < 101; i++) {
            dao.dummyDataInsert("임시제목"+i,"홍길동"+i,"임시줄거리"+i);
        }
        
        model.addAttribute("request",request);
        
        return "redirect:list";
    }
	
}
