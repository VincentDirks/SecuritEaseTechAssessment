package net.dirksonline.securiteasetechassessment;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
public class UserListPage {
    public int page;
    public int per_page;
    public int total;
    public int total_pages;
    public ArrayList<User> data;
    public Support support;

    @Data
    @Builder
    public static class User {
        public int id;
        public String email;
        public String first_name;
        public String last_name;
        public String avatar;
    }

    @Data public static class Support{
        public String url;
        public String text;
    }

}
