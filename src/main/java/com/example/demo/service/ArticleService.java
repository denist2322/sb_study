package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.vo.Article;

@Service
public class ArticleService {
	private ArticleRepository articleRepository;

	public ArticleService(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}

	public List<Article> getForPrintArticles() {
		return articleRepository.getArticles();
	}

	public Article getForPrintArticle(int id) {
		return articleRepository.getForPrintArticle(id);
	}

	public void doDelete(int id) {
		articleRepository.doDelete(id);

	}

	public void doModify(int id, String title, String body) {
		articleRepository.doModify(id, title, body);

	}

	public void doAdd(int loginedMemberId, String title, String body) {
		articleRepository.doAdd(loginedMemberId, title, body);
		
	}

}
