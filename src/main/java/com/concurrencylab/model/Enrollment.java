
package com.concurrencylab.model;

import com.concurrencylab.enums.EnrollmentStatus;

public class Enrollment {

    private final String enrollmentId;
    private final Student student;
    private final String moduleCode;
    private final String labGroupId;

    private EnrollmentStatus status;

    public Enrollment(String enrollmentId, Student student,
            String moduleCode, String labGroupId) {

        if (enrollmentId == null || enrollmentId.isBlank()
                || student == null
                || moduleCode == null || moduleCode.isBlank()
                || labGroupId == null || labGroupId.isBlank()) {
            throw new IllegalArgumentException(
                    "Invalid enrollment details");
        }

        this.enrollmentId = enrollmentId;
        this.student = student;
        this.moduleCode = moduleCode;
        this.labGroupId = labGroupId;
        this.status = EnrollmentStatus.ENROLLED;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getLabGroupId() {
        return labGroupId;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}
