package com.lzh.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

//消息事物状态记录
@Entity(name = "shop_txlog")
@Data
public class TxLog {
    @Id
    private String txId;
    //private Date date;
}
