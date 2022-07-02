package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	public List<Article> getArticles();

	public Article getArticle(@Param("id") int id);

	public void doDelete(int id);

	public void doModify(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	public void doAdd(@Param("memberId") int memberId,@Param("title") String title, @Param("body") String body);

}
