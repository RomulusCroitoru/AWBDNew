package org.proiect.awbd.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String name;
    private Long libraryCardId; // Adăugăm ID-ul cardului de bibliotecă pentru a reflecta asocierea din Member
    private Set<Long> borrowedBookIds; // Adăugăm o listă de ID-uri pentru cărțile împrumutate de membru

}
