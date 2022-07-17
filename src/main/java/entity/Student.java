package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="STUDENT")
@Data
@XmlRootElement
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student implements Serializable {
    @Id
    @Column(nullable = false)
    int id;

    @Column(nullable = false)
    String fullName;

    @Column(nullable = false)
    Date birthday;

    @Column(name = "CLASS_NAME", nullable = false)
    String className;

    @Column(nullable = false)
    String major;

    @Column(nullable = false)
    String hometown;

    @Column(nullable = false)
    String gender;

    @Column(name = "AVERAGE_MARK", nullable = false)
    Double averageMark;

}
