package org.proiect.awbd.mappers;


import org.proiect.awbd.dtos.MemberDTO;
import org.proiect.awbd.model.Member;

import java.util.stream.Collectors;

public class MemberMapper {

    private MemberMapper() {
        // Constructor privat pentru a preveni instantierea clasei
    }

    public static MemberDTO toMemberDTO(Member member) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(member.getId());
        memberDTO.setName(member.getName());
        memberDTO.setLibraryCardId(
                member.getLibraryCard() != null ? member.getLibraryCard().getId() : null
        );
        memberDTO.setBorrowedBookIds(
                member.getBorrowedBooks() != null ?
                        member.getBorrowedBooks().stream().map(book -> book.getId()).collect(Collectors.toSet())
                        : null
        );
        return memberDTO;
    }

    public static Member toMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setId(memberDTO.getId());
        member.setName(memberDTO.getName());
        // În mod normal, nu am transforma ID-urile înapoi în obiecte complexe (LibraryCard, Book, etc.) într-un Mapper
        // întrucât Mapper-urile sunt utilizate în principal pentru transformări între DTO-uri și entități
        return member;
    }
}
