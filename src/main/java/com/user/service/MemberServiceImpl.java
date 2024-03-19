package com.user.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.common.exception.NotUserException;
import com.multi.mapper.MemberMapper;
import com.user.domain.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service("memberService")
@RequiredArgsConstructor // 생성자 주입을 하고자 할 때 ==> 주입될 의존성을 갖는 객체는 final필드여야 한다
@Log4j
public class MemberServiceImpl implements MemberService {

//	@Inject
//	private MemberMapper mMapper;//Field Injection
	private final MemberMapper mMapper;//final field ==> 생성자 주입(@RequredArgsConstructor)
	
	private final BCryptPasswordEncoder passwordEncoder;//==>생성자 주입
	//사용자가 입력한 비번에 솔트(랜덤하게 생성)를 덧붙이고 이를 해시하여 암호화된 비밀번호를 만들어준다(암호화)
	//<==> 복호화(암호화된 것을 푸는 것)
	@Override
	public int insertMember(MemberVO vo) {
		log.info("passwordEncoder: "+passwordEncoder);
		//비밀번호 암호화 처리////////////////
		vo.setPwd(passwordEncoder.encode(vo.getPwd()));
		/////////////////////////
		log.info("암호화된 비밀번호: "+vo.getPwd());
		return mMapper.insertMember(vo);
	}

	@Override
	public boolean idCheck(String userid) {
		return (mMapper.idCheck(userid)==1)? false:true;
	}

	@Override
	public List<MemberVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemberVO selectByUserid(String userid) {
		return mMapper.selectByUserid(userid);
	}

	@Override
	public MemberVO loginCheck(MemberVO tmpUser) throws NotUserException {
		MemberVO dbUser=this.selectByUserid(tmpUser.getUserid());
		if(dbUser==null) {
			//아이디가 없는 경우
			throw new NotUserException("아이디 또는 비밀번호가 일치하지 않아요");
		}
		//비밀번호 일치여부 체크
		boolean isMatch=passwordEncoder.matches(tmpUser.getPwd(), dbUser.getPwd());
		log.info("tmpUser.getPwd(): "+tmpUser.getPwd());
		log.info("dbUser.getPwd(): "+dbUser.getPwd());
		
		if(!isMatch) throw new NotUserException("아이디 또는 비밀번호가 일치하지 않아요");
		
		return dbUser;
	}

}
