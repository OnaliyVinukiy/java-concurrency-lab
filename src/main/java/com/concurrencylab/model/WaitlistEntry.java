
package com.concurrencylab.model;

public class WaitlistEntry {

    private final Student student;
    private final String moduleCode;
    private final String preferredLabGroupId;
    private final long sequenceNumber;

    public WaitlistEntry(Student student, String moduleCode,
            String preferredLabGroupId,
            long sequenceNumber) {

        if (student == null || moduleCode == null
                || moduleCode.isBlank() || sequenceNumber <= 0) {
            throw new IllegalArgumentException(
                    "Invalid waitlist entry");
        }

        this.student = student;
        this.moduleCode = moduleCode;
        this.preferredLabGroupId = preferredLabGroupId;
        this.sequenceNumber = sequenceNumber;
    }

    public Student getStudent() {
        return student;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getPreferredLabGroupId() {
        return preferredLabGroupId;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }
}
