
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
 
public class main {
    public static void main(String[] args) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader("request.json"));
        String line;
        while ((line = fileReader.readLine()) != null)
        {
            var str = line.split(" ");
            if (str.length != 2) {
                System.out.println("invalid request: require 2 params");
                continue;
            }
            JSONObject jo;
            try {
                jo = new JSONObject(str[1]);
            }
            catch (JSONException e) {
                System.out.println("invalid request: second param is not json");
                continue;
            }


            System.out.println("response: " + request(str[0], jo).toString());
            System.out.println("data size: " + data.size());
        }
        fileReader.close();
    }

    private static Map<String, Integer> data = new HashMap<String, Integer>();

    public static JSONObject request(String line) {
        var str = line.split(" ");
        if (str.length != 2) {
            return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: require 2 params\" }");
        }
        try {
            return request(str[0], new JSONObject(str[1]));
        }
        catch (JSONException e) {
            return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: second param is not json\" }");
        }
    }

    private static JSONObject request(String type, JSONObject req){
        try {
            switch (type) {
                case "add":
                    int value;
                    var str_value = req.get("value").toString();
                    try {
                        return add(req.get("name").toString(), Integer.parseInt(str_value));
                    } catch (NumberFormatException e) {
                        return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: value is nan\" }");
                    }
                case "remove":
                    return remove(req.get("name").toString());
                case "get":
                    return get(req.get("name").toString());
                case "sum":
                    return sum(req.get("first").toString(), req.get("second").toString());
                default:
                    return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: type request\" }");
            }
        }
        catch (JSONException e) {
            return new JSONObject("{ \"code\" : 1, \"description\": \"invalid request: json content\" }");
        }
    }

    private static JSONObject add(String name, int value) {
        if (data.containsKey(name))
            return new JSONObject("{ \"code\" : 1, \"description\": \"name exists\" }");

        data.put(name, value);
        return new JSONObject("{ \"code\":0,\"description\":\"OK\"}");
    }

    private static JSONObject get(String name) {
        if (!data.containsKey(name))
            return new JSONObject("{ \"code\" : 1, \"description\": \"name not exist\" }");

        var value = data.get(name);
        return new JSONObject(String.format("{ \"value\" : %s,\"code\" : 0, \"description\": \"OK\" }", value));
    }

    private static JSONObject remove(String name) {
        data.remove(name);
        return new JSONObject("{ \"code\" : 0, \"description\": \"OK\" }");
    }

    private static JSONObject sum(String name1, String name2) {
        if (! data.containsKey(name1) || ! data.containsKey(name2))
            return new JSONObject("{\"code\":1,\"description\":\"name not exist\" }");

        var sum = data.get(name1) + data.get(name2);
        return new JSONObject(String.format("{ \"sum\" : %s,\"code\" : 0, \"description\": \"OK\" }", sum));
    }

}

