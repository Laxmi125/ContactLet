package in.contactlet.repo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import in.contactlet.binding.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Long> {
	
	 List<Contact> findByEmailOrPhoneNumber(String email, String phoneNumber);
	 List<Contact> findByLinkedId(Long linkedId);
}
