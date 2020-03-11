package com.qust.domain;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月27日 18时25分
 */
public class AjaxRes {

    private Boolean success;

    private String mes;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return "AjaxRes{" + "success=" + success + ", mes='" + mes + '\'' + '}';
    }
}
