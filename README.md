<h1 align="center">💡 ContactLet</h1>
<h3 align="center">An Intelligent Contact Consolidation API</h3>

This is a Spring Boot RESTful web service that intelligently consolidates user contact information (email & phone number) across multiple inputs using shadow-linking logic and it is easy to test via Postman.

---

## 🚀 Features

✅ Accepts contact data (`email` + `phoneNumber`)  
✅ Automatically detects existing entries using email/phone overlaps  
✅ Maintains **primary** and **secondary** contact relationships  
✅ Prevents duplicate records intelligently  
✅ Exposes a clean JSON-based `/identify` API  

---
## 🧱 Schema (Simplified)

```text
Contact {
  id: Int
  phoneNumber: String?
  email: String?
  linkedId: Int?        // reference to another Contact ID
  linkPrecedence: 'primary' | 'secondary'
  createdAt: DateTime
  updatedAt: DateTime
  deletedAt: DateTime?
}
```
## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA 
- MySQL (or any JDBC DB)
- Lombok
- Maven

---
## 🚀 Run Locally

Prerequisites:
- Java 17+
- Maven
- MySQL (create a DB moonrider)

---
## 🧪 How to Test the API using Postman

- URL: POST http://localhost:8080/identify
- Body (raw, JSON):
```text
{
  "email": "laxmi@gmail.com",
  "phoneNumber": "1234567890"
}
```
---
## 📌 Use Cases
- Contact deduplication
- User identity linking
- CRM backend logic
- Shadow merge logic in ecommerce or social apps

---
## ✅ Future Enhancements (Optional)

- Continue adding UI features
- Add validation (email format, phone regex)
- Show contact timeline/history
- Export results to PDF
- Deploy to cloud (Render / Railway / Vercel)

---
## 🙌 Contribute
Want to enhance this? Fork the repo, add features, and open a pull request!
