package id.ac.ui.cs.advprog.reviewkeranjangservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import java.util.Map;

import java.util.UUID;

@Getter @Setter
@Entity
public class Keranjang {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

}
