package in.contactlet.service;
import in.contactlet.dto.ContactReq;
import in.contactlet.dto.ContactResp;


public interface ContactService {
	ContactResp identifyContact(ContactReq request);
}
