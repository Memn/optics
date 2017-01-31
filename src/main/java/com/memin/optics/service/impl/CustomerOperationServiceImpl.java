package com.memin.optics.service.impl;

import com.memin.optics.service.CustomerOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerOperationServiceImpl implements CustomerOperationService {

    private final Logger log = LoggerFactory.getLogger(CustomerOperationServiceImpl.class);

}
