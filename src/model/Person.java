/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;

/**
 *
 * @author LuisDAM
 */
public class Person {
    
    private String name;
    private String familyName;
    private int age;

    public Person(){
        
    }
    public Person(String name, String familyName,int age){
        this.name = name;
        this.familyName =familyName;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "name=" + name + ", familyName=" + familyName + ", age=" + age + '}';
    }

    
    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
         Person person = (Person) obj;
        //Comprovació demanada per l'activitat
        if(
                this.age == person.age                  &&
                person.name.equalsIgnoreCase(this.name) &&
                person.familyName.equalsIgnoreCase(this.familyName)
                ){
            return true;
        }
        
        if (this == obj) {
            return true;
        }
       
        
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (this.age != other.age) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.familyName, other.familyName)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
