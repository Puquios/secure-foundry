package com.ibm.ids.example;
// Copyright 2015 IBM
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and

import java.io.*;
import java.util.*;
import java.util.regex.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowResult
 */
public class ShowResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private WordCountFinder wordCountFinder;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowResult() {
        super();
        wordCountFinder = new WordCountFinder();
    }


    //STATIC SCAN 
    //The following code prints input from the user to the page.
    //This allows for the user to enter script content that will be processesed 
    //For example ?name=<img src=x onerror=alert("ha") />
    //To remove this vunerability use the doGet method below
/*
    public void doGet( HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        String name = request.getParameter( "name" );
        response.setContentType( "text/html" );
        PrintWriter out = response.getWriter();

        // log what we received in a vulnerable way
        try{ 
            writeToVulnerableSink(getVulnerableSource(name));
        }catch (Exception e){
            // ignore this, we're just logging, right?
        }

        out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>" );
        out.println( "Hello, " + name );
        out.println( "</BODY></HTML>" );
    }

*/
    //STATIC SCAN 
    // this version of doGet filters out bad input 
    private Pattern namePattern = Pattern.compile("^[a-zA-Z]{3,10}$");
    public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
            String name = request.getParameter("name");
            Locale locale = request.getLocale();
            ResourceBundle messages = ResourceBundle.getBundle("com.ibm.ids.example.Messages", locale);
            String error = messages.getString("error");

            PrintWriter out = response.getWriter();

            int wordCount = wordCountFinder.countWords(name);

            if ( name == null ){
                String nobody = messages.getString("nobody");
                out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>"+nobody+"</BODY></HTML>");
            }else if ( !namePattern.matcher( name ).matches() )
            {
                out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>" + error + " </BODY></HTML>");
            }else{ 
                response.setContentType( "text/html" );
                String hello = messages.getString("hello");
                String question = messages.getString("question");
                String escapedName = name.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;");
                if (wordCount >= 2){
                    out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>"+hello+", " + escapedName + "</BODY></HTML>");
                }else { 
                    out.println( "<HTML><HEAD><TITLE>Hello World</TITLE></HEAD><BODY>"+hello+", " + escapedName + " " + question + "</BODY></HTML>");
                }
            }  
    }

    public static String getVulnerableSource(String file)
        throws java.io.IOException, java.io.FileNotFoundException {
        FileInputStream fis = new FileInputStream(file);
        byte[] buf = new byte[100];
        fis.read(buf);
        String ret = new String(buf);
        fis.close();
        return ret;
    }

    public static void writeToVulnerableSink(String str)
        throws java.io.FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(str);
        PrintWriter writer = new PrintWriter(fos);
        //STATIC SCAN 
        //to remove this vunerability issue use writer.append rather than writer.write 
        //writer.write(str); 
        writer.append(str);
    }
}
