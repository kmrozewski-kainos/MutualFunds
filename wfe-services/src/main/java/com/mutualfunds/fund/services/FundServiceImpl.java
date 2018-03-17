package com.mutualfunds.fund.services;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mutualfunds.persistence.fund.dao.FundDao;
import com.mutualfunds.persistence.fund.domains.Fund;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FundServiceImpl implements FundService {

    private final @NonNull FundDao fundDao;

    @Override
    public List<Fund> getFunds() {
        return fundDao.getAllFunds();
    }
}
