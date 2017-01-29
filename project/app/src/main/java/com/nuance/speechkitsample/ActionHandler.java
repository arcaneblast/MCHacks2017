package com.nuance.speechkitsample;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vasu on 29/01/17.
 * //cool_query cool_q, crap
 * //explain
 * query_activities activity
 * query_application
 * query_buildings program, building
 * query_costs program_fees
 * query_courses term, course_code, nuance_Number
 * query_details_about_program program
 * query_help
 * query_program program
 * query_studies
 * search
 * query_website Website
 *
 * */


public class ActionHandler {
    private String search(String crap) {
        return "https://www.google.ca/search?q=" + crap;
    }
    private String cool_query(String cool_q) {
        return cool_q;
    }
    private String query_course(String number, String term, String courseCode) {
        return courseCode + number;
    }

    private String queryProgram(String program)
    {
        return program;
    }
    private String query_building(String program, String building) {
        return program + building;
    }

    private String explain() {
        //to do andree
        return "I'm the ultimate life form who is gonna control the world!";
    }

    private String query_costs(String program_fees) {
        return "http://www.mcgill.ca/student-accounts/tuition-charges/fallwinter-term-tuition-and-fees/undergraduate-fees";
    }
    public String handle(String action, JSONObject jobj) {
        switch (action) {
            case "cool_query":
                try {
                    return this.cool_query(jobj.getJSONObject("concepts").getJSONArray("cool_q").getJSONObject(0).getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;

            case "search":
                String crap = "";
                try {
                    crap = (jobj.getJSONObject("concepts").getJSONArray("crap").getJSONObject(0).getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return this.search(crap);
            case "explain":
                return this.explain();

            case "query_activities":

                break;
            case "query_application":
                break;

            case "query_buildings":
                String program = "";
                String building = "";

                try {
                    program= jobj.getJSONObject("concepts").getJSONArray("program").getJSONObject(0).getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    building= jobj.getJSONObject("concepts").getJSONArray("building").getJSONObject(0).getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return this.query_building(program, building);

            case "query_costs":
                String programfees = "";
                try {
                    programfees= jobj.getJSONObject("concepts").getJSONArray("program_fees").getJSONObject(0).getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return this.query_costs(programfees);

            case "query_courses":
                String number = "";
                String term = "";
                String courseCode = "";
                try {
                    courseCode = (jobj.getJSONObject("concepts").getJSONArray("course_code").getJSONObject(0).getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    term = (jobj.getJSONObject("concepts").getJSONArray("term").getJSONObject(0).getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    number = jobj.getJSONObject("concepts").getJSONArray("nuance_NUMBER").getJSONObject(0).getJSONObject("concepts").getJSONArray("nuance_CARDINAL_NUMBER").getJSONObject(0).getString("value");
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                return this.query_course(number, term, courseCode);

            case "query_details_about_program":
                break;
            case "query_help":
                break;
            case "query_programs":
                String programName="";
                try {
                     programName=(jobj.getJSONObject("concepts").getJSONArray("program").getJSONObject(0).getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return this.queryProgram(programName);
            case "query_studies":
                break;
            case "query_website":
                break;

        }
        return "NO_MATCH";
    }
}


