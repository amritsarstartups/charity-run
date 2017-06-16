<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<link href="ewcss.css" rel="stylesheet" type="text/css"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body style="background-color: gray">
    <center>
   
        <form class="c" method="post" action="login.jsp">
            
    
        
            <table border="0" width="50%" cellpadding="0">
                <thead>
                        <tr>
                        
                        <th colspan="0" style="width: 20%;height: 80px;background-color: black;font-size: 24px;color: whitesmoke">Login Here</th>
                    </tr>
                </thead>
                
                    <tr>
                           <td><input class="d" type="text" name="uname" value="" placeholder="Username" /></td>
                        
                    </tr>
                    <tr>
                            <td><input class="d" type="password" name="pass" value="" placeholder="Password"/></td>
                        
                    </tr>
                    <tr>
                        <td><input class="e" type="submit" value="Login" />
                        <input class="e" style="float: right" type="reset" value="Reset" /></td>
                    </tr>
<br>
<br>
                    <tr style="margin: 20px;">

                    </tr>
               
            </table>
    
           
        </form>
    
         </center>
    </body>
</html>