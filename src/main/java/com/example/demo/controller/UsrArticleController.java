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
	public String showList(HttpSession httpSession, Model model) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		List<Article> articles = articleService.getForPrintArticles(loginedMemberId);

		model.addAttribute("articles", articles);

		return "/usr/article/list";
	}

	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpSession httpSession, Model model, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getForPrintArticle(loginedMemberId, id);

		model.addAttribute("article", article);

		return "/usr/article/detail";
	}

	@RequestMapping("/usr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(HttpSession httpSession, int id) {
		boolean isLogined = false;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		Article article = articleService.getForPrintArticle(loginedMemberId, id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d??? ???????????? ???????????? ????????????.", id));
		}

		return ResultData.from("S-1", Ut.f("%d??? ????????? ?????????.", id), "article", article);
	}

	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpSession httpSession, int id) {
		boolean isLogined = false;
		// loginedMemberId??? ????????? ?????????, ????????? ??????, httpSession?????? key??????. int loginedMemberId =
		// 0;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			return ResultData.from("F-A", "????????? ??? ??????????????????~");
		}

		Article article = articleService.getForPrintArticle(loginedMemberId, id);

		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", "????????????(???????????????;;)??");
		}
		
//		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d??? ???????????? ?????????;;", id));
//		}

		articleService.doDelete(id);

		return ResultData.from("S-1", Ut.f("%d ??? ???????????? ?????????", id), "id", id);
	}

	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpSession httpSession, int id, String title, String body) {
		boolean isLogined = false;
		// loginedMemberId??? ????????? ?????????, ????????? ??????, httpSession?????? key??????. int loginedMemberId =
		// 0;
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			return ResultData.from("F-A", "????????? ??? ??????????????????~");
		}

		Article article = articleService.getForPrintArticle(loginedMemberId, id);
		
		if (article.getMemberId() != loginedMemberId) {
			return ResultData.from("F-2", "????????????(???????????????;;)??");
		}
		
//		if (article == null) {
//			return ResultData.from("F-1", Ut.f("%d??? ???????????? ?????????;;", id));
//		}

		ResultData actorCanModifyRd = articleService.actorCanModify(loginedMemberId, article);
		
		if(actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}
		
		return articleService.doModify(id, title, body);
	}

	@RequestMapping("/usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(HttpSession httpSession, String title, String body) {
		boolean isLogined = false;
		// loginedMemberId??? ????????? ?????????, ????????? ??????, httpSession?????? key??????.
		int loginedMemberId = 0;

		if (httpSession.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) httpSession.getAttribute("loginedMemberId");
		}

		if (isLogined == false) {
			return ResultData.from("F-A", "????????? ??? ??????????????????~");
		}

		if (Ut.empty(title)) {
			return ResultData.from("F-1", "????????? ???????????????.");
		}

		articleService.doAdd(loginedMemberId, title, body);
		return ResultData.from("S-1", Ut.f("????????? ?????????."));
	}
}
