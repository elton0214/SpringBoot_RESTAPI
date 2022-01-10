package idv.Healthcare.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import static javax.persistence.GenerationType.AUTO;

@Entity //@Entity 表示是一個對應到 Database Table 的 Object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser {
    @Id
//    @GeneratedValue(generator="system_uuid")
//    @GenericGenerator(name="system_uuid",strategy="uuid")
    public String username;
    public String useremail;
    public String password;
    public String user_mobile;
    public String location;

//    public ApplicationUser(String user_name, String user_email, String password, String user_mobile, String location) {
//        super();
//        this.user_name = user_name;
//        this.user_email = user_email;
//        this.password = password;
//        this.user_mobile = user_mobile;
//        this.location = location;
//    }

//    public ApplicationUser() {
//        super();
//    }

    public ApplicationUser(String user_name, String password) {
        super();
        this.username = user_name;
        this.password = password;
    }

    //    public Date user_dob;

}


