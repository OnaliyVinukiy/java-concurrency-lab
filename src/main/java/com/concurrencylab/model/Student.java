
package com.concurrencylab.model;

public class Student {

    private final String studentId;
    private final String studentName;

    public Student(String studentId, String studentName) {
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("Student ID cannot be empty");
        }

        if (studentName == null || studentName.isBlank()) {
            throw new IllegalArgumentException("Student name cannot be empty");
        }

        this.studentId = studentId;
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }
}
