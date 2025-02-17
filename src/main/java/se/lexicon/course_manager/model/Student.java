package se.lexicon.course_manager.model;

import se.lexicon.course_manager.data.sequencers.StudentSequencer;

import java.util.Objects;

public class Student {
    private int id;
    private String name;
    private String email;
    private String address;

    //getters
    public int getId() {return id;}
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getAddress() {return address;}

    //setters
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    //constructor
    public Student(int id, String name, String email, String address){
        setName(name);
        setEmail(email);
        setAddress(address);
        setId(id);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(email, student.email) && Objects.equals(address, student.address);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address);
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
