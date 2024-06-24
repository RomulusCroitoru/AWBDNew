package org.proiect.awbd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
