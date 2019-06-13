package com.example.ipd.yueyue.bean;

public class RegisterBean {
    /**
     * code : 200
     * msg : 用户注册成功
     * data : {"user_token":"28BCBEDE32413F14CCE025D2C9653DBE","user_id":74,"name":"18502994087","phone":"18502994087","avatar":"/images/default_head.png","agency":null,"position":null}
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
         * user_token : 28BCBEDE32413F14CCE025D2C9653DBE
         * user_id : 74
         * name : 18502994087
         * phone : 18502994087
         * avatar : /images/default_head.png
         * agency : null
         * position : null
         */

        private String user_token;
        private int user_id;
        private String name;
        private String phone;
        private String avatar;
        private Object agency;
        private Object position;

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getAgency() {
            return agency;
        }

        public void setAgency(Object agency) {
            this.agency = agency;
        }

        public Object getPosition() {
            return position;
        }

        public void setPosition(Object position) {
            this.position = position;
        }
    }


//    /**
//     * user_token : 28BCBEDE32413F14CCE025D2C9653DBE
//     * user_id : 74
//     * name : 18502994087
//     * phone : 18502994087
//     * avatar : /images/default_head.png
//     * agency : null
//     * position : null
//     */
//
//    private String user_token;
//    private int user_id;
//    private String name;
//    private String phone;
//    private String avatar;
//    private Object agency;
//    private Object position;
//
//    public String getUser_token() {
//        return user_token;
//    }
//
//    public void setUser_token(String user_token) {
//        this.user_token = user_token;
//    }
//
//    public int getUser_id() {
//        return user_id;
//    }
//
//    public void setUser_id(int user_id) {
//        this.user_id = user_id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }
//
//    public Object getAgency() {
//        return agency;
//    }
//
//    public void setAgency(Object agency) {
//        this.agency = agency;
//    }
//
//    public Object getPosition() {
//        return position;
//    }
//
//    public void setPosition(Object position) {
//        this.position = position;
//    }
}
