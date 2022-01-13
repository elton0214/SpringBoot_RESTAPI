package idv.Healthcare.Model;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import static javax.persistence.GenerationType.AUTO;

@Entity //@Entity 表示是一個對應到 Database Table 的 Object
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationUser implements UserDetails{
    @Id @GeneratedValue(strategy = AUTO)
    private Long id;
    public String username1;
    public String useremail;
    public String password;
    public String user_mobile;
    public String location;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();




    public ApplicationUser(String useremail, String password) {
        super();
        this.useremail = useremail;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return useremail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    //    public Date user_dob;

}


