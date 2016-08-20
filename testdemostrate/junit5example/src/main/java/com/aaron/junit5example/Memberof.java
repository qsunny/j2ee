package com.aaron.junit5example;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

/**
 * Created by Administrator on 2016/8/20.
 */
public class Memberof {
    public static void main(String[] args) {

        Hashtable env = new Hashtable();
        String adminName = "CN=Administrator,CN=Users,DC=ADATUM,DC=COM";
        String adminPassword = "147@asdf";
        String ldapURL = "ldap://aaron.com:389";
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        //set security credentials, note using simple cleartext authentication
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, adminName);
        env.put(Context.SECURITY_CREDENTIALS, adminPassword);

        //connect to my domain controller
        env.put(Context.PROVIDER_URL, ldapURL);
        //specify attributes to be returned in binary format
        env.put("java.naming.ldap.attributes.binary", "tokenGroups");


        try {

            //Create the initial directory context
            LdapContext ctx = new InitialLdapContext(env, null);

            //Create the search controls
            SearchControls userSearchCtls = new SearchControls();

            //Specify the search scope
            userSearchCtls.setSearchScope(SearchControls.OBJECT_SCOPE);

            //specify the LDAP search filter to find the user in question
            String userSearchFilter = "(objectClass=user)";

            //paceholder for an LDAP filter that will store SIDs of the groups the user belongs to
            StringBuffer groupsSearchFilter = new StringBuffer();
            groupsSearchFilter.append("(|");

            //Specify the Base for the search
            String userSearchBase = "CN=Alex Tcherni,CN=Users,DC=adatum,DC=com";

            //Specify the attributes to return
            String userReturnedAtts[] = {"tokenGroups"};
            userSearchCtls.setReturningAttributes(userReturnedAtts);

            //Search for objects using the filter
            NamingEnumeration userAnswer = ctx.search(userSearchBase, userSearchFilter, userSearchCtls);

            //Loop through the search results
            while (userAnswer.hasMoreElements()) {

                SearchResult sr = (SearchResult) userAnswer.next();
                Attributes attrs = sr.getAttributes();

                if (attrs != null) {

                    try {
                        for (NamingEnumeration ae = attrs.getAll(); ae.hasMore(); ) {
                            Attribute attr = (Attribute) ae.next();
                            for (NamingEnumeration e = attr.getAll(); e.hasMore(); ) {

                                byte[] sid = (byte[]) e.next();
                                groupsSearchFilter.append("(objectSid=" + binarySidToStringSid(sid) + ")");

                            }
                            groupsSearchFilter.append(")");
                        }

                    } catch (NamingException e) {
                        System.err.println("Problem listing membership: " + e);
                    }
                }
            }


            // Search for groups the user belongs to in order to get their names
            //Create the search controls
            SearchControls groupsSearchCtls = new SearchControls();

            //Specify the search scope
            groupsSearchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);

            //Specify the Base for the search
            String groupsSearchBase = "DC=adatum,DC=com";

            //Specify the attributes to return
            String groupsReturnedAtts[] = {"sAMAccountName"};
            groupsSearchCtls.setReturningAttributes(groupsReturnedAtts);

            //Search for objects using the filter
            NamingEnumeration groupsAnswer = ctx.search(groupsSearchBase, groupsSearchFilter.toString(), groupsSearchCtls);

            //Loop through the search results
            while (groupsAnswer.hasMoreElements()) {

                SearchResult sr = (SearchResult) groupsAnswer.next();
                Attributes attrs = sr.getAttributes();

                if (attrs != null) {
                    System.out.println(attrs.get("sAMAccountName").get());
                }
            }

            ctx.close();

        } catch (NamingException e) {
            System.err.println("Problem searching directory: " + e);
        }
    }


    public static final String binarySidToStringSid(byte[] SID) {

        String strSID = "";


        //convert the SID into string format


        long version;
        long authority;
        long count;
        long rid;


        strSID = "S";
        version = SID[0];
        strSID = strSID + "-" + Long.toString(version);
        authority = SID[4];


        for (int i = 0; i < 4; i++) {
            authority <<= 8;
            authority += SID[4 + i] & 0xFF;
        }


        strSID = strSID + "-" + Long.toString(authority);
        count = SID[2];
        count <<= 8;
        count += SID[1] & 0xFF;


        for (int j = 0; j < count; j++) {


            rid = SID[11 + (j * 4)] & 0xFF;


            for (int k = 1; k < 4; k++) {


                rid <<= 8;


                rid += SID[11 - k + (j * 4)] & 0xFF;


            }


            strSID = strSID + "-" + Long.toString(rid);


        }


        return strSID;

    }

}
