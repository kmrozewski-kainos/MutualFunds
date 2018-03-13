package com.mutualfunds.fund;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfunds.persistence.fund.FundDao;
import com.mutualfunds.persistence.fund.domain.Fund;

@Service
public class FundServiceImpl implements FundService {

    @Autowired
    private FundDao fundDao;

    @Override
    public List<Fund> getAllFunds() {
        return fundDao.getAllFunds();
    }
}
