package ivk.danilo.touristinfo.models;

import java.io.Serializable;

public final class Location implements Serializable {
    public final static String SERIALIZABLE = "location";
    private final String name;
    private final String description;
    private final String contactInfo;
    private final String workingHours;
    private final String image;

    public Location(String name, String description, String contactInfo, String workingHours, String image) {
        this.name = name;
        this.description = description;
        this.contactInfo = contactInfo;
        this.workingHours = workingHours;
        this.image = image;
    }

    public Location(String name, String description, String contactInfo, String workingHours) {
        this(name, description, contactInfo, workingHours, "");
    }

    public Location() {
        this("Location", "Description", "Contact info", "08:00 - 18:00");
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getContactInfo() {
        return this.contactInfo;
    }

    public String getWorkingHours() {
        return this.workingHours;
    }

    public String getImage() {
        return this.image;
    }
}
