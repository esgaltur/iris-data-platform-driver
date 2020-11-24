/*
 
*/

package cz.esgaltur.example

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class SomeTable  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SomeGener")
    @SequenceGenerator(name = "SomeGener", sequenceName = "SomeSeqName", allocationSize = 1)
    @Column(name = "PrimaryKey", nullable = false, unique = true)
    private Long id;
  

}

