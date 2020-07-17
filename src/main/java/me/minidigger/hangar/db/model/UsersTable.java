package me.minidigger.hangar.db.model;


import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.StringJoiner;

public class UsersTable implements Serializable {

    private long id;
    private OffsetDateTime createdAt;
    private String fullName;
    private String name;
    private String email;
    private String tagline;
    private OffsetDateTime joinDate;
    private int[] readPrompts;
    private boolean isLocked;
    private String language;


    public UsersTable() {

    }

    public UsersTable(int id, String fullName, String name, String email, String tagline, int[] readPrompts, boolean isLocked, String language) {
        this.id = id;
        this.fullName = fullName;
        this.name = name;
        this.email = email;
        this.tagline = tagline;
        this.readPrompts = readPrompts;
        this.isLocked = isLocked;
        this.language = language;
    }

    public UsersTable getUser() {
        return this; // TODO dummy to fix frontend
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }


    public OffsetDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(OffsetDateTime joinDate) {
        this.joinDate = joinDate;
    }


    public int[] getReadPrompts() {
        return readPrompts;
    }

    public void setReadPrompts(int[] readPrompts) {
        this.readPrompts = readPrompts;
    }


    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }


    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAvatarUrl() {
        return "https://paper.readthedocs.io/en/latest/_images/papermc_logomark_500.png"; // TODO figure out what to do with avatar url
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UsersTable.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("fullName='" + fullName + "'")
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("tagline='" + tagline + "'")
                .add("joinDate=" + joinDate)
                .add("readPrompts=" + readPrompts)
                .add("isLocked=" + isLocked)
                .add("language='" + language + "'")
                .toString();
    }
}