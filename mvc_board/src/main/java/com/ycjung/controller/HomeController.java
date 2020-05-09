package com.ycjung.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ycjung.paging.PagingVo;
import com.ycjung.service.Command;
import com.ycjung.service.ContentCommand;
import com.ycjung.service.DeleteAllCommand;
import com.ycjung.service.DeleteCommand;
import com.ycjung.service.DummyCommand;
import com.ycjung.service.ListCommand;
import com.ycjung.service.ModifyCommand;
import com.ycjung.service.WriteCommand;

/**
 * Handles requests for the application home page.
 */
@Controller
@ComponentScan("com.ycjung.config")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private PagingVo pv;
	   
    @RequestMapping(value = "/list")
    public String list(Model model) {
        System.out.println("list()");
        
        Command command = new ListCommand();
        command.execute(model, sqlSession, pv);
        
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
        
        model.addAttribute("request",request);
        Command command = new WriteCommand();
        command.execute(model, sqlSession, pv);
        
        return "redirect:list";
    }
    
    @RequestMapping("/content_view")
    public String content_view(HttpServletRequest request, Model model) {
        System.out.println("content_view()");
        
        model.addAttribute("request",request);
        Command command = new ContentCommand();
        command.execute(model, sqlSession, pv);
        
        return "content_view";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/modify")
    public String modify(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
        System.out.println("modify()");
        
        model.addAttribute("request",request);
        Command command = new ModifyCommand();
        command.execute(model, sqlSession, pv);
        
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
        
        model.addAttribute("request",request);
        Command command = new DeleteCommand();
        command.execute(model, sqlSession, pv);
        
        return "redirect:list";
    }
    
    @RequestMapping("/deleteAll")
    public String deleteAll(HttpServletRequest request, Model model) {
        System.out.println("allDelete()");
        
        model.addAttribute("request",request);
        Command command = new DeleteAllCommand();
        command.execute(model, sqlSession, pv);
        
        return "redirect:list";
    }
    
    @RequestMapping("/dummy")
    public String dummy(HttpServletRequest request, Model model) {
        System.out.println("dummy()");
        
        model.addAttribute("request",request);
        Command command = new DummyCommand();
        command.execute(model, sqlSession, pv);
        
        return "redirect:list";
    }
	
}
