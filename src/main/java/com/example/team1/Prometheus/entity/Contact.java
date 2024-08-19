package com.example.team1.Prometheus.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;

//@Entity
public class Contact {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    private String name;
    private String phoneNumber;
    private String email;

    public Contact() {
    }

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public ContactResponseDto toContactReponseDto() {
//        ContactResponseDto contactResponseDto = new ContactResponseDto(contactId, name, phoneNumber, email);
//        return contactResponseDto;
//    }

}
