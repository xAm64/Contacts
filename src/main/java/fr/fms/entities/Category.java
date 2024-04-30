package fr.fms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category implements Serializable{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @NotNull
    @Size(min=3, max=30)
    private String name;
    @Size(max= 70)
    private String description;
    @OneToMany(mappedBy = "category")
    @ToString.Exclude
    private Collection<Contact> contacts;

    public Category(String name, String description, Collection contacts) {
        this.name = name;
        this.description = description;
        this.contacts = contacts;
    }
}
