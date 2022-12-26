package com.yyh.wubida.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyh.wubida.entity.Member;
import com.yyh.wubida.mapper.MemberMapper;
import com.yyh.wubida.service.IMemberService;
import org.springframework.stereotype.Service;

/**
 * 用户服务类实现
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

}