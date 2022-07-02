package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.vo.Member;

@Mapper
public interface MemberRepository {

	void doJoin(@Param("loginId") String loginId, @Param("loginPw") String loginPw, @Param("name") String name,
			@Param("nickname") String nickname, @Param("cellphoneNo") String cellphoneNo, @Param("email") String email);

	int getLastId();

	Member getMemberByLoginId(@Param("loginId")String loginId);

	Member getMemberByNameAndEmail(@Param("name")String name, @Param("email")String email);

	Member getMemberById(@Param("id")int id);

	Member getMemberByloginId(@Param("loginId")String loginId);

}
