package org.proiect.awbd.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "library_card")
public class LibraryCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}

