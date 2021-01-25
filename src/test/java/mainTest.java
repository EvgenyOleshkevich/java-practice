import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class mainTest {

    @Test
    void request_addition() {
        // addition name
        String str = main.request("add {\"name\":\"a\",\"value\":7}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"a\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":7}", str);

        // addition name
        str = main.request("add {\"name\":\"b\",\"value\":8}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}").toString(); // extra parameters do not interfere
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":8}", str);

        // twice addition one name
        str = main.request("add {\"name\":\"a\",\"value\":1}").toString();
        assertEquals("{\"code\":1,\"description\":\"name exists\"}", str);
        str = main.request("get {\"name\":\"a\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":7}", str);

        // json content is invalid
        str = main.request("add {\"name\":\"a\",\"invalid\":1}").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: json content\"}", str);

        // json content is invalid
        str = main.request("add {\"invalid\":\"a\",\"value\":1}").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: json content\"}", str);

        // json content is invalid
        str = main.request("add {\"invalid1\":\"a\",\"invalid2\":1}").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: json content\"}", str);

        // name is a number, its ok
        str = main.request("add {\"name\":10,\"value\":10}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);

        // value is not a number
        str = main.request("add {\"name\":10,\"value\":\"a\"}").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: value is nan\"}", str);
    }

    @Test
    void invalid_form_request() {
        String str = main.request("text").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: require 2 params\"}", str);

        // space in json is wrong
        str = main.request("add {\"name\":\"a\" , \"value\":7").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: require 2 params\"}", str);

        // second is not json
        str = main.request("add any_text").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: second param is not json\"}", str);

        // invalid type request
        str = main.request("any_text {\"name\":\"a\",\"value\":7}").toString();
        assertEquals("{\"code\":1,\"description\":\"invalid request: type request\"}", str);
    }

    @Test
    void getting() {
        // addition name
        String str = main.request("add {\"name\":\"a\",\"value\":7}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"a\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":7}", str);

        // addition name
        str = main.request("add {\"name\":\"b\",\"value\":8}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}").toString(); // extra parameters do not interfere
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":8}", str);

        // name is not exist
        str = main.request("get {\"name\":\"c\"}").toString();
        assertEquals("{\"code\":1,\"description\":\"name not exist\"}", str);
    }

    @Test
    void removing() {
        // addition name
        String str = main.request("add {\"name\":\"a\",\"value\":7}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"a\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":7}", str);

        // addition name
        str = main.request("add {\"name\":\"b\",\"value\":8}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}").toString(); // extra parameters do not interfere
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":8}", str);

        // removing
        str = main.request("remove {\"name\":\"a\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);

        // name is not exist
        str = main.request("get {\"name\":\"a\"}").toString();
        assertEquals("{\"code\":1,\"description\":\"name not exist\"}", str);

        // removing
        str = main.request("remove {\"name\":\"b\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);

        // name is not exist
        str = main.request("get {\"name\":\"b\"}").toString();
        assertEquals("{\"code\":1,\"description\":\"name not exist\"}", str);

        // twice removing
        str = main.request("remove {\"name\":\"b\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);

        // removing not exist ever
        str = main.request("remove {\"name\":\"any_name_which_dont_use_before\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
    }

    @Test
    void sum() {
        // addition name
        String str = main.request("add {\"name\":\"a\",\"value\":7}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"a\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":7}", str);

        // addition name
        str = main.request("add {\"name\":\"b\",\"value\":8}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\"}", str);
        str = main.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}").toString(); // extra parameters do not interfere
        assertEquals("{\"code\":0,\"description\":\"OK\",\"value\":8}", str);

        str = main.request("sum {\"first\":\"a\",\"second\":\"b\"}").toString();
        assertEquals("{\"code\":0,\"description\":\"OK\",\"sum\":15}", str);

        str = main.request("sum {\"first\":\"c\",\"second\":\"b\"}").toString();
        assertEquals("{\"code\":1,\"description\":\"name not exist\"}", str);

        str = main.request("sum {\"first\":\"c\",\"second\":\"d\"}").toString();
        assertEquals("{\"code\":1,\"description\":\"name not exist\"}", str);
    }
}