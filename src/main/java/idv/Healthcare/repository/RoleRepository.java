package idv.Healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import idv.Healthcare.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRolename(String rolename);
}
