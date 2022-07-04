package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Article;

@Mapper
public interface ArticleRepository {
	@Select("""
			SELECT A.*,
			M.nickname AS extra__writerName
			FROM article AS A
			LEFT JOIN member AS M
			ON A.memberId = M.id
			ORDER BY A.id DESC;
			""")
	public List<Article> getArticles();
	
	@Select("""
			SELECT A.*,
			M.nickname AS extra__writerName
			FROM article AS A
			LEFT JOIN member AS M
			ON A.memberId = M.id
			WHERE 1
			AND A.id = #{id};
			""")
	public Article getForPrintArticle(@Param("id") int id);

	public void doDelete(int id);

	public void doModify(@Param("id") int id, @Param("title") String title, @Param("body") String body);

	public void doAdd(@Param("memberId") int memberId,@Param("title") String title, @Param("body") String body);

}
