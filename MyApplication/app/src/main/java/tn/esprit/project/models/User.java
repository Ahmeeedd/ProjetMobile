package tn.esprit.project.models;

import androidx.annotation.InspectableProperty;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

    @Entity
    public class User {

        @PrimaryKey(autoGenerate = true)
        private long userId;
        @ColumnInfo
        private String firstname;
        @ColumnInfo
        private String lastname;
        @ColumnInfo
        private String email;
        @ColumnInfo
        private String password;
        @ColumnInfo
        private String phonenumber;
        @ColumnInfo
        private String Image;
        @ColumnInfo
        private Role role;

        public User(long userId, String firstname, String lastname, String email, String password, String phonenumber, String image, Role role) {
            this.userId = userId;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.password = password;
            this.phonenumber = phonenumber;
            Image = image;
            this.role = role;
        }

        public User(String firstname, String lastname, String email, String password, String phonenumber, String image, Role role) {
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.password = password;
            this.phonenumber = phonenumber;
            Image = image;
            this.role = role;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    ", phonenumber='" + phonenumber + '\'' +
                    ", Image='" + Image + '\'' +
                    ", role=" + role +
                    '}';
        }

        public User() {
        }

        public long getUserId() {
            return userId;
        }

        public void setUserId(long userId) {
            this.userId = userId;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }
    }

