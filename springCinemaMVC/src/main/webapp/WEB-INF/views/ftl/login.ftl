<html>
    <head>
        <title>Login Page</title>
    </head>
    <body>
        <div id="login-box">

            <h2>Login with Username and Password</h2>

            <#if error??>
                <div>${error}</div>
            </#if>

            <#if msg??>
                <div>${msg}</div>
            </#if>

            <form name='loginForm' action="login" method='POST'>
                <table>
                    <tr>
                        <td>User:</td>
                        <td><input type='text' name='username' value=''></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type='password' name='password'/></td>
                    </tr>
                    <tr>
                        <td colspan='2'>
                            <input name="submit" type="submit" value="submit"/>
                        </td>
                    </tr>
                </table>
                <input type="hidden" name="${_csrf.parameterName}"
                       value="${_csrf.token}" />
            </form>
        </div>
    </body>
</html>