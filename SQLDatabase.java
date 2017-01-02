package project.android.com.android5777_9254_6826.model.backend;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import project.android.com.android5777_9254_6826.model.entities.Account;
import project.android.com.android5777_9254_6826.model.entities.Address;
import project.android.com.android5777_9254_6826.model.entities.Attraction;
import project.android.com.android5777_9254_6826.model.entities.Business;
import project.android.com.android5777_9254_6826.model.entities.Properties;

/**
 * Created by Arele-PC on 12/27/2016.
 */

public class SQLDatabase implements Backend {


    @Override
    public int addNewAccount(String UserName, String Password) {
        return 0;
    }

    @Override
    public int addNewAccount(Account toInsert) {
        return 0;
    }

    @Override
    public ArrayList<Account> getAccountList() {
        return null;
    }

    @Override
    public Cursor CgetAccountList() throws Exception {
        Account acc;

        MatrixCursor accountCursor = new MatrixCursor(new String[]{"AccountNumber","UserName", "Password"});

//        for (int i =0; i < accountList.size();i++){
//            acc = accountList.get(i);
//            accountCursor.addRow(new Object[]{acc.getAccountNumber(),acc.getUserName(),acc.getPassword()});
//        }
//        return accountCursor;
//        MatrixCursor agenciesCursor = new MatrixCursor(new String[]{"_ID", "Name", "Country", "City", "Street", "HouseNumber", "PhoneNumber", "Email"});
//
        JSONArray array = new JSONObject(GET(Constraints.WEB_URL)).getJSONArray("Account");
        for (int i = 0; i < array.length(); i++) {
            JSONObject account = array.getJSONObject(i);
            accountCursor.addRow(new Object[]{
                    account.getString("AccountNumber"),
                    account.getString("UserName"),
                    account.getString("Password")});
        }
        return accountCursor;
    }

    @Override
    public Account getAccount(long id) throws Exception {
        return null;
    }

    @Override
    public Account getAccount(String username) throws Exception {
        Cursor accounts = CgetAccountList();
        accounts.moveToFirst();
        while (!accounts.isAfterLast()) {
            String accountsString = accounts.getString(1);
            if(accountsString.equals(username)){
                return new Account(Long.parseLong(accounts.getString(0)),accounts.getString(1),accounts.getString(2));
            }
            accounts.moveToNext();
        }
        return null;
    }

    @Override
    public boolean isRegistered(String userName) {
        return false;
    }

    @Override
    public Account verifyPassword(String userName, String passToCheck) throws Exception {
        Account account= getAccount(userName);
        if(account.getPassword().equals(passToCheck)){
            return account;
        }
        return null;
    }

    @Override
    public int removeAccount(String username) {
        return 0;
    }

    @Override
    public int removeAccount(int rowID) {
        return 0;
    }


    @Override
    public Uri insert(Account ac) {
        return null;
    }

    @Override
    public int addNewAttraction(Properties.AttractionType Type, String AttractionName, String Country, String StartDate, String EndDate, float Price, String Description, String BusinessID) {
        return 0;
    }

    @Override
    public int addNewAttraction(Attraction toInsert) {
        return 0;
    }

    @Override
    public ArrayList<Attraction> getAttractionList() {
        return null;
    }

    @Override
    public ArrayList<Attraction> getAttractionList(String BusinessID) {
        return null;
    }

    public Cursor CgetAttractionList(String BusinessID) throws Exception {
        MatrixCursor attractionCursor = new MatrixCursor(
                new String[]{"AttractionID","Type", "Country",
                        "StartDate","EndDate","Price","Description","BusinessID"});
        Cursor attractionlist = CgetAttractionList();
        attractionlist.moveToFirst();
        while (!attractionlist.isAfterLast()) {
            String accountIdString = attractionlist.getString(7);
            if(accountIdString.equals(BusinessID)){
                attractionCursor.addRow(new Object[]{
                        attractionlist.getString(0),
                        attractionlist.getString(1),
                        attractionlist.getString(2),
                        attractionlist.getString(3),
                        attractionlist.getString(4),
                        attractionlist.getString(5),
                        attractionlist.getString(6),
                        attractionlist.getString(7),
                });
            }
            attractionlist.moveToNext();
        }
        return attractionCursor;


    }

