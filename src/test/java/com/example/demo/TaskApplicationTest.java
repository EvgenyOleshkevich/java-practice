package com.example.demo;

class TaskApplicationTest {
    /*@Test
    void requestAddition() {
        // addition name
        JSONObject obj = TaskApplication.request("add {\"name\":\"a\",\"value\":7}");
        assertEquals(0, obj.get("code"));
        obj = TaskApplication.request("get {\"name\":\"a\"}");
        assertEquals(0, obj.get("code"));

        // addition name
        obj = TaskApplication.request("add {\"name\":\"a\",\"value\":7}");
        assertEquals(4, obj.get("code"));
        obj = TaskApplication.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}"); // extra parameters do not interfere
        assertEquals(5, obj.get("code"));

        // twice addition one name
        obj = TaskApplication.request("add {\"name\":\"a\",\"value\":1}");
        assertEquals(4, obj.get("code"));
        obj = TaskApplication.request("get {\"name\":\"a\"}");
        assertEquals(0, obj.get("code"));

        // json content is invalid
        obj = TaskApplication.request("add {\"name\":\"a\",\"invalid\":1}");
        assertEquals(CodeResponse.JSONFormatError.ordinal(), obj.get("code"));

        // json content is invalid
        obj = TaskApplication.request("add {\"invalid\":\"a\",\"value\":1}");
        assertEquals(CodeResponse.JSONFormatError.ordinal(), obj.get("code"));

        // json content is invalid
        obj = TaskApplication.request("add {\"invalid1\":\"a\",\"invalid2\":1}");
        assertEquals(CodeResponse.JSONFormatError.ordinal(), obj.get("code"));

        // name is a number, its ok
        obj = TaskApplication.request("add {\"name\":10,\"value\":10}");
        assertEquals(CodeResponse.Ok.ordinal(), obj.get("code"));

        // value is not a number
        obj = TaskApplication.request("add {\"name\":10,\"value\":\"a\"}");
        assertEquals(CodeResponse.JSONFormatError.ordinal(), obj.get("code"));

        TaskApplication.clearData();
    }

    @Test
    void invalidFormRequest() {
        JSONObject obj = TaskApplication.request("text");
        assertEquals(obj.get("code"), 1);

        // space in json is wrong
        obj = TaskApplication.request("add {\"name\":\"a\" , \"value\":7");
        assertEquals(obj.get("code"), 1);

        // second is not json
        obj = TaskApplication.request("add any_text");
        assertEquals(CodeResponse.InvalidRequest.ordinal(), obj.get("code"));

        // invalid type request
        obj = TaskApplication.request("any_text {\"name\":\"a\",\"value\":7}");
        assertEquals(obj.get("code"), 2);

        TaskApplication.clearData();
    }

    @Test
    void getting() {
        // addition name
        JSONObject obj = TaskApplication.request("add {\"name\":\"a\",\"value\":7}");
        assertEquals(obj.get("code"), 0);
        obj = TaskApplication.request("get {\"name\":\"a\"}");
        assertEquals(obj.get("code"), 0);

        // addition name
        obj = TaskApplication.request("add {\"name\":\"b\",\"value\":8}");
        assertEquals(obj.get("code"), 0);
        obj = TaskApplication.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}"); // extra parameters do not interfere
        assertEquals(obj.get("code"), 0);

        // name is not exist
        obj = TaskApplication.request("get {\"name\":\"c\"}");
        assertEquals(obj.get("code"), 5);

        TaskApplication.clearData();
    }

    @Test
    void removing() {
        // addition name
        JSONObject obj = TaskApplication.request("add {\"name\":\"a\",\"value\":7}");
        assertEquals(obj.get("code"), 0);
        obj = TaskApplication.request("get {\"name\":\"a\"}");
        assertEquals(obj.get("code"), 0);

        // addition name
        obj = TaskApplication.request("add {\"name\":\"b\",\"value\":8}");
        assertEquals(obj.get("code"), 0);
        obj = TaskApplication.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}"); // extra parameters do not interfere
        assertEquals(obj.get("code"), 0);

        // removing
        obj = TaskApplication.request("remove {\"name\":\"a\"}");
        assertEquals(obj.get("code"), 0);

        // name is not exist
        obj = TaskApplication.request("get {\"name\":\"a\"}");
        assertEquals(obj.get("code"), 5);

        // removing
        obj = TaskApplication.request("remove {\"name\":\"b\"}");
        assertEquals(obj.get("code"), 0);

        // name is not exist
        obj = TaskApplication.request("get {\"name\":\"b\"}");
        assertEquals(obj.get("code"), 5);

        // twice removing
        obj = TaskApplication.request("remove {\"name\":\"b\"}");
        assertEquals(obj.get("code"), 0);

        // removing not exist ever
        obj = TaskApplication.request("remove {\"name\":\"any_name_which_dont_use_before\"}");
        assertEquals(obj.get("code"), 0);

        TaskApplication.clearData();
    }

    @Test
    void summation() {
        // addition name
        JSONObject obj = TaskApplication.request("add {\"name\":\"a\",\"value\":7}");
        assertEquals(obj.get("code"), 0);
        obj = TaskApplication.request("get {\"name\":\"a\"}");
        assertEquals(obj.get("code"), 0);

        // addition name
        obj = TaskApplication.request("add {\"name\":\"b\",\"value\":8}");
        assertEquals(obj.get("code"), 0);
        obj = TaskApplication.request("get {\"name\":\"b\",\"value\":7,\"ggg\":7}"); // extra parameters do not interfere
        assertEquals(obj.get("code"), 0);

        obj = TaskApplication.request("sum {\"first\":\"a\",\"second\":\"b\"}");
        assertEquals(obj.get("code"), 0);

        obj = TaskApplication.request("sum {\"first\":\"c\",\"second\":\"b\"}");
        assertEquals(obj.get("code"), 5);

        obj = TaskApplication.request("sum {\"first\":\"c\",\"second\":\"d\"}");
        assertEquals(obj.get("code"), 5);

        TaskApplication.clearData();
    }
*/
}