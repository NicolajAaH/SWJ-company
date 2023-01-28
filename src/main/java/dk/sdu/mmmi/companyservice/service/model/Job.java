package dk.sdu.mmmi.companyservice.service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Entity
@Setter
@ToString
public class Job {
    @Id
    private Long id;

    private String title;

    private String location;

    private String description;

    private JobType jobType;

    private Double salary;

    private Date createdAt;

    private Date updatedAt;

    private Date expiresAt;

    @ManyToOne(optional = false)
    private Company company;

}