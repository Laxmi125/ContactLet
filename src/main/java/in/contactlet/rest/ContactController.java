package in.contactlet.rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import in.contactlet.dto.ContactReq;
import in.contactlet.dto.ContactResp;
import in.contactlet.service.ContactService;

@RestController
@RequestMapping("/identify")
public class ContactController {
		
	 @Autowired
	 private ContactService contactService;

    @PostMapping
    public ResponseEntity<ContactResp> identify(@RequestBody ContactReq request) {
        return ResponseEntity.ok(contactService.identifyContact(request));
    }
}