    @Override
    public Cursor CgetAttractionList() throws Exception {

        MatrixCursor attractionCursor = new MatrixCursor(
                new String[]{"AttractionID","Type", "Country",
                        "StartDate","EndDate","Price","Description","BusinessID"});
        JSONArray array = new JSONObject(GET(Constraints.WEB_URL + Constraints.addAttraction)).getJSONArray("Attraction");
        for (int i = 0; i < array.length(); i++) {
            JSONObject attraction = array.getJSONObject(i);
            attractionCursor.addRow(new Object[]{
                    attraction.getString("AttractionID"),
                    attraction.getString("Type"),
                    attraction.getString("Country"),
                    attraction.getString("StartDate"),
                    attraction.getString("EndDate"),
                    attraction.getString("Price"),
                    attraction.getString("Description"),
                    attraction.getString("BusinessID")
            });
        }
        return attractionCursor;
    }

    @Override
    public Attraction getAttraction(String attractionID) throws Exception {
        return null;
    }

    @Override
    public boolean ifNewAttractionAdded() {
        return false;
    }

    @Override
    public int removeAttraction(String attractionID) {
        return 0;
    }

    @Override
    public int removeAttraction(int rowID) {
        return 0;
    }

    @Override
    public Uri insert(Attraction ac) {
        return null;
    }

    @Override
    public int addNewBusiness(String accountID, String Name, Address address, String Email, URL Website) {
        return 0;
    }

    @Override
    public int addNewBusiness(Business toInsert) {
        return 0;
    }

    @Override
    public ArrayList<Business> getBusinessList() {
        return null;
    }

    @Override
    public Cursor CgetBusinessList() throws Exception {
        Business bus;
        MatrixCursor businessCursor = new MatrixCursor(
                new String[]{"AccountNumber","BusinessID","BusinessName", "City","Country","Street",
                        "Email","Website"});

        JSONArray array = new JSONObject(GET(Constraints.WEB_URL + Constraints.getBusinesses)).getJSONArray("Business");
        for (int i = 0; i < array.length(); i++) {
            JSONObject agency = array.getJSONObject(i);
            businessCursor.addRow(new Object[]{
                    agency.getString("AccountNumber"),
                    agency.getString("BusinessID"),
                    agency.getString("City"),
                    agency.getString("Country"),
                    agency.getString("Street"),
                    agency.getString("Email"),
                    agency.getString("Website"),
            });
        }
        return businessCursor;
    }

    @Override
    public boolean ifNewBusinessAdded() {
        return false;
    }

    @Override
    public Business getBusiness(String businessID) throws Exception {
        return null;
    }

    @Override
    public int removeBusiness(String businessID) {
        return 0;
    }

    @Override
    public int removeBusiness(int rowID) {
        return 0;
    }

    @Override
    public Uri insert(Business ac) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public ArrayList<Business> getBusinessList(String AccountID) throws Exception {
        MatrixCursor businescursor = new MatrixCursor(  new String[]{"AccountNumber","BusinessID","BusinessName", "City","Country","Street",
                "Email","Website"});
        Cursor businessList = CgetBusinessList();
        businessList.moveToFirst();
        while (!businessList.isAfterLast()) {
            String accountIdString = businessList.getString(0);
            if(accountIdString.equals(AccountID)){
                businescursor.addRow(new Object[]{
                        businessList.getString(0),
                        businessList.getString(1),
                        businessList.getString(2),
                        businessList.getString(3),
                        businessList.getString(4),
                        businessList.getString(5),
                        businessList.getString(6),
                });
            }
            businessList.moveToNext();
        }
        return null;
    }


    public Cursor CgetBusinessList(String AccountID) throws Exception {
        MatrixCursor businescursor = new MatrixCursor(  new String[]{"AccountNumber","BusinessID","BusinessName", "City","Country","Street",
                "Email","Website"});
        Cursor businessList = CgetBusinessList();
        businessList.moveToFirst();
        while (!businessList.isAfterLast()) {
            String accountIdString = businessList.getString(0);
            if(accountIdString.equals(AccountID)){
                businescursor.addRow(new Object[]{
                        businessList.getString(0),
                        businessList.getString(1),
                        businessList.getString(2),
                        businessList.getString(3),
                        businessList.getString(4),
                        businessList.getString(5),
                        businessList.getString(6),
                });
            }
            businessList.moveToNext();
        }
        return businescursor;
    }



    private static String GET(String url) throws Exception {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            return response.toString();
        } else {
            return "";
        }
    }
    private static String POST(String url, Map<String,Object> params) throws IOException {

        //Convert Map<String,Object> into key=value&key=value pairs.
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postData.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();
        System.out.println("POST Response Code :: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { //success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else return "";
    }

}
