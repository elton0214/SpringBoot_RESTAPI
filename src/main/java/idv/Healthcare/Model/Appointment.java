package idv.Healthcare.Model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Appointment {

    @Id
//    @GeneratedValue(generator="system_uuid")
//    @GenericGenerator(name="system_uuid",strategy="uuid")
    private String bookingid;
    private String disease;

    private Date tentativeDate;
    private String priority;

    private String patientid;
    private Date bookingTime;

    public Appointment( String disease, Date tentativeDate, String priority, String patientId) {
        super();

        this.disease = disease;
        this.tentativeDate = tentativeDate;
        this.priority = priority;
        this.patientid = patientid;

    }

}
