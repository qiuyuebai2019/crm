package com.qust.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * .
 *
 * @author jiangxin (jiangxin@zhengheyingshi.com)
 * @since 2019年05月26日 14时12分
 */
public class Menu {

    private Long id;

    private String text;

    private String url;

    private Menu parent;

    private Permission permission;

    private List<Menu> children = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Menu getParent() {
        return parent;
    }

    public void setParent(Menu parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "Menu{" + "id=" + id + ", text='" + text + '\'' + ", url='" + url + '\'' + ", parent=" + parent + ", permission=" + permission + ", children=" + children + '}';
    }
}
