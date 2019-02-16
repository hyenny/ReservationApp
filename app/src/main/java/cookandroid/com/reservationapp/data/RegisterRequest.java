package cookandroid.com.reservationapp.data;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LG on 2018-05-18.
 */

//회원가입 요청

public class RegisterRequest extends StringRequest {

    final static  private String URL = "http://simssbook9.cafe24.com/Register.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userName, String userTel, String userBirth, String userGender, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID",userID);
        parameters.put("userPassword",userPassword);
        parameters.put("userName",userName);
        parameters.put("userTel",userTel);
        parameters.put("userBirth",userBirth);
        parameters.put("userGender",userGender);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
