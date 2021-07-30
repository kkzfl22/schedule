package com.ddd.common.infrastructure.entity;

/**
 * 领域的分页对象
 *
 * @param <T> 对象信息
 * @author liujun
 */
public class DomainPage<T> {

    /**
     * 查询的请求
     */
    private T data;

    /**
     * 当前页
     */
    private Integer page;

    /**
     * 每页显示的大小
     */
    private Integer size;

    /**
     * 决条数
     */
    private Long total;

    private DomainPage(Builder<T> param) {
        this.data = param.data;
        this.page = param.page;
        this.size = param.size;
        this.total = param.total;
    }

    /**
     * 用来进行作为参数的build类
     *
     * @author liujun
     * @date 2014年12月16日
     * @vsersion 0.0.1
     */
    public static class Builder<T> {

        /**
         * 当前页
         */
        private Integer page;

        /**
         * 每页显示的大小
         */
        private Integer size;

        /**
         * 决条数
         */
        private Long total;

        /**
         * 通用的查询结果集
         */
        private T data;

        public Builder<T> page(Integer page) {
            this.page = page;
            return this;
        }

        public Builder<T> size(Integer size) {
            this.size = size;
            return this;
        }

        public Builder<T> total(Long total) {
            this.total = total;
            return this;
        }

        public Builder<T> data(T queryData) {
            this.data = queryData;
            return this;
        }

        public DomainPage<T> build() {
            return new DomainPage<>(this);
        }
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public T getData() {
        return data;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public Long getTotal() {
        return total;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DomainPage{");
        sb.append("data=").append(data);
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}

