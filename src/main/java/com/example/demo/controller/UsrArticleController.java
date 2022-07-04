package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.ut.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;

@Controller
public class UsrArticleController {
	private ArticleService articleService;
	
	public UsrArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping("/usr/article/list")
	public String showList(Model model){
		List<Article> articles = articleService.getForPrintArticles();
		
		model.addAttribute("articles", articles);
		
		return "/usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(Model model, int id){
		Article article = articleService.getForPrintArticle(id);
		
		model.addAttribute("article", article);
		
		return "/usr/article/detail";
	}
	
	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id){
		Article article = articleService.getForPrintArticle(id);
		if(article == null) {
			return  ResultData.from("F-1", "게시물이 없어요;;");
		}
		
		return ResultData.from("S-1", "게시물입니당.",article);
	}
	
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession httpSession, int id){
		boolean isLogined = false;
		//loginedMemberId가 이름은 같으나, 여기는 변수, httpSession에는 key이다.	int loginedMemberId = 0;
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId")!= null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세여~");
		}
		
		Article article = articleService.getForPrintArticle(id);
		
		if(article == null) {
			return  ResultData.from("F-1", "게시물이 없어요;;");
		}
		
		if(article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", "누구세요(권한없어요;;)??");
		}
			
		articleService.doDelete(id);
		
		return ResultData.from("S-1", Ut.f("%d 번 게시물이 없어짐", id),article);
	}
	
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession httpSession, int id, String title, String body){
		boolean isLogined = false;
		//loginedMemberId가 이름은 같으나, 여기는 변수, httpSession에는 key이다.	int loginedMemberId = 0;
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId")!= null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세여~");
		}
		
		Article article = articleService.getForPrintArticle(id);
		
		if(article == null) {
			return  ResultData.from("F-1", "게시물이 없어요;;");
		}
		
		if(article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", "누구세요(권한없어요;;)??");
		}
				
		articleService.doModify(id, title, body);
		article = articleService.getForPrintArticle(id);
		
		return ResultData.from("S-1", Ut.f("%d 번 게시물 수정함", id),article);
	}
	
	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(HttpSession httpSession, String title, String body){	
		boolean isLogined = false;
		//loginedMemberId가 이름은 같으나, 여기는 변수, httpSession에는 key이다.
		int loginedMemberId = 0;
		
		if(httpSession.getAttribute("loginedMemberId")!= null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}
		
		if(isLogined == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세여~");
		}
		
		
		if(Ut.empty(title)) {
			return ResultData.from("F-1", "제목을 입력하시오.");
		}
		
		articleService.doAdd(loginedMemberId, title, body);		
		return ResultData.from("S-1", Ut.f("게시물 생성함."));
	}
}
