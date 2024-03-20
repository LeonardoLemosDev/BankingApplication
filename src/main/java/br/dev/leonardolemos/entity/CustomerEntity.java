package br.dev.leonardolemos.entity;

import java.util.Objects;

public class CustomerEntity {
    private String name;
    private String document;
    private String email;

    public CustomerEntity() {
    }

    public CustomerEntity(String name, String document, String email) {
        this.name = name;
        this.document = document;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerEntity that)) return false;
        return Objects.equals(getName(), that.getName()) && Objects.equals(getDocument(), that.getDocument()) && Objects.equals(getEmail(), that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDocument(), getEmail());
    }


    @Override
    public String toString() {

        return "Cliente: " + name + "\n" +
                "CPF: " + document + "\n" +
                "E-mail: " + email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
