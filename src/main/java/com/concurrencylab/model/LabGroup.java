
package com.concurrencylab.model;

public class LabGroup {

    private final String groupId;
    private final String moduleCode;
    private final String timeSlot;
    private final int capacity;

    private int allocatedPlaces;

    public LabGroup(String groupId, String moduleCode,
            String timeSlot, int capacity) {

        if (groupId == null || groupId.isBlank()
                || moduleCode == null || moduleCode.isBlank()
                || timeSlot == null || timeSlot.isBlank()) {
            throw new IllegalArgumentException(
                    "Lab group details cannot be empty");
        }

        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Lab capacity must be positive");
        }

        this.groupId = groupId;
        this.moduleCode = moduleCode;
        this.timeSlot = timeSlot;
        this.capacity = capacity;
        this.allocatedPlaces = 0;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAllocatedPlaces() {
        return allocatedPlaces;
    }

    public int getAvailablePlaces() {
        return capacity - allocatedPlaces;
    }

    public boolean hasAvailablePlace() {
        return allocatedPlaces < capacity;
    }

    public void allocatePlace() {
        if (!hasAvailablePlace()) {
            throw new IllegalStateException(
                    "Lab group is full");
        }

        allocatedPlaces++;
    }

    public void releasePlace() {
        if (allocatedPlaces <= 0) {
            throw new IllegalStateException(
                    "No allocated places to release");
        }

        allocatedPlaces--;
    }

    @Override
    public String toString() {
        return "LabGroup{" +
                "groupId='" + groupId + '\'' +
                ", moduleCode='" + moduleCode + '\'' +
                ", timeSlot='" + timeSlot + '\'' +
                ", capacity=" + capacity +
                ", allocatedPlaces=" + allocatedPlaces +
                '}';
    }
}
