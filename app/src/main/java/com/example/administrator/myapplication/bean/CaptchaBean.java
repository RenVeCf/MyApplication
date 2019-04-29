package com.example.administrator.myapplication.bean;

public class CaptchaBean {
    /**
     * code : 200
     * msg : 登录成功
     * data : {"Message":"OK","Code":"OK","RequestId":null,"phone":null}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * Message : OK
         * Code : OK
         * RequestId : null
         * phone : null
         */

        private String Message;
        private String Code;
        private Object RequestId;
        private Object phone;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public Object getRequestId() {
            return RequestId;
        }

        public void setRequestId(Object RequestId) {
            this.RequestId = RequestId;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }
    }


//    /**
//     * Message : OK
//     * Code : OK
//     * RequestId :
//     * BizId :
//     */
//
//    private String Message;
//    private String Code;
//    private String RequestId;
//    private String BizId;
//
//    public String getMessage() {
//        return Message;
//    }
//
//    public void setMessage(String Message) {
//        this.Message = Message;
//    }
//
//    public String getCode() {
//        return Code;
//    }
//
//    public void setCode(String Code) {
//        this.Code = Code;
//    }
//
//    public String getRequestId() {
//        return RequestId;
//    }
//
//    public void setRequestId(String RequestId) {
//        this.RequestId = RequestId;
//    }
//
//    public String getBizId() {
//        return BizId;
//    }
//
//    public void setBizId(String BizId) {
//        this.BizId = BizId;
//    }
}
