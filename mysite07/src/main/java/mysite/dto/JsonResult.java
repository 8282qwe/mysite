package mysite.dto;

public class JsonResult {
    // "success" or "fail"
    private String result;

    // if success, set Or null
    private Object data;

    // if fail, set Or null
    private String message;

    public static JsonResult success(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult fail(String message) {
        return new JsonResult(message);
    }

    private JsonResult(Object data) {
        this.result = "success";
        this.data = data;
    }

    private JsonResult(String message) {
        this.result = "fail";
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public Object getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
