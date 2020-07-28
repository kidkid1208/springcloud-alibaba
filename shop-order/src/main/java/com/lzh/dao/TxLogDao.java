package com.lzh.dao;

import com.lzh.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxLogDao extends JpaRepository<TxLog,String> {
}