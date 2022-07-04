package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.ut.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {
	private MemberRepository memberRepository;
	
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository=memberRepository;
	}

	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNo, String email) {
		Member oldmember = getMemberByLoginId(loginId);
		
		if(oldmember != null) {
			return ResultData.from("F-7", Ut.f("해당 로그인 아이디(%s)는 이미 사용중 입니다.", "loginId", loginId));
					
		}
		
		oldmember = getMemberByNameAndEmail(name, email);
		
		if(oldmember != null) {
			return ResultData.from("F-8", Ut.f("해당 이름(%s)과 이메일은 이미 사용중 입니다.", name, email));
					
		}
		
		memberRepository.doJoin(loginId, loginPw, name, nickname, cellphoneNo, email);		
	
		int id = memberRepository.getLastId();
		
		Member member = memberRepository.getMemberById(id);
		
		if(member == null) {
			return ResultData.from("F-8", "회원가입에 실패했습니다.", "member", member);
		}
		
		return ResultData.from("S-1", "회원가입이 완료되었습니다.", "member", member);
	}

	public Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name, email);
	}

	public Member getMemberByLoginId(String loginId) {
		return memberRepository.getMemberByLoginId(loginId);
	}


}
