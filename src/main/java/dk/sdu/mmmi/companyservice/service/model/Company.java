package dk.sdu.mmmi.companyservice.service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "companies")
@Getter
@Setter
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "website", nullable = false)
    private String website;

    @Column(name = "phone", nullable = false)
    private Integer phone;

    @Column(name = "created_at", columnDefinition = "timestamp default now()", insertable = false, updatable = false, nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", columnDefinition = "timestamp default now()", insertable = false, updatable = false, nullable = false)
    private Date updatedAt;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @ToString.Exclude
    private Set<Job> jobs;
}
