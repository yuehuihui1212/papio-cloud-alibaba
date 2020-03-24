package com.papio.contentcenter.domain.entity.content;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "share")
public class Share {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    private String title;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_original")
    private Boolean isOriginal;

    private String author;

    private String cover;

    private String summary;

    private Integer price;

    @Column(name = "download_url")
    private String downloadUrl;

    @Column(name = "buy_count")
    private Integer buyCount;

    @Column(name = "show_flag")
    private Boolean showFlag;

    @Column(name = "audit__status")
    private String auditStatus;

    private String reason;
}