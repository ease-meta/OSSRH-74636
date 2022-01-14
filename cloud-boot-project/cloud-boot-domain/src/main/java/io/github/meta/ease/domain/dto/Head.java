package io.github.meta.ease.domain.dto;

/**
 * @author leijian
 * @version 1.0
 * @date 2021/11/7 12:10
 */
public class Head {

    /**
     * 分页信息
     */
    private Integer pageIndex = 1;

    private Integer pageSize = 10;

    /**
     * 租户信息
     */
    private String appId;

    private String tenantId;

    private String user;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
