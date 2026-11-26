
package com.concurrencylab.model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Module {

    private final String moduleCode;
    private final String moduleName;
    private final int waitlistLimit;

    private int seatCapacity;
    private boolean enrollmentOpen;

    // Maintain enrolled students in insertion order
    private final Map<String, Enrollment> enrollments;

    // Maintain students in FIFO order.
    private final Queue<WaitlistEntry> waitlist;

    public Module(String moduleCode, String moduleName,
            int seatCapacity, int waitlistLimit) {

        if (moduleCode == null || moduleCode.isBlank()
                || moduleName == null || moduleName.isBlank()) {
            throw new IllegalArgumentException(
                    "Module code and name cannot be empty");
        }

        if (seatCapacity <= 0 || waitlistLimit < 0) {
            throw new IllegalArgumentException(
                    "Invalid module capacity or waitlist limit");
        }

        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
        this.seatCapacity = seatCapacity;
        this.waitlistLimit = waitlistLimit;
        this.enrollmentOpen = false;

        this.enrollments = new LinkedHashMap<>();
        this.waitlist = new ArrayDeque<>();
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getModuleName() {
        return moduleName;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public int getWaitlistLimit() {
        return waitlistLimit;
    }

    public boolean isEnrollmentOpen() {
        return enrollmentOpen;
    }

    public int getEnrolledCount() {
        return enrollments.size();
    }

    public int getAvailableSeats() {
        return seatCapacity - enrollments.size();
    }

    public int getWaitlistSize() {
        return waitlist.size();
    }

    public boolean hasAvailableSeat() {
        return enrollments.size() < seatCapacity;
    }

    public boolean hasWaitlistSpace() {
        return waitlist.size() < waitlistLimit;
    }

    public Map<String, Enrollment> getEnrollments() {
        return Collections.unmodifiableMap(
                new LinkedHashMap<>(enrollments));
    }

    public List<WaitlistEntry> getWaitlist() {
        return Collections.unmodifiableList(
                new ArrayList<>(waitlist));
    }

    public boolean isStudentEnrolled(String studentId) {
        return enrollments.containsKey(studentId);
    }

    public boolean isStudentWaitlisted(String studentId) {
        return waitlist.stream().anyMatch(
                entry -> entry.getStudent().getStudentId()
                        .equals(studentId));
    }

    public void openEnrollment() {
        enrollmentOpen = true;
    }

    public void closeEnrolment() {
        enrollmentOpen = false;
    }

    public void increaseSeatCapacity(int additionalSeats) {
        if (additionalSeats <= 0) {
            throw new IllegalArgumentException(
                    "Additional seats must be positive");
        }

        seatCapacity += additionalSeats;
    }

    public void addEnrollment(Enrollment enrollment) {
        String studentId = enrollment.getStudent().getStudentId();

        if (enrollments.containsKey(studentId)) {
            throw new IllegalStateException(
                    "Student is already enrolled");
        }

        if (!hasAvailableSeat()) {
            throw new IllegalStateException(
                    "Module has no available seats");
        }

        enrollments.put(studentId, enrollment);
    }

    public Enrollment removeEnrolment(String studentId) {
        return enrollments.remove(studentId);
    }

    public void addToWaitlist(WaitlistEntry entry) {
        if (!hasWaitlistSpace()) {
            throw new IllegalStateException(
                    "Waitlist is full");
        }

        String studentId = entry.getStudent().getStudentId();

        if (isStudentEnrolled(studentId)
                || isStudentWaitlisted(studentId)) {
            throw new IllegalStateException(
                    "Student already has a module record");
        }

        waitlist.add(entry);
    }

    public WaitlistEntry peekWaitlistHead() {
        return waitlist.peek();
    }

    public WaitlistEntry removeWaitlistHead() {
        return waitlist.poll();
    }

    @Override
    public String toString() {
        return "Module{" +
                "moduleCode='" + moduleCode + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", seatCapacity=" + seatCapacity +
                ", enrolledCount=" + enrollments.size() +
                ", waitlistSize=" + waitlist.size() +
                ", enrolmentOpen=" + enrollmentOpen +
                '}';
    }
}
