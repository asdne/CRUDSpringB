package ru.asd.CRUDSpringB.entity;

import javax.persistence.*;

import static java.lang.Math.toIntExact;


@Entity
@Table(name = "persons", schema = "javalearning", catalog = "")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "surname", length = 25, nullable = false)
    private String surname;

    @Column(name = "name", length = 25, nullable = false)
    private String name;
    /*
        @Column(name = "birthday", unique = true, updatable = , length = 25)
        private Date birthday;
    */

    @Column(name = "address", length = 75)
    private String address;

    public Person() {

    }

    public Person(String surname, String name, String address) {
        this.surname = surname;
        this.name = name;
        this.address = address;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /* public Date getBirthday() {
         return birthday;
     }

     public void setBirthday(Date birthday) {
         this.birthday = birthday;
     }
 */
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public int hashCode() {
        int result = toIntExact(id);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        //     result = 31 *result + (birthday!=null? birthday.hashCode():0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person that = (Person) o;

        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        //     if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
