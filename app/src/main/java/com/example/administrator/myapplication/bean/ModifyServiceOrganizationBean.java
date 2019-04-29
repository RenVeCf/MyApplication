package com.example.administrator.myapplication.bean;

public class ModifyServiceOrganizationBean {

    /**
     * code : 200
     * msg : 修改成功
     * data : {"id":74,"phone":"18502994087","avatar":"/images/default_head.png","password":"yf62043098c8013e08d0fc997a8cb284","is_del":0,"user_token":"91D15B627B6FB28676F5CD7E4C7E9982","last_login_time":"1555383950","update_time":"1555386629","ctime":"1555308291","name":"18502994087","locking":0,"agency":"服务机构","position":"职务"}
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
         * id : 74
         * phone : 18502994087
         * avatar : /images/default_head.png
         * password : yf62043098c8013e08d0fc997a8cb284
         * is_del : 0
         * user_token : 91D15B627B6FB28676F5CD7E4C7E9982
         * last_login_time : 1555383950
         * update_time : 1555386629
         * ctime : 1555308291
         * name : 18502994087
         * locking : 0
         * agency : 服务机构
         * position : 职务
         */

        private int id;
        private String phone;
        private String avatar;
        private String password;
        private int is_del;
        private String user_token;
        private String last_login_time;
        private String update_time;
        private String ctime;
        private String name;
        private int locking;
        private String agency;
        private String position;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getIs_del() {
            return is_del;
        }

        public void setIs_del(int is_del) {
            this.is_del = is_del;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLocking() {
            return locking;
        }

        public void setLocking(int locking) {
            this.locking = locking;
        }

        public String getAgency() {
            return agency;
        }

        public void setAgency(String agency) {
            this.agency = agency;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }
    }
}
