package playground;

import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private final String firstName;
    private final String surName;

    public Employee(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    @Override
    public String toString() {
        return "[" + surName + "," + firstName + "]";
    }

    @Override
    public int compareTo(Employee e) {
        int result = surName.compareTo(e.surName);
        if (result == 0)
            result = firstName.compareTo(e.firstName);
        return result;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object otherEmp) {
        if(otherEmp == this) return true;
        if(otherEmp == null | otherEmp.getClass() != this.getClass()) return false;
        Employee emp = (Employee) otherEmp;

        return Objects.equals(firstName,emp.firstName) &&
                Objects.equals(surName,emp.surName);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }
}
