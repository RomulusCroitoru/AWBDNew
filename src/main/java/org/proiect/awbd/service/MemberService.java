package org.proiect.awbd.service;

import org.proiect.awbd.model.Member;
import org.proiect.awbd.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return (List<Member>) memberRepository.findAll();
    }

    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    public void deleteMemberById(Long id) {
        memberRepository.deleteById(id);
    }

    public List<String> getAllMemberNames() {
        List<Member> members = getAllMembers();
        return members.stream()
                .map(Member::getName)
                .collect(Collectors.toList());
    }

}
