package idv.Healthcare.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import static javax.persistence.GenerationType.AUTO;

//@Table(name = "patient")
@Entity //@Entity 表示是一個對應到 Database Table 的 Object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id //@GeneratedValue(strategy = AUTO) //can't use when id is String
//    @GeneratedValue(generator="system_uuid") //or just generate manually
//    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String patientid;
    private String patient_name;
    private String patient_email;
    private String patient_mobile;
    private Date registeredDate;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @OneToMany(fetch = FetchType.EAGER)
//    private Collection<Appointment> appmt = new ArrayList<>();


//    public Patient(String patient_name, String patient_email, String patient_mobile, Date registeredDate) {
//        this.patient_name = patient_name;
//        this.patient_email = patient_email;
//        this.patient_mobile = patient_mobile;
//        this.registeredDate = registeredDate;
//    }

//    public Patient() {
//        super();
//    }

}
