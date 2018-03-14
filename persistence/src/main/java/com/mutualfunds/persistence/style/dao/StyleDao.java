package com.mutualfunds.persistence.style.dao;

import java.util.stream.Stream;

import com.mutualfunds.persistence.style.domains.Style;

public interface StyleDao {

    Stream<Style> getInvestmentStylesByName(String styleType);
}
